package ca.school.tictactoejoe;

import java.util.Random;

/**
 * Created by lance on 3/24/14.
 * This Java Class lets us get the computers move ands set moves where we go weather or
 * we have a winner or not or a tie
 */
public class TicTacJoeGame {

    private char mTicTacToeBoard[];
    private final static int SIZE_OF_BOARD = 9;

    public static final char PLAYER1 = 'X';
    public static final char COMPUTER1 = 'O';
    public static final char EMPTY_SPACE = ' ';

    public int player_move_count = 0;
    public int computer_move_count = 0;

    private Random mRandom;

    public static int getBoardSize() {
        return SIZE_OF_BOARD;
    }

    public TicTacJoeGame() {

        mTicTacToeBoard = new char[SIZE_OF_BOARD];

        for (int i = 0; i < SIZE_OF_BOARD; i++)
            mTicTacToeBoard[i] = EMPTY_SPACE;
        mRandom = new Random();
    }

    public void ClearBoard() {
        for (int i = 0; i < SIZE_OF_BOARD; i++) {
            mTicTacToeBoard[i] = EMPTY_SPACE;
        }
        player_move_count = 0;
        computer_move_count = 0;

    }

    public void setCurMove(char player, int location) {
        mTicTacToeBoard[location] = player;
        if(player == PLAYER1)
            player_move_count++;
    }

