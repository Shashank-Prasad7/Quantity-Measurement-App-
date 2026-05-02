import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {
    private static final double EPSILON = 1e-6;

    // Yard same
    @Test
    void testEquality_YardToYard_SameValue() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        var q2 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARDS);

        assertEquals(true, q1.equals(q2));
    }

    // Yard different
    @Test
    void testEquality_YardToYard_DifferentValue() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        var q2 = new QuantityMeasurementApp.Quantity(2.0, QuantityMeasurementApp.LengthUnit.YARDS);

        assertEquals(false, q1.equals(q2));
    }

    // Yard ↔ Feet
    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        var q2 = new QuantityMeasurementApp.Quantity(3.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(true, q1.equals(q2));
    }

    @Test
    void testEquality_FeetToYard_EquivalentValue() {
        var q1 = new QuantityMeasurementApp.Quantity(3.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARDS);

        assertEquals(true, q1.equals(q2));
    }

    // Yard ↔ Inches
    @Test
    void testEquality_YardToInches_EquivalentValue() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        var q2 = new QuantityMeasurementApp.Quantity(36.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(true, q1.equals(q2));
    }

    @Test
    void testEquality_InchesToYard_EquivalentValue() {
        var q1 = new QuantityMeasurementApp.Quantity(36.0, QuantityMeasurementApp.LengthUnit.INCH);
        var q2 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARDS);

        assertEquals(true, q1.equals(q2));
    }

    // Non-equivalent
    @Test
    void testEquality_YardToFeet_NonEquivalentValue() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        var q2 = new QuantityMeasurementApp.Quantity(2.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(false, q1.equals(q2));
    }

    // CM vs Feet (not equal)
    @Test
    void testEquality_centimetersToFeet_NonEquivalentValue() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        var q2 = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(false, q1.equals(q2));
    }

    // Transitive
    @Test
    void testEquality_MultiUnit_TransitiveProperty() {
        var yard = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        var feet = new QuantityMeasurementApp.Quantity(3.0, QuantityMeasurementApp.LengthUnit.FEET);
        var inch = new QuantityMeasurementApp.Quantity(36.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(true, yard.equals(feet));
        assertEquals(true, feet.equals(inch));
        assertEquals(true, yard.equals(inch));
    }

    // Null unit
    @Test
    void testEquality_YardWithNullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityMeasurementApp.Quantity(1.0, null);
        });
    }

    // Same reference
    @Test
    void testEquality_YardSameReference() {
        var q = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        assertEquals(true, q.equals(q));
    }

    // Null comparison
    @Test
    void testEquality_YardNullComparison() {
        var q = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        assertEquals(false, q.equals(null));
    }

    // Complex scenario
    @Test
    void testEquality_AllUnits_ComplexScenario() {
        var yard = new QuantityMeasurementApp.Quantity(2.0, QuantityMeasurementApp.LengthUnit.YARDS);
        var feet = new QuantityMeasurementApp.Quantity(6.0, QuantityMeasurementApp.LengthUnit.FEET);
        var inch = new QuantityMeasurementApp.Quantity(72.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(true, yard.equals(feet));
        assertEquals(true, feet.equals(inch));
        assertEquals(true, yard.equals(inch));
    }

    @Test
    void testConversion_FeetToInches() {
        double result = QuantityMeasurementApp.convert(1.0, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.INCH);
        assertEquals(12.0, result, EPSILON);
    }

    @Test
    void testConversion_InchesToFeet() {
        double result = QuantityMeasurementApp.convert(24.0, QuantityMeasurementApp.LengthUnit.INCH, QuantityMeasurementApp.LengthUnit.FEET);
        assertEquals(2.0, result, EPSILON);
    }

    @Test
    void testConversion_YardsToInches() {
        double result = QuantityMeasurementApp.convert(1.0, QuantityMeasurementApp.LengthUnit.YARDS, QuantityMeasurementApp.LengthUnit.INCH);
        assertEquals(36.0, result, EPSILON);
    }

    @Test
    void testConversion_InchesToYards() {
        double result = QuantityMeasurementApp.convert(72.0, QuantityMeasurementApp.LengthUnit.INCH, QuantityMeasurementApp.LengthUnit.YARDS);
        assertEquals(2.0, result, EPSILON);
    }

    @Test
    void testConversion_CentimetersToInches() {
        double result = QuantityMeasurementApp.convert(2.54, QuantityMeasurementApp.LengthUnit.CENTIMETERS, QuantityMeasurementApp.LengthUnit.INCH);
        assertEquals(1.0, result, EPSILON);
    }

    @Test
    void testConversion_ZeroValue() {
        double result = QuantityMeasurementApp.convert(0.0, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.INCH);
        assertEquals(0.0, result, EPSILON);
    }

    @Test
    void testConversion_NegativeValue() {
        double result = QuantityMeasurementApp.convert(-1.0, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.INCH);
        assertEquals(-12.0, result, EPSILON);
    }

    @Test
    void testConversion_InvalidUnit_Throws() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.convert(1.0, null, QuantityMeasurementApp.LengthUnit.FEET));
        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.convert(1.0, QuantityMeasurementApp.LengthUnit.FEET, null));
    }

    @Test
    void testConversion_NaNOrInfinite_Throws() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.convert(Double.NaN, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.INCH));
        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.convert(Double.POSITIVE_INFINITY, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.INCH));
    }

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {
        var result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.Quantity(2.0, QuantityMeasurementApp.LengthUnit.FEET)
        );
        assertEquals(new QuantityMeasurementApp.Quantity(3.0, QuantityMeasurementApp.LengthUnit.FEET), result);
    }

    @Test
    void testAddition_SameUnit_InchPlusInch() {
        var result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.Quantity(6.0, QuantityMeasurementApp.LengthUnit.INCH),
                new QuantityMeasurementApp.Quantity(6.0, QuantityMeasurementApp.LengthUnit.INCH)
        );
        assertEquals(new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH), result);
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {
        var result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH)
        );
        assertEquals(new QuantityMeasurementApp.Quantity(2.0, QuantityMeasurementApp.LengthUnit.FEET), result);
    }

    @Test
    void testAddition_IdentityWithZero() {
        var result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.Quantity(5.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.Quantity(0.0, QuantityMeasurementApp.LengthUnit.INCH)
        );
        assertEquals(new QuantityMeasurementApp.Quantity(5.0, QuantityMeasurementApp.LengthUnit.FEET), result);
    }

    @Test
    void testAddition_WithNegativeValue() {
        var result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.Quantity(5.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.Quantity(-2.0, QuantityMeasurementApp.LengthUnit.FEET)
        );
        assertEquals(new QuantityMeasurementApp.Quantity(3.0, QuantityMeasurementApp.LengthUnit.FEET), result);
    }

    @Test
    void testAddition_NullQuantity_Throws() {
        var first = new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> QuantityMeasurementApp.add(first, null));
    }
}
