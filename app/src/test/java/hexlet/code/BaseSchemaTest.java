package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BaseSchemaTest {
    @Test
    void validatorBaseSchemaShaopeTest() {
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
