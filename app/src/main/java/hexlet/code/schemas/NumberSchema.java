package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {
    public final NumberSchema required() {
        this.isRequired = true;
        Predicate<Object> required = s -> (s != null && (int) s > 0);
        super.addPred(required);
        return this;
    }

    public final NumberSchema positive() {
        Predicate<Object> positive =  s -> (int) s >= 0;
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
