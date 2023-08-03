package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private final List<Predicate<Object>> conditions = new ArrayList<>();
    protected boolean isRequired;

    public final boolean isValid(Object obj) {
        if (!this.isRequired && isInvalid(obj)) {
            return true;
        }
        for (Predicate<Object> pred : conditions) {
            if (!pred.test(obj)) {
                return false;
            }
        }
        return true;
    }

    public final void addPred(Predicate<Object> pred) {
        conditions.add(pred);
    }
    abstract boolean isInvalid(Object obj);
}
