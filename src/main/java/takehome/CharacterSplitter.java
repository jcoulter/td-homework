package takehome;

import java.util.HashMap;

public class CharacterSplitter {
    public static final String[] ZERO = new String[]{
            " _ ",
            "| |",
            "|_|"
    };

    public static final String[] ONE = new String[]{
            "   ",
            "  |",
            "  |"
    };

    public static final String[] TWO = new String[]{
            " _ ",
            " _|",
            "|_ "
    };

    public static final String[] THREE = new String[]{
            " _ ",
            " _|",
            " _|"
    };

    public static final String[] FOUR = new String[]{
            "   ",
            "|_|",
            "  |"
    };

    public static final String[] FIVE = new String[]{
            " _ ",
            "|_ ",
            " _|"
    };

    public static final String[] SIX = new String[]{
            " _ ",
            "|_ ",
            "|_|"
    };

    public static final String[] SEVEN = new String[]{
            " _ ",
            "  |",
            "  |"
    };

    public static final String[] EIGHT = new String[]{
            " _ ",
            "|_|",
            "|_|"
    };

    public static final String[] NINE = new String[]{
            " _ ",
            "|_|",
            " _|"
    };

    //Completely ignore the hard problem.
    //All known error cases could be a key in this map pointing to the correct number until we get to story #4
    private static final HashMap<String[], String> MAPPINGS = new HashMap<String[], String>() {{
        put(ZERO, "0");
        put(ONE, "1");
        put(TWO, "2");
        put(THREE, "3");
        put(FOUR, "4");
        put(FIVE, "5");
        put(SIX, "6");
        put(SEVEN, "7");
        put(EIGHT, "8");
        put(NINE, "9");
    }};


    // todo: 3 lines and 3 chars per digit means that this constant is overused.
    // Those coincidental concerns should not be represented by the same value.
    public static final int EXPECTED_LINES = 3;

    public String[][] splitCharacterData(String[] entry) {
        // todo test cases enforcing called with exactly 3 lines
        String[][] splitRows = new String[EXPECTED_LINES][];

        //every time I write this style of for loop I wish I was writing in a more functional language like ruby
        for (int i = 0; i < EXPECTED_LINES; i++) {
            splitRows[i] = splitPieces(entry[i]);
        }

        return toCharacters(splitRows);
    }

    private String[][] toCharacters(String[][] splitRows) {
        //todo: test cases for empty strings
        String[][] characters = new String[splitRows[0].length][];
        for (int i = 0; i < splitRows[0].length; i++) {
            characters[i] = new String[]{splitRows[0][i], splitRows[1][i], splitRows[2][i]};
        }
        return characters;
    }

    public String[] splitPieces(String input) {
        //todo: enhance readability by introducing a dependency on a library that splits strings
        String[] pieces = input.split("(?<=\\G...)");
        String[] result = new String[pieces.length];

        for (int i = 0; i < pieces.length; i++) {
            result[i] = String.format("%-3s", pieces[i]);
        }
        return result;
    }

    public String toNumericString(String[][] characters) {
        StringBuilder result = new StringBuilder();
        for (String[] character : characters) {
            result.append(MAPPINGS.getOrDefault(character, "?"));
        }
        return result.toString();
    }
}
