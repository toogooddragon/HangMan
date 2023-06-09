import java.util.Random;

/**
 * These are private variable
 * secretWord
 * userGuess
 *
 */
public class Hangman {
    private String secretWord;
    private String usersGuess;

    /**
     * These are local variables
     * number
     * numLetters
     *
     * This is a constructor for the Hangman class
     *
     * When called, it chooses a random number between 0 and 6. It will then assign the secret word to whatever word
     * is in the switch statement based on the randomized number, and then output how many underscore based on the
     * length of the assigned word as userGuess
     *
     */
    public Hangman() {
            Random random = new Random();
            int number = random.nextInt(6);

            switch (number){
                case 1 ->
                    this.secretWord = "swill";

                case 2 ->
                    this.secretWord = "bloke";

                case 3 ->
                    this.secretWord = "place";

                case 4 ->
                    this.secretWord = "whether";

                case 5 ->
                    this.secretWord = "compensate";

                case 6 ->
                    this.secretWord = "condescending";

                case 7 ->
                    this.secretWord = "demented";

                case 8 ->
                    this.secretWord = "vicissitude";

                case 9 ->
                    this.secretWord = "accretion";

                case 10 ->
                    this.secretWord = "avarice ";

                case 11 ->
                    this.secretWord = "predatory";

                default ->
                    this.secretWord = "letter";
            }
            int numLetters = secretWord.length();

            usersGuess = "";

            for(int i = 0; i < numLetters; i++)
            {
                usersGuess += "_";
            }

        }

    /**
     * setter method
     * @param secretWord
     */
    public void setSecretWord(String secretWord) {
        this.secretWord = secretWord;
    }

    /**
     * setter method
     * @param usersGuess
     */
    public void setUsersGuess(String usersGuess) {
        this.usersGuess = usersGuess;
    }

    /**
     * getter method
     */
    public String getSecretWord() {
        return secretWord;
    }

    /**
     * getter method
     */
    public String getUsersGuess() {
        return usersGuess;
    }

    /**
     * toString method
     */
    public String toString() {
            return usersGuess;
    }
}
