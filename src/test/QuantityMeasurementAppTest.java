import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {
    private static final double EPSILON = 1e-6;

    // Yard same
    @Test
    void testEquality_YardToYard_SameValue() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.YARDS);
        var q2 = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.YARDS);

        assertEquals(true, q1.equals(q2));
    }

    // Yard different
    @Test
    void testEquality_YardToYard_DifferentValue() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.YARDS);
        var q2 = new QuantityMeasurementApp.Quantity(2.0, LengthUnit.YARDS);

        assertEquals(false, q1.equals(q2));
    }

    // Yard ↔ Feet
    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.YARDS);
        var q2 = new QuantityMeasurementApp.Quantity(3.0, LengthUnit.FEET);

        assertEquals(true, q1.equals(q2));
    }

    @Test
    void testEquality_FeetToYard_EquivalentValue() {
        var q1 = new QuantityMeasurementApp.Quantity(3.0, LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.YARDS);

        assertEquals(true, q1.equals(q2));
    }

    // Yard ↔ Inches
    @Test
    void testEquality_YardToInches_EquivalentValue() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.YARDS);
        var q2 = new QuantityMeasurementApp.Quantity(36.0, LengthUnit.INCH);

        assertEquals(true, q1.equals(q2));
    }

    @Test
    void testEquality_InchesToYard_EquivalentValue() {
        var q1 = new QuantityMeasurementApp.Quantity(36.0, LengthUnit.INCH);
        var q2 = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.YARDS);

        assertEquals(true, q1.equals(q2));
    }

    // Non-equivalent
    @Test
    void testEquality_YardToFeet_NonEquivalentValue() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.YARDS);
        var q2 = new QuantityMeasurementApp.Quantity(2.0, LengthUnit.FEET);

        assertEquals(false, q1.equals(q2));
    }

    // CM vs Feet (not equal)
    @Test
    void testEquality_centimetersToFeet_NonEquivalentValue() {
        var q1 = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.CENTIMETERS);
        var q2 = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.FEET);

        assertEquals(false, q1.equals(q2));
    }

    // Transitive
    @Test
    void testEquality_MultiUnit_TransitiveProperty() {
        var yard = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.YARDS);
        var feet = new QuantityMeasurementApp.Quantity(3.0, LengthUnit.FEET);
        var inch = new QuantityMeasurementApp.Quantity(36.0, LengthUnit.INCH);

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
        var q = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.YARDS);
        assertEquals(true, q.equals(q));
    }

    // Null comparison
    @Test
    void testEquality_YardNullComparison() {
        var q = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.YARDS);
        assertEquals(false, q.equals(null));
    }

    // Complex scenario
    @Test
    void testEquality_AllUnits_ComplexScenario() {
        var yard = new QuantityMeasurementApp.Quantity(2.0, LengthUnit.YARDS);
        var feet = new QuantityMeasurementApp.Quantity(6.0, LengthUnit.FEET);
        var inch = new QuantityMeasurementApp.Quantity(72.0, LengthUnit.INCH);

        assertEquals(true, yard.equals(feet));
        assertEquals(true, feet.equals(inch));
        assertEquals(true, yard.equals(inch));
    }

    @Test
    void testConversion_FeetToInches() {
        double result = QuantityMeasurementApp.convert(1.0, LengthUnit.FEET, LengthUnit.INCH);
        assertEquals(12.0, result, EPSILON);
    }

    @Test
    void testConversion_InchesToFeet() {
        double result = QuantityMeasurementApp.convert(24.0, LengthUnit.INCH, LengthUnit.FEET);
        assertEquals(2.0, result, EPSILON);
    }

    @Test
    void testConversion_YardsToInches() {
        double result = QuantityMeasurementApp.convert(1.0, LengthUnit.YARDS, LengthUnit.INCH);
        assertEquals(36.0, result, EPSILON);
    }

    @Test
    void testConversion_InchesToYards() {
        double result = QuantityMeasurementApp.convert(72.0, LengthUnit.INCH, LengthUnit.YARDS);
        assertEquals(2.0, result, EPSILON);
    }

    @Test
    void testConversion_CentimetersToInches() {
        double result = QuantityMeasurementApp.convert(2.54, LengthUnit.CENTIMETERS, LengthUnit.INCH);
        assertEquals(1.0, result, EPSILON);
    }

    @Test
    void testConversion_ZeroValue() {
        double result = QuantityMeasurementApp.convert(0.0, LengthUnit.FEET, LengthUnit.INCH);
        assertEquals(0.0, result, EPSILON);
    }

    @Test
    void testConversion_NegativeValue() {
        double result = QuantityMeasurementApp.convert(-1.0, LengthUnit.FEET, LengthUnit.INCH);
        assertEquals(-12.0, result, EPSILON);
    }

    @Test
    void testConversion_InvalidUnit_Throws() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.convert(1.0, null, LengthUnit.FEET));
        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.convert(1.0, LengthUnit.FEET, null));
    }

    @Test
    void testConversion_NaNOrInfinite_Throws() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCH));
        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.convert(Double.POSITIVE_INFINITY, LengthUnit.FEET, LengthUnit.INCH));
    }

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {
        var result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.Quantity(1.0, LengthUnit.FEET),
                new QuantityMeasurementApp.Quantity(2.0, LengthUnit.FEET)
        );
        assertEquals(new QuantityMeasurementApp.Quantity(3.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_SameUnit_InchPlusInch() {
        var result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.Quantity(6.0, LengthUnit.INCH),
                new QuantityMeasurementApp.Quantity(6.0, LengthUnit.INCH)
        );
        assertEquals(new QuantityMeasurementApp.Quantity(12.0, LengthUnit.INCH), result);
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {
        var result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.Quantity(1.0, LengthUnit.FEET),
                new QuantityMeasurementApp.Quantity(12.0, LengthUnit.INCH)
        );
        assertEquals(new QuantityMeasurementApp.Quantity(2.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_IdentityWithZero() {
        var result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.Quantity(5.0, LengthUnit.FEET),
                new QuantityMeasurementApp.Quantity(0.0, LengthUnit.INCH)
        );
        assertEquals(new QuantityMeasurementApp.Quantity(5.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_WithNegativeValue() {
        var result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.Quantity(5.0, LengthUnit.FEET),
                new QuantityMeasurementApp.Quantity(-2.0, LengthUnit.FEET)
        );
        assertEquals(new QuantityMeasurementApp.Quantity(3.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_NullQuantity_Throws() {
        var first = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> QuantityMeasurementApp.add(first, null));
    }

    @Test
    void testAddition_WithTargetUnit_FeetPlusInches_ToYards() {
        var result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.Quantity(1.0, LengthUnit.FEET),
                new QuantityMeasurementApp.Quantity(12.0, LengthUnit.INCH),
                LengthUnit.YARDS
        );
        assertEquals(new QuantityMeasurementApp.Quantity(2.0 / 3.0, LengthUnit.YARDS), result);
    }

    @Test
    void testAddition_WithTargetUnit_YardsPlusFeet_ToInches() {
        var result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.Quantity(1.0, LengthUnit.YARDS),
                new QuantityMeasurementApp.Quantity(3.0, LengthUnit.FEET),
                LengthUnit.INCH
        );
        assertEquals(new QuantityMeasurementApp.Quantity(72.0, LengthUnit.INCH), result);
    }

    @Test
    void testAddition_WithTargetUnit_CentimetersAndInches_ToCentimeters() {
        var result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.Quantity(2.54, LengthUnit.CENTIMETERS),
                new QuantityMeasurementApp.Quantity(1.0, LengthUnit.INCH),
                LengthUnit.CENTIMETERS
        );
        assertEquals(new QuantityMeasurementApp.Quantity(5.08, LengthUnit.CENTIMETERS), result);
    }

    @Test
    void testAddition_WithTargetUnit_CommutativityInSameTarget() {
        var first = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.FEET);
        var second = new QuantityMeasurementApp.Quantity(12.0, LengthUnit.INCH);

        var aPlusB = QuantityMeasurementApp.add(first, second, LengthUnit.FEET);
        var bPlusA = QuantityMeasurementApp.add(second, first, LengthUnit.FEET);

        assertEquals(aPlusB, bPlusA);
    }

    @Test
    void testAddition_WithTargetUnit_NullTarget_Throws() {
        var first = new QuantityMeasurementApp.Quantity(1.0, LengthUnit.FEET);
        var second = new QuantityMeasurementApp.Quantity(12.0, LengthUnit.INCH);
        assertThrows(IllegalArgumentException.class, () -> QuantityMeasurementApp.add(first, second, null));
    }

    @Test
    void testLengthUnit_ConvertToBaseUnit() {
        assertEquals(3.0, LengthUnit.YARDS.toFeet(1.0), EPSILON);
        assertEquals(1.0, LengthUnit.INCH.toFeet(12.0), EPSILON);
    }

    @Test
    void testLengthUnit_ConvertFromBaseUnit() {
        assertEquals(12.0, LengthUnit.INCH.fromFeet(1.0), EPSILON);
        assertEquals(1.0, LengthUnit.YARDS.fromFeet(3.0), EPSILON);
    }

    @Test
    void testBackwardCompatibility_UC5ConversionStillWorks() {
        double result = QuantityMeasurementApp.convert(1.0, LengthUnit.FEET, LengthUnit.INCH);
        assertEquals(12.0, result, EPSILON);
    }
}
