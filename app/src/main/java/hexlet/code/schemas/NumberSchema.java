package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {
    private boolean condition = null instanceof Integer;

    public final NumberSchema condition() {
        this.isRequired = true;
        Predicate<Object> condition = s -> !(s instanceof String);
        super.addPred(condition);
        return this;
    }

    public final NumberSchema required() {
        this.isRequired = true;
        Predicate<Object> required = s -> (s != null && (int) s != 0) && s instanceof Integer;
        super.addPred(required);
        return this;
    }

    public final NumberSchema positive() {
        Predicate<Object> positive =  s -> s == null || (int) s >= 0;
        super.addPred(positive);
        return this;
    }

    public final NumberSchema range(int beg, int fin) {
        Predicate<Object> range = s -> (int) s >= beg && (int) s <= fin;
        super.addPred(range);
        return this;
    }

    @Override
    boolean isInvalid(Object obj) {
        return !(obj instanceof Integer);
    }
}
