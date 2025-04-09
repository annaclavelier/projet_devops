package bababooeyz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class DataframeTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void dataFrameConstructionWithCsvFile(){
        File f = new File("data/fruits.csv");
        Dataframe dataframe = new Dataframe(f.getAbsolutePath());

        
        Column<String> col = new Column<>("nom", String.class);
        col.add("fraise");
        col.add("abricot");
        col.add("pomme");
        col.setValues(new ArrayList<>(Arrays.asList("fraise", "abricot", "pomme")));

        Column<Double> col2 = new Column<>("prix", Double.class);
        col2.setValues(new ArrayList<>(Arrays.asList(2.80,1.50,2.0)));

        Column<Integer> col3 = new Column<>("stock", Integer.class);
        col3.setValues(new ArrayList<>(Arrays.asList(150,3000,420)));

        List <Column<?>> columnsExpected = new ArrayList<>(Arrays.asList(col, col2, col3));
        
        List<Column<?>> columnsActual = dataframe.getData();

        // should be equal columns size
        assertEquals(columnsExpected.size(), columnsActual.size());
        // checking columns are the same
        for(int i =0; i< columnsActual.size(); i++){
            assertEquals( columnsExpected.get(i).getName(), columnsActual.get(i).getName());
            assertEquals(columnsExpected.get(i).getValues(), columnsActual.get(i).getValues());
            assertEquals(columnsExpected.get(i).getType(), columnsActual.get(i).getType());
        }

        
    }
}
