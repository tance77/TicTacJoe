package ca.school.tictactoejoe;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

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
        setContentView(R.layout.activity_main);

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
    }

    private void NewGame()
    {
        
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
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
