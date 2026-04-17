public class QuantityMeasurementApp {

    // Enum for units
    public enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0); // 1 inch = 1/12 feet

        private final double conversionFactorToFeet;

        LengthUnit(double factor) {
            this.conversionFactorToFeet = factor;
        }

        public double toFeet(double value) {
            return value * conversionFactorToFeet;
        }
    }

    // Generic Quantity class
    public static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        // Convert to base unit (feet)
        private double toFeet() {
            return unit.toFeet(value);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;

            // Compare after converting both to feet
            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }
    }

    // Main method (demo)
    public static void main(String[] args) {

        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);

        System.out.println("Are equal: " + q1.equals(q2)); // true
    }
}