package com.example.cordakatbabupoolish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Game extends AppCompatActivity {

    TextView player[] = new TextView[5];
    Button[] PlayerName = new Button[5];
    Button check, check_cordakat, refresh;
    int a[] = new int[5];
    String s[] = new String[5];
    int i;
    String str;
    boolean set = true;
    String[] name = new String[5];
    boolean next = false;
    TextView cor_dakat_pala;
    String value;
    int range,k=0;
    int[] score = new int[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        player[0] = findViewById(R.id.player1ID);
        player[1] = findViewById(R.id.player2ID);
        player[2] = findViewById(R.id.player3ID);
        player[3] = findViewById(R.id.player4ID);

        PlayerName[0] = findViewById(R.id.player1Button);
        PlayerName[1] = findViewById(R.id.player2Button);
        PlayerName[2] = findViewById(R.id.player3Button);
        PlayerName[3] = findViewById(R.id.player4Button);

        cor_dakat_pala = findViewById(R.id.cor_dakat_pala);

        check = findViewById(R.id.check);
        check_cordakat = findViewById(R.id.check_cordakat);
        refresh = findViewById(R.id.refresh);

        Bundle all = getIntent().getExtras();
        name = all.getStringArray("name");
        value = all.getString("range");
        range = Integer.parseInt(value);

        for(i=0;i<4;i++)
        {
            PlayerName[i].setText(name[i]);
        }

        cor_dakat_pala.setText("চোরের পালা");


        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(set)
                {
                    set = false;
                    for(i=0;i<4;i++)
                    {
                        a[i] = 50;
                    }

                    Random rand = new Random();

                    a[0] = rand.nextInt(4)+1;
                    s[0] = String.valueOf(a[0]);
                    a[1] = rand.nextInt(4)+1;
                    s[1] = String.valueOf(a[1]);
                    a[2] = rand.nextInt(4)+1;
                    s[2] = String.valueOf(a[2]);
                    a[3] = rand.nextInt(4)+1;
                    s[3] = String.valueOf(a[3]);

                    while(a[1] == a[0])
                    {
                        a[1] = rand.nextInt(4)+1;
                        s[1] = String.valueOf(a[1]);
                    }

                    while(a[2] == a[0] || a[2] == a[1])
                    {
                        a[2] = rand.nextInt(4)+1;
                        s[2] = String.valueOf(a[2]);
                    }

                    while(a[3] == a[0] || a[3] == a[1] || a[3] == a[2])
                    {
                        a[3] = rand.nextInt(4)+1;
                        s[3] = String.valueOf(a[3]);
                    }

                    for(i=0;i<4;i++)
                    {
                        if(s[i].equals("3"))
                        {
                            s[i] = "বাবু";
                            player[i].setText(s[i]);
                            score[i] = score[i] + 1000;
                        }
                        if(s[i].equals("4"))
                        {
                            s[i] = "পুলিশ";
                            player[i].setText(s[i]);
                        }
                    }

                    check_cordakat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for(i=0;i<4;i++)
                            {
                                if(s[i].equals("1"))
                                {
                                    s[i] = "চোর";
                                    player[i].setText(s[i]);
                                }
                                if(s[i].equals("2"))
                                {
                                    s[i] = "ডাকাত";
                                    player[i].setText(s[i]);
                                }
                            }
                            next = true;
                        }
                    });
                    k++;
                    refresh.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(next && k<range)
                            {
                                for(i=0;i<4;i++)
                                {
                                    player[i].setText("???");
                                }
                                set = true;
                                if((k+1)%2 == 1)
                                {
                                    cor_dakat_pala.setText("চোরের পালা");
                                }
                                else
                                {
                                    cor_dakat_pala.setText("ডাকাতের পালা");
                                }
                                next = false;
                            }
                            else if(next && k==range)
                            {
                                //Toast.makeText(Game.this,"EndGame",Toast.LENGTH_LONG).show();
                                Intent result = new Intent(Game.this, result.class);

                                startActivity(result);
                            }

                        }
                    });
                }
                if(k == range && !next)
                {
                    refresh.setText("ফলাফল");
                }
            }
        });

    }
}
