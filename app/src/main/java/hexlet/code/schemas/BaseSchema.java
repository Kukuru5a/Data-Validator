package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private final List<Predicate<Object>> conditions = new ArrayList<>();

    public final boolean isValid(Object obj) {
        for (Predicate<Object> pred : conditions) {
            if (!pred.test(obj)) {
                return false;
            }
        }
        return true;
    }
    protected final void addPred(Predicate<Object> pred) {
        conditions.add(pred);
    }
}
