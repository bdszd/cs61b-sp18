import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    //Uncomment this class once you've created your CharacterComparator interface and OffByOne class. **/
    @Test
    public void testOffByOne() {
        assertFalse(offByOne.equalChars('a','B'));
        assertFalse(offByOne.equalChars('a','m'));
        assertFalse(offByOne.equalChars('%','*'));

        assertTrue(offByOne.equalChars('a','b'));
        assertTrue(offByOne.equalChars('A','B'));
        assertTrue(offByOne.equalChars('n','m'));
        assertTrue(offByOne.equalChars('%','&'));
    }
}
