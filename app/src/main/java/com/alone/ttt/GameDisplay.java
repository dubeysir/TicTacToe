package com.alone.ttt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameDisplay extends AppCompatActivity {


    private TTTboard tttb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_display);

        Button playAgainBTN=findViewById(R.id.button3);
        Button homeBTN=findViewById(R.id.button4);
        TextView playerTurn=findViewById(R.id.textView5);


        playAgainBTN.setVisibility(View.GONE);
        homeBTN.setVisibility(View.GONE);

        String[] playerNames=getIntent().getStringArrayExtra("PLAYER NAME");


        if(playerNames!=null){
            playerTurn.setText((playerNames[0]+"'s Turn"));
        }


        tttb=findViewById(R.id.TTTboard);

        tttb.setUpGame(playAgainBTN,homeBTN,playerTurn,playerNames);
    }

    public void playAgainButtonClick(View v){
   tttb.resetGame();
   tttb.invalidate();
    }
    public void homeButtonClick(View v){
Intent i=new Intent(this, MainActivity.class);
startActivity(i);
    }
}