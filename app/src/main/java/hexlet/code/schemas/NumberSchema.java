package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {
    public final NumberSchema condition() {
        this.isRequired = true;
        Predicate<Object> condition = s -> !(s instanceof String);
        super.addPred(condition);
        return this;
    }

    public final void required() {
        this.isRequired = true;
        Predicate<Object> required = s -> (s != null && (int) s != 0) && s instanceof Integer;
        super.addPred(required);
    }

    public final NumberSchema positive() {
        Predicate<Object> positive =  s -> s == null || (int) s >= 0;
        super.addPred(positive);
        return this;
    }

    public final void range(int beg, int fin) {
        Predicate<Object> range = s -> (int) s >= beg && (int) s <= fin;
        super.addPred(range);
    }

    @Override
    boolean isInvalid(Object obj) {
        return !(obj instanceof Integer);
    }
}
