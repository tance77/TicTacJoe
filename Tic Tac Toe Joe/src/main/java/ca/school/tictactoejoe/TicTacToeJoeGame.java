package ca.school.tictactoejoe;

import java.util.Random;

/**
 * Created by lance on 3/24/14.
 */
public class TicTacToeJoeGame {

    private char mTicTacToeBoard[];
    private final static int SIZE_OF_BOARD = 9;

    public static final char PLAYER1 = 'X';
    public static final char COMPUTER1 = 'O';
    public static final char EMPTY_SPACE = ' ';

    private Random mRandom;

    public static int getBoardSize() {
        return SIZE_OF_BOARD;
    }

    public TicTacToeJoeGame() {

        mTicTacToeBoard = new char[SIZE_OF_BOARD];

        for (int i = 0; i < SIZE_OF_BOARD; i++)
            mTicTacToeBoard[i] = EMPTY_SPACE;
        mRandom = new Random();
    }

    public void ClearBoard() {
        for (int i = 0; i < SIZE_OF_BOARD; i++)
            mTicTacToeBoard[i] = EMPTY_SPACE;
    }

    public void setCurMove(char player, int location) {
        mTicTacToeBoard[location] = player;
    }

    public int getComputerMove() {
        int move;
        for (int i = 0; i < getBoardSize(); i++) { //Check to see if the computer player can move to win the game
            if (mTicTacToeBoard[i] != PLAYER1 && mTicTacToeBoard[i] != COMPUTER1) {
                char current = mTicTacToeBoard[i];
                mTicTacToeBoard[i] = COMPUTER1;
                if (WinnerCheck() == 3) {
                    setCurMove(COMPUTER1, i);
                    return i;
                } else
                    mTicTacToeBoard[i] = current;
            }
        }

        for (int i = 0; i < getBoardSize(); i++) { //Check to see if the computer player needs to block a move
            if (mTicTacToeBoard[i] != PLAYER1 && mTicTacToeBoard[i] != COMPUTER1) {
                char current = mTicTacToeBoard[i];
                mTicTacToeBoard[i] = PLAYER1; //if the player were to win at this spot the computer needs to block
                if (WinnerCheck() == 2) {
                    setCurMove(COMPUTER1, i); //if doesn't need to block set back to blank and continue
                    return i;
                } else
                    mTicTacToeBoard[i] = current;
            }
        }
        do { //else the computer can go wherever it wants
            move = mRandom.nextInt(getBoardSize());
        }while (mTicTacToeBoard[move] == PLAYER1 || mTicTacToeBoard[move] == COMPUTER1);
        setCurMove(COMPUTER1, move);
        return move;
    }

    public int WinnerCheck() {
        for (int i = 0; i <= 6; i += 3) {//Horizontal Win Check
            //Check to see if the player has won the game horizontally
            if (mTicTacToeBoard[i] == PLAYER1 &&
                    mTicTacToeBoard[i + 1] == PLAYER1 &&
                    mTicTacToeBoard[i + 2] == PLAYER1) {
                return 2; //PLayer wins on 2
            }
            //Check to see if the computer won the game horizontally
            if (mTicTacToeBoard[i] == COMPUTER1 &&
                    mTicTacToeBoard[i + 1] == COMPUTER1 &&
                    mTicTacToeBoard[i + 2] == COMPUTER1) {
                return 3; //Computer wins on 3
            }
        }
        for (int i = 0; i <= 2; i++) {           //Vertical win check
            //Check to see if the player won the game vertically
            if (mTicTacToeBoard[i] == PLAYER1 &&
                    mTicTacToeBoard[i + 3] == PLAYER1 &&
                    mTicTacToeBoard[i + 6] == PLAYER1) {
                return 2; //Player wins on 2
            }
            //Check to see if the computer won the game vertically
            if (mTicTacToeBoard[i] == COMPUTER1 &&
                    mTicTacToeBoard[i + 3] == COMPUTER1 &&
                    mTicTacToeBoard[i + 6] == COMPUTER1) {
                return 3; //computer wins on 3
            }
        }
        //Checking Diagonal Wins
        if ((mTicTacToeBoard[0] == PLAYER1 &&
                mTicTacToeBoard[4] == PLAYER1 &&
                mTicTacToeBoard[8] == PLAYER1) ||
                (mTicTacToeBoard[2] == PLAYER1 &&
                        mTicTacToeBoard[4] == PLAYER1 &&
                        mTicTacToeBoard[6] == PLAYER1)) {
            return 2; //PLayer wins on 2
        }
        if ((mTicTacToeBoard[0] == COMPUTER1 &&
                mTicTacToeBoard[4] == COMPUTER1 &&
                mTicTacToeBoard[8] == COMPUTER1) ||
                (mTicTacToeBoard[2] == COMPUTER1 &&
                        mTicTacToeBoard[4] == COMPUTER1 &&
                        mTicTacToeBoard[6] == COMPUTER1)) {
            return 3; //Computer wins on 3
        }
        for (int i = 0; i < getBoardSize(); i++) {
            //see if any of the spots are blank
            if (mTicTacToeBoard[i] != PLAYER1 && mTicTacToeBoard[i] != COMPUTER1) {
                return 0; //still a move open return 0
            }
        }
        return 1;
    }
}