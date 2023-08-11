package hexlet.code.schemas;


import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {
    public StringSchema() {
        this.addPred(v -> v == null || v instanceof String);
    }

    public StringSchema required() {
        Predicate<Object> required =  s -> s instanceof String && s != null && !s.equals("");
        super.addPred(required);
        return this;
    }

    public StringSchema contains(String str) {
        Predicate<Object> contains = s -> s.toString().contains(str);
        super.addPred(contains);
        return this;
    }

    public StringSchema minLength(int number) {
        Predicate<Object> minLength = s -> s.toString().length() >= number;
        super.addPred(minLength);
        return this;
    }

}
