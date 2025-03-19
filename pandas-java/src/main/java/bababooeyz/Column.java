package bababooeyz;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a column in a table-like structure, storing values of a specific type.
 * 
 * @param <T> The type of the values stored in the column.
 */
public class Column<T> {
    private String name; // The name of the column.
    private Class<T> type; // The type of the values stored in the column.
    private List<T> values; // The list of values stored in the column.

    /**
     * Constructs a new Column with the specified name and type.
     * 
     * @param name The name of the column.
     * @param type The class type of the values stored in the column.
     */
    public Column(String name, Class<T> type){
        this.name = name;
        this.type = type;
        values = new ArrayList<>();
    }

    /**
     * Adds a value to the column. Ensures the value is of the correct type.
     * 
     * @param value The value to add to the column.
     * @throws IllegalArgumentException If the value is not of the correct type.
     */
    public void add(T value){
        if(value != null && !type.isInstance(value)){
            throw new IllegalArgumentException("Incorrect type for column "+ name);
        }
        values.add(value);
    }

    /**
     * Retrieves the value at the specified index in the column.
     * 
     * @param index The index of the value to retrieve.
     * @return The value at the specified index.
     */
    public T getValue(int index){
        return values.get(index);
    }
}
