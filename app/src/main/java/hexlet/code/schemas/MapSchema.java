package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {
    public final void required() {
        this.isRequired = true;
        Predicate<Object> required = s -> s instanceof Map;
        super.addPred(required);
    }

    public final void sizeof(int value) {
        Predicate<Object> sizeof = s -> ((Map<?, ?>) s).size() == value;
        super.addPred(sizeof);
    }

    public final void shape(Map<String, BaseSchema> schema) {
        Predicate<Object> shape = s -> shapeIsRequired(schema, (Map<?, ?>) s);
        super.addPred(shape);
    }

    private boolean shapeIsRequired(Map<String, BaseSchema> schema, Map<?, ?> map) {
        for (Map.Entry<String, BaseSchema> mapEntry : schema.entrySet()) {
            String key = mapEntry.getKey();
            if (!map.containsKey(key) || !mapEntry.getValue().isValid(map.get(key))) {
                return false;
            }
        }
        return true;
    }

    @Override
    boolean isInvalid(Object obj) {
        return !(obj instanceof Map);
    }
}
