import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

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
}