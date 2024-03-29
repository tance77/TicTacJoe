package ca.school.tictactoejoe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OnePlayer extends Activity {

    private TicTacJoeGame m_Game;

    private Button m_boardButton[];

    private TextView m_info;
    private TextView m_playerCount;
    private TextView m_tieCount;
    private TextView m_computerCount;

    private int m_playerCounter = 0;
    private int m_tieCounter = 0;
    private int m_computerCounter = 0;

    private boolean m_playerFirst = true;
    private boolean m_gameOver = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_player);

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
        m_playerCount = (TextView) findViewById(R.id.player_count);
        m_tieCount = (TextView) findViewById(R.id.tie_count);
        m_computerCount = (TextView) findViewById(R.id.times_joe_kicked_ass);

        m_playerCount.setText(Integer.toString(m_playerCounter));
        m_tieCount.setText(Integer.toString(m_tieCounter));
        m_computerCount.setText(Integer.toString(m_computerCounter));

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
            m_info.setText(R.string.player_goes_first);
            m_playerFirst = false;
        }
        else
        {
            m_info.setText(R.string.computer_turn); //setting the text on the screen probably wont see this because the computer moves so fast
            int computer_move = m_Game.getComputerMove(); //local variable for computers move
            setMove(m_Game.COMPUTER1, computer_move);
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
                    setMove(m_Game.PLAYER1, location);

                    int winner = m_Game.WinnerCheck();

                    if (winner == 0) {
                        m_info.setText(R.string.computer_turn); //this happens so fast we wont see this
                        int move = m_Game.getComputerMove();
                        setMove(m_Game.COMPUTER1, move); //setting the move of the computer
                        winner = m_Game.WinnerCheck();
                    }
                    if (winner == 0) { //game is still going because the computer didnt win above
                        m_info.setText(R.string.player_turn); //displaying its players turn
                    } else if (winner == 1) { //the game was a tie
                        m_info.setText(R.string.tie);//displaying text as tie
                        m_tieCounter++;
                        m_tieCount.setText(Integer.toString(m_tieCounter)); //updating the tie counter on the screen
                        m_gameOver = true;
                        for(int i = 0; i < m_boardButton.length; i++) //loop to set the board up to nothing
                        {
                            m_boardButton[i].setEnabled(false);
                        }
                    } else if (winner == 2) { //the player has won at this point
                        m_info.setText(R.string.player_win);//displaying text as tie
                        m_playerCounter++;
                        m_playerCount.setText(Integer.toString(m_playerCounter)); //updating the tie counter on the screen
                        m_gameOver = true;
                        for(int i = 0; i < m_boardButton.length; i++) //loop to set the board up to nothing
                        {
                            m_boardButton[i].setEnabled(false); //locks the game when it is over
                        }
                    } else { //the computer has won and winner is 3
                        m_info.setText(R.string.computer_win);//displaying text as tie
                        m_computerCounter++;
                        m_computerCount.setText(Integer.toString(m_computerCounter)); //updating the tie counter on the screen
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
                OnePlayer.this.finish();
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
                    m_computerCounter++;
                    m_computerCount.setText(Integer.toString(m_computerCounter));
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
                OnePlayer.this.finish();
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
    public void mainMenuClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Main Menu");
        builder.setMessage("Are You Sure?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                OnePlayer.this.finish();
                Intent intent = new Intent(OnePlayer.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
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