public class QuantityMeasurementApp {

    // Enum for units (base: FEET)
    public enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),        // 1 inch = 1/12 feet
        YARDS(3.0),              // 1 yard = 3 feet
        CENTIMETERS(0.0328084);  // 1 cm = 0.0328084 feet (converted from inches)

        private final double conversionFactorToFeet;

        LengthUnit(double factor) {
            this.conversionFactorToFeet = factor;
        }

        public double toFeet(double value) {
            return value * conversionFactorToFeet;
        }
    }

    // Generic Quantity class (unchanged)
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

        private double toFeet() {
            return unit.toFeet(value);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;

            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }
    }

    // Main method (demo)
    public static void main(String[] args) {

        Quantity q1 = new Quantity(1.0, LengthUnit.YARDS);
        Quantity q2 = new Quantity(3.0, LengthUnit.FEET);

        System.out.println("Are equal: " + q1.equals(q2)); // true
    }
}