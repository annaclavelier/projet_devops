package bababooeyz;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;

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
        df.showDataFrame();

        // Expected output as list of lines
        List<String> expectedLines = Arrays.asList(
                "Nom Âge",
                "Alice 30",
                "Bob 25");

        // Actual output
        List<String> actualLines = consoleCaptor.getStandardOutput();

        // Assertion
        assertEquals(expectedLines, actualLines, "L'affichage du Dataframe ne correspond pas à ce qui est attendu.");

        consoleCaptor.close();
    }

    @Test
    public void testShowHeadOutput() {
        // Setup columns
        Column<String> col1 = new Column<>("Nom", String.class);
        col1.add("Alice");
        col1.add("Bob");
        col1.add("Charlie");

        Column<Integer> col2 = new Column<>("Âge", Integer.class);
        col2.add(30);
        col2.add(25);
        col2.add(35);

        // Create dataframe
        Dataframe df = new Dataframe(Arrays.asList(col1, col2));

        // Capture console output
        ConsoleCaptor consoleCaptor = new ConsoleCaptor();

        // Call showHead
        df.showHead(1);

        // Expected output as list of lines
        List<String> expectedLines = Arrays.asList(
                "Nom Âge",
                "Alice 30");

        // Actual output
        List<String> actualLines = consoleCaptor.getStandardOutput();

        // Assertion
        assertEquals(expectedLines, actualLines,
                "L'affichage de la tête du Dataframe ne correspond pas à ce qui est attendu.");

        consoleCaptor.close();
    }

    @Test
    public void testShowTailOutput() {
        // Setup columns
        Column<String> col1 = new Column<>("Nom", String.class);
        col1.add("Alice");
        col1.add("Bob");
        col1.add("Charlie");

        Column<Integer> col2 = new Column<>("Âge", Integer.class);
        col2.add(30);
        col2.add(25);
        col2.add(35);

        // Create dataframe
        Dataframe df = new Dataframe(Arrays.asList(col1, col2));

        // Capture console output
        ConsoleCaptor consoleCaptor = new ConsoleCaptor();

        // Call showTail
        df.showTail(1);

        // Expected output as list of lines
        List<String> expectedLines = Arrays.asList(
                "Nom Âge",
                "Charlie 35");

        // Actual output
        List<String> actualLines = consoleCaptor.getStandardOutput();

        // Assertion
        assertEquals(expectedLines, actualLines,
                "L'affichage de la queue du Dataframe ne correspond pas à ce qui est attendu.");

        consoleCaptor.close();
    }

    @Test
    public void testDataFrameConstructorWithCsvFile() {
        File f = new File("data/fruits.csv");
        Dataframe dataframe = new Dataframe(f.getAbsolutePath());

        Column<String> col = new Column<>("nom", String.class);
        col.add("fraise");
        col.add("abricot");
        col.add("pomme");
        col.setValues(new ArrayList<>(Arrays.asList("fraise", "abricot", "pomme")));

        Column<Double> col2 = new Column<>("prix", Double.class);
        col2.setValues(new ArrayList<>(Arrays.asList(2.80, 1.50, 2.0)));

        Column<Integer> col3 = new Column<>("stock", Integer.class);
        col3.setValues(new ArrayList<>(Arrays.asList(150, 3000, 420)));

        List<Column<?>> columnsExpected = new ArrayList<>(Arrays.asList(col, col2, col3));

        List<Column<?>> columnsActual = dataframe.getData();

        // should be equal columns size
        assertEquals(columnsExpected.size(), columnsActual.size());
        // checking columns are the same
        for (int i = 0; i < columnsActual.size(); i++) {
            assertEquals(columnsExpected.get(i).getName(), columnsActual.get(i).getName());
            assertEquals(columnsExpected.get(i).getValues(), columnsActual.get(i).getValues());
            assertEquals(columnsExpected.get(i).getType(), columnsActual.get(i).getType());
        }

        
    }
    @Test
    public void testDataframeAdvancedSelection(){
        Column<String> col1 = new Column<>("name", String.class);
        col1.add("Alice");
        col1.add("Bob");
        col1.add("Charlie");

        Column<Integer> col2 = new Column<>("age", Integer.class);
        col2.add(30);
        col2.add(25);
        col2.add(35);

        // Create dataframe
        Dataframe df = new Dataframe(Arrays.asList(col1, col2));
        Column<Object> col1_exp = new Column<>("name", Object.class);
        col1_exp.add("Alice");
        Column<Object> col2_exp = new Column<>("age", Object.class);
        col2_exp.add(30);
        Dataframe res = df.selectLinesBoolean("age > 0 && name.startsWith('A')");
        // point to point of values verification
        assertEquals("Alice", res.getData().get(0).getValue(0));
        assertEquals(30, res.getData().get(1).getValue(0));
    }
    }

    @Test
    public void testSelectionLines() {

        // Setup columns
        Column<String> col1 = new Column<>("Nom", String.class);
        col1.add("Alice");
        col1.add("Bob");
        col1.add("Charlie");

        Column<Integer> col2 = new Column<>("Âge", Integer.class);
        col2.add(30);
        col2.add(25);
        col2.add(35);

        Column<String> col1_ = new Column<>("Nom", String.class);
        col1_.add("Charlie");

        Column<Integer> col2_ = new Column<>("Âge", Integer.class);
        col2_.add(35);

        // Create dataframe
        Dataframe df = new Dataframe(Arrays.asList(col1, col2));

        Dataframe df_expected = new Dataframe(Arrays.asList(col1_,col2_));

        Dataframe dfSelected = df.selectLines(Arrays.asList(2));

        assertEquals(df_expected, dfSelected);

    }

    @Test
    public void testSelectionColumns() {
        // Setup columns
        Column<String> col1 = new Column<>("Nom", String.class);
        col1.add("Alice");
        col1.add("Bob");
        col1.add("Charlie");

        Column<Integer> col2 = new Column<>("Âge", Integer.class);
        col2.add(30);
        col2.add(25);
        col2.add(35);

        // Create dataframe
        Dataframe df = new Dataframe(Arrays.asList(col1, col2));

        Dataframe df_expected = new Dataframe(Arrays.asList(col1));

        Dataframe dfSelected = df.selectColumns(Arrays.asList("Nom"));

        assertEquals(df_expected, dfSelected);
        
    }
}
