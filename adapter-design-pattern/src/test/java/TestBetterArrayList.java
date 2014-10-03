import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by slawek on 02/10/14.
 */
public class TestBetterArrayList {


    private BetterArrayList<String> betterArrayList;

    @Before
    public void setUp() {
        betterArrayList = new BetterArrayList<String>(String.class);
    }

    @Test
    public void printTwoNames() {
        betterArrayList.add("First Name");
        betterArrayList.add("Second Name");

        String[] names = betterArrayList.toArray();

        for(String name:names) {
            System.out.println(name);
        }
        assertThat(names[0], is("First Name"));
        assertThat(names[1], is("Second Name"));
    }
}
