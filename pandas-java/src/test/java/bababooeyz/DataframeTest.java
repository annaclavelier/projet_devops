package bababooeyz;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import nl.altindag.console.ConsoleCaptor;


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
    public void testShowDataFrameOutput() {
        // Setup columns
        Column<String> col1 = new Column<>("Nom", String.class);
        col1.add("Alice");
        col1.add("Bob");

        Column<Integer> col2 = new Column<>("Âge", Integer.class);
        col2.add(30);
        col2.add(25);

        // Create dataframe
        Dataframe df = new Dataframe(Arrays.asList(col1, col2));

        // Capture console output
        ConsoleCaptor consoleCaptor = new ConsoleCaptor();

        // Call ShowDataFrame
        df.ShowDataFrame(df);

        // Expected output as list of lines
        List<String> expectedLines = Arrays.asList(
            "Nom Âge",
            "Alice 30",
            "Bob 25"
        );

        // Actual output
        List<String> actualLines = consoleCaptor.getStandardOutput();

        // Assertion
        assertEquals(expectedLines, actualLines, "L'affichage du Dataframe ne correspond pas à ce qui est attendu.");

        consoleCaptor.close();
    }
    
}