    public int getComputerMove() {
        int move;
        //if the player is using the corner strategy
        if (player_move_count == 1 && computer_move_count == 0 &&
                (mTicTacToeBoard[0] == PLAYER1 ||
                mTicTacToeBoard[2] == PLAYER1 ||
                mTicTacToeBoard[6] == PLAYER1 ||
                mTicTacToeBoard[8] == PLAYER1)
                && (mTicTacToeBoard[4] == EMPTY_SPACE))
        {
            setCurMove(COMPUTER1, 4);
            computer_move_count++;
            return 4;
        }
        //in the player goes in the middle computer must play in corners
        else if(player_move_count == 1 && mTicTacToeBoard[4] == PLAYER1 && computer_move_count == 0){
            if(mTicTacToeBoard[0] != EMPTY_SPACE) //this means it is full with a certain character
            {
                int tmp[] = {2,6,8};
                move = mRandom.nextInt(3);
                move = tmp[move];
                setCurMove(COMPUTER1, move);
                computer_move_count++;
                return move;
            }
            else if(mTicTacToeBoard[2] != EMPTY_SPACE) //this means it is full with a certain character
            {
                int tmp[] = {0,6,8};
                move = mRandom.nextInt(3);
                move = tmp[move];
                setCurMove(COMPUTER1, move);
                computer_move_count++;
                return move;
            }
            else if(mTicTacToeBoard[6] != EMPTY_SPACE) //this means it is full with a certain character
            {
                int tmp[] = {0,2,8};
                move = mRandom.nextInt(3);
                move = tmp[move];
                setCurMove(COMPUTER1, move);
                computer_move_count++;
                return move;
            }
            else if(mTicTacToeBoard[8] != EMPTY_SPACE) //this means it is full with a certain character
            {
                int tmp[] = {0,2,6};
                move = mRandom.nextInt(3);
                move = tmp[move];
                setCurMove(COMPUTER1, move);
                computer_move_count++;
                return move;
            }
            else //none of the above
            {
                int tmp[] = {0,2,6,8};
                move = mRandom.nextInt(4);
                move = tmp[move];
                setCurMove(COMPUTER1, move);
                computer_move_count++;
                return move;
            }

        }
        //if the computer starts on a center tile not the middle piece and then the player plays on the center the computer then needs to play in a corner.
        else if(player_move_count == 1 && mTicTacToeBoard[4] == PLAYER1 && computer_move_count == 1 &&
                (mTicTacToeBoard[1] == COMPUTER1 ||  mTicTacToeBoard[3] == COMPUTER1 || mTicTacToeBoard[5] == COMPUTER1 || mTicTacToeBoard[7] == COMPUTER1)){
            if(mTicTacToeBoard[0] != EMPTY_SPACE) //this means it is full with a certain character
            {
                int tmp[] = {2,6,8};
                move = mRandom.nextInt(3);
                move = tmp[move];
                setCurMove(COMPUTER1, move);
                computer_move_count++;
                return move;
            }
            else if(mTicTacToeBoard[2] != EMPTY_SPACE) //this means it is full with a certain character
            {
                int tmp[] = {0,6,8};
                move = mRandom.nextInt(3);
                move = tmp[move];
                setCurMove(COMPUTER1, move);
                computer_move_count++;
                return move;
            }
            else if(mTicTacToeBoard[6] != EMPTY_SPACE) //this means it is full with a certain character
            {
                int tmp[] = {0,2,8};
                move = mRandom.nextInt(3);
                move = tmp[move];
                setCurMove(COMPUTER1, move);
                computer_move_count++;
                return move;
            }
            else if(mTicTacToeBoard[8] != EMPTY_SPACE) //this means it is full with a certain character
            {
                int tmp[] = {0,2,6};
                move = mRandom.nextInt(3);
                move = tmp[move];
                setCurMove(COMPUTER1, move);
                computer_move_count++;
                return move;
            }
            else //none of the above all center middle pieces are empty
            {
                int tmp[] = {0,2,6,8};
                move = mRandom.nextInt(4);
                move = tmp[move];
                setCurMove(COMPUTER1, move);
                computer_move_count++;
                return move;
            }
        }
        //the player starts in the middle computer plays on the top left and the player plays in bottom right
        //the computer must play in corner 2 or 6
        else if(computer_move_count == 1 &&
                player_move_count == 2 &&
                mTicTacToeBoard[4] == PLAYER1 &&
                mTicTacToeBoard[0] == COMPUTER1 &&
                mTicTacToeBoard[8]== PLAYER1)
        {
            int tmp[] = {2,6};
            move = mRandom.nextInt(2);
            move = tmp[move];
            setCurMove(COMPUTER1, move);
            computer_move_count++;
            return move;
        }
        //the player starts in the middle computer plays in the top right player plays in the bottom left
        //computer must play in corner 0 or 8
        else if(computer_move_count == 1 &&
        player_move_count == 2 &&
        mTicTacToeBoard[4] == PLAYER1 &&
        mTicTacToeBoard[2] == COMPUTER1 &&
        mTicTacToeBoard[6]== PLAYER1)
        {
            int tmp[] = {0,8};
            move = mRandom.nextInt(2);
            move = tmp[move];
            setCurMove(COMPUTER1, move);
            computer_move_count++;
            return move;           
        }
        //player starts middle then computer plays bottom left then player plays top right
        //computer must play in corner 0 or 8
        else if(computer_move_count == 1 &&
        player_move_count == 2 &&
        mTicTacToeBoard[4] == PLAYER1 &&
        mTicTacToeBoard[6] == COMPUTER1 &&
        mTicTacToeBoard[2]== PLAYER1)
        {
            int tmp[] = {0,8};
            move = mRandom.nextInt(2);
            move = tmp[move];
            setCurMove(COMPUTER1, move);
            computer_move_count++;
            return move;           
        }
        //player starts middle, computer plays bottom right, player plays top right
        //computer must play corner 2 or 6
        else if(computer_move_count == 1 &&
        player_move_count == 2 &&
        mTicTacToeBoard[4] == PLAYER1 &&
        mTicTacToeBoard[8] == COMPUTER1 &&
        mTicTacToeBoard[0]== PLAYER1)
        {
            int tmp[] = {2,6};
            move = mRandom.nextInt(2);
            move = tmp[move];
            setCurMove(COMPUTER1, move);
            computer_move_count++;
            return move;           
        }
        //player starts corner then computer goes middle then player goes opposite corner
        else if((computer_move_count == 1 && player_move_count == 2) && (
                (mTicTacToeBoard[0] == PLAYER1 &&
                mTicTacToeBoard[4] == COMPUTER1 &&
                mTicTacToeBoard[8]== PLAYER1) ||
                (mTicTacToeBoard[2] == PLAYER1 &&
                 mTicTacToeBoard[4] == COMPUTER1 &&
                 mTicTacToeBoard[6]== PLAYER1)))
        {
            int tmp[] = {1,3,5,7};
            move = mRandom.nextInt(4);
            move = tmp[move];
            setCurMove(COMPUTER1, move);
            computer_move_count++;
            return move;
        }
        else if((player_move_count == 1 && computer_move_count == 0) &&
                (mTicTacToeBoard[1] == PLAYER1 ||
                mTicTacToeBoard[3] == PLAYER1 ||
                mTicTacToeBoard[5] == PLAYER1 ||
                mTicTacToeBoard[7] == PLAYER1 ))
        {
            int tmp[] = {0,2,6,8};
            move = mRandom.nextInt(4);
            move = tmp[move];
            setCurMove(COMPUTER1, move);
            computer_move_count++;
            return move;
        }
        else {
            for (int i = 0; i < getBoardSize(); i++) { //Check to see if the computer player can move to win the game
                if (mTicTacToeBoard[i] != PLAYER1 && mTicTacToeBoard[i] != COMPUTER1) {
                    char current = mTicTacToeBoard[i];
                    mTicTacToeBoard[i] = COMPUTER1;
                    if (WinnerCheck() == 3) {
                        setCurMove(COMPUTER1, i);
                        computer_move_count++;
                        return i;
                    } else
                        mTicTacToeBoard[i] = current;
                }
            }

            for (int i = 0; i < getBoardSize(); i++) { //Check to see if the computer player needs to block a move
                if (mTicTacToeBoard[i] != PLAYER1 && mTicTacToeBoard[i] != COMPUTER1) {
                    char current = mTicTacToeBoard[i]; //store a " " so we can set it back later
                    mTicTacToeBoard[i] = PLAYER1; //if the player were to win at this spot the computer needs to block
                    if (WinnerCheck() == 2) {
                        setCurMove(COMPUTER1, i);
                        computer_move_count++;
                        return i;
                    } else
                        mTicTacToeBoard[i] = current; //if doesn't need to block set back to blank and continue
                }
            }
            do { //else the computer can go wherever it wants
                move = mRandom.nextInt(getBoardSize());
            } while (mTicTacToeBoard[move] == PLAYER1 || mTicTacToeBoard[move] == COMPUTER1);
            setCurMove(COMPUTER1, move);
            computer_move_count++;
            return move;
        }
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
