package ca.school.tictactoejoe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TwoPlayer extends Activity {

    private TicTacJoeGame m_Game;

    private Button m_boardButton[];

    private TextView m_info;
    private TextView m_player1Count;
    private TextView m_tieCount;
    private TextView m_player2Count;

    private int m_player1Counter = 0;
    private int m_tieCounter = 0;
    private int m_player2Counter = 0;

    private boolean m_playerFirst = true;
    private boolean m_gameOver = false;
    private boolean m_first_player_turn = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player);

        m_boardButton = new Button[m_Game.getBoardSize()];
        m_boardButton[0] = (Button) findViewById(R.id.one);
        m_boardButton[1] = (Button) findViewById(R.id.two);
        m_boardButton[2] = (Button) findViewById(R.id.three);
        m_boardButton[3] = (Button) findViewById(R.id.four);
        m_boardButton[4] = (Button) findViewById(R.id.five);
        m_boardButton[5] = (Button) findViewById(R.id.six);
        m_boardButton[6] = (Button) findViewById(R.id.seven);
        m_boardButton[7] = (Button) findViewById(R.id.eight);
        m_boardButton[8] = (Button) findViewById(R.id.nine);

        m_info = (TextView) findViewById(R.id.information);
        m_player1Count = (TextView) findViewById(R.id.player_count);
        m_tieCount = (TextView) findViewById(R.id.tie_count);
        m_player2Count = (TextView) findViewById(R.id.times_joe_kicked_ass);

        m_player1Count.setText(Integer.toString(m_player1Counter));
        m_tieCount.setText(Integer.toString(m_tieCounter));
        m_player2Count.setText(Integer.toString(m_player2Counter));

        m_Game = new TicTacJoeGame();
        NewGame(); //actually start the game here
    }
    private void NewGame() //starting up a new game and clearing the board
    {
        m_Game.ClearBoard();

        for(int i = 0; i < m_boardButton.length; i++) //loop to set the board up to nothing
        {
            m_boardButton[i].setText("");
            m_boardButton[i].setEnabled(true);
            m_boardButton[i].setOnClickListener(new ButtonClickListener(i));
        }

        if(m_playerFirst)
        {
            m_info.setTextColor(Color.rgb(173,216,230));
            m_info.setText(R.string.player_1);
            m_playerFirst = false;
        }
        else
        {
            m_info.setTextColor(Color.rgb(196,116,81));
            m_info.setText(R.string.player_2);
            m_playerFirst = true; //so when new games happens player goes first
        }
    }
    private class ButtonClickListener implements View.OnClickListener
    {
        int location;
        public ButtonClickListener(int location)
        {
            this.location = location;
        }

        //check to see if the game is over pretty much
        @Override
        public void onClick(View view) {
            if(!m_gameOver) {
                //run this if the game isnt over and the button is not disabled
                if (m_boardButton[location].isEnabled()) {
                    if(m_first_player_turn){
                        setMove(m_Game.PLAYER1, location);
                        m_first_player_turn = false;
                        m_info.setText(R.string.player_2);
                        m_info.setTextColor(Color.rgb(196,116,81));

                    }
                    else {
                        setMove(m_Game.COMPUTER1, location);
                        m_first_player_turn = true;
                        m_info.setText(R.string.player_1);
                        m_info.setTextColor(Color.rgb(173,216,230));
                    }
                    int winner = m_Game.WinnerCheck();

                    if (winner == 1) { //the game was a tie
                        m_info.setText(R.string.tie);//displaying text as tie
                        m_tieCounter++;
                        m_tieCount.setText(Integer.toString(m_tieCounter)); //updating the tie counter on the screen
                        m_info.setTextColor(Color.rgb(128,0,0));
                        m_gameOver = true;
                        for(int i = 0; i < m_boardButton.length; i++) //loop to set the board up to nothing
                        {
                            m_boardButton[i].setEnabled(false);
                        }
                    } else if (winner == 2) { //the player has won at this point
                        m_info.setText(R.string.player_1_win);
                        m_player1Counter++;
                        m_player1Count.setText(Integer.toString(m_player1Counter));
                        m_info.setTextColor(Color.rgb(173,216,230));
                        m_gameOver = true;
                        for(int i = 0; i < m_boardButton.length; i++) //loop to set the board up to nothing
                        {
                            m_boardButton[i].setEnabled(false); //locks the game when it is over
                        }
                    } else if (winner == 3){ //the player 2 has won and winner is 3
                        m_info.setText(R.string.player_2_win);//displaying text as tie
                        m_player2Counter++;
                        m_player2Count.setText(Integer.toString(m_player2Counter));
                        m_info.setTextColor(Color.rgb(196,116,81));
                        m_gameOver = true;
                        for(int i = 0; i < m_boardButton.length; i++) //loop to set the board up to nothing
                        {
                            m_boardButton[i].setEnabled(false);
                        }
                    }
                }
            }
        }
    }
    private void setMove(char player, int location) //this set move calls tictacjoegame's setmove
    {
        m_Game.setCurMove(player, location);
        m_boardButton[location].setEnabled(false); //so the player can not play in that spot
        m_boardButton[location].setText(String.valueOf(player)); //x or o depending on who played
        if(player == m_Game.PLAYER1){ //Giving the computer and human some color of their moves
            m_boardButton[location].setTextColor(Color.rgb(173,216,230)); //player is green
        }
        else { //computer is red
            m_boardButton[location].setTextColor(Color.rgb(196,116,81));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId())
        {
            case R.id.new_game:
                NewGame();
                m_gameOver = false;
                break;
            case R.id.exit_game:
                TwoPlayer.this.finish();
                break;
            case R.id.action_settings:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void newGameClick(View view) {
        if (!m_gameOver) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Start a new Game");
            builder.setMessage("Give up?");

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    if(m_first_player_turn) {
                        m_player2Counter++;
                        m_player2Count.setText(Integer.toString(m_player2Counter));
                        m_first_player_turn = false;
                        m_info.setTextColor(Color.rgb(196,116,81));
                    }
                    else{
                        m_player1Counter++;
                        m_player1Count.setText(Integer.toString(m_player1Counter));
                        m_info.setTextColor(Color.rgb(173,216,230));
                        m_first_player_turn = true;
                    }
                    NewGame();
                    m_gameOver = false;
                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        } else {
            NewGame();
            m_gameOver = false;
        }
    }
    public void exitGameClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit");
        builder.setMessage("Are You Sure?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                TwoPlayer.this.finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}