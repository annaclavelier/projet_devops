package bababooeyz;

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
        for (int i = 0; i < data.size(); i++) {
            this.data.put(i, data.get(i));
        }
    }
}
