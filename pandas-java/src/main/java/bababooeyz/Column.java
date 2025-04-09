package bababooeyz;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a column in a table-like structure, storing values of a specific type.
 * 
 * @param <T> The type of the values stored in the column.
 */
public class Column<T> {
    private String name;
    private Class<T> type; 
    private List<T> values; 

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

    public T getValue(int index){
        return values.get(index);
    }

    /**
     * Retrieves the values stored in the column.
     * 
     * @return A list of values in the column.
     */
    public List<T> getValues(){
        return values;
    }

    /**
     * Sets the values of the column.
     * 
     * @param values A list of values to set in the column.
     */
    public void setValues(List<T> values){
        this.values = values;
    }

    /**
     * Retrieves the type of values stored in the column.
     * 
     * @return The class type of the column.
     */
    public Class<T> getType(){
        return type;
    }

    /**
     * Retrieves the name of the column.
     * 
     * @return Column name.
     */
    public String getName(){
        return name;
    }

    /**
     * Returns the maximum value of the column if its type is comparable.
     *
     *
     * @return The maximum value from the list of values.
     * @throws IllegalArgumentException if the type is not Comparable or if the list is empty.
     */
    @SuppressWarnings("unchecked")
    public T getMaxValue() throws IllegalArgumentException{
        // Verify that the column type is comparable
        if (Comparable.class.isAssignableFrom(type)) {
            if (values.isEmpty()) {
                throw new IllegalArgumentException("Column " + name +" is empty.");
            }
            // Initialize the max value with the first element
            T max = values.get(0);
            // Find max
            for (T value : values) {
                if (((Comparable<T>) value).compareTo(max) > 0) {
                    max = value;
                }
            }
            return max;
        }
        else {
            throw new IllegalArgumentException("Column type " + type.getSimpleName() + " is not Comparable.");
        }
    }

    /**
     * Returns the minimum value of the column if its type is comparable.
     *
     *
     * @return The minumum value from the list of values.
     * @throws IllegalArgumentException if the type is not Comparable or if the list is empty.
     */
    @SuppressWarnings("unchecked")
    public T getMinValue() throws IllegalArgumentException{
        // Verify that the column type is comparable
        if (Comparable.class.isAssignableFrom(type)) {
            if (values.isEmpty()) {
                throw new IllegalArgumentException("Column " + name +" is empty.");
            }
            // Initialize the min value with the first element
            T min = values.get(0);
            // Find min
            for (T value : values) {
                if (((Comparable<T>) value).compareTo(min) < 0) {
                    min = value;
                }
            }
            return min;
        }
        else {
            throw new IllegalArgumentException("Column type " + type.getSimpleName() + " is not Comparable.");
        }
    }
}
