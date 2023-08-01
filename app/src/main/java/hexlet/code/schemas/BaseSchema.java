package hexlet.code.schemas;

import java.util.LinkedList;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private final LinkedList<Predicate<Object>> conditions = new LinkedList<>();
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
        conditions.addFirst(pred);
    }
    abstract boolean isInvalid(Object obj);
}
