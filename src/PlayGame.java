/**
 *
 *   Description of Program’s Functionality: This program demonstrates the different uses of loops,
 *   and its main logic is while-loop. When the program runs, it gets a random word and the user have to
 *   guess the word by guessing the letters that are contained in the word.
 *
 */

import javax.swing.*;
import java.util.ArrayList;

/**
 * These are global variable
 * gameWon
 * gameLoss
 */
public class PlayGame {

    public static int gameWon;
    public static int gameLoss;

    /**
     * @param args
     * This is a local variable
     *      playAgain
     *      aHangman
     *      choice
     *
     * This is the main method, it first set playAgain as true, then create a new domain class referring it to
     * a variable called aHangman. It then calls the method processGuesses() and determineWinner() with the
     * parameter of aHangman. It then displays the secret word and asks the user of they want to play again,
     * and if the answer is anything other than "no," it will loop everything again. Once the user says "no,"
     * the loop will stop and the summarize() method will run.
     *
     */
    public static void main(String[] args) {
        boolean playAgain = true;

        while(playAgain) {
            Hangman aHangman = new Hangman();
            processGuesses(aHangman);
            if(!aHangman.getSecretWord().equalsIgnoreCase(aHangman.getUsersGuess())) determineWinner(aHangman);
            String choice = JOptionPane.showInputDialog(null, "The word was " +
                    aHangman.getSecretWord() + "\n\nDo you want to play again?");

            if(choice.equalsIgnoreCase("no")){
                playAgain = false;
            }
        }

        summarize();
    }

    /**
     * These are local variables
     *      secretWordLength
     *      chances
     *      usedChances
     *      loc
     *      guessed
     *      string1
     *      string2
     *
     * The method first display a screen that shows the user how many letters are in the secret word they
     * have to guess, how many chances they have, the dashed spaces for the word, and an input box for the
     * user to input the letter
     *
     * Once the user inputs a letter, it will assign that letter to guessed, lowercase it,  and check if the letter
     * the user inputted is in the secret word, and it will only take one letter everytime the user input something
     *
     * If the letter the user guessed is in the word, it will concatenate the underscore with the correct letter,
     * and shows the correct guessed letter
     *
     * If the letter the user guessed is not in the word, the chances the user has will minus one
     *
     * And as long as the user still has chances, the screen will continue to pop up with how many letters are in the
     * secret word they have to guess, how many chances they have, the dashed spaces for the word, and an input box
     * for the user to input the letter
     *
     * Once the user guessed all the letters, the method stops
     *
     * @param aHangman this is the only param to the processGuesses() method
     */
    public static void processGuesses(Hangman aHangman) {
        int secretWordLength = aHangman.getSecretWord().length();
        int chances = 2 * secretWordLength;
        int usedChances = 0;
        int loc = -1;
        ArrayList<String> guessedLetter = new ArrayList<>();
        ArrayList<String> guessedWord = new ArrayList<>();

        while(usedChances < chances  && !(aHangman.getSecretWord().equalsIgnoreCase(aHangman.getUsersGuess()))) {

            usedChances++;
            String guessed = JOptionPane.showInputDialog(null,"The word has " +
                    aHangman.getSecretWord().length() + " letters\n"
                    + "You have " + (chances - usedChances) + " chances to guess\n"
                    + aHangman.getUsersGuess()
                    + "\nGuessed Letters: " + guessedLetter
                    + "\nGuessed Words: " + guessedWord
                    + "\nwhat is the letter that you want to guess?");
            guessed = guessed.toLowerCase();

            if(guessed.length() == 1) {
                if (guessedLetter.contains(guessed)) {
                    JOptionPane.showMessageDialog(null, "You already input that answer");
                } else {
                    guessedLetter.add(guessed);
                    do {
                        loc++;
                        loc = aHangman.getSecretWord().indexOf(guessed, loc);

                        if (loc != -1) {
                            String string1 = aHangman.getUsersGuess().substring(0, loc);
                            String string2 = aHangman.getUsersGuess().substring(loc + 1);
                            aHangman.setUsersGuess(string1 + guessed + string2);
                        }
                    } while (loc > -1);
                }
            } else if(guessed.length() == aHangman.getSecretWord().length()
                    && guessed.equalsIgnoreCase(aHangman.getSecretWord()))
            {
                aHangman.setUsersGuess(guessed);
                gameWon++;
            }
            else if(guessed.length() == aHangman.getSecretWord().length()
                    && !guessed.equalsIgnoreCase(aHangman.getSecretWord()))
            {
                usedChances--;
                guessedWord.add(guessed);

            }

            else {
                usedChances--;
            }
        }
    }

    /**
     * This is a local variable
     * finalGuess
     *
     * The method asked the user to guess the full word
     * If the user guessed it right the first time, it will add 1 to the gameWon global variable
     * If the user guessed it wrong the first time, it will give the user one last chance to guess it
     *
     * If the second guess is right, it will add 1 to the gameWon global variable, but if it's wrong, it will
     * add 1 to the gameLoss global variable
     *
     * @param aHangman this is the only param to the determineWinner() method
     */
    public static void determineWinner(Hangman aHangman) {
        for(int i = 0; i < 2; i++) {
            String finalGuess = JOptionPane.showInputDialog(null, "\nGuess the word");

            if(finalGuess.equalsIgnoreCase(aHangman.getSecretWord())) {
                JOptionPane.showMessageDialog(null, "\nCongrats, you guessed the word correctly");
                gameWon++;
                i++;
            } else if(i == 1){
                JOptionPane.showMessageDialog(null, "That's the wrong answer");
                gameLoss++;
            }

            else {
                JOptionPane.showMessageDialog(null, "you have 1 more chance");
            }
        }
    }

    /**
     * This method displays how many word the user had guessed right and wrong while playing the game
     *
     */
    public static void summarize() {
        if(gameWon > 0) {
            JOptionPane.showMessageDialog(null, "You have guessed " + gameWon + " word(s) correctly");
        }

        if(gameLoss > 0) {
            JOptionPane.showMessageDialog(null, "You have guessed " + gameLoss + " word(s) incorrectly");
        }
    }
}
