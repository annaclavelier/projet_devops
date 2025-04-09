package bababooeyz;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.opencsv.CSVReader;

/**
 * The Dataframe class represents the Dataframe type used in the pandas library.
 * It is a two-dimensional, with axes labeled rows and columns.
 */
public class Dataframe {
    private List<Column<?>> data;

    /**
     * Constructs a Dataframe with the given list of columns.
     * Each column is assigned an index in the map.
     *
     * @param data A list of columns to be stored in the Dataframe.
     */
    public Dataframe(List<Column<?>> data) {
        this.data = data;
    }

    /**
     * Constructs a Dataframe from a CSV file
     * 
     * @param filePath the file path
     */
    public Dataframe(String filePath) {
        this.data = new ArrayList<>();
        try {

            // create csvReader with file reader as a parameter
            CSVReader csvReader = new CSVReader(new FileReader(filePath));

            String[] lines;
            int countLine = 0;
            String[] columnNames = {};

            // go through lines
            while ((lines = csvReader.readNext()) != null) {
                // first line is for column names
                if (countLine == 0) {
                    // remove whitespaces for columns names
                    for (int i = 0; i < lines.length; i++) {
                        lines[i] = lines[i].trim();
                    }
                    columnNames = lines;

                } else if (countLine == 1) {

                    // Determine type and add first line
                    for (int i = 0; i < lines.length; i++) {
                        Class<?> type = determineTypeFromString(lines[i].trim());
                        data.add(new Column<>(columnNames[i], type));
                        addValueToColumn(data.get(i), convertValue(lines[i], type));
                    }
                } else {
                    // Add values to corresponding columns
                    for (int i = 0; i < lines.length; i++) {
                        addValueToColumn(data.get(i), convertValue(lines[i], data.get(i).getType()));
                    }
                }

                // increment count
                countLine++;
            }

            csvReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add value to column safe
     * 
     * @param <T>
     * @param column
     * @param value
     */
    private <T> void addValueToColumn(Column<?> column, Object value) {
        Column<T> typedColumn = (Column<T>) column;
        typedColumn.add((T) value);
    }

    /**
     * Determine the class of a string with matches of string
     * 
     * @param text
     * @return Integer | Double | Boolean or by default String
     */
    private Class<?> determineTypeFromString(String text) {
        if (text.matches("-?\\d+")) {
            // Integer
            return Integer.class;
        } else if (text.matches("-?\\d*\\.\\d+")) {
            // Double
            return Double.class;
        } else if (text.equalsIgnoreCase("true") || text.equalsIgnoreCase("false")) {
            // Boolean
            return Boolean.class;
        } else {
            // else default string
            return String.class;
        }
    }

    /**
     * Convert a string in the desired class
     * 
     * @param text orginal string
     * @param type class chosen
     * @return string transformed in type desired
     */
    private Object convertValue(String text, Class<?> type) {
        String text_trimmed = text.trim();
        if (type == Integer.class) {
            return Integer.parseInt(text_trimmed);
        } else if (type == Double.class) {
            return Double.parseDouble(text_trimmed);
        } else if (type == Boolean.class) {
            return Boolean.parseBoolean(text_trimmed);
        } else {
            return text;
        }
    }

    // Affiche tout le Dataframe
    public void showDataFrame() {
        if (data.isEmpty())
            return;
        int rowCount = data.get(0).getValues().size();
        printRows(0, rowCount);
    }

    // Affiche les premières N lignes
    public void showHead(int n) {
        if (data.isEmpty())
            return;
        int rowCount = data.get(0).getValues().size();
        int end = Math.min(n, rowCount);
        printRows(0, end);
    }

    // Affiche les dernières N lignes
    public void showTail(int n) {
        if (data.isEmpty())
            return;
        int rowCount = data.get(0).getValues().size();
        int start = Math.max(0, rowCount - n);
        printRows(start, rowCount);
    }

    private void printRows(int start, int end) {
        // Afficher les noms de colonnes
        for (Column<?> column : data) {
            System.out.print(column.getName() + " ");
        }
        System.out.println();

        // Afficher les lignes entre start (inclus) et end (exclus)
        for (int i = start; i < end; i++) {
            for (Column<?> column : data) {
                System.out.print(column.getValue(i) + " ");
            }
            System.out.println();
        }
    }

    /**
     * Create a new dataframe with selection of lines
     * 
     * @param indexes list of lines indexes to keep
     * @return new dataframe with selection of lines
     */
    public Dataframe selectLines(List<Integer> indexes){
        List<Column<?>> selection = new ArrayList<>();

        for (int i=0;i< getData().size(); i++){
            Column <?> col = getData().get(i);
            for (int j=0; j < col.getValues().size(); j++){
                if (!indexes.contains(j)){
                    col.getValues().remove(j);
                }
            }
            selection.add(col);
        }

        return new Dataframe(selection);
    }

    /**
     * Create a new dataframe with selection of columns
     * 
     * @param columnNames list of the names of columns to keep
     * @return new dataframe with columns selected
     */
    public Dataframe selectColumns(List<String> columnNames) {
        List<Column<?>> selection = new ArrayList<>();

        for (int i = 0; i < getData().size(); i++) {
            Column<?> col = getData().get(i);
            if (columnNames.contains(col.getName())) {
                selection.add(col);
            }
        }

        return new Dataframe(selection);
    }

    public List<Column<?>> getData() {
        return data;
    }

    public void setData(List<Column<?>> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Dataframe))
            return false;
        Dataframe dataframe = (Dataframe) o;
        return this.getData().equals(dataframe.getData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}
