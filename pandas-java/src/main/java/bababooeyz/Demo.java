package bababooeyz;

public class Demo {
    public static void main(String[] args) {
        try {
            Dataframe df = new Dataframe("data/fruits.csv");
            System.out.println("=== Head ===");
            df.showHead(3);
            System.out.println("=== Stats ===");
            System.out.println("Moyenne col prix: " + df.getColumnAverage(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
