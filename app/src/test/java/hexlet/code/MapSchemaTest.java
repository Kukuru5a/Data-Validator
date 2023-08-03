package hexlet.code;

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
        Map<String, String> map = new HashMap<>();

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
}
