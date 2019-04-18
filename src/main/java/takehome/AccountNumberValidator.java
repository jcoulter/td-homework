package takehome;

import java.util.Arrays;

public class AccountNumberValidator {

    public static final int EXPECTED_LENGTH = 9;

    public boolean validate(String accountNumber) {
        return isExpectedLength(accountNumber) && isChecksumValid(accountNumber);
    }

    private boolean isChecksumValid(String accountNumber){
        return calculateChecksum(toInts(accountNumber)) == 0;
    }

    private boolean isExpectedLength(String accountNumber){
        return accountNumber.length() == EXPECTED_LENGTH;
    }

    int[] toInts(String numericString) {
        return Arrays.stream(numericString.split("")).mapToInt(Integer::parseInt).toArray();
    }

    int calculateChecksum(int[] ints) {
        int product = 0;
        for (int i = 0; i < ints.length; i++) {
            int multiplier = i + 1;
            product += multiplier * ints[ints.length - multiplier];
        }
        return product % 11;
    }
}
