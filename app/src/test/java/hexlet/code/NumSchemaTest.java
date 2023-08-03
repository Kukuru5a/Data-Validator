package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumSchemaTest {
    @Test
    public void validatorNumberSchemaTest() {
        Validator val = new Validator();
        NumberSchema nSch = val.number();
        int firstInt = 5;
        int secInt = -10;

        assertTrue(nSch.isValid(firstInt));
        assertTrue(nSch.isValid(secInt));

    }

    @Test
    public void validatorRequiredTest() {
        Validator val = new Validator();
        NumberSchema nSch = val.number();

        assertTrue(nSch.isValid(null));

        nSch.required();
        assertFalse(nSch.isValid(null));

    }
    @Test
    public void validatorInOutOfRangeTest() {
        Validator val = new Validator();
        NumberSchema nSch = val.number();
        int rangeBeginning = 6;
        int rangeFinish = 11;
        int intInRange = 7; // between 6 & 11
        int intPutOfRange = 12; // between 6 & 11

        nSch.range(rangeBeginning, rangeFinish);

        assertTrue(nSch.isValid(intInRange));
        assertFalse(nSch.isValid(intPutOfRange));
    }

    @Test
    public void validatorPositiveTest() {
        Validator val = new Validator();
        NumberSchema nSch = val.number();
        int firstInt = 5;
        int secInt = -10;

        assertTrue(nSch.isValid(firstInt));
        assertTrue(nSch.isValid(secInt));

        nSch.positive();

        assertFalse(nSch.isValid(secInt));
        assertTrue(nSch.isValid(firstInt));
    }
}
