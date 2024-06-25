package com.alone.ttt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PlayerSetup extends AppCompatActivity {


    private EditText p1;
    private EditText p2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_setup);


        p2=findViewById(R.id.editTextText2);
        p1=findViewById(R.id.editTextText);
    }

    public  void submitButtonClick(View v){
        String p1N=p1.getText().toString();
        String p2N=p2.getText().toString();

        Intent i=new Intent(this, GameDisplay.class);
        i.putExtra("Player_Name",new String[] {p1N,p2N});
        startActivity(i);

    }
}