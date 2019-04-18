package takehome;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class AccountNumberValidatorTest {

    private AccountNumberValidator accountNumberValidator;

    @Before
    public void setUp() throws Exception {
        accountNumberValidator = new AccountNumberValidator();
    }

    @Test
    public void invalid_too_long(){
        assertFalse(accountNumberValidator.validate("0123456789"));
    }

    @Test
    public void invalid_too_short(){
        assertFalse(accountNumberValidator.validate("0123"));
    }

    @Test
    public void invalid_checksum(){
        assertFalse(accountNumberValidator.validate("664371495"));
    }

    @Test
    public void calculates_checksum_valid(){
        assertTrue(accountNumberValidator.validate("457508000"));
    }

    @Test
    public void converts_to_integer_array(){
        assertArrayEquals(new int[]{9,8,7,6,5,4,3,2,1,0}, accountNumberValidator.toInts("9876543210"));
    }

    @Test(expected = NumberFormatException.class)
    public void toInts_requires_numeric(){
        accountNumberValidator.toInts("garbage");
    }

    @Test
    public void valid_checksum(){
        assertEquals(0, accountNumberValidator.calculateChecksum(new int[]{4,5,7,5,0,8,0,0,0}));
        assertEquals(5, accountNumberValidator.calculateChecksum(new int[]{4,5,7,5,0,8,0,0,5}));
        assertEquals(4, accountNumberValidator.calculateChecksum(new int[]{4,5,7,5,0,8,0,0,4}));
        assertEquals(3, accountNumberValidator.calculateChecksum(new int[]{4,5,7,5,0,8,0,0,3}));
    }
}
