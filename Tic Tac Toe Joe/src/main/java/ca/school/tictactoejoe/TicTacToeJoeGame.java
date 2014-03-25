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

    public static int getBoardSize(){
        return SIZE_OF_BOARD;
    }

    public TicTacToeJoeGame(){

        mTicTacToeBoard = new char [SIZE_OF_BOARD];

        for(int i = 0; i < SIZE_OF_BOARD; i++)
            mTicTacToeBoard[i] = EMPTY_SPACE;
        mRandom = new Random();
    }

    public void ClearBoard(){
        for(int i = 0; i < SIZE_OF_BOARD; i++)
            mTicTacToeBoard[i] = EMPTY_SPACE;
    }

    public void setCurMove(char player, int location){
        mTicTacToeBoard[location] = player;
    }

    public int getComputerMove(){
        int move;
        for(int i = 0; i < getBoardSize(); i++){ //Check to see if the computer player can move to win the game
            if(mTicTacToeBoard[i] != PLAYER1 && mTicTacToeBoard[i] != COMPUTER1){
                char current = mTicTacToeBoard[i];
                mTicTacToeBoard[i] = COMPUTER1;
                if(WinnerCheck() == 3){
                    setCurMove(COMPUTER1, i);
                    return i;
                }
                else
                    mTicTacToeBoard[i] = current;
            }
        }
    }
}
