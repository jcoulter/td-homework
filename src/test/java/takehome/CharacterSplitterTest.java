package takehome;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static takehome.CharacterSplitter.*;

public class CharacterSplitterTest {

    private CharacterSplitter characterSplitter;

    @Before
    public void setUp() throws Exception {
        characterSplitter = new CharacterSplitter();
    }

    //todo: all of these tests have too many assertions.
    // It will be difficult to know why it failed if something goes wrong.
    @Test
    public void test_split_characters_data() {
        // assume file reader drops every 4th line and invokes this with each meaningful 3.
        String line1 = " _     _  _     _  _  _  _  _ ";
        String line2 = "| |  | _| _||_||_ |_   ||_||_|";
        String line3 = "|_|  ||_  _|  | _||_|  ||_| _|";

        String[] entry = {line1, line2, line3};

        String[][] split = characterSplitter.splitCharacterData(entry);
        assertArrayEquals(ZERO, split[0]);
        assertArrayEquals(ONE, split[1]);
        assertArrayEquals(TWO, split[2]);
        assertArrayEquals(THREE, split[3]);
        assertArrayEquals(FOUR, split[4]);
        assertArrayEquals(FIVE, split[5]);
        assertArrayEquals(SIX, split[6]);
        assertArrayEquals(SEVEN, split[7]);
        assertArrayEquals(EIGHT, split[8]);
        assertArrayEquals(NINE, split[9]);
    }

    @Test
    public void test_split_pieces() {

        String line = "0123456789";

        String[] peice = characterSplitter.splitPieces(line);
        assertEquals("012", peice[0]);
        assertEquals("345", peice[1]);
        assertEquals("678", peice[2]);
        assertEquals("9  ", peice[3]);
    }

    @Test
    public void test_character_data_to_digits() {
        String[][] characters = new String[][]{ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE};

        String accountNumber = characterSplitter.toNumericString(characters);
        assertEquals("0123456789", accountNumber);
    }

    @Test
    public void test_illegible_character_data_replaced_by_question_mark() {
        String[] invalidCharacter = {"gar", "bage", "value"};
        String[][] characters = new String[][]{NINE, EIGHT, invalidCharacter, SIX, FIVE, invalidCharacter, THREE, TWO, invalidCharacter, ZERO};

        String accountNumber = characterSplitter.toNumericString(characters);
        assertEquals("98?65?32?0", accountNumber);
    }

//### use case 1
//
//<pre>
// _  _  _  _  _  _  _  _  _
//| || || || || || || || || |
//|_||_||_||_||_||_||_||_||_|
//
//=> 000000000
//
//  |  |  |  |  |  |  |  |  |
//  |  |  |  |  |  |  |  |  |
//
//=> 111111111
// _  _  _  _  _  _  _  _  _
// _| _| _| _| _| _| _| _| _|
//|_ |_ |_ |_ |_ |_ |_ |_ |_
//
//=> 222222222
// _  _  _  _  _  _  _  _  _
// _| _| _| _| _| _| _| _| _|
// _| _| _| _| _| _| _| _| _|
//
//=> 333333333
//
//|_||_||_||_||_||_||_||_||_|
//  |  |  |  |  |  |  |  |  |
//
//=> 444444444
// _  _  _  _  _  _  _  _  _
//|_ |_ |_ |_ |_ |_ |_ |_ |_
// _| _| _| _| _| _| _| _| _|
//
//=> 555555555
// _  _  _  _  _  _  _  _  _
//|_ |_ |_ |_ |_ |_ |_ |_ |_
//|_||_||_||_||_||_||_||_||_|
//
//=> 666666666
// _  _  _  _  _  _  _  _  _
//  |  |  |  |  |  |  |  |  |
//  |  |  |  |  |  |  |  |  |
//
//=> 777777777
// _  _  _  _  _  _  _  _  _
//|_||_||_||_||_||_||_||_||_|
//|_||_||_||_||_||_||_||_||_|
//
//=> 888888888
// _  _  _  _  _  _  _  _  _
//|_||_||_||_||_||_||_||_||_|
// _| _| _| _| _| _| _| _| _|
//
//=> 999999999
//    _  _     _  _  _  _  _
//  | _| _||_||_ |_   ||_||_|
//  ||_  _|  | _||_|  ||_| _|
//
//=> 123456789
//</pre>
//
//### use case 3
//<pre>
// _  _  _  _  _  _  _  _
//| || || || || || || ||_   |
//|_||_||_||_||_||_||_| _|  |
//
//=> 000000051
//    _  _  _  _  _  _     _
//|_||_|| || ||_   |  |  | _
//  | _||_||_||_|  |  |  | _|
//
//=> 49006771? ILL
//    _  _     _  _  _  _  _
//  | _| _||_| _ |_   ||_||_|
//  ||_  _|  | _||_|  ||_| _
//
//=> 1234?678? ILL
//</pre>
//
//### use case 4
//<pre>
//
//  |  |  |  |  |  |  |  |  |
//  |  |  |  |  |  |  |  |  |
//
//=> 711111111
// _  _  _  _  _  _  _  _  _
//  |  |  |  |  |  |  |  |  |
//  |  |  |  |  |  |  |  |  |
//
//=> 777777177
// _  _  _  _  _  _  _  _  _
// _|| || || || || || || || |
//|_ |_||_||_||_||_||_||_||_|
//
//=> 200800000
// _  _  _  _  _  _  _  _  _
// _| _| _| _| _| _| _| _| _|
// _| _| _| _| _| _| _| _| _|
//
//=> 333393333
// _  _  _  _  _  _  _  _  _
//|_||_||_||_||_||_||_||_||_|
//|_||_||_||_||_||_||_||_||_|
//
//=> 888888888 AMB ['888886888', '888888880', '888888988']
// _  _  _  _  _  _  _  _  _
//|_ |_ |_ |_ |_ |_ |_ |_ |_
// _| _| _| _| _| _| _| _| _|
//
//=> 555555555 AMB ['555655555', '559555555']
// _  _  _  _  _  _  _  _  _
//|_ |_ |_ |_ |_ |_ |_ |_ |_
//|_||_||_||_||_||_||_||_||_|
//
//=> 666666666 AMB ['666566666', '686666666']
// _  _  _  _  _  _  _  _  _
//|_||_||_||_||_||_||_||_||_|
// _| _| _| _| _| _| _| _| _|
//
//=> 999999999 AMB ['899999999', '993999999', '999959999']
//    _  _  _  _  _  _     _
//|_||_|| || ||_   |  |  ||_
//  | _||_||_||_|  |  |  | _|
//
//=> 490067715 AMB ['490067115', '490067719', '490867715']
//    _  _     _  _  _  _  _
// _| _| _||_||_ |_   ||_||_|
//  ||_  _|  | _||_|  ||_| _|
//
//=> 123456789
// _     _  _  _  _  _  _
//| || || || || || || ||_   |
//|_||_||_||_||_||_||_| _|  |
//
//=> 000000051
//    _  _  _  _  _  _     _
//|_||_|| ||_||_   |  |  | _
//  | _||_||_||_|  |  |  | _|
//
//=> 490867715
//</pre>
}
