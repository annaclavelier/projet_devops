package bababooeyz;

import java.util.*;

public class Dataframe {
    private Map <Integer, Column<?>> data;

    public Dataframe(List<Column<?>> data){
        for(int i = 0; i<data.size(); i++)
            this.data.put(i, data.get(i));
    }
}
