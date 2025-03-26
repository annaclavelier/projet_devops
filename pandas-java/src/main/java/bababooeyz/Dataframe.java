package bababooeyz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Dataframe class represents the Dataframe type used in the pandas library.
 * It is a two-dimensional, with axes labeled rows and columns.
 */
public class Dataframe {
    private Map<Integer, Column<?>> data;

    /**
     * Constructs a Dataframe with the given list of columns.
     * Each column is assigned an index in the map.
     *
     * @param data A list of columns to be stored in the Dataframe.
     */
    public Dataframe(List<Column<?>> data) {
        this.data = new HashMap<>();
        for (int i = 0; i < data.size(); i++) {
            this.data.put(i, data.get(i));
        }
    }

    public void ShowDataFrame(Dataframe df) {
        // afficher les colonnes
        for (int i = 0; i < df.data.size(); i++) {
            System.out.print(df.data.get(i).getName() + " ");
        }
        System.out.println();
        // afficher les valeurs
        for (int i = 0; i < df.data.get(0).getValues().size(); i++) {
            for (int j = 0; j < df.data.size(); j++) {
                System.out.print(df.data.get(j).getValues().get(i) + " ");
            }
            System.out.println();
        }
    }

    public Map<Integer, Column<?>> getData() {
        return data;
    }

    public void setData(Map<Integer, Column<?>> data) {
        this.data = data;
    }
}
