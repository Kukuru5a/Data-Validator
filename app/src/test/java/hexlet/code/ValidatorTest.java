package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ValidatorTest {

    @Test
    public void validatorStringSchemaTest() {
        Validator validator = new Validator();
        StringSchema stringSchema = validator.string();
        String testFirstString = "what does the fox say";
        String testSecondString = "moon";
        int testInteger = 29;
        int minLength = 5;

        assertTrue(stringSchema.isValid(""));
        assertTrue(stringSchema.isValid(null));

        stringSchema.required();

        assertTrue(stringSchema.isValid(testFirstString));
        assertTrue(stringSchema.isValid(testSecondString));
        assertFalse(stringSchema.isValid(""));
        assertFalse(stringSchema.isValid(null));
        assertFalse(stringSchema.isValid(testInteger));

        assertTrue(stringSchema.minLength(minLength).isValid(testFirstString));
        assertFalse(stringSchema.minLength(minLength).isValid(testSecondString));


        stringSchema.contains("wh");
        assertTrue(stringSchema.isValid(testFirstString));

        stringSchema.contains("what");
        assertTrue(stringSchema.isValid(testFirstString));

        stringSchema.contains("whatthe");
        assertFalse(stringSchema.isValid(testFirstString));

        assertFalse(stringSchema.isValid(testFirstString));
    }

    @Test
    public void validatorNumberSchemaTest() {
        Validator val = new Validator();
        NumberSchema nSch = val.number();
        int firstInt = 5;
        int secInt = -10;
        int rangeBeginning = 6;
        int rangeFinish = 11;
        int intInRange = 7; // between 6 & 11
        int intPutOfRange = 12; // between 6 & 11

        assertTrue(nSch.isValid(firstInt));
        assertTrue(nSch.isValid(secInt));
        assertTrue(nSch.isValid(null));

        nSch.required();
        assertFalse(nSch.isValid(null));

        nSch.positive();

        assertFalse(nSch.isValid(secInt));
        assertTrue(nSch.isValid(firstInt));

        nSch.range(rangeBeginning, rangeFinish);

        assertTrue(nSch.isValid(intInRange));
        assertFalse(nSch.isValid(intPutOfRange));
    }

    @Test
    void validatorMapSchemaTest() {
        Validator val = new Validator();
        MapSchema mSch = val.map();
        Map<String, String> map = new HashMap<>();

        assertTrue(mSch.isValid(null));
        mSch.required();
        assertFalse(mSch.isValid(null));

        map.put("key1", "val1");
        assertTrue(mSch.isValid(map));

        mSch.sizeof(2);
        assertFalse(mSch.isValid(map));

        map.put("key2", "val2");
        assertTrue(mSch.isValid(map));
    }
    @Test
    void validatorBaseSchemaTest() {
        Validator val = new Validator();
        MapSchema mSch = val.map();
        Map<String, BaseSchema> map = new HashMap<>();

        map.put("name", val.string().required());
        map.put("age", val.number().positive());

        mSch.shape(map);

        Map<String, Object> h1 = new HashMap<>();
        Map<String, Object> h2 = new HashMap<>();
        Map<String, Object> h3 = new HashMap<>();
        Map<String, Object> h4 = new HashMap<>();

        h1.put("name", "Roman");
        h1.put("age", 23);

        assertTrue(mSch.isValid(h1));

        h2.put("name", "Kolya");
        h2.put("age", null);

        assertTrue(mSch.isValid(h2));

        h3.put("name", null);
        h3.put("age", 23);

        assertFalse(mSch.isValid(h3));

        h4.put("name", "Roman");
        h4.put("age", -4);

        assertFalse(mSch.isValid(h4));
    }
}
