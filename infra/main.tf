provider "google" {
  project = "projet-devops-457006"
  region  = "europe-west1"
  zone    = "europe-west1-b"
}

# Création d'une VM avec Apache installé
resource "google_compute_instance" "vm_instance" {
  name         = "devops-vm"
  machine_type = "e2-micro"
  zone         = "europe-west1-b"

  boot_disk {
    initialize_params {
      image = "debian-cloud/debian-11"
    }
  }

  network_interface {
    network = "default"
    access_config {} # Nécessaire pour obtenir une IP publique
  }

  metadata_startup_script = <<-EOT
    #!/bin/bash
    apt update
    apt install -y apache2
    systemctl start apache2
  EOT

  # Tag réseau pour appliquer la règle firewall
  tags = ["http-server"]
}

# Règle de pare-feu pour autoriser le trafic HTTP
resource "google_compute_firewall" "default-allow-http" {
  name    = "default-allow-http"
  network = "default"

  allow {
    protocol = "tcp"
    ports    = ["80"]
  }

  direction     = "INGRESS"
  priority      = 1000
  source_ranges = ["0.0.0.0/0"]
  target_tags   = ["http-server"]
}

# Affichage automatique de l'IP publique
output "vm_ip" {
  value       = google_compute_instance.vm_instance.network_interface[0].access_config[0].nat_ip
  description = "Adresse IP publique de la VM"
}
