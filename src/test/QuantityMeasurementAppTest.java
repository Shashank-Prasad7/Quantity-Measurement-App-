import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // Test: Same values should be equal
    @Test
    void testEquality_SameValue() {
        QuantityMeasurementApp.Feet value1 = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet value2 = new QuantityMeasurementApp.Feet(1.0);

        assertEquals(true, value1.equals(value2));
    }

    // Test: Different values should not be equal
    @Test
    void testEquality_DifferentValue() {
        QuantityMeasurementApp.Feet value1 = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet value2 = new QuantityMeasurementApp.Feet(2.0);

        assertEquals(false, value1.equals(value2));
    }

    // Test: Comparing with null should return false
    @Test
    void testEquality_NullComparison() {
        QuantityMeasurementApp.Feet value = new QuantityMeasurementApp.Feet(1.0);

        assertEquals(false, value.equals(null));
    }

    // Test: Comparing with non-numeric / different type
    @Test
    void testEquality_NonNumericInput() {
        QuantityMeasurementApp.Feet value = new QuantityMeasurementApp.Feet(1.0);
        String nonNumeric = "Not a number";

        assertEquals(false, value.equals(nonNumeric));
    }

    // Test: Same reference should be equal
    @Test
    void testEquality_SameReference() {
        QuantityMeasurementApp.Feet value = new QuantityMeasurementApp.Feet(1.0);

        assertEquals(true, value.equals(value));
    }
}