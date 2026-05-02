enum LengthUnit {
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

    public double fromFeet(double feetValue) {
        return feetValue / conversionFactorToFeet;
    }
}

enum WeightUnit {
    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double conversionFactorToKilogram;

    WeightUnit(double factor) {
        this.conversionFactorToKilogram = factor;
    }

    public double toKilogram(double value) {
        return value * conversionFactorToKilogram;
    }

    public double fromKilogram(double kilogramValue) {
        return kilogramValue / conversionFactorToKilogram;
    }
}

public class QuantityMeasurementApp {
    private static final double EPSILON = 1e-6;

    // Generic Quantity class (unchanged)
    public static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            validateFinite(value);
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        private double toFeet() {
            return unit.toFeet(value);
        }

        public Quantity convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }
            double convertedValue = targetUnit.fromFeet(this.toFeet());
            return new Quantity(convertedValue, targetUnit);
        }

        public Quantity add(Quantity other) {
            if (other == null) {
                throw new IllegalArgumentException("Quantity cannot be null");
            }
            double sumInFeet = this.toFeet() + other.toFeet();
            double valueInCurrentUnit = this.unit.fromFeet(sumInFeet);
            return new Quantity(valueInCurrentUnit, this.unit);
        }

        public Quantity add(Quantity other, LengthUnit targetUnit) {
            if (other == null) {
                throw new IllegalArgumentException("Quantity cannot be null");
            }
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }
            double sumInFeet = this.toFeet() + other.toFeet();
            double valueInTargetUnit = targetUnit.fromFeet(sumInFeet);
            return new Quantity(valueInTargetUnit, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;

            return Math.abs(this.toFeet() - other.toFeet()) <= EPSILON;
        }
    }

    public static class QuantityWeight {
        private final double value;
        private final WeightUnit unit;

        public QuantityWeight(double value, WeightUnit unit) {
            validateFinite(value);
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        private double toKilogram() {
            return unit.toKilogram(value);
        }

        public QuantityWeight convertTo(WeightUnit targetUnit) {
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }
            double convertedValue = targetUnit.fromKilogram(this.toKilogram());
            return new QuantityWeight(convertedValue, targetUnit);
        }

        public QuantityWeight add(QuantityWeight other) {
            if (other == null) {
                throw new IllegalArgumentException("Quantity cannot be null");
            }
            double sumInKilogram = this.toKilogram() + other.toKilogram();
            double valueInCurrentUnit = this.unit.fromKilogram(sumInKilogram);
            return new QuantityWeight(valueInCurrentUnit, this.unit);
        }

        public QuantityWeight add(QuantityWeight other, WeightUnit targetUnit) {
            if (other == null) {
                throw new IllegalArgumentException("Quantity cannot be null");
            }
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }
            double sumInKilogram = this.toKilogram() + other.toKilogram();
            double valueInTargetUnit = targetUnit.fromKilogram(sumInKilogram);
            return new QuantityWeight(valueInTargetUnit, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            QuantityWeight other = (QuantityWeight) obj;
            return Math.abs(this.toKilogram() - other.toKilogram()) <= EPSILON;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(Math.round(this.toKilogram() / EPSILON) * EPSILON);
        }
    }

    public static double convert(double value, LengthUnit sourceUnit, LengthUnit targetUnit) {
        validateFinite(value);
        if (sourceUnit == null || targetUnit == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }
        double baseInFeet = sourceUnit.toFeet(value);
        return targetUnit.fromFeet(baseInFeet);
    }

    public static Quantity add(Quantity first, Quantity second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException("Quantities cannot be null");
        }
        return first.add(second);
    }

    public static Quantity add(Quantity first, Quantity second, LengthUnit targetUnit) {
        if (first == null || second == null) {
            throw new IllegalArgumentException("Quantities cannot be null");
        }
        return first.add(second, targetUnit);
    }

    public static QuantityWeight add(QuantityWeight first, QuantityWeight second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException("Quantities cannot be null");
        }
        return first.add(second);
    }

    public static QuantityWeight add(QuantityWeight first, QuantityWeight second, WeightUnit targetUnit) {
        if (first == null || second == null) {
            throw new IllegalArgumentException("Quantities cannot be null");
        }
        return first.add(second, targetUnit);
    }

    private static void validateFinite(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }
    }

    // Main method (demo)
    public static void main(String[] args) {

        Quantity q1 = new Quantity(1.0, LengthUnit.YARDS);
        Quantity q2 = new Quantity(3.0, LengthUnit.FEET);

        System.out.println("Are equal: " + q1.equals(q2)); // true
    }
}
