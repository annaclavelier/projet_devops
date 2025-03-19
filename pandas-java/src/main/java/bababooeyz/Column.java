package bababooeyz;

import java.util.ArrayList;
import java.util.List;

public class Column<T> {
    private String name;
    private Class<T> type;
    private List<T> values;

    public Column(String name, Class<T> type){
        this.name = name;
        this.type = type;
        values = new ArrayList<>();
    }

    public void add(T value){
        if(value != null && !type.isInstance(value)){
            throw new IllegalArgumentException("Incorrect type for column "+ name);
        }
        values.add(value);
    }

    public T getValue(int index){
        return values.get(index);
    }
}
