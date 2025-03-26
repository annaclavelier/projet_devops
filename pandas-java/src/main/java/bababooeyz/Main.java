package bababooeyz;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Création de colonnes
        Column<String> col1 = new Column<>("Nom", String.class);
        col1.add("Alice");
        col1.add("Bob");

        Column<Integer> col2 = new Column<>("Âge", Integer.class);
        col2.add(30);
        col2.add(25);

        // Création du dataframe
        Dataframe df = new Dataframe(Arrays.asList(col1, col2));

        // Affichage
        df.ShowDataFrame(df);
    }
}
