import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by slawek on 02/10/14.
 */
public class BetterCollectionObjectAdapter<T> implements BetterCollection<T>{

    private final Collection<T> adaptee;
    private final Class<T> valueType;

    public BetterCollectionObjectAdapter(Collection<T> adaptee, Class<T> valueType) {
        this.adaptee = adaptee;
        this.valueType = valueType;
    }

    @Override
    public T[] toArray() {
        return adaptee.toArray((T[])Array.newInstance(valueType, adaptee.size()));
    }

    @Override
    public int size() {
        return adaptee.size();
    }

    @Override
    public boolean isEmpty() {
        return adaptee.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return adaptee.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return adaptee.iterator();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return adaptee.toArray(ts);
    }

    @Override
    public boolean add(T t) {
        return adaptee.add(t);
    }

    @Override
    public boolean remove(Object o) {
        return adaptee.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return adaptee.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return adaptee.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return adaptee.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return adaptee.removeAll(c);
    }

    @Override
    public void clear() {
        adaptee.clear();

    }
}
