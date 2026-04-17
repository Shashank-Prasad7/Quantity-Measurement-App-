import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // ----------- FEET TESTS -----------

    @Test
    void testEquality_SameValue_Feet() {
        assertEquals(true, QuantityMeasurementApp.compareFeet(1.0, 1.0));
    }

    @Test
    void testEquality_DifferentValue_Feet() {
        assertEquals(false, QuantityMeasurementApp.compareFeet(1.0, 2.0));
    }

    @Test
    void testEquality_NullComparison_Feet() {
        QuantityMeasurementApp.Feet value = new QuantityMeasurementApp.Feet(1.0);
        assertEquals(false, value.equals(null));
    }

    @Test
    void testEquality_NonNumericInput_Feet() {
        QuantityMeasurementApp.Feet value = new QuantityMeasurementApp.Feet(1.0);
        String nonNumeric = "abc";
        assertEquals(false, value.equals(nonNumeric));
    }

    @Test
    void testEquality_SameReference_Feet() {
        QuantityMeasurementApp.Feet value = new QuantityMeasurementApp.Feet(1.0);
        assertEquals(true, value.equals(value));
    }

    // ----------- INCHES TESTS -----------

    @Test
    void testEquality_SameValue_Inches() {
        assertEquals(true, QuantityMeasurementApp.compareInches(12.0, 12.0));
    }

    @Test
    void testEquality_DifferentValue_Inches() {
        assertEquals(false, QuantityMeasurementApp.compareInches(12.0, 24.0));
    }

    @Test
    void testEquality_NullComparison_Inches() {
        QuantityMeasurementApp.Inches value = new QuantityMeasurementApp.Inches(12.0);
        assertEquals(false, value.equals(null));
    }

    @Test
    void testEquality_NonNumericInput_Inches() {
        QuantityMeasurementApp.Inches value = new QuantityMeasurementApp.Inches(12.0);
        String nonNumeric = "xyz";
        assertEquals(false, value.equals(nonNumeric));
    }

    @Test
    void testEquality_SameReference_Inches() {
        QuantityMeasurementApp.Inches value = new QuantityMeasurementApp.Inches(12.0);
        assertEquals(true, value.equals(value));
    }
}