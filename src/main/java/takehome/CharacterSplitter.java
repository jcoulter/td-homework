package takehome;

public class CharacterSplitter {

    // 3 lines and 3 chars per digit means that this constant is overused.
    // Those coincidental concerns should not be represented by the same value.
    public static final int EXPECTED_LINES = 3;

    public String[][] splitCharacters(String[] entry) {
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
}
