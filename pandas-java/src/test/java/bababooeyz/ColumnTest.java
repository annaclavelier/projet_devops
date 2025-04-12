package bababooeyz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ColumnTest {

    @Test
    public void testGetMaxMin() {   
        // Set a comparable column
        Column<Integer> numbers = new Column<>("MyNumbers", Integer.class);

        numbers.add(1);
        numbers.add(42);
        // Set an incomparable column
        Column<Object> objects = new Column<>("MyObjects", Object.class);

        // Assertion of max
        assertEquals(42, numbers.getMax(), "Max values do not correspond!");
        // Assertion of min
        assertEquals(1, numbers.getMin(), "Min values do not correspond!");

        // Trying to get max value from empty should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            objects.getMax();
        });

        // Populate objects
        objects.add(new Object());
        objects.add(new Object());

        // Trying to get max value from non-comparable type should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            objects.getMax();
        });
    }
    @Test
    public void testGetAverage(){
        // Set a numeric column
        Column<Integer> numbers = new Column<>("MyNumbers", Integer.class);
        // Trying to get max value from empty should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            numbers.getMax();
        });
        numbers.add(1);
        numbers.add(43);
        // Assertion of average
        assertEquals(22, numbers.getAverage(), "Average values do not correspond!");

        // Set an incomparable column
        Column<Object> objects = new Column<>("MyObjects", Object.class);
        // Trying to get max value from non-comparable type should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            objects.getAverage();
        });
    }   
}
