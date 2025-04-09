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

        List <Column<?>> columnsExpected = new ArrayList<>(Arrays.asList(new Column<>("nom", String.class), new Column<>(null, null)));
        
        List<Column<?>> columns = dataframe.getData();

        dataframe.printData();

        // should be equal columns size
        assertEquals(columnNames.size(), columns.size());
        // should be same columns name
        for(int i =0; i< columns.size(); i++){
            assertEquals( columnNames.get(i), columns.get(i).getName());
        }

        

    }
}
