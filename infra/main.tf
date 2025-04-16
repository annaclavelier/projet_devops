provider "google" {
  project = "projet-devops-457006"
  region  = "europe-west1"
  zone    = "europe-west1-b"
}

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
    access_config {}  # ← Pour avoir une IP publique
  }

  metadata_startup_script = <<-EOT
    #!/bin/bash
    apt update
    apt install -y apache2
    systemctl start apache2
  EOT
}
