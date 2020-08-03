package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0 : red, 1 : yellow;
    TextView textView;
    Button button;

    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int activePlayer = 0;
    boolean gameActive = true;

    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{2,4,6},{0,4,8}};

    public void dropIn(View view) {

        ImageView imageView = (ImageView) view;

        int tappedImage = Integer.parseInt(imageView.getTag().toString());

        if (gameState[tappedImage] == 2 && gameActive) {

            imageView.setTranslationY(-1500);

            gameState[tappedImage] = activePlayer;

            Log.i("Game", imageView.getTag().toString());

            if (activePlayer == 1) {
                imageView.setImageResource(R.drawable.red);
                activePlayer = 0;
            } else {
                imageView.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            }
            imageView.animate().translationYBy(1500).setDuration(300);
        }
        for (int[] position : winningPositions) {
            if (gameState[position[0]] == gameState[position[1]]
                    && gameState[position[1]] == gameState[position[2]]
                    && gameState[position[0]] != 2) {

                String winner = "";

                if (activePlayer == 1) {
                    winner = "Yellow";
                } else {
                    winner = "Red";
                }
                textView.setText(winner + " won");
                textView.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                gameActive = false;
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstraintLayout layout = findViewById(R.id.parentLayout);

                for (int i = 0; i < layout.getChildCount(); i++) {
                    ImageView imageView =(ImageView) layout.getChildAt(i);
                    imageView.setImageDrawable(null);

                    gameState[i] = 2;
                }

                gameActive = true;
                activePlayer = 0; //желтый

                button.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.INVISIBLE);
            }
        });
    }
}
