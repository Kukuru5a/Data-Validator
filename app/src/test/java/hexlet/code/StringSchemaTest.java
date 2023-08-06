package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringSchemaTest {
    @Test
    public void validatorStringSchemaTest() {
        Validator validator = new Validator();
        StringSchema stringSchema = validator.string();
        String testFirstString = "what does the fox say";
        String testSecondString = "moon";
        String testThirdString = null;

        assertTrue(stringSchema.isValid(testFirstString));
        assertTrue(stringSchema.isValid(testSecondString));

        stringSchema.contains("wh");
        assertTrue(stringSchema.isValid(testFirstString));

        stringSchema.contains("what");
        assertTrue(stringSchema.isValid(testFirstString));

        stringSchema.contains("whatthe");
        assertFalse(stringSchema.isValid(testFirstString));

        stringSchema.contains("wh");
    }

    @Test
    public void validatoMinLengthTest() {
        Validator validator = new Validator();
        StringSchema stringSchema = validator.string();
        String testFirstString = "what does the fox say";
        String testSecondString = "moon";
        int minLength = 5;

        stringSchema.minLength(minLength);

        assertTrue(stringSchema.isValid(testFirstString));
        assertFalse(stringSchema.isValid(testSecondString));

    }
    @Test
    public void validatorRequiredTest() {
        Validator validator = new Validator();
        StringSchema stringSchema = validator.string();

        assertTrue(stringSchema.isValid(""));
        assertTrue(stringSchema.isValid(null));

        stringSchema.required();

        assertFalse(stringSchema.isValid(""));
        assertFalse(stringSchema.isValid(null));
    }
}
