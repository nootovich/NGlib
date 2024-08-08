package nootovich.nglib;

import java.util.function.Supplier;

public class NGLazyValue<T> implements Supplier<T> {
    private final    Supplier<T> supplier;
    private volatile T           value;

    public NGLazyValue(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public synchronized T get() {
        if (value == null) {
            value = supplier.get();
        }
        return value;
    }

    @Override
    public String toString() {
        return get().toString();
    }
}
