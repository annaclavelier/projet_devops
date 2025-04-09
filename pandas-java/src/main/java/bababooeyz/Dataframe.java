package bababooeyz;

import java.util.List;

/**
 * The Dataframe class represents the Dataframe type used in the pandas library.
 * It is a two-dimensional, with axes labeled rows and columns.
 */
public class Dataframe {
    private List<Column<?>> data;

    /**
     * Constructs a Dataframe with the given list of columns.
     * Each column is assigned an index in the map.
     *
     * @param data A list of columns to be stored in the Dataframe.
     */
    public Dataframe(List<Column<?>> data) {
        this.data = data;
    }

    // Affiche tout le Dataframe
    public void showDataFrame() {
        if (data.isEmpty()) return;
        int rowCount = data.get(0).getValues().size();
        printRows(0, rowCount);
    }

    // Affiche les premières N lignes
    public void showHead(int n) {
        if (data.isEmpty()) return;
        int rowCount = data.get(0).getValues().size();
        int end = Math.min(n, rowCount);
        printRows(0, end);
    }

    // Affiche les dernières N lignes
    public void showTail(int n) {
        if (data.isEmpty()) return;
        int rowCount = data.get(0).getValues().size();
        int start = Math.max(0, rowCount - n);
        printRows(start, rowCount);
    }

    private void printRows(int start, int end) {
        // Afficher les noms de colonnes
        for (Column<?> column : data) {
            System.out.print(column.getName() + " ");
        }
        System.out.println();

        // Afficher les lignes entre start (inclus) et end (exclus)
        for (int i = start; i < end; i++) {
            for (Column<?> column : data) {
                System.out.print(column.getValue(i) + " ");
            }
            System.out.println();
        }
    }


    public List<Column<?>> getData() {
        return data;
    }

    public void setData(List<Column<?>> data) {
        this.data = data;
    }
}
