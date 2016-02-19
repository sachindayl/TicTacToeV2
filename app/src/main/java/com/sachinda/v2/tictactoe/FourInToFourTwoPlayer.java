package com.sachinda.v2.tictactoe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;

public class FourInToFourTwoPlayer extends Activity implements View.OnClickListener {

    //buttons initialized
    Button one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve, thirteen, fourteen, fifteen, sixteen;

    //to check if there are buttons that has not been clicked
    boolean oneChk = false;
    boolean twoChk = false;
    boolean threeChk = false;
    boolean fourChk = false;
    boolean fiveChk = false;
    boolean sixChk = false;
    boolean sevenChk = false;
    boolean eightChk = false;
    boolean nineChk = false;
    boolean tenChk = false;
    boolean elevenChk = false;
    boolean twelveChk = false;
    boolean thirteenChk = false;
    boolean fourteenChk = false;
    boolean fifteenChk = false;
    boolean sixteenChk = false;

    //counter to recognize which opponent
    int counter;

    String cross = "Cross";
    String circle = "Circle";
    AlertDialog.Builder alert;
    AlertDialog dialog;
    Intent intent, end;
    Thread th;

    ImageView startGame, p1, p2, p1Wins, p2Wins, draw;

    AlphaAnimation startTheGame, p1turn, p2turn, p1winner, p2winner, itsDraw;

    //for understanding if cross or circle
    Boolean isCircle;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        int style = ((GlobalVariable) this.getApplication()).getStyle();
        if (style == 1) {
            setContentView(R.layout.activity_four_in_to_four_two_player);
        } else if (style == 2) {
            setContentView(R.layout.activity_four_in_to_four_two_player_fairy);
        }

        intent = getIntent();
        end = new Intent(FourInToFourTwoPlayer.this, BoardType.class);

        one = (Button) findViewById(R.id.empty_button);
        two = (Button) findViewById(R.id.empty_button2);
        three = (Button) findViewById(R.id.empty_button3);
        four = (Button) findViewById(R.id.empty_button4);
        five = (Button) findViewById(R.id.empty_button5);
        six = (Button) findViewById(R.id.empty_button6);
        seven = (Button) findViewById(R.id.empty_button7);
        eight = (Button) findViewById(R.id.empty_button8);
        nine = (Button) findViewById(R.id.empty_button9);
        ten = (Button) findViewById(R.id.empty_button10);
        eleven = (Button) findViewById(R.id.empty_button11);
        twelve = (Button) findViewById(R.id.empty_button12);
        thirteen = (Button) findViewById(R.id.empty_button13);
        fourteen = (Button) findViewById(R.id.empty_button14);
        fifteen = (Button) findViewById(R.id.empty_button15);
        sixteen = (Button) findViewById(R.id.empty_button16);

        startGame = (ImageView) findViewById(R.id.start_the_game);
        p1 = (ImageView) findViewById(R.id.player_one);
        p2 = (ImageView) findViewById(R.id.player_two);
        p1Wins = (ImageView) findViewById(R.id.player_one_wins);
        p2Wins = (ImageView) findViewById(R.id.player_two_wins);
        draw = (ImageView) findViewById(R.id.its_draw);

        startTheGame = new AlphaAnimation(1.0f, 0.0f);
        p1turn = new AlphaAnimation(1.0f, 0.0f);
        p2turn = new AlphaAnimation(1.0f, 0.0f);
        p1winner = new AlphaAnimation(1.0f, 0.0f);
        p2winner = new AlphaAnimation(1.0f, 0.0f);
        itsDraw = new AlphaAnimation(1.0f, 0.0f);

        counter = 0;

        one.setOnClickListener(this);
        one.setEnabled(true);
        two.setOnClickListener(this);
        two.setEnabled(true);
        three.setOnClickListener(this);
        three.setEnabled(true);
        four.setOnClickListener(this);
        four.setEnabled(true);
        five.setOnClickListener(this);
        five.setEnabled(true);
        six.setOnClickListener(this);
        six.setEnabled(true);
        seven.setOnClickListener(this);
        seven.setEnabled(true);
        eight.setOnClickListener(this);
        eight.setEnabled(true);
        nine.setOnClickListener(this);
        nine.setEnabled(true);
        ten.setOnClickListener(this);
        ten.setEnabled(true);
        eleven.setOnClickListener(this);
        eleven.setEnabled(true);
        twelve.setOnClickListener(this);
        twelve.setEnabled(true);
        thirteen.setOnClickListener(this);
        thirteen.setEnabled(true);
        fourteen.setOnClickListener(this);
        fourteen.setEnabled(true);
        fifteen.setOnClickListener(this);
        fifteen.setEnabled(true);
        sixteen.setOnClickListener(this);
        sixteen.setEnabled(true);


