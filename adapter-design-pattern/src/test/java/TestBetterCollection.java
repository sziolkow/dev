import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by slawek on 02/10/14.
 */
public class TestBetterCollection {

    private BetterCollection<String> betterCollection;

    @Before
    public void setUp() {
        betterCollection = new BetterCollectionObjectAdapter(new LinkedList(), String.class);
    }

    @Test
    public void printTwoNames() {
        betterCollection.add("First Name");
        betterCollection.add("Second Name");

        String[] names = betterCollection.toArray();

        for(String name:names) {
            System.out.println(name);
        }
        assertThat(names[0], is("First Name"));
        assertThat(names[1], is("Second Name"));
    }
}
