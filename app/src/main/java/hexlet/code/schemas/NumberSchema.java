package hexlet.code.schemas;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {

    public NumberSchema() {
        this.addPred(v -> v == null || v instanceof Integer);
    }
    public NumberSchema required() {
        Predicate<Object> required = s -> (s != null) && s instanceof Integer;
        super.addPred(required);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Object> positive =  s -> s == null || (int) s >= 0;
        super.addPred(positive);
        return this;
    }

    public NumberSchema range(int beg, int fin) {
        Predicate<Object> range = s -> (int) s >= beg && (int) s <= fin;
        super.addPred(range);
        return this;
    }

}
