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

        assertFalse(nSch.isValid("a"));
        assertTrue(nSch.isValid(null));
        assertTrue(nSch.isValid(5));
        assertTrue(nSch.isValid(-10));

    }

    @Test
    public void validatorRequiredTest() {
        Validator val = new Validator();
        NumberSchema nSch = val.number();

        assertTrue(nSch.isValid(null));

        nSch.required();
        assertFalse(nSch.isValid("num"));
        assertFalse(nSch.isValid(null));

    }
    @Test
    public void validatorInOutOfRangeTest() {
        Validator val = new Validator();
        NumberSchema nSch = val.number();

        nSch.range(6, 11);

        assertTrue(nSch.isValid(7));
        assertFalse(nSch.isValid(12));
    }

    @Test
    public void validatorPositiveTest() {
        Validator val = new Validator();
        NumberSchema nSch = val.number();

        assertTrue(nSch.isValid(5));
        assertTrue(nSch.isValid(-10));

        nSch.positive();

        assertFalse(nSch.isValid(-10));
        assertTrue(nSch.isValid(5));
    }
}
