package q002;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemTest {
    @Test
    public void success() {
        // setup
        String arg = "1,伊藤";

        // test
        Item actual = new Item(arg);

        // check
        assertEquals("1,伊藤", actual.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void error_args_non_comma() {
        // setup
        String arg = "1伊藤";

        // test
        Item actual = new Item(arg);
    }

    @Test(expected = IllegalArgumentException.class)
    public void error_args_comma_over() {
        // setup
        String arg = "1,伊藤,aaa";

        // test
        Item actual = new Item(arg);
    }

    @Test(expected = IllegalArgumentException.class)
    public void error_id_is_not_number() {
        // setup
        String arg = "a,伊藤";

        // test
        Item actual = new Item(arg);
    }
}