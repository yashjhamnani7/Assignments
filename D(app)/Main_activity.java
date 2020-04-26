package com.example.yashjhamnani.tictactoe;





import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.AppCompatDialog;


public class MainActivity extends AppCompatActivity {

    private Button[][] buttons = new Button[3][3];

    private boolean player1Turn = true;

    private int roundCount;
TextView tv3;
int counter=1;
    private int player1Points;
    private int player2Points;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    MediaPlayer ring1;

    int c;
    TextView tv4;
    //EditText user1;
     //EditText user2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
  //   alert a=new alert();
    // Dialog s=a.onCreateDialog();

       // user1=(EditText)findViewById(R.id.user1);
       c=1;
        //user2= (EditText)findViewById(R.id.user2);
        textViewPlayer1 = findViewById(R.id.tv1);
        textViewPlayer2 = findViewById(R.id.tv2);
        ring1= MediaPlayer.create(MainActivity.this,R.raw.dor);
        //ring2= MediaPlayer.create(MainActivity.this,R.raw.beep);
        tv3=(TextView)findViewById(R.id.display);
        tv3.setText("Nobita's turn");
        tv4=(TextView)findViewById(R.id.round);
        ring1.start();



        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                final int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(View v) {
                        if (!((Button) v).getText().toString().equals(""))
                        {
                            return;
                        }





                        if (player1Turn) {
                            ((Button) v).setBackgroundResource(R.drawable.nobita);
                            ((Button) v).setText("X");
                            //((Button) v).setTextColor(R.drawable.yello);

                            tv3.setText("Shinchan's turn");


                        } else {
                            ((Button) v).setBackgroundResource(R.drawable.sin);
                            ((Button )v).setText("O");
                            //((Button) v).setTextColor(R.drawable.red);

                            tv3.setText("Nobita's turn");
                        }

                        roundCount++;

                        if (checkForWin()) {
                            if (player1Turn) {
                                player1Wins();
                            } else {
                                player2Wins();
                            }
                        } else if (roundCount == 9) {
                            draw();
                        } else {
                            player1Turn = !player1Turn;
                        }

                    }
                });
            }
        }


        Button buttonReset = findViewById(R.id.btnreset);


        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Are you sure you want to reset?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                resetGame();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });
    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void player1Wins() {
        player1Points++;
        Toast.makeText(this, "Congratz Nobita won!", Toast.LENGTH_SHORT).show();
        ring1.pause();

        CountDownTimer cntr_aCounter = new CountDownTimer(1500, 1500) {
            MediaPlayer ring = MediaPlayer.create(MainActivity.this, R.raw.noblaugh);


            @Override
            public void onTick(long millisUntilFinished) {
                ring.start();
            }

            public void onFinish() {
                //code fire after finish
                ring.stop();
            }

        };cntr_aCounter.start();

        updatePointsText();
        resetBoard();

    }




    private void player2Wins() {
        player2Points++;
        Toast.makeText(this, "Congratz Shinchan won!", Toast.LENGTH_SHORT).show();
       ring1.pause();

        CountDownTimer cntr_aCounter = new CountDownTimer(1500, 1500) {
            MediaPlayer ring = MediaPlayer.create(MainActivity.this, R.raw.laugh);


            @Override
            public void onTick(long millisUntilFinished) {
                ring.start();
            }

            public void onFinish() {
                //code fire after finish
                ring.stop();
            }

        };cntr_aCounter.start();

        updatePointsText();
        resetBoard();

    }

    private void draw() {
        Toast.makeText(this, "Oh No! It's a draw", Toast.LENGTH_SHORT).show();
        ring1.pause();

        CountDownTimer cntr_aCounter = new CountDownTimer(1000, 1000) {
            MediaPlayer ring = MediaPlayer.create(MainActivity.this, R.raw.osin);


            @Override
            public void onTick(long millisUntilFinished) {
                ring.start();
            }

            public void onFinish() {
                //code fire after finish
                ring.stop();
            }

        };cntr_aCounter.start();

        resetBoard();
    }

    private void updatePointsText() {
        textViewPlayer1.setText("Nobita :" + player1Points);
        textViewPlayer2.setText("Shinchan :" + player2Points);
    }

    private void resetBoard() {
        c++;
        counter++;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setBackgroundResource(R.drawable.ffe4e1);
            }
        }
        tv4.setText("Round :"+counter);
        if(c%2==0)
        {
            ring1.stop();
            ring1= MediaPlayer.create(MainActivity.this,R.raw.sintune);
            ring1.seekTo(0);
            ring1.start();
        }
        else
        {
            ring1.stop();
            ring1= MediaPlayer.create(MainActivity.this,R.raw.dor);
            ring1.seekTo(0);
            ring1.start();
        }


        roundCount = 0;
        player1Turn = true;
        tv3.setText("Nobita's turn");

    }

    private void resetGame() {
        player1Points = 0;
        player2Points = 0;
        tv3.setText("Nobita's turn");
        tv4.setText("Round :1");
        counter=0;
        updatePointsText();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", roundCount);
        outState.putInt("player1Points", player1Points);
        outState.putInt("player2Points", player2Points);
        outState.putBoolean("player1Turn", player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        player1Turn = savedInstanceState.getBoolean("player1Turn");

    }
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ring1.stop();
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
    protected void onPause() {
        super.onPause();

        if (ring1 != null){
            ring1.pause();
            //if (isFinishing()){

              //  ring1.release();
            }

        }

    @Override
    protected void onResume() {
        super.onResume();
        ring1.start();
    }


    @Override
    public void enforceCallingOrSelfPermission(String permission, String message) {
        super.enforceCallingOrSelfPermission(permission, message);
        ring1.pause();
    }
}


