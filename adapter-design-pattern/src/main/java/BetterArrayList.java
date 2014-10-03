import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by slawek on 02/10/14.
 */
public class BetterArrayList<T> extends ArrayList<T> {

    private final Class<T> valueType;

    public BetterArrayList(int initialCapacity, Class<T> valueType) {
        super(initialCapacity);
        this.valueType = valueType;
    }

    public BetterArrayList(Class<T> valueType) {
        this.valueType = valueType;
    }

    public BetterArrayList(Collection<? extends T> c, Class<T> valueType) {
        super(c);
        this.valueType = valueType;
    }

    public T[] toArray() {
        return toArray((T[]) Array.newInstance(valueType, size()));
    }
}
