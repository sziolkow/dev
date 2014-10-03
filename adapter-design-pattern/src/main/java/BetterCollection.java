import java.util.Collection;

/**
 * Created by slawek on 02/10/14.
 */
public interface BetterCollection<T> extends Collection<T> {

    T[] toArray();
}
