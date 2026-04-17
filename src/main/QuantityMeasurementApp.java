import java.util.Scanner;

public class QuantityMeasurementApp {

    // Inner class Feet
    public static class Feet {
        private final double value;

        // Constructor
        public Feet(double value) {
            this.value = value;
        }

        // Override equals method
        @Override
        public boolean equals(Object obj) {

            // Same reference
            if (this == obj) {
                return true;
            }

            // Null or different type
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            // Cast and compare
            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }

    // Main method with user input
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            // Taking input from user
            System.out.print("Enter first value in feet: ");
            double input1 = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter second value in feet: ");
            double input2 = Double.parseDouble(scanner.nextLine());

            // Creating objects
            Feet value1 = new Feet(input1);
            Feet value2 = new Feet(input2);

            // Comparing values
            boolean result = value1.equals(value2);

            // Output result
            System.out.println("Are both values equal? " + result);

        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter numeric values only.");
        }

        scanner.close();
    }
}