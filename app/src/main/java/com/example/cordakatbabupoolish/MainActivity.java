package com.example.cordakatbabupoolish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Random;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {

    EditText[] playerName = new EditText[5];
    Button start;
    String[] name = new String[5];
    int i;
    Boolean NameFill = true;
    TextView fill;
    String[] range = new String[100];
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerName[0] = findViewById(R.id.player1Name);
        playerName[1] = findViewById(R.id.player2Name);
        playerName[2] = findViewById(R.id.player3Name);
        playerName[3] = findViewById(R.id.player4Name);

        spinner = findViewById(R.id.spinnerID);

        start = findViewById(R.id.start);
        fill = findViewById(R.id.fill);

        for(i=0;i<100;i++)
        {
            range[i] = String.valueOf(i+1);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.sample_view,R.id.sampleTextID,range);
        spinner.setAdapter(adapter);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(i=0;i<4;i++)
                {
                    name[i] = playerName[i].getText().toString();
                    if(name[i].length()==0)
                    {
                        NameFill = false;
                        break;
                    }
                }
                //fill.setText(name[0]);
                if(!NameFill)
                {
                    fill.setText("সব খেলোয়াড়ের নাম দিন");
                    NameFill = true;
                }
                else if(NameFill)
                {
                    String value = spinner.getSelectedItem().toString();
                    Intent game = new Intent(MainActivity.this,Game.class);
                    game.putExtra("name",name);
                    game.putExtra("range",value);
                    startActivity(game);
                }
            }
        });

    }
}