        animationSetup();
    }

    //delays the replay or quit dialog box
    public void delayDialog() {
        th = new Thread() {
            public void run() {
                try {
                    Thread.sleep(2500);
                    FourInToFourTwoPlayer.this.runOnUiThread(new Runnable() {
                        public void run() {
                            alert = new AlertDialog.Builder(FourInToFourTwoPlayer.this);
                            alert.setMessage("Do you want to start a new game?")
                                    .setCancelable(false)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                            startActivity(intent);

                                        }
                                    })
                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                            finish();
                                            startActivity(end);

                                        }
                                    });

                            dialog = alert.show();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        th.start();
    }

    //shows what happens after button click
    @Override
    public void onClick(View v) {
        //using style to recognize which theme is being used
        int style = ((GlobalVariable) this.getApplication()).getStyle();
        switch (v.getId()) {
            case R.id.empty_button:

                if (one.isEnabled()) {
                    oneChk = true;
                    if (counter == 0) {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.redsquarecircle);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bsbluesquarecircle);
                        }
                        counter++;
                        v.setTag(circle);
                        isCircle = true;
                        //checks for 1,2,3,4 pattern
                        if (v.getTag().equals(two.getTag())
                                && v.getTag().equals(three.getTag()) && v.getTag().equals(four.getTag())) {

                            buttonColorChangerAfterWin(one, two, three, four, style, isCircle);

                            //locks all buttons after there is a winner
                            disableAllButtons();
                            delayDialog();

                            //checks for 1,5,9,13 pattern
                        } else if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(nine.getTag()) && v.getTag().equals(thirteen.getTag())) {

                            buttonColorChangerAfterWin(one, five, nine, thirteen, style, isCircle);

                            disableAllButtons();

                            delayDialog();
                            //checks for 1,6,11,16 pattern
                        } else if (v.getTag().equals(six.getTag())
                                && v.getTag().equals(eleven.getTag()) && v.getTag().equals(sixteen.getTag())) {

                            buttonColorChangerAfterWin(one, six, eleven, sixteen, style, isCircle);

                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk || !tenChk || !elevenChk
                                    || !twelveChk || !thirteenChk || !fourteenChk
                                    || !fifteenChk || !sixteenChk) {
                                p2.setAnimation(p2turn);
                            } else {
                                disableAllButtons();

                                draw.startAnimation(itsDraw);
                                delayDialog();
                            }

                        }

                    } else {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.redsquarecross);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bsbluesquarecross);
                        }
                        counter--;
                        v.setTag(cross);
                        isCircle = false;
                        if (v.getTag().equals(two.getTag())
                                && v.getTag().equals(three.getTag()) && v.getTag().equals(four.getTag())) {

                            buttonColorChangerAfterWin(one, two, three, four, style, isCircle);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(nine.getTag()) && v.getTag().equals(thirteen.getTag())) {

                            buttonColorChangerAfterWin(one, five, nine, thirteen, style, isCircle);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(six.getTag())
                                && v.getTag().equals(eleven.getTag()) && v.getTag().equals(sixteen.getTag())) {

                            buttonColorChangerAfterWin(one, six, eleven, sixteen, style, isCircle);

                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk || !tenChk || !elevenChk
                                    || !twelveChk || !thirteenChk || !fourteenChk
                                    || !fifteenChk || !sixteenChk) {
                                p1.startAnimation(p1turn);
                            } else {
                                disableAllButtons();

                                draw.startAnimation(itsDraw);
                                delayDialog();
                            }

                        }

                    }

                    one.setEnabled(false);
                }
                break;

            case R.id.empty_button2:

                if (two.isEnabled()) {
                    twoChk = true;
                    if (counter == 0) {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.blacksquarecircle);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bswhitesquarecircle);
                        }
                        counter++;
                        v.setTag(circle);
                        isCircle = true;
                        if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(three.getTag()) && v.getTag().equals(four.getTag())) {

                            buttonColorChangerAfterWin(one, two, three, four, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(six.getTag())
                                && v.getTag().equals(ten.getTag()) && v.getTag().equals(fourteen.getTag())) {
                            buttonColorChangerAfterWin(two, six, ten, fourteen, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk || !tenChk || !elevenChk
                                    || !twelveChk || !thirteenChk || !fourteenChk
                                    || !fifteenChk || !sixteenChk) {
                                p2.startAnimation(p2turn);
                            } else {
                                disableAllButtons();

                                draw.startAnimation(itsDraw);
                                delayDialog();
                            }

                        }

                    } else {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.blacksquarecross);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bswhitesquarecross);
                        }
                        counter--;
                        v.setTag(cross);
                        isCircle = false;
                        if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(three.getTag()) && v.getTag().equals(four.getTag())) {

                            buttonColorChangerAfterWin(one, two, three, four, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(six.getTag())
                                && v.getTag().equals(ten.getTag()) && v.getTag().equals(fourteen.getTag())) {

                            buttonColorChangerAfterWin(two, six, ten, fourteen, style, isCircle);

                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk || !tenChk || !elevenChk
                                    || !twelveChk || !thirteenChk || !fourteenChk
                                    || !fifteenChk || !sixteenChk) {
                                p1.startAnimation(p1turn);
                            } else {
                                disableAllButtons();

                                draw.startAnimation(itsDraw);
                                delayDialog();
                            }

                        }
                    }

                    two.setEnabled(false);

                }
                break;

            case R.id.empty_button3:

                if (three.isEnabled()) {
                    threeChk = true;
                    if (counter == 0) {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.redsquarecircle);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bsbluesquarecircle);
                        }
                        counter++;
                        v.setTag(circle);
                        isCircle = true;

                        if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(two.getTag()) && v.getTag().equals(four.getTag())) {
                            buttonColorChangerAfterWin(one, two, three, four, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(seven.getTag())
                                && v.getTag().equals(eleven.getTag()) && v.getTag().equals(fifteen.getTag())) {
                            buttonColorChangerAfterWin(three, seven, eleven, fifteen, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk || !tenChk || !elevenChk
                                    || !twelveChk || !thirteenChk || !fourteenChk
                                    || !fifteenChk || !sixteenChk) {
                                p2.startAnimation(p2turn);
                            } else {
                                disableAllButtons();

                                draw.startAnimation(itsDraw);
                                delayDialog();
                            }

                        }
                    } else {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.redsquarecross);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bsbluesquarecross);
                        }
                        counter--;
                        v.setTag(cross);
                        isCircle = false;
                        if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(two.getTag()) && v.getTag().equals(four.getTag())) {
                            buttonColorChangerAfterWin(one, two, three, four, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(seven.getTag())
                                && v.getTag().equals(eleven.getTag()) && v.getTag().equals(fifteen.getTag())) {
                            buttonColorChangerAfterWin(three, seven, eleven, fifteen, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk || !tenChk || !elevenChk
                                    || !twelveChk || !thirteenChk || !fourteenChk
                                    || !fifteenChk || !sixteenChk) {
                                p1.startAnimation(p1turn);
                            } else {
                                disableAllButtons();

                                draw.startAnimation(itsDraw);
                                delayDialog();
                            }

                        }

                    }

                    three.setEnabled(false);

                }
                break;

            case R.id.empty_button4:
                if (four.isEnabled()) {
                    fourChk = true;
                    if (counter == 0) {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.blacksquarecircle);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bswhitesquarecircle);
                        }
                        counter++;
                        v.setTag(circle);
                        isCircle = true;
                        if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(two.getTag()) && v.getTag().equals(three.getTag())) {
                            buttonColorChangerAfterWin(one, two, three, four, style, isCircle);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(seven.getTag())
                                && v.getTag().equals(ten.getTag()) && v.getTag().equals(thirteen.getTag())) {
                            buttonColorChangerAfterWin(four, seven, ten, thirteen, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(eight.getTag())
                                && v.getTag().equals(twelve.getTag()) && v.getTag().equals(sixteen.getTag())) {
                            buttonColorChangerAfterWin(four, seven, ten, thirteen, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk || !tenChk || !elevenChk
                                    || !twelveChk || !thirteenChk || !fourteenChk
                                    || !fifteenChk || !sixteenChk) {
                                p2.startAnimation(p2turn);
                            } else {
                                disableAllButtons();

                                draw.startAnimation(itsDraw);
                                delayDialog();
                            }

                        }
                    } else {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.blacksquarecross);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bswhitesquarecross);
                        }
                        counter--;
                        v.setTag(cross);
                        isCircle = false;
                        if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(two.getTag()) && v.getTag().equals(three.getTag())) {
                            buttonColorChangerAfterWin(one, two, three, four, style, isCircle);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(seven.getTag())
                                && v.getTag().equals(ten.getTag()) && v.getTag().equals(thirteen.getTag())) {
                            buttonColorChangerAfterWin(four, seven, ten, thirteen, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(eight.getTag())
                                && v.getTag().equals(twelve.getTag()) && v.getTag().equals(sixteen.getTag())) {
                            buttonColorChangerAfterWin(four, seven, ten, thirteen, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk || !tenChk || !elevenChk
                                    || !twelveChk || !thirteenChk || !fourteenChk
                                    || !fifteenChk || !sixteenChk) {
                                p1.startAnimation(p1turn);
                            } else {
                                disableAllButtons();

                                draw.startAnimation(itsDraw);
                                delayDialog();
                            }

                        }
                    }
                    four.setEnabled(false);
                }
                break;

            case R.id.empty_button5:
                if (five.isEnabled()) {
                    fiveChk = true;
                    if (counter == 0) {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.blacksquarecircle);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bswhitesquarecircle);
                        }
                        counter++;
                        v.setTag(circle);

                        isCircle = true;
                        if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(nine.getTag()) && v.getTag().equals(thirteen.getTag())) {
                            buttonColorChangerAfterWin(one, five, nine, thirteen, style, isCircle);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(six.getTag())
                                && v.getTag().equals(seven.getTag()) && v.getTag().equals(eight.getTag())) {
                            buttonColorChangerAfterWin(five, six, seven, eight, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk || !tenChk || !elevenChk
                                    || !twelveChk || !thirteenChk || !fourteenChk
                                    || !fifteenChk || !sixteenChk) {
                                p2.startAnimation(p2turn);
                            } else {
                                disableAllButtons();

                                draw.startAnimation(itsDraw);
                                delayDialog();
                            }

                        }
                    } else {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.blacksquarecross);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bswhitesquarecross);
                        }
                        counter--;
                        v.setTag(cross);
                        isCircle = false;
                        if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(nine.getTag()) && v.getTag().equals(thirteen.getTag())) {
                            buttonColorChangerAfterWin(one, five, nine, thirteen, style, isCircle);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(six.getTag())
                                && v.getTag().equals(seven.getTag()) && v.getTag().equals(eight.getTag())) {
                            buttonColorChangerAfterWin(five, six, seven, eight, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk || !tenChk || !elevenChk
                                    || !twelveChk || !thirteenChk || !fourteenChk
                                    || !fifteenChk || !sixteenChk) {
                                p1.startAnimation(p1turn);
                            } else {
                                disableAllButtons();

                                draw.startAnimation(itsDraw);
                                delayDialog();
                            }

                        }
                    }

                    five.setEnabled(false);

                }
                break;

            case R.id.empty_button6:
                if (six.isEnabled()) {
                    sixChk = true;
                    if (counter == 0) {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.redsquarecircle);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bsbluesquarecircle);
                        }
                        counter++;
                        v.setTag(circle);
                        isCircle = true;
                        if (v.getTag().equals(two.getTag())
                                && v.getTag().equals(ten.getTag()) && v.getTag().equals(fourteen.getTag())) {
                            buttonColorChangerAfterWin(two, six, ten, fourteen, style, isCircle);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(seven.getTag()) && v.getTag().equals(eight.getTag())) {
                            buttonColorChangerAfterWin(five, six, seven, eight, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(eleven.getTag()) && v.getTag().equals(sixteen.getTag())) {
                            buttonColorChangerAfterWin(one, six, eleven, sixteen, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk || !tenChk || !elevenChk
                                    || !twelveChk || !thirteenChk || !fourteenChk
                                    || !fifteenChk || !sixteenChk) {
                                p2.startAnimation(p2turn);
                            } else {
                                disableAllButtons();

                                draw.startAnimation(itsDraw);
                                delayDialog();
                            }

                        }
                    } else {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.redsquarecross);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bsbluesquarecross);
                        }
                        counter--;
                        v.setTag(cross);
                        isCircle = false;
                        if (v.getTag().equals(two.getTag())
                                && v.getTag().equals(ten.getTag()) && v.getTag().equals(fourteen.getTag())) {
                            buttonColorChangerAfterWin(two, six, ten, fourteen, style, isCircle);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(seven.getTag()) && v.getTag().equals(eight.getTag())) {
                            buttonColorChangerAfterWin(five, six, seven, eight, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(eleven.getTag()) && v.getTag().equals(sixteen.getTag())) {
                            buttonColorChangerAfterWin(one, six, eleven, sixteen, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk || !tenChk || !elevenChk
                                    || !twelveChk || !thirteenChk || !fourteenChk
                                    || !fifteenChk || !sixteenChk) {
                                p1.startAnimation(p1turn);
                            } else {
                                disableAllButtons();

                                draw.startAnimation(itsDraw);
                                delayDialog();
                            }

                        }
                    }

                    six.setEnabled(false);
                }
                break;

            case R.id.empty_button7:
                if (seven.isEnabled()) {
                    sevenChk = true;
                    if (counter == 0) {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.blacksquarecircle);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bswhitesquarecircle);
                        }
                        counter++;
                        v.setTag(circle);
                        isCircle = true;
                        if (v.getTag().equals(three.getTag())
                                && v.getTag().equals(eleven.getTag()) && v.getTag().equals(fifteen.getTag())) {
                            buttonColorChangerAfterWin(three, seven, eleven, fifteen, style, isCircle);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(six.getTag()) && v.getTag().equals(eight.getTag())) {
                            buttonColorChangerAfterWin(five, six, seven, eight, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(four.getTag())
                                && v.getTag().equals(ten.getTag()) && v.getTag().equals(thirteen.getTag())) {
                            buttonColorChangerAfterWin(four, seven, ten, thirteen, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk || !tenChk || !elevenChk
                                    || !twelveChk || !thirteenChk || !fourteenChk
                                    || !fifteenChk || !sixteenChk) {
                                p2.startAnimation(p2turn);
                            } else {

                                disableAllButtons();

                                draw.startAnimation(itsDraw);
                                delayDialog();
                            }

                        }
                    } else {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.blacksquarecross);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bswhitesquarecross);
                        }
                        counter--;
                        v.setTag(cross);
                        isCircle = false;
                        if (v.getTag().equals(three.getTag())
                                && v.getTag().equals(eleven.getTag()) && v.getTag().equals(fifteen.getTag())) {
                            buttonColorChangerAfterWin(three, seven, eleven, fifteen, style, isCircle);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(six.getTag()) && v.getTag().equals(eight.getTag())) {
                            buttonColorChangerAfterWin(five, six, seven, eight, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(four.getTag())
                                && v.getTag().equals(ten.getTag()) && v.getTag().equals(thirteen.getTag())) {
                            buttonColorChangerAfterWin(four, seven, ten, thirteen, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk || !tenChk || !elevenChk
                                    || !twelveChk || !thirteenChk || !fourteenChk
                                    || !fifteenChk || !sixteenChk) {
                                p1.startAnimation(p1turn);
                            } else {

                                disableAllButtons();

                                draw.startAnimation(itsDraw);
                                delayDialog();
                            }

                        }
                    }

                    seven.setEnabled(false);
                }
                break;

            case R.id.empty_button8:
                if (eight.isEnabled()) {
                    eightChk = true;
                    if (counter == 0) {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.redsquarecircle);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bsbluesquarecircle);
                        }
                        counter++;
                        v.setTag(circle);
                        isCircle = true;
                        if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(six.getTag()) && v.getTag().equals(seven.getTag())) {
                            buttonColorChangerAfterWin(five, six, seven, eight, style, isCircle);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(four.getTag())
                                && v.getTag().equals(twelve.getTag()) && v.getTag().equals(sixteen.getTag())) {
                            buttonColorChangerAfterWin(four, eight, twelve, sixteen, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk || !tenChk || !elevenChk
                                    || !twelveChk || !thirteenChk || !fourteenChk
                                    || !fifteenChk || !sixteenChk) {
                                p2.startAnimation(p2turn);
                            } else {

                                disableAllButtons();

                                draw.startAnimation(itsDraw);
                                delayDialog();
                            }

                        }
                    } else {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.redsquarecross);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bsbluesquarecross);
                        }
                        counter--;
                        v.setTag(cross);
                        isCircle = false;
                        if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(six.getTag()) && v.getTag().equals(seven.getTag())) {
                            buttonColorChangerAfterWin(five, six, seven, eight, style, isCircle);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(four.getTag())
                                && v.getTag().equals(twelve.getTag()) && v.getTag().equals(sixteen.getTag())) {
                            buttonColorChangerAfterWin(four, eight, twelve, sixteen, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk || !tenChk || !elevenChk
                                    || !twelveChk || !thirteenChk || !fourteenChk
                                    || !fifteenChk || !sixteenChk) {
                                p1.startAnimation(p1turn);
                            } else {

                                disableAllButtons();

                                draw.startAnimation(itsDraw);
                                delayDialog();
                            }

                        }
                    }

                    eight.setEnabled(false);
                }
                break;

            case R.id.empty_button9:
                if (nine.isEnabled()) {
                    nineChk = true;
                    if (counter == 0) {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.redsquarecircle);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bsbluesquarecircle);
                        }
                        counter++;
                        v.setTag(circle);
                        isCircle = true;
                        if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(five.getTag()) && v.getTag().equals(thirteen.getTag())) {
                            buttonColorChangerAfterWin(one, five, nine, thirteen, style, isCircle);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(ten.getTag())
                                && v.getTag().equals(eleven.getTag()) && v.getTag().equals(twelve.getTag())) {
                            buttonColorChangerAfterWin(nine, ten, eleven, twelve, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk || !tenChk || !elevenChk
                                    || !twelveChk || !thirteenChk || !fourteenChk
                                    || !fifteenChk || !sixteenChk) {
                                p2.startAnimation(p2turn);
                            } else {

                                disableAllButtons();

                                draw.startAnimation(itsDraw);
                                delayDialog();
                            }

                        }
                    } else {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.redsquarecross);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bsbluesquarecross);
                        }
                        counter--;
                        v.setTag(cross);
                        isCircle = false;
                        if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(five.getTag()) && v.getTag().equals(thirteen.getTag())) {
                            buttonColorChangerAfterWin(one, five, nine, thirteen, style, isCircle);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(ten.getTag())
                                && v.getTag().equals(eleven.getTag()) && v.getTag().equals(twelve.getTag())) {
                            buttonColorChangerAfterWin(nine, ten, eleven, twelve, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk || !tenChk || !elevenChk
                                    || !twelveChk || !thirteenChk || !fourteenChk
                                    || !fifteenChk || !sixteenChk) {
                                p1.startAnimation(p1turn);
                            } else {

                                disableAllButtons();

                                draw.startAnimation(itsDraw);
                                delayDialog();
                            }

                        }
                    }

                    nine.setEnabled(false);

                }
                break;

            case R.id.empty_button10:
                if (ten.isEnabled()) {
                    tenChk = true;
                    if (counter == 0) {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.blacksquarecircle);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bswhitesquarecircle);
                        }
                        counter++;
                        v.setTag(circle);
                        isCircle = true;
                        if (v.getTag().equals(two.getTag())
                                && v.getTag().equals(six.getTag()) && v.getTag().equals(fourteen.getTag())) {
                            buttonColorChangerAfterWin(two, six, ten, fourteen, style, isCircle);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(nine.getTag())
                                && v.getTag().equals(eleven.getTag()) && v.getTag().equals(twelve.getTag())) {
                            buttonColorChangerAfterWin(nine, ten, eleven, twelve, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(four.getTag())
                                && v.getTag().equals(seven.getTag()) && v.getTag().equals(thirteen.getTag())) {
                            buttonColorChangerAfterWin(four, seven, ten, thirteen, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk || !tenChk || !elevenChk
                                    || !twelveChk || !thirteenChk || !fourteenChk
                                    || !fifteenChk || !sixteenChk) {
                                p2.startAnimation(p2turn);
                            } else {

                                disableAllButtons();

                                draw.startAnimation(itsDraw);
                                delayDialog();
                            }

                        }
                    } else {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.blacksquarecross);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bswhitesquarecross);
                        }
                        counter--;
                        v.setTag(cross);
                        isCircle = false;
                        if (v.getTag().equals(two.getTag())
                                && v.getTag().equals(six.getTag()) && v.getTag().equals(fourteen.getTag())) {
                            buttonColorChangerAfterWin(two, six, ten, fourteen, style, isCircle);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(nine.getTag())
                                && v.getTag().equals(eleven.getTag()) && v.getTag().equals(twelve.getTag())) {
                            buttonColorChangerAfterWin(nine, ten, eleven, twelve, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(four.getTag())
                                && v.getTag().equals(seven.getTag()) && v.getTag().equals(thirteen.getTag())) {
                            buttonColorChangerAfterWin(four, seven, ten, thirteen, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk || !tenChk || !elevenChk
                                    || !twelveChk || !thirteenChk || !fourteenChk
                                    || !fifteenChk || !sixteenChk) {
                                p1.startAnimation(p1turn);
                            } else {

                                disableAllButtons();

                                draw.startAnimation(itsDraw);
                                delayDialog();
                            }

                        }
                    }

                    ten.setEnabled(false);

                }
                break;

            case R.id.empty_button11:
                if (eleven.isEnabled()) {
                    elevenChk = true;
                    if (counter == 0) {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.redsquarecircle);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bsbluesquarecircle);
                        }
                        counter++;
                        v.setTag(circle);
                        isCircle = true;
                        if (v.getTag().equals(three.getTag())
                                && v.getTag().equals(seven.getTag()) && v.getTag().equals(fifteen.getTag())) {
                            buttonColorChangerAfterWin(three, seven, eleven, fifteen, style, isCircle);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(nine.getTag())
                                && v.getTag().equals(ten.getTag()) && v.getTag().equals(twelve.getTag())) {
                            buttonColorChangerAfterWin(nine, ten, eleven, twelve, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk || !tenChk || !elevenChk
                                    || !twelveChk || !thirteenChk || !fourteenChk
                                    || !fifteenChk || !sixteenChk) {
                                p2.startAnimation(p2turn);
                            } else {

                                disableAllButtons();

                                draw.startAnimation(itsDraw);
                                delayDialog();
                            }

                        }
                    } else {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.redsquarecross);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bsbluesquarecross);
                        }
                        counter--;
                        v.setTag(cross);
                        isCircle = false;
                        if (v.getTag().equals(three.getTag())
                                && v.getTag().equals(seven.getTag()) && v.getTag().equals(fifteen.getTag())) {
                            buttonColorChangerAfterWin(three, seven, eleven, fifteen, style, isCircle);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(nine.getTag())
                                && v.getTag().equals(ten.getTag()) && v.getTag().equals(twelve.getTag())) {
                            buttonColorChangerAfterWin(nine, ten, eleven, twelve, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk || !tenChk || !elevenChk
                                    || !twelveChk || !thirteenChk || !fourteenChk
                                    || !fifteenChk || !sixteenChk) {
                                p1.startAnimation(p1turn);
                            } else {

                                disableAllButtons();

                                draw.startAnimation(itsDraw);
                                delayDialog();
                            }

                        }
                    }

                    eleven.setEnabled(false);

                }
                break;

            case R.id.empty_button12:
                if (twelve.isEnabled()) {
                    twelveChk = true;
                    if (counter == 0) {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.blacksquarecircle);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bswhitesquarecircle);
                        }
                        counter++;
                        v.setTag(circle);
                        isCircle = true;
                        if (v.getTag().equals(four.getTag())
                                && v.getTag().equals(eight.getTag()) && v.getTag().equals(sixteen.getTag())) {
                            buttonColorChangerAfterWin(four, eight, twelve, sixteen, style, isCircle);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(nine.getTag())
                                && v.getTag().equals(ten.getTag()) && v.getTag().equals(eleven.getTag())) {
                            buttonColorChangerAfterWin(nine, ten, eleven, twelve, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk || !tenChk || !elevenChk
                                    || !twelveChk || !thirteenChk || !fourteenChk
                                    || !fifteenChk || !sixteenChk) {
                                p2.startAnimation(p2turn);
                            } else {

                                disableAllButtons();

                                draw.startAnimation(itsDraw);
                                delayDialog();
                            }

                        }
                    } else {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.blacksquarecross);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bswhitesquarecross);
                        }
                        counter--;
                        v.setTag(cross);
                        isCircle = false;
                        if (v.getTag().equals(four.getTag())
                                && v.getTag().equals(eight.getTag()) && v.getTag().equals(sixteen.getTag())) {
                            buttonColorChangerAfterWin(four, eight, twelve, sixteen, style, isCircle);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(nine.getTag())
                                && v.getTag().equals(ten.getTag()) && v.getTag().equals(eleven.getTag())) {
                            buttonColorChangerAfterWin(nine, ten, eleven, twelve, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk || !tenChk || !elevenChk
                                    || !twelveChk || !thirteenChk || !fourteenChk
                                    || !fifteenChk || !sixteenChk) {
                                p1.startAnimation(p1turn);
                            } else {

                                disableAllButtons();

                                draw.startAnimation(itsDraw);
                                delayDialog();
                            }

                        }
                    }

                    twelve.setEnabled(false);

                }
                break;

            case R.id.empty_button13:
                if (thirteen.isEnabled()) {
                    thirteenChk = true;
                    if (counter == 0) {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.blacksquarecircle);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bswhitesquarecircle);
                        }
                        counter++;
                        v.setTag(circle);
                        isCircle = true;
                        if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(five.getTag()) && v.getTag().equals(nine.getTag())) {
                            buttonColorChangerAfterWin(one, five, nine, thirteen, style, isCircle);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(fourteen.getTag())
                                && v.getTag().equals(fifteen.getTag()) && v.getTag().equals(sixteen.getTag())) {
                            buttonColorChangerAfterWin(thirteen, fourteen, fifteen, sixteen, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(four.getTag())
                                && v.getTag().equals(seven.getTag()) && v.getTag().equals(ten.getTag())) {
                            buttonColorChangerAfterWin(four, seven, ten, thirteen, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk || !tenChk || !elevenChk
                                    || !twelveChk || !thirteenChk || !fourteenChk
                                    || !fifteenChk || !sixteenChk) {
                                p2.startAnimation(p2turn);
                            } else {

                                disableAllButtons();

                                draw.startAnimation(itsDraw);
                                delayDialog();
                            }

                        }
                    } else {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.blacksquarecross);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bswhitesquarecross);
                        }
                        counter--;
                        v.setTag(cross);
                        isCircle = false;
                        if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(five.getTag()) && v.getTag().equals(nine.getTag())) {
                            buttonColorChangerAfterWin(one, five, nine, thirteen, style, isCircle);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(fourteen.getTag())
                                && v.getTag().equals(fifteen.getTag()) && v.getTag().equals(sixteen.getTag())) {
                            buttonColorChangerAfterWin(thirteen, fourteen, fifteen, sixteen, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(four.getTag())
                                && v.getTag().equals(seven.getTag()) && v.getTag().equals(ten.getTag())) {
                            buttonColorChangerAfterWin(four, seven, ten, thirteen, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk || !tenChk || !elevenChk
                                    || !twelveChk || !thirteenChk || !fourteenChk
                                    || !fifteenChk || !sixteenChk) {
                                p1.startAnimation(p1turn);
                            } else {

                                disableAllButtons();

                                draw.startAnimation(itsDraw);
                                delayDialog();
                            }

                        }
                    }

                    thirteen.setEnabled(false);

                }
                break;

            case R.id.empty_button14:
                if (fourteen.isEnabled()) {
                    fourteenChk = true;
                    if (counter == 0) {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.redsquarecircle);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bsbluesquarecircle);
                        }
                        counter++;
                        v.setTag(circle);
                        isCircle = true;
                        if (v.getTag().equals(two.getTag())
                                && v.getTag().equals(six.getTag()) && v.getTag().equals(ten.getTag())) {
                            buttonColorChangerAfterWin(two, six, ten, fourteen, style, isCircle);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(thirteen.getTag())
                                && v.getTag().equals(fifteen.getTag()) && v.getTag().equals(sixteen.getTag())) {
                            buttonColorChangerAfterWin(thirteen, fourteen, fifteen, sixteen, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk || !tenChk || !elevenChk
                                    || !twelveChk || !thirteenChk || !fourteenChk
                                    || !fifteenChk || !sixteenChk) {
                                p2.startAnimation(p2turn);
                            } else {

                                disableAllButtons();

                                draw.startAnimation(itsDraw);
                                delayDialog();
                            }

                        }
                    } else {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.redsquarecross);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bsbluesquarecross);
                        }
                        counter--;
                        v.setTag(cross);
                        isCircle = false;
                        if (v.getTag().equals(two.getTag())
                                && v.getTag().equals(six.getTag()) && v.getTag().equals(ten.getTag())) {
                            buttonColorChangerAfterWin(two, six, ten, fourteen, style, isCircle);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(thirteen.getTag())
                                && v.getTag().equals(fifteen.getTag()) && v.getTag().equals(sixteen.getTag())) {
                            buttonColorChangerAfterWin(thirteen, fourteen, fifteen, sixteen, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk || !tenChk || !elevenChk
                                    || !twelveChk || !thirteenChk || !fourteenChk
                                    || !fifteenChk || !sixteenChk) {
                                p1.startAnimation(p1turn);
                            } else {

                                disableAllButtons();

                                draw.startAnimation(itsDraw);
                                delayDialog();
                            }

                        }
                    }

                    fourteen.setEnabled(false);

                }
                break;

            case R.id.empty_button15:
                if (fifteen.isEnabled()) {
                    fifteenChk = true;
                    if (counter == 0) {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.blacksquarecircle);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bswhitesquarecircle);
                        }
                        counter++;
                        v.setTag(circle);
                        isCircle = true;
                        if (v.getTag().equals(three.getTag())
                                && v.getTag().equals(seven.getTag()) && v.getTag().equals(eleven.getTag())) {
                            buttonColorChangerAfterWin(three, seven, eleven, fifteen, style, isCircle);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(thirteen.getTag())
                                && v.getTag().equals(fourteen.getTag()) && v.getTag().equals(sixteen.getTag())) {
                            buttonColorChangerAfterWin(thirteen, fourteen, fifteen, sixteen, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk || !tenChk || !elevenChk
                                    || !twelveChk || !thirteenChk || !fourteenChk
                                    || !fifteenChk || !sixteenChk) {
                                p2.startAnimation(p2turn);
                            } else {

                                disableAllButtons();

                                draw.startAnimation(itsDraw);
                                delayDialog();
                            }

                        }
                    } else {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.blacksquarecross);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bswhitesquarecross);
                        }
                        counter--;
                        v.setTag(cross);
                        isCircle = false;
                        if (v.getTag().equals(three.getTag())
                                && v.getTag().equals(seven.getTag()) && v.getTag().equals(eleven.getTag())) {
                            buttonColorChangerAfterWin(three, seven, eleven, fifteen, style, isCircle);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(thirteen.getTag())
                                && v.getTag().equals(fourteen.getTag()) && v.getTag().equals(sixteen.getTag())) {
                            buttonColorChangerAfterWin(thirteen, fourteen, fifteen, sixteen, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk || !tenChk || !elevenChk
                                    || !twelveChk || !thirteenChk || !fourteenChk
                                    || !fifteenChk || !sixteenChk) {
                                p1.startAnimation(p1turn);
                            } else {

                                disableAllButtons();

                                draw.startAnimation(itsDraw);
                                delayDialog();
                            }

                        }
                    }

                    fifteen.setEnabled(false);

                }
                break;

            case R.id.empty_button16:
                if (sixteen.isEnabled()) {
                    sixteenChk = true;
                    if (counter == 0) {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.redsquarecircle);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bsbluesquarecircle);
                        }
                        counter++;
                        v.setTag(circle);
                        isCircle = true;
                        if (v.getTag().equals(four.getTag())
                                && v.getTag().equals(eight.getTag()) && v.getTag().equals(twelve.getTag())) {
                            buttonColorChangerAfterWin(four, eight, twelve, sixteen, style, isCircle);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(thirteen.getTag())
                                && v.getTag().equals(fourteen.getTag()) && v.getTag().equals(fifteen.getTag())) {
                            buttonColorChangerAfterWin(thirteen, fourteen, fifteen, sixteen, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(six.getTag()) && v.getTag().equals(eleven.getTag())) {
                            buttonColorChangerAfterWin(one, six, eleven, sixteen, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk || !tenChk || !elevenChk
                                    || !twelveChk || !thirteenChk || !fourteenChk
                                    || !fifteenChk || !sixteenChk) {
                                p2.startAnimation(p2turn);
                            } else {

                                disableAllButtons();

                                draw.startAnimation(itsDraw);
                                delayDialog();
                            }

                        }
                    } else {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.redsquarecross);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bsbluesquarecross);
                        }
                        counter--;
                        v.setTag(cross);
                        isCircle = false;
                        if (v.getTag().equals(four.getTag())
                                && v.getTag().equals(eight.getTag()) && v.getTag().equals(twelve.getTag())) {
                            buttonColorChangerAfterWin(four, eight, twelve, sixteen, style, isCircle);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(thirteen.getTag())
                                && v.getTag().equals(fourteen.getTag()) && v.getTag().equals(fifteen.getTag())) {
                            buttonColorChangerAfterWin(thirteen, fourteen, fifteen, sixteen, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(six.getTag()) && v.getTag().equals(eleven.getTag())) {
                            buttonColorChangerAfterWin(one, six, eleven, sixteen, style, isCircle);
                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk || !tenChk || !elevenChk
                                    || !twelveChk || !thirteenChk || !fourteenChk
                                    || !fifteenChk || !sixteenChk) {
                                p1.startAnimation(p1turn);
                            } else {

                                disableAllButtons();

                                draw.startAnimation(itsDraw);
                                delayDialog();
                            }

                        }
                    }

                    sixteen.setEnabled(false);

                }
                break;
        }

    }

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to end the game?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                FourInToFourTwoPlayer.this.finish();
                                startActivity(end);

                            }
                        }).setNegativeButton("No", null).show();
    }

    public void buttonColorChangerAfterWin(Button w, Button x, Button y, Button z, int style, Boolean circleOrNot) {
        if (circleOrNot) {
            if (style == 1) {
                w.setBackgroundResource(R.drawable.redcircle);
                x.setBackgroundResource(R.drawable.redcircle);
                y.setBackgroundResource(R.drawable.redcircle);
                z.setBackgroundResource(R.drawable.redcircle);
            } else if (style == 2) {
                w.setBackgroundResource(R.drawable.bscirclewon);
                x.setBackgroundResource(R.drawable.bscirclewon);
                y.setBackgroundResource(R.drawable.bscirclewon);
                z.setBackgroundResource(R.drawable.bscirclewon);
            }
            p1Wins.startAnimation(p1winner);
        } else {
            if (style == 1) {
                w.setBackgroundResource(R.drawable.redcross);
                x.setBackgroundResource(R.drawable.redcross);
                y.setBackgroundResource(R.drawable.redcross);
                z.setBackgroundResource(R.drawable.redcross);
            } else if (style == 2) {
                w.setBackgroundResource(R.drawable.bscrosswon);
                x.setBackgroundResource(R.drawable.bscrosswon);
                y.setBackgroundResource(R.drawable.bscrosswon);
                z.setBackgroundResource(R.drawable.bscrosswon);
            }
            p2Wins.startAnimation(p2winner);
        }
    }

    public void disableAllButtons() {
        one.setEnabled(false);
        two.setEnabled(false);
        three.setEnabled(false);
        four.setEnabled(false);
        five.setEnabled(false);
        six.setEnabled(false);
        seven.setEnabled(false);
        eight.setEnabled(false);
        nine.setEnabled(false);
        ten.setEnabled(false);
        eleven.setEnabled(false);
        twelve.setEnabled(false);
        thirteen.setEnabled(false);
        fourteen.setEnabled(false);
        fifteen.setEnabled(false);
        sixteen.setEnabled(false);
    }

    public void animationSetup() {


        startTheGame.setDuration(3000);
        startTheGame.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation a) {
                startGame.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }
        });
        startGame.setAnimation(startTheGame);


        p1turn.setDuration(1000);
        p1turn.setStartOffset(500);
        p1turn.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation a) {

                p1.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }
        });


        p2turn.setDuration(1000);
        p2turn.setStartOffset(500);
        p2turn.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation a) {

                p2.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

        });


        p1winner.setDuration(5000);
        p1winner.setStartOffset(1000);
        p1winner.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation a) {
                p1Wins.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }
        });


        p2winner.setDuration(5000);
        p2winner.setStartOffset(1000);
        p2winner.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation a) {
                p2Wins.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }
        });


        itsDraw.setStartOffset(1000);
        itsDraw.setDuration(5000);
        itsDraw.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation a) {
                p2.setVisibility(View.GONE);
                p1.setVisibility(View.GONE);
                p1Wins.setVisibility(View.GONE);
                p2Wins.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }
        });
    }
}
