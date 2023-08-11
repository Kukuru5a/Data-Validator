package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaTest {
    @Test
    void validatorMapSchemaTest() {
        Validator val = new Validator();
        MapSchema mSch = val.map();
        Map<String, String> map = new HashMap<>();

        map.put("key1", "val1");
        assertTrue(mSch.isValid(map));

    }

    @Test
    void validatoRequiredTest() {
        Validator val = new Validator();
        MapSchema mSch = val.map();

        assertTrue(mSch.isValid(null));
        mSch.required();
        assertFalse(mSch.isValid(null));
    }

    @Test
    void validatorSizeOfTest() {
        Validator val = new Validator();
        MapSchema mSch = val.map();
        Map<String, String> map = new HashMap<>();

        map.put("key1", "val1");
        assertTrue(mSch.isValid(map));

        mSch.sizeof(2);
        assertFalse(mSch.isValid(map));

        map.put("key2", "val2");
        assertTrue(mSch.isValid(map));
    }
    @Test
    void validatorBaseSchemaShapeTest() {
        Validator val = new Validator();
        MapSchema mSch = val.map();
        Map<String, BaseSchema> map = new HashMap<>();

        map.put("name", val.string().required());
        map.put("age", val.number().positive());

        mSch.shape(map);

        Map<String, Object> h1 = Map.of("name", "Roman", "age", 23);
        assertTrue(mSch.isValid(h1));

        Map<String, Object> h2 = new HashMap<>();
        h2.put("name", "Kolya");
        h2.put("age", null);

        assertTrue(mSch.isValid(h2));

        Map<String, Object> h3 = new HashMap<>();
        h3.put("name", null);
        h3.put("age", 23);

        assertFalse(mSch.isValid(h3));

        Map<String, Object> h4 = Map.of("name", "Roman", "age", -4);
        assertFalse(mSch.isValid(h4));
    }
}
