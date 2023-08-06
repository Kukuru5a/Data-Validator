package hexlet.code.schemas;


import java.util.function.Predicate;

public class StringSchema extends BaseSchema {
    public final StringSchema required() {
        this.isRequired = true;
        Predicate<Object> required = s -> s instanceof String && !s.equals("");
        super.addPred(required);
        return this;
    }

    public final void contains(String str) {
        Predicate<Object> contains = s -> s.toString().contains(str);
        super.addPred(contains);
    }

    public void minLength(int number) {
        Predicate<Object> minLength = s -> s.toString().length() >= number;
        super.addPred(minLength);
    }
    @Override
    public boolean isInvalid(Object obj) {
        return !(obj instanceof String);
    }

}
