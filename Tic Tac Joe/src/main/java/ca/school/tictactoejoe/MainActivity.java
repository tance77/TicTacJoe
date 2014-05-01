package ca.school.tictactoejoe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements View.OnClickListener {

    public Button m_onePlayer;
    public Button m_twoPlayer;
    public Button m_about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_onePlayer=(Button)findViewById(R.id.onePlayer);
        m_onePlayer.setOnClickListener(this);
        m_twoPlayer=(Button)findViewById(R.id.twoPlayer);
        m_twoPlayer.setOnClickListener(this);
        m_about=(Button)findViewById(R.id.about);
        m_about.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onClick(View v) {

        // One Player button was pressed
        if (v.getId()== R.id.onePlayer) {
            Intent i = new Intent(this, OnePlayer.class);
            // start the activity based on the Intent
            startActivity(i);
            finish();
        }
        // Two Player button was pressed
        else if (v.getId() == R.id.twoPlayer) {
            Intent i = new Intent(this, TwoPlayer.class);
            // start the activity based on the Intent
            startActivity(i);
            finish();
        }
        // About button was pressed
        else if (v.getId() == R.id.about) {
            Intent i = new Intent(this, About.class);
            // start the activity based on the Intent
            startActivity(i);
            finish();
        }
    }

}