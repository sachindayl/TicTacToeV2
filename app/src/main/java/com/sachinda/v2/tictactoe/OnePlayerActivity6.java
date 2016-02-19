package com.sachinda.v2.tictactoe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;

public class OnePlayerActivity6 extends Activity implements
        View.OnClickListener {
    // buttons
    Button one, two, three, four, five, six, seven, eight, nine;
    Button[] buttons = {one, two, three, four, five, six, seven, eight, nine};

    int enabledKeys;

    boolean oneOne = false, oneTwo = false, oneThree = false, oneFour = false,
            oneFive = false, oneSix = false, oneSeven = false,
            oneEight = false, oneNine = false, twoOne = false, twoTwo = false,
            twoThree = false, twoFour = false, twoFive = false, twoSix = false,
            twoSeven = false, twoEight = false, twoNine = false;
    // to check if button have already been clicked
    boolean oneChk = false;
    boolean twoChk = false;
    boolean threeChk = false;
    boolean fourChk = false;
    boolean fiveChk = false;
    boolean sixChk = false;
    boolean sevenChk = false;
    boolean eightChk = false;
    boolean nineChk = false;

    // to check which button for computer to click

    int counter;
    ImageView startGame, yt, ct, yWin, cWin, draw;
    AlphaAnimation yourTurn, compTurn, youWin, compWin, itsdraw;
    String cross = "Cross";
    String circle = "Circle";
    String noTag = "noTag";
    AlertDialog.Builder alert;
    AlertDialog dialog;
    Intent nextGame, end;
    Thread th;

    // private Runnable compMoveRunner;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        int style = ((GlobalVariable) this.getApplication()).getStyle();
        if (style == 1) {
            setContentView(R.layout.activity_one_player);
        } else if (style == 2) {
            setContentView(R.layout.activity_one_player_activity2);
        }

        nextGame = new Intent(OnePlayerActivity6.this, OnePlayerActivity.class);
        end = new Intent(OnePlayerActivity6.this, BoardType.class);

        // Context context = getApplicationContext();
        // CharSequence text = "Activity 6";
        // int duration = Toast.LENGTH_SHORT;
        //
        // Toast toast = Toast.makeText(context, text, duration);
        // toast.show();
        counter = 0;

        one = (Button) findViewById(R.id.empty_button);
        two = (Button) findViewById(R.id.empty_button2);
        three = (Button) findViewById(R.id.empty_button3);
        four = (Button) findViewById(R.id.empty_button4);
        five = (Button) findViewById(R.id.empty_button5);
        six = (Button) findViewById(R.id.empty_button6);
        seven = (Button) findViewById(R.id.empty_button7);
        eight = (Button) findViewById(R.id.empty_button8);
        nine = (Button) findViewById(R.id.empty_button9);

        one.setOnClickListener(this);
        one.setEnabled(true);
        one.setTag(noTag);
        two.setOnClickListener(this);
        two.setEnabled(true);
        two.setTag(noTag);
        three.setOnClickListener(this);
        three.setEnabled(true);
        three.setTag(noTag);
        four.setOnClickListener(this);
        four.setEnabled(true);
        four.setTag(noTag);
        five.setOnClickListener(this);
        five.setEnabled(true);
        five.setTag(noTag);
        six.setOnClickListener(this);
        six.setEnabled(true);
        six.setTag(noTag);
        seven.setOnClickListener(this);
        seven.setEnabled(true);
        seven.setTag(noTag);
        eight.setOnClickListener(this);
        eight.setEnabled(true);
        eight.setTag(noTag);
        nine.setOnClickListener(this);
        nine.setEnabled(true);
        nine.setTag(noTag);

        animationSetup();
        computerMove();
    }

    public void delayDialog() {
        th = new Thread() {
            public void run() {
                try {
                    Thread.sleep(1500);
                    OnePlayerActivity6.this.runOnUiThread(new Runnable() {
                        public void run() {
                            alert = new AlertDialog.Builder(
                                    OnePlayerActivity6.this);
                            alert.setMessage("Do you want to start a new game?")
                                    .setCancelable(false)
                                    .setPositiveButton(
                                            "Yes",
                                            new DialogInterface.OnClickListener() {

                                                @Override
                                                public void onClick(
                                                        DialogInterface dialog,
                                                        int which) {
                                                    finish();
                                                    startActivity(nextGame);

                                                }
                                            })
                                    .setNegativeButton(
                                            "No",
                                            new DialogInterface.OnClickListener() {

                                                @Override
                                                public void onClick(
                                                        DialogInterface dialog,
                                                        int which) {
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

    @Override
    public void onClick(View v) {
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
                        twoOne = true;
                        v.setTag(circle);

                        if (v.getTag().equals(two.getTag())
                                && v.getTag().equals(three.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcircle);
                                two.setBackgroundResource(R.drawable.redcircle);
                                three.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscirclewon);
                                two.setBackgroundResource(R.drawable.bscirclewon);
                                three.setBackgroundResource(R.drawable.bscirclewon);
                            }
                            cWin.startAnimation(compWin);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(four.getTag())
                                && v.getTag().equals(seven.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcircle);
                                four.setBackgroundResource(R.drawable.redcircle);
                                seven.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscirclewon);
                                four.setBackgroundResource(R.drawable.bscirclewon);
                                seven.setBackgroundResource(R.drawable.bscirclewon);
                            }


                            disableAllButtons();

                            cWin.startAnimation(compWin);
                            delayDialog();
                        } else if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(nine.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcircle);
                                five.setBackgroundResource(R.drawable.redcircle);
                                nine.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscirclewon);
                                five.setBackgroundResource(R.drawable.bscirclewon);
                                nine.setBackgroundResource(R.drawable.bscirclewon);
                            }

                            disableAllButtons();

                            cWin.startAnimation(compWin);
                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                yt.startAnimation(yourTurn);

                                twoOne = true;

                            } else {
                                draw.startAnimation(itsdraw);

                                disableAllButtons();

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
                        oneOne = true;
                        if (v.getTag().equals(two.getTag())
                                && v.getTag().equals(three.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcross);
                                two.setBackgroundResource(R.drawable.redcross);
                                three.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscrosswon);
                                two.setBackgroundResource(R.drawable.bscrosswon);
                                three.setBackgroundResource(R.drawable.bscrosswon);
                            }

                            yWin.startAnimation(youWin);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(four.getTag())
                                && v.getTag().equals(seven.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcross);
                                four.setBackgroundResource(R.drawable.redcross);
                                seven.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscrosswon);
                                four.setBackgroundResource(R.drawable.bscrosswon);
                                seven.setBackgroundResource(R.drawable.bscrosswon);
                            }

                            yWin.startAnimation(youWin);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(nine.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcross);
                                five.setBackgroundResource(R.drawable.redcross);
                                nine.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscrosswon);
                                five.setBackgroundResource(R.drawable.bscrosswon);
                                nine.setBackgroundResource(R.drawable.bscrosswon);
                            }

                            yWin.startAnimation(youWin);

                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                ct.startAnimation(compTurn);
                                computerMove();
                            } else {
                                draw.startAnimation(itsdraw);

                                disableAllButtons();

                                delayDialog();
                            }

                        }
                        one.setEnabled(false);
                    }

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
                        twoTwo = true;
                        v.setTag(circle);
                        if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(three.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcircle);
                                two.setBackgroundResource(R.drawable.redcircle);
                                three.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscirclewon);
                                two.setBackgroundResource(R.drawable.bscirclewon);
                                three.setBackgroundResource(R.drawable.bscirclewon);
                            }

                            cWin.startAnimation(compWin);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(eight.getTag())) {
                            if (style == 1) {
                                two.setBackgroundResource(R.drawable.redcircle);
                                five.setBackgroundResource(R.drawable.redcircle);
                                eight.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                two.setBackgroundResource(R.drawable.bscirclewon);
                                five.setBackgroundResource(R.drawable.bscirclewon);
                                eight.setBackgroundResource(R.drawable.bscirclewon);
                            }
                            cWin.startAnimation(compWin);

                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                yt.startAnimation(yourTurn);
                                twoTwo = true;

                            } else {
                                draw.startAnimation(itsdraw);

                                disableAllButtons();

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
                        oneTwo = true;
                        if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(three.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcross);
                                two.setBackgroundResource(R.drawable.redcross);
                                three.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscrosswon);
                                two.setBackgroundResource(R.drawable.bscrosswon);
                                three.setBackgroundResource(R.drawable.bscrosswon);
                            }

                            yWin.startAnimation(youWin);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(eight.getTag())) {
                            if (style == 1) {
                                two.setBackgroundResource(R.drawable.redcross);
                                five.setBackgroundResource(R.drawable.redcross);
                                eight.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                two.setBackgroundResource(R.drawable.bscrosswon);
                                five.setBackgroundResource(R.drawable.bscrosswon);
                                eight.setBackgroundResource(R.drawable.bscrosswon);
                            }

                            yWin.startAnimation(youWin);

                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                ct.startAnimation(compTurn);
                                computerMove();

                            } else {
                                draw.startAnimation(itsdraw);

                                disableAllButtons();

                                delayDialog();
                            }

                        }
                        two.setEnabled(false);
                    }

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
                        twoThree = true;
                        v.setTag(circle);

                        if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(two.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcircle);
                                two.setBackgroundResource(R.drawable.redcircle);
                                three.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscirclewon);
                                two.setBackgroundResource(R.drawable.bscirclewon);
                                three.setBackgroundResource(R.drawable.bscirclewon);
                            }

                            cWin.startAnimation(compWin);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(seven.getTag())) {
                            if (style == 1) {
                                three.setBackgroundResource(R.drawable.redcircle);
                                five.setBackgroundResource(R.drawable.redcircle);
                                seven.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                three.setBackgroundResource(R.drawable.bscirclewon);
                                five.setBackgroundResource(R.drawable.bscirclewon);
                                seven.setBackgroundResource(R.drawable.bscirclewon);
                            }

                            cWin.startAnimation(compWin);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(six.getTag())
                                && v.getTag().equals(nine.getTag())) {
                            if (style == 1) {
                                three.setBackgroundResource(R.drawable.redcircle);
                                six.setBackgroundResource(R.drawable.redcircle);
                                nine.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                three.setBackgroundResource(R.drawable.bscirclewon);
                                six.setBackgroundResource(R.drawable.bscirclewon);
                                nine.setBackgroundResource(R.drawable.bscirclewon);
                            }
                            cWin.startAnimation(compWin);

                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                yt.startAnimation(yourTurn);
                                twoThree = true;

                            } else {
                                draw.startAnimation(itsdraw);

                                disableAllButtons();

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
                        oneThree = true;
                        if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(two.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcross);
                                two.setBackgroundResource(R.drawable.redcross);
                                three.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscrosswon);
                                two.setBackgroundResource(R.drawable.bscrosswon);
                                three.setBackgroundResource(R.drawable.bscrosswon);
                            }

                            yWin.startAnimation(youWin);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(seven.getTag())) {
                            if (style == 1) {
                                three.setBackgroundResource(R.drawable.redcross);
                                five.setBackgroundResource(R.drawable.redcross);
                                seven.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                three.setBackgroundResource(R.drawable.bscrosswon);
                                five.setBackgroundResource(R.drawable.bscrosswon);
                                seven.setBackgroundResource(R.drawable.bscrosswon);
                            }

                            yWin.startAnimation(youWin);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(six.getTag())
                                && v.getTag().equals(nine.getTag())) {
                            if (style == 1) {
                                three.setBackgroundResource(R.drawable.redcross);
                                six.setBackgroundResource(R.drawable.redcross);
                                nine.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                three.setBackgroundResource(R.drawable.bscrosswon);
                                six.setBackgroundResource(R.drawable.bscrosswon);
                                nine.setBackgroundResource(R.drawable.bscrosswon);
                            }

                            yWin.startAnimation(youWin);

                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                ct.startAnimation(compTurn);
                                computerMove();

                            } else {
                                draw.startAnimation(itsdraw);

                                disableAllButtons();

                                delayDialog();
                            }

                        }
                        three.setEnabled(false);
                    }

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
                        twoFour = true;
                        v.setTag(circle);
                        if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(seven.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcircle);
                                four.setBackgroundResource(R.drawable.redcircle);
                                seven.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscirclewon);
                                four.setBackgroundResource(R.drawable.bscirclewon);
                                seven.setBackgroundResource(R.drawable.bscirclewon);
                            }

                            cWin.startAnimation(compWin);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(six.getTag())) {
                            if (style == 1) {
                                four.setBackgroundResource(R.drawable.redcircle);
                                five.setBackgroundResource(R.drawable.redcircle);
                                six.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                four.setBackgroundResource(R.drawable.bscirclewon);
                                five.setBackgroundResource(R.drawable.bscirclewon);
                                six.setBackgroundResource(R.drawable.bscirclewon);
                            }
                            cWin.startAnimation(compWin);

                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                yt.startAnimation(yourTurn);
                                twoFour = true;

                            } else {
                                draw.startAnimation(itsdraw);

                                disableAllButtons();

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
                        oneFour = true;
                        if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(seven.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcross);
                                four.setBackgroundResource(R.drawable.redcross);
                                seven.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscrosswon);
                                four.setBackgroundResource(R.drawable.bscrosswon);
                                seven.setBackgroundResource(R.drawable.bscrosswon);
                            }

                            yWin.startAnimation(youWin);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(six.getTag())) {
                            if (style == 1) {
                                four.setBackgroundResource(R.drawable.redcross);
                                five.setBackgroundResource(R.drawable.redcross);
                                six.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                four.setBackgroundResource(R.drawable.bscrosswon);
                                five.setBackgroundResource(R.drawable.bscrosswon);
                                six.setBackgroundResource(R.drawable.bscrosswon);
                            }

                            yWin.startAnimation(youWin);

                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                ct.startAnimation(compTurn);
                                computerMove();

                            } else {
                                draw.startAnimation(itsdraw);

                                disableAllButtons();

                                delayDialog();
                            }

                        }
                        four.setEnabled(false);
                    }
                }
                break;

            case R.id.empty_button5:
                if (five.isEnabled()) {
                    fiveChk = true;
                    if (counter == 0) {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.redsquarecircle);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bsbluesquarecircle);
                        }
                        counter++;
                        twoFive = true;
                        v.setTag(circle);

                        if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(nine.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcircle);
                                five.setBackgroundResource(R.drawable.redcircle);
                                nine.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscirclewon);
                                five.setBackgroundResource(R.drawable.bscirclewon);
                                nine.setBackgroundResource(R.drawable.bscirclewon);
                            }

                            cWin.startAnimation(compWin);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(two.getTag())
                                && v.getTag().equals(eight.getTag())) {
                            if (style == 1) {
                                two.setBackgroundResource(R.drawable.redcircle);
                                five.setBackgroundResource(R.drawable.redcircle);
                                eight.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                two.setBackgroundResource(R.drawable.bscirclewon);
                                five.setBackgroundResource(R.drawable.bscirclewon);
                                eight.setBackgroundResource(R.drawable.bscirclewon);
                            }

                            cWin.startAnimation(compWin);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(three.getTag())
                                && v.getTag().equals(seven.getTag())) {
                            if (style == 1) {
                                three.setBackgroundResource(R.drawable.redcircle);
                                five.setBackgroundResource(R.drawable.redcircle);
                                seven.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                three.setBackgroundResource(R.drawable.bscirclewon);
                                five.setBackgroundResource(R.drawable.bscirclewon);
                                seven.setBackgroundResource(R.drawable.bscirclewon);
                            }

                            cWin.startAnimation(compWin);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(four.getTag())
                                && v.getTag().equals(six.getTag())) {
                            if (style == 1) {
                                four.setBackgroundResource(R.drawable.redcircle);
                                five.setBackgroundResource(R.drawable.redcircle);
                                six.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                four.setBackgroundResource(R.drawable.bscirclewon);
                                five.setBackgroundResource(R.drawable.bscirclewon);
                                six.setBackgroundResource(R.drawable.bscirclewon);
                            }

                            cWin.startAnimation(compWin);

                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                yt.startAnimation(yourTurn);

                            } else {
                                draw.startAnimation(itsdraw);

                                disableAllButtons();

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
                        oneFive = true;
                        if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(nine.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcross);
                                five.setBackgroundResource(R.drawable.redcross);
                                nine.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscrosswon);
                                five.setBackgroundResource(R.drawable.bscrosswon);
                                nine.setBackgroundResource(R.drawable.bscrosswon);
                            }

                            yWin.startAnimation(youWin);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(two.getTag())
                                && v.getTag().equals(eight.getTag())) {
                            if (style == 1) {
                                two.setBackgroundResource(R.drawable.redcross);
                                five.setBackgroundResource(R.drawable.redcross);
                                eight.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                two.setBackgroundResource(R.drawable.bscrosswon);
                                five.setBackgroundResource(R.drawable.bscrosswon);
                                eight.setBackgroundResource(R.drawable.bscrosswon);
                            }

                            yWin.startAnimation(youWin);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(three.getTag())
                                && v.getTag().equals(seven.getTag())) {
                            if (style == 1) {
                                three.setBackgroundResource(R.drawable.redcross);
                                five.setBackgroundResource(R.drawable.redcross);
                                seven.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                three.setBackgroundResource(R.drawable.bscrosswon);
                                five.setBackgroundResource(R.drawable.bscrosswon);
                                seven.setBackgroundResource(R.drawable.bscrosswon);
                            }

                            yWin.startAnimation(youWin);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(four.getTag())
                                && v.getTag().equals(six.getTag())) {
                            if (style == 1) {
                                four.setBackgroundResource(R.drawable.redcross);
                                five.setBackgroundResource(R.drawable.redcross);
                                six.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                four.setBackgroundResource(R.drawable.bscrosswon);
                                five.setBackgroundResource(R.drawable.bscrosswon);
                                six.setBackgroundResource(R.drawable.bscrosswon);
                            }


                            yWin.startAnimation(youWin);

                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                ct.startAnimation(compTurn);
                                computerMove();

                            } else {
                                draw.startAnimation(itsdraw);

                                disableAllButtons();

                                delayDialog();
                            }

                        }
                        five.setEnabled(false);
                    }

                }
                break;

            case R.id.empty_button6:
                if (six.isEnabled()) {
                    sixChk = true;
                    if (counter == 0) {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.blacksquarecircle);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bswhitesquarecircle);
                        }
                        counter++;
                        twoSix = true;
                        v.setTag(circle);
                        if (v.getTag().equals(three.getTag())
                                && v.getTag().equals(nine.getTag())) {
                            if (style == 1) {
                                three.setBackgroundResource(R.drawable.redcircle);
                                six.setBackgroundResource(R.drawable.redcircle);
                                nine.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                three.setBackgroundResource(R.drawable.bscirclewon);
                                six.setBackgroundResource(R.drawable.bscirclewon);
                                nine.setBackgroundResource(R.drawable.bscirclewon);
                            }

                            cWin.startAnimation(compWin);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(four.getTag())) {
                            if (style == 1) {
                                four.setBackgroundResource(R.drawable.redcircle);
                                five.setBackgroundResource(R.drawable.redcircle);
                                six.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                four.setBackgroundResource(R.drawable.bscirclewon);
                                five.setBackgroundResource(R.drawable.bscirclewon);
                                six.setBackgroundResource(R.drawable.bscirclewon);
                            }


                            cWin.startAnimation(compWin);

                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                yt.startAnimation(yourTurn);

                            } else {
                                draw.startAnimation(itsdraw);

                                disableAllButtons();

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
                        oneSix = true;
                        if (v.getTag().equals(three.getTag())
                                && v.getTag().equals(nine.getTag())) {
                            if (style == 1) {
                                three.setBackgroundResource(R.drawable.redcross);
                                six.setBackgroundResource(R.drawable.redcross);
                                nine.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                three.setBackgroundResource(R.drawable.bscrosswon);
                                six.setBackgroundResource(R.drawable.bscrosswon);
                                nine.setBackgroundResource(R.drawable.bscrosswon);
                            }

                            yWin.startAnimation(youWin);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(four.getTag())) {
                            if (style == 1) {
                                four.setBackgroundResource(R.drawable.redcross);
                                five.setBackgroundResource(R.drawable.redcross);
                                six.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                four.setBackgroundResource(R.drawable.bscrosswon);
                                five.setBackgroundResource(R.drawable.bscrosswon);
                                six.setBackgroundResource(R.drawable.bscrosswon);
                            }


                            yWin.startAnimation(youWin);

                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                ct.startAnimation(compTurn);
                                computerMove();

                            } else {
                                draw.startAnimation(itsdraw);

                                disableAllButtons();

                                delayDialog();
                            }

                        }
                        six.setEnabled(false);
                    }

                }
                break;

            case R.id.empty_button7:
                if (seven.isEnabled()) {
                    sevenChk = true;
                    if (counter == 0) {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.redsquarecircle);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bsbluesquarecircle);
                        }
                        counter++;
                        twoSeven = true;
                        v.setTag(circle);
                        if (v.getTag().equals(four.getTag())
                                && v.getTag().equals(one.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcircle);
                                four.setBackgroundResource(R.drawable.redcircle);
                                seven.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscirclewon);
                                four.setBackgroundResource(R.drawable.bscirclewon);
                                seven.setBackgroundResource(R.drawable.bscirclewon);
                            }

                            cWin.startAnimation(compWin);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(three.getTag())) {
                            if (style == 1) {
                                three.setBackgroundResource(R.drawable.redcircle);
                                five.setBackgroundResource(R.drawable.redcircle);
                                seven.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                three.setBackgroundResource(R.drawable.bscirclewon);
                                five.setBackgroundResource(R.drawable.bscirclewon);
                                seven.setBackgroundResource(R.drawable.bscirclewon);
                            }

                            cWin.startAnimation(compWin);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(eight.getTag())
                                && v.getTag().equals(nine.getTag())) {
                            if (style == 1) {
                                seven.setBackgroundResource(R.drawable.redcircle);
                                eight.setBackgroundResource(R.drawable.redcircle);
                                nine.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                seven.setBackgroundResource(R.drawable.bscirclewon);
                                eight.setBackgroundResource(R.drawable.bscirclewon);
                                nine.setBackgroundResource(R.drawable.bscirclewon);
                            }
                            cWin.startAnimation(compWin);

                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                yt.startAnimation(yourTurn);

                            } else {
                                draw.startAnimation(itsdraw);

                                disableAllButtons();

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
                        oneSeven = true;
                        if (v.getTag().equals(four.getTag())
                                && v.getTag().equals(one.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcross);
                                four.setBackgroundResource(R.drawable.redcross);
                                seven.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscrosswon);
                                four.setBackgroundResource(R.drawable.bscrosswon);
                                seven.setBackgroundResource(R.drawable.bscrosswon);
                            }

                            yWin.startAnimation(youWin);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(three.getTag())) {
                            if (style == 1) {
                                three.setBackgroundResource(R.drawable.redcross);
                                five.setBackgroundResource(R.drawable.redcross);
                                seven.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                three.setBackgroundResource(R.drawable.bscrosswon);
                                five.setBackgroundResource(R.drawable.bscrosswon);
                                seven.setBackgroundResource(R.drawable.bscrosswon);
                            }

                            yWin.startAnimation(youWin);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(eight.getTag())
                                && v.getTag().equals(nine.getTag())) {
                            if (style == 1) {
                                seven.setBackgroundResource(R.drawable.redcross);
                                eight.setBackgroundResource(R.drawable.redcross);
                                nine.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                seven.setBackgroundResource(R.drawable.bscrosswon);
                                eight.setBackgroundResource(R.drawable.bscrosswon);
                                nine.setBackgroundResource(R.drawable.bscrosswon);
                            }

                            yWin.startAnimation(youWin);

                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                ct.startAnimation(compTurn);
                                computerMove();

                            } else {
                                draw.startAnimation(itsdraw);

                                disableAllButtons();

                                delayDialog();
                            }

                        }
                        seven.setEnabled(false);
                    }

                }
                break;

            case R.id.empty_button8:
                if (eight.isEnabled()) {
                    eightChk = true;
                    if (counter == 0) {
                        if (style == 1) {
                            v.setBackgroundResource(R.drawable.blacksquarecircle);
                        } else if (style == 2) {
                            v.setBackgroundResource(R.drawable.bswhitesquarecircle);
                        }
                        counter++;
                        twoEight = true;
                        v.setTag(circle);
                        if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(two.getTag())) {
                            if (style == 1) {
                                two.setBackgroundResource(R.drawable.redcircle);
                                five.setBackgroundResource(R.drawable.redcircle);
                                eight.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                two.setBackgroundResource(R.drawable.bscirclewon);
                                five.setBackgroundResource(R.drawable.bscirclewon);
                                eight.setBackgroundResource(R.drawable.bscirclewon);
                            }

                            cWin.startAnimation(compWin);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(seven.getTag())
                                && v.getTag().equals(nine.getTag())) {
                            if (style == 1) {
                                seven.setBackgroundResource(R.drawable.redcircle);
                                eight.setBackgroundResource(R.drawable.redcircle);
                                nine.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                seven.setBackgroundResource(R.drawable.bscirclewon);
                                eight.setBackgroundResource(R.drawable.bscirclewon);
                                nine.setBackgroundResource(R.drawable.bscirclewon);
                            }

                            cWin.startAnimation(compWin);

                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                yt.startAnimation(yourTurn);

                            } else {
                                draw.startAnimation(itsdraw);

                                disableAllButtons();

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
                        oneEight = true;
                        if (v.getTag().equals(five.getTag())
                                && v.getTag().equals(two.getTag())) {
                            if (style == 1) {
                                two.setBackgroundResource(R.drawable.redcross);
                                five.setBackgroundResource(R.drawable.redcross);
                                eight.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                two.setBackgroundResource(R.drawable.bscrosswon);
                                five.setBackgroundResource(R.drawable.bscrosswon);
                                eight.setBackgroundResource(R.drawable.bscrosswon);
                            }

                            yWin.startAnimation(youWin);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(seven.getTag())
                                && v.getTag().equals(nine.getTag())) {
                            if (style == 1) {
                                seven.setBackgroundResource(R.drawable.redcross);
                                eight.setBackgroundResource(R.drawable.redcross);
                                nine.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                seven.setBackgroundResource(R.drawable.bscrosswon);
                                eight.setBackgroundResource(R.drawable.bscrosswon);
                                nine.setBackgroundResource(R.drawable.bscrosswon);
                            }

                            yWin.startAnimation(youWin);

                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                ct.startAnimation(compTurn);
                                computerMove();

                            } else {
                                draw.startAnimation(itsdraw);

                                disableAllButtons();

                                delayDialog();
                            }

                        }
                        eight.setEnabled(false);
                    }

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
                        twoNine = true;
                        v.setTag(circle);
                        if (v.getTag().equals(three.getTag())
                                && v.getTag().equals(six.getTag())) {
                            if (style == 1) {
                                three.setBackgroundResource(R.drawable.redcircle);
                                six.setBackgroundResource(R.drawable.redcircle);
                                nine.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                three.setBackgroundResource(R.drawable.bscirclewon);
                                six.setBackgroundResource(R.drawable.bscirclewon);
                                nine.setBackgroundResource(R.drawable.bscirclewon);
                            }

                            cWin.startAnimation(compWin);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(five.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcircle);
                                five.setBackgroundResource(R.drawable.redcircle);
                                nine.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscirclewon);
                                five.setBackgroundResource(R.drawable.bscirclewon);
                                nine.setBackgroundResource(R.drawable.bscirclewon);
                            }

                            cWin.startAnimation(compWin);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(seven.getTag())
                                && v.getTag().equals(eight.getTag())) {
                            if (style == 1) {
                                seven.setBackgroundResource(R.drawable.redcircle);
                                eight.setBackgroundResource(R.drawable.redcircle);
                                nine.setBackgroundResource(R.drawable.redcircle);
                            } else if (style == 2) {
                                seven.setBackgroundResource(R.drawable.bscirclewon);
                                eight.setBackgroundResource(R.drawable.bscirclewon);
                                nine.setBackgroundResource(R.drawable.bscirclewon);
                            }

                            cWin.startAnimation(compWin);

                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                yt.startAnimation(yourTurn);

                            } else {
                                draw.startAnimation(itsdraw);

                                disableAllButtons();

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
                        oneNine = true;
                        if (v.getTag().equals(three.getTag())
                                && v.getTag().equals(six.getTag())) {
                            if (style == 1) {
                                three.setBackgroundResource(R.drawable.redcross);
                                six.setBackgroundResource(R.drawable.redcross);
                                nine.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                three.setBackgroundResource(R.drawable.bscrosswon);
                                six.setBackgroundResource(R.drawable.bscrosswon);
                                nine.setBackgroundResource(R.drawable.bscrosswon);
                            }

                            yWin.startAnimation(youWin);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(one.getTag())
                                && v.getTag().equals(five.getTag())) {
                            if (style == 1) {
                                one.setBackgroundResource(R.drawable.redcross);
                                five.setBackgroundResource(R.drawable.redcross);
                                nine.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                one.setBackgroundResource(R.drawable.bscrosswon);
                                five.setBackgroundResource(R.drawable.bscrosswon);
                                nine.setBackgroundResource(R.drawable.bscrosswon);
                            }

                            yWin.startAnimation(youWin);

                            disableAllButtons();

                            delayDialog();
                        } else if (v.getTag().equals(seven.getTag())
                                && v.getTag().equals(eight.getTag())) {
                            if (style == 1) {
                                seven.setBackgroundResource(R.drawable.redcross);
                                eight.setBackgroundResource(R.drawable.redcross);
                                nine.setBackgroundResource(R.drawable.redcross);
                            } else if (style == 2) {
                                seven.setBackgroundResource(R.drawable.bscrosswon);
                                eight.setBackgroundResource(R.drawable.bscrosswon);
                                nine.setBackgroundResource(R.drawable.bscrosswon);
                            }

                            yWin.startAnimation(youWin);

                            disableAllButtons();

                            delayDialog();
                        } else {
                            if (!oneChk || !twoChk || !threeChk || !fourChk
                                    || !fiveChk || !sixChk || !sevenChk
                                    || !eightChk || !nineChk) {
                                ct.startAnimation(compTurn);
                                computerMove();

                            } else {
                                draw.startAnimation(itsdraw);

                                disableAllButtons();

                                delayDialog();
                            }

                        }
                        nine.setEnabled(false);
                    }

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
                                OnePlayerActivity6.this.finish();
                                startActivity(end);

                            }
                        }).setNegativeButton("No", null).show();
    }

    public void animationSetup() {

        startGame = (ImageView) findViewById(R.id.start_the_game);

        AlphaAnimation startTheGame = new AlphaAnimation(1.0f, 0.0f);

        startTheGame.setDuration(2000);
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

        yt = (ImageView) findViewById(R.id.your_turn);

        yourTurn = new AlphaAnimation(1.0f, 0.0f);
        yourTurn.setDuration(500);
        yourTurn.setStartOffset(500);

        yourTurn.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation a) {

                yt.setVisibility(View.GONE);
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

        ct = (ImageView) findViewById(R.id.comp_turn);
        compTurn = new AlphaAnimation(1.0f, 0.0f);
        compTurn.setStartOffset(150);
        compTurn.setDuration(300);
        compTurn.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation a) {
                ct.setVisibility(View.GONE);
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

        yWin = (ImageView) findViewById(R.id.you_win);
        youWin = new AlphaAnimation(1.0f, 0.0f);
        youWin.setStartOffset(1000);
        youWin.setDuration(5000);
        youWin.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation a) {
                ct.setVisibility(View.GONE);
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

        cWin = (ImageView) findViewById(R.id.computer_wins);
        compWin = new AlphaAnimation(1.0f, 0.0f);
        compWin.setStartOffset(1000);
        compWin.setDuration(5000);
        compWin.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation a) {
                ct.setVisibility(View.GONE);
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

        draw = (ImageView) findViewById(R.id.its_draw);
        itsdraw = new AlphaAnimation(1.0f, 0.0f);
        itsdraw.setStartOffset(1000);
        itsdraw.setDuration(5000);
        itsdraw.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation a) {
                ct.setVisibility(View.GONE);
                yt.setVisibility(View.GONE);
                yWin.setVisibility(View.GONE);
                cWin.setVisibility(View.GONE);
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

    private void computerMove() {

        if (oneOne && one.isEnabled()) {
            if (twoFour && twoFive && six.isEnabled()) {
                delayedMove(six);
            } else if (twoFive && twoSix && four.isEnabled()) {
                delayedMove(four);
            } else if (twoFour && twoSix && five.isEnabled()) {
                delayedMove(five);
            } else if (twoSeven && twoEight && nine.isEnabled()) {
                nine.performClick();
            } else if (twoEight && twoNine && seven.isEnabled()) {
                delayedMove(seven);
            } else if (twoSeven && twoNine && eight.isEnabled()) {
                delayedMove(eight);
            } else if (twoTwo && twoFive && eight.isEnabled()) {
                delayedMove(eight);
            } else if (twoTwo && twoEight && five.isEnabled()) {
                delayedMove(five);
            } else if (twoFive && twoEight && two.isEnabled()) {
                delayedMove(two);
            } else if (twoThree && twoSix && nine.isEnabled()) {
                delayedMove(nine);
            } else if (twoThree && twoNine && six.isEnabled()) {
                delayedMove(six);
            } else if (twoSix && twoNine && three.isEnabled()) {
                delayedMove(three);
            } else if (twoThree && twoFive && seven.isEnabled()) {
                delayedMove(seven);
            } else if (twoThree && twoSeven && five.isEnabled()) {
                delayedMove(five);
            } else if (twoFive && twoSeven && three.isEnabled()) {
                delayedMove(three);
            } else if (oneTwo && three.isEnabled()) {
                delayedMove(three);
            } else if (oneThree && two.isEnabled()) {
                delayedMove(two);
            } else if (oneFive && nine.isEnabled()) {
                delayedMove(nine);
            } else if (oneNine && five.isEnabled()) {
                delayedMove(five);
            } else if (oneFour && seven.isEnabled()) {
                delayedMove(seven);
            } else if (oneSeven && four.isEnabled()) {
                delayedMove(four);
            } else {
                if (five.isEnabled())
                    delayedMove(five);
                else if (eight.isEnabled())
                    delayedMove(eight);
                else if (four.isEnabled())
                    delayedMove(four);
                else if (two.isEnabled())
                    delayedMove(two);
                else if (three.isEnabled())
                    delayedMove(three);
                else if (seven.isEnabled())
                    delayedMove(seven);
                else if (six.isEnabled())
                    delayedMove(six);
                else if (nine.isEnabled())
                    delayedMove(nine);
            }
            one.setEnabled(false);
        }
        if (oneTwo && two.isEnabled()) {
            if (twoOne && twoFour && seven.isEnabled()) {
                delayedMove(seven);
            } else if (twoOne && twoSeven && four.isEnabled()) {
                delayedMove(four);
            } else if (twoFour && twoSeven && one.isEnabled()) {
                delayedMove(one);
            } else if (twoThree && twoSix && nine.isEnabled()) {
                delayedMove(nine);
            } else if (twoThree && twoNine && six.isEnabled()) {
                delayedMove(six);
            } else if (twoSix && twoNine && three.isEnabled()) {
                delayedMove(three);
            } else if (twoOne && twoFive && nine.isEnabled()) {
                delayedMove(nine);
            } else if (twoOne && twoNine && five.isEnabled()) {
                delayedMove(five);
            } else if (twoFive && twoNine && one.isEnabled()) {
                delayedMove(one);
            } else if (twoThree && twoFive && seven.isEnabled()) {
                delayedMove(seven);
            } else if (twoThree && twoSeven && five.isEnabled()) {
                delayedMove(five);
            } else if (twoFive && twoSeven && three.isEnabled()) {
                delayedMove(three);
            } else if (twoFour && twoFive && six.isEnabled()) {
                delayedMove(six);
            } else if (twoFour && twoSix && five.isEnabled()) {
                delayedMove(five);
            } else if (twoFive && twoSix && four.isEnabled()) {
                delayedMove(four);
            } else if (twoSeven && twoEight && nine.isEnabled()) {
                delayedMove(nine);
            } else if (twoSeven && twoNine && eight.isEnabled()) {
                delayedMove(eight);
            } else if (twoEight && twoNine && seven.isEnabled()) {
                delayedMove(seven);
            } else if (oneOne && three.isEnabled()) {
                delayedMove(three);
            } else if (oneThree && one.isEnabled()) {
                delayedMove(one);
            } else if (oneFive && eight.isEnabled()) {
                delayedMove(eight);
            } else if (oneEight && five.isEnabled()) {
                delayedMove(five);
            } else {
                if (five.isEnabled()) {
                    delayedMove(five);
                } else if (four.isEnabled()) {
                    delayedMove(four);
                } else if (six.isEnabled()) {
                    delayedMove(six);
                } else if (seven.isEnabled()) {
                    delayedMove(seven);
                } else if (three.isEnabled()) {
                    delayedMove(three);
                } else if (one.isEnabled()) {
                    delayedMove(one);
                } else if (nine.isEnabled()) {
                    delayedMove(nine);
                } else if (eight.isEnabled()) {
                    delayedMove(eight);
                }
            }
            two.setEnabled(false);
        }

        if (oneThree && three.isEnabled()) {
            if (twoOne && twoFour && seven.isEnabled()) {
                delayedMove(seven);
            } else if (twoOne && twoSeven && four.isEnabled()) {
                delayedMove(four);
            } else if (twoFour && twoSeven && one.isEnabled()) {
                delayedMove(one);
            } else if (twoTwo && twoFive && eight.isEnabled()) {
                delayedMove(eight);
            } else if (twoTwo && twoEight && five.isEnabled()) {
                delayedMove(five);
            } else if (twoFive && twoEight && two.isEnabled()) {
                delayedMove(two);
            } else if (twoOne && twoFive && nine.isEnabled()) {
                delayedMove(nine);
            } else if (twoOne && twoNine && five.isEnabled()) {
                delayedMove(five);
            } else if (twoFive && twoNine && one.isEnabled()) {
                delayedMove(one);
            }
            // 4 5 6
            else if (twoFour && twoFive && six.isEnabled()) {
                delayedMove(six);
            } else if (twoFour && twoSix && five.isEnabled()) {
                delayedMove(five);
            } else if (twoFive && twoSix && four.isEnabled()) {
                delayedMove(four);
            } else if (twoSeven && twoEight && nine.isEnabled()) {
                delayedMove(nine);
            } else if (twoSeven && twoNine && eight.isEnabled()) {
                delayedMove(eight);
            } else if (twoEight && twoNine && seven.isEnabled()) {
                delayedMove(seven);
            } else if (oneOne && two.isEnabled()) {
                delayedMove(two);
            } else if (oneTwo && one.isEnabled()) {
                delayedMove(one);
            } else if (oneSix && nine.isEnabled()) {
                delayedMove(nine);
            } else if (oneNine && six.isEnabled()) {
                delayedMove(six);
            } else if (oneFive && seven.isEnabled()) {
                delayedMove(seven);
            } else if (oneSeven && five.isEnabled()) {
                delayedMove(five);
            } else {
                if (five.isEnabled()) {
                    delayedMove(five);
                } else if (eight.isEnabled()) {
                    delayedMove(eight);
                } else if (four.isEnabled()) {
                    delayedMove(four);
                } else if (seven.isEnabled()) {
                    delayedMove(seven);
                } else if (two.isEnabled()) {
                    delayedMove(two);
                } else if (one.isEnabled()) {
                    delayedMove(one);
                } else if (six.isEnabled()) {
                    delayedMove(six);
                } else if (nine.isEnabled()) {
                    delayedMove(nine);
                }
            }

            three.setEnabled(false);

        }
        // four is clicked and is Enabled
        if (oneFour && four.isEnabled()) {
            // 1 2 3
            if (twoOne && twoTwo && three.isEnabled()) {
                delayedMove(three);
            } else if (twoOne && twoThree && two.isEnabled()) {
                delayedMove(two);
            } else if (twoTwo && twoThree && one.isEnabled()) {
                delayedMove(one);
            }
            // 3 6 9
            else if (twoThree && twoSix && nine.isEnabled()) {
                delayedMove(nine);
            } else if (twoThree && twoNine && six.isEnabled()) {
                delayedMove(six);
            } else if (twoSix && twoNine && three.isEnabled()) {
                delayedMove(three);
            }
            // 1 5 9
            else if (twoOne && twoFive && nine.isEnabled()) {
                delayedMove(nine);
            } else if (twoOne && twoNine && five.isEnabled()) {
                delayedMove(five);
            } else if (twoFive && twoNine && one.isEnabled()) {
                delayedMove(one);
            }
            // 3 5 7
            else if (twoThree && twoFive && seven.isEnabled()) {
                delayedMove(seven);
            } else if (twoThree && twoSeven && five.isEnabled()) {
                delayedMove(five);
            } else if (twoFive && twoSeven && three.isEnabled()) {
                delayedMove(three);
            }
            // 2 5 8
            else if (twoTwo && twoFive && eight.isEnabled()) {
                delayedMove(eight);
            } else if (twoTwo && twoEight && five.isEnabled()) {
                delayedMove(five);
            } else if (twoFive && twoEight && two.isEnabled()) {
                delayedMove(two);
            }
            // 7 8 9
            else if (twoSeven && twoEight && nine.isEnabled()) {
                delayedMove(nine);
            } else if (twoSeven && twoNine && eight.isEnabled()) {
                delayedMove(eight);
            } else if (twoEight && twoNine && seven.isEnabled()) {
                delayedMove(seven);
            } else if (oneFive && six.isEnabled())
                delayedMove(six);
            else if (oneSix && five.isEnabled())
                delayedMove(five);
            else if (oneOne && seven.isEnabled())
                delayedMove(seven);
            else if (oneSeven && one.isEnabled())
                delayedMove(one);
            else {
                if (five.isEnabled())
                    delayedMove(five);
                else if (two.isEnabled())
                    delayedMove(two);
                else if (three.isEnabled())
                    delayedMove(three);
                else if (eight.isEnabled())
                    delayedMove(eight);
                else if (nine.isEnabled())
                    delayedMove(nine);
                else if (six.isEnabled())
                    delayedMove(six);
                else if (one.isEnabled())
                    delayedMove(one);
                else if (seven.isEnabled())
                    delayedMove(seven);
            }

            four.setEnabled(false);
        }
        // five is clicked and enabled
        if (oneFive && five.isEnabled()) {
            // 1 2 3
            if (twoOne && twoTwo && three.isEnabled()) {
                delayedMove(three);
            } else if (twoOne && twoThree && two.isEnabled()) {
                delayedMove(two);
            } else if (twoTwo && twoThree && one.isEnabled()) {
                delayedMove(one);
            }
            // 3 6 9
            else if (twoThree && twoSix && nine.isEnabled()) {
                delayedMove(nine);
            } else if (twoThree && twoNine && six.isEnabled()) {
                delayedMove(six);
            } else if (twoSix && twoNine && three.isEnabled()) {
                delayedMove(three);
            }
            // 1 4 7
            else if (twoOne && twoFour && seven.isEnabled()) {
                delayedMove(seven);
            } else if (twoOne && twoSeven && four.isEnabled()) {
                delayedMove(four);
            } else if (twoFour && twoSeven && one.isEnabled()) {
                delayedMove(one);
            }
            // 7 8 9
            else if (twoSeven && twoEight && nine.isEnabled()) {
                delayedMove(nine);
            } else if (twoSeven && twoNine && eight.isEnabled()) {
                delayedMove(eight);
            } else if (twoEight && twoNine && seven.isEnabled()) {
                delayedMove(seven);
            } else if (oneFour && six.isEnabled())
                delayedMove(six);
            else if (oneSix && four.isEnabled())
                delayedMove(four);
            else if (oneOne && nine.isEnabled())
                delayedMove(nine);
            else if (oneNine && one.isEnabled())
                delayedMove(one);
            else if (oneThree && seven.isEnabled())
                delayedMove(seven);
            else if (oneSeven && three.isEnabled())
                delayedMove(three);
            else if (oneEight && two.isEnabled())
                delayedMove(two);
            else if (oneTwo && eight.isEnabled())
                delayedMove(eight);
            else {
                if (one.isEnabled())
                    delayedMove(one);
                else if (three.isEnabled())
                    delayedMove(three);
                else if (seven.isEnabled())
                    delayedMove(seven);
                else if (two.isEnabled())
                    delayedMove(two);
                else if (six.isEnabled())
                    delayedMove(six);
                else if (four.isEnabled())
                    delayedMove(four);
                else if (nine.isEnabled())
                    delayedMove(nine);
                else if (eight.isEnabled())
                    delayedMove(eight);
            }

            five.setEnabled(false);
        }
        // six is clicked
        if (oneSix && six.isEnabled()) {
            // 1 2 3
            if (twoOne && twoTwo && three.isEnabled()) {
                delayedMove(three);
            } else if (twoOne && twoThree && two.isEnabled()) {
                delayedMove(two);
            } else if (twoTwo && twoThree && one.isEnabled()) {
                delayedMove(one);
            }
            // 1 4 7
            else if (twoOne && twoFour && seven.isEnabled()) {
                delayedMove(seven);
            } else if (twoOne && twoSeven && four.isEnabled()) {
                delayedMove(four);
            } else if (twoFour && twoSeven && one.isEnabled()) {
                delayedMove(one);
            }
            // 1 5 9
            else if (twoOne && twoFive && nine.isEnabled()) {
                delayedMove(nine);
            } else if (twoOne && twoNine && five.isEnabled()) {
                delayedMove(five);
            } else if (twoFive && twoNine && one.isEnabled()) {
                delayedMove(one);
            }
            // 3 5 7
            else if (twoThree && twoFive && seven.isEnabled()) {
                delayedMove(seven);
            } else if (twoThree && twoSeven && five.isEnabled()) {
                delayedMove(five);
            } else if (twoFive && twoSeven && three.isEnabled()) {
                delayedMove(three);
            }
            // 2 5 8
            else if (twoTwo && twoFive && eight.isEnabled()) {
                delayedMove(eight);
            } else if (twoTwo && twoEight && five.isEnabled()) {
                delayedMove(five);
            } else if (twoFive && twoEight && two.isEnabled()) {
                delayedMove(two);
            }
            // 7 8 9
            else if (twoSeven && twoEight && nine.isEnabled()) {
                delayedMove(nine);
            } else if (twoSeven && twoNine && eight.isEnabled()) {
                delayedMove(eight);
            } else if (twoEight && twoNine && seven.isEnabled()) {
                delayedMove(seven);
            } else if (oneFour && five.isEnabled())
                delayedMove(five);
            else if (oneFive && four.isEnabled())
                delayedMove(four);
            else if (oneNine && three.isEnabled())
                delayedMove(three);
            else if (oneThree && nine.isEnabled())
                delayedMove(nine);
            else {
                if (five.isEnabled())
                    delayedMove(five);
                else if (two.isEnabled())
                    delayedMove(two);
                else if (seven.isEnabled())
                    delayedMove(seven);
                else if (eight.isEnabled())
                    delayedMove(eight);
                else if (one.isEnabled())
                    delayedMove(one);
                else if (four.isEnabled())
                    delayedMove(four);
                else if (three.isEnabled())
                    delayedMove(three);
                else if (nine.isEnabled())
                    delayedMove(nine);
            }

            six.setEnabled(false);
        }
        // seven is clicked
        if (oneSeven && seven.isEnabled()) {
            // 1 2 3
            if (twoOne && twoTwo && three.isEnabled()) {
                delayedMove(three);
            } else if (twoOne && twoThree && two.isEnabled()) {
                delayedMove(two);
            } else if (twoTwo && twoThree && one.isEnabled()) {
                delayedMove(one);
            }
            // 3 6 9
            else if (twoThree && twoSix && nine.isEnabled()) {
                delayedMove(nine);
            } else if (twoThree && twoNine && six.isEnabled()) {
                delayedMove(six);
            } else if (twoSix && twoNine && three.isEnabled()) {
                delayedMove(three);
            }
            // 1 5 9
            else if (twoOne && twoFive && nine.isEnabled()) {
                delayedMove(nine);
            } else if (twoOne && twoNine && five.isEnabled()) {
                delayedMove(five);
            } else if (twoFive && twoNine && one.isEnabled()) {
                delayedMove(one);
            }
            // 4 5 6
            else if (twoFour && twoFive && six.isEnabled()) {
                delayedMove(six);
            } else if (twoFour && twoSix && five.isEnabled()) {
                delayedMove(five);
            } else if (twoFive && twoSix && four.isEnabled()) {
                delayedMove(four);
            }
            // 2 5 8
            else if (twoTwo && twoFive && eight.isEnabled()) {
                delayedMove(eight);
            } else if (twoTwo && twoEight && five.isEnabled()) {
                delayedMove(five);
            } else if (twoFive && twoEight && two.isEnabled()) {
                delayedMove(two);
            } else if (oneOne && four.isEnabled())
                delayedMove(four);
            else if (oneFour && one.isEnabled())
                delayedMove(one);
            else if (oneThree && five.isEnabled())
                delayedMove(five);
            else if (oneFive && three.isEnabled())
                delayedMove(three);
            else if (oneEight && nine.isEnabled())
                delayedMove(nine);
            else if (oneNine && eight.isEnabled())
                delayedMove(eight);
            else {
                if (five.isEnabled())
                    delayedMove(five);
                else if (six.isEnabled())
                    delayedMove(six);
                else if (two.isEnabled())
                    delayedMove(two);
                else if (three.isEnabled())
                    delayedMove(three);
                else if (one.isEnabled())
                    delayedMove(one);
                else if (nine.isEnabled())
                    delayedMove(nine);
                else if (four.isEnabled())
                    delayedMove(four);
                else if (eight.isEnabled())
                    delayedMove(eight);
            }

            seven.setEnabled(false);
        }
        // eight clicked
        if (oneEight && eight.isEnabled()) {
            // 1 2 3
            if (twoOne && twoTwo && three.isEnabled()) {
                delayedMove(three);
            } else if (twoOne && twoThree && two.isEnabled()) {
                delayedMove(two);
            } else if (twoTwo && twoThree && one.isEnabled()) {
                delayedMove(one);
            }
            // 1 4 7
            else if (twoOne && twoFour && seven.isEnabled()) {
                delayedMove(seven);
            } else if (twoOne && twoSeven && four.isEnabled()) {
                delayedMove(four);
            } else if (twoFour && twoSeven && one.isEnabled()) {
                delayedMove(one);
            }
            // 3 6 9
            else if (twoThree && twoSix && nine.isEnabled()) {
                delayedMove(nine);
            } else if (twoThree && twoNine && six.isEnabled()) {
                delayedMove(six);
            } else if (twoSix && twoNine && three.isEnabled()) {
                delayedMove(three);
            }
            // 4 5 6
            else if (twoFour && twoFive && six.isEnabled()) {
                delayedMove(six);
            } else if (twoFour && twoSix && five.isEnabled()) {
                delayedMove(five);
            } else if (twoFive && twoSix && four.isEnabled()) {
                delayedMove(four);
            }
            // 1 5 9
            else if (twoOne && twoFive && nine.isEnabled()) {
                delayedMove(nine);
            } else if (twoOne && twoNine && five.isEnabled()) {
                delayedMove(five);
            } else if (twoFive && twoNine && one.isEnabled()) {
                delayedMove(one);
            }
            // 3 5 7
            else if (twoThree && twoFive && seven.isEnabled()) {
                delayedMove(seven);
            } else if (twoThree && twoSeven && five.isEnabled()) {
                delayedMove(five);
            } else if (twoFive && twoSeven && three.isEnabled()) {
                delayedMove(three);
            } else if (oneSeven && nine.isEnabled())
                delayedMove(nine);
            else if (oneNine && seven.isEnabled())
                delayedMove(seven);
            else if (oneFive && two.isEnabled())
                delayedMove(two);
            else if (oneTwo && five.isEnabled())
                delayedMove(five);
            else {
                if (five.isEnabled())
                    delayedMove(five);
                else if (three.isEnabled())
                    delayedMove(three);
                else if (one.isEnabled())
                    delayedMove(one);
                else if (six.isEnabled())
                    delayedMove(six);
                else if (four.isEnabled())
                    delayedMove(four);
                else if (two.isEnabled())
                    delayedMove(two);
                else if (one.isEnabled())
                    delayedMove(one);
                else if (seven.isEnabled())
                    delayedMove(seven);
                else if (nine.isEnabled())
                    delayedMove(nine);
            }

            eight.setEnabled(false);
        }
        if (oneNine && nine.isEnabled()) {

            // 1 2 3
            if (twoOne && twoTwo && three.isEnabled()) {
                delayedMove(three);
            } else if (twoOne && twoThree && two.isEnabled()) {
                delayedMove(two);
            } else if (twoTwo && twoThree && one.isEnabled()) {
                delayedMove(one);
            }
            // 1 4 7
            else if (twoOne && twoFour && seven.isEnabled()) {
                delayedMove(seven);
            } else if (twoOne && twoSeven && four.isEnabled()) {
                delayedMove(four);
            } else if (twoFour && twoSeven && one.isEnabled()) {
                delayedMove(one);
            }

            // 4 5 6
            else if (twoFour && twoFive && six.isEnabled()) {
                delayedMove(six);
            } else if (twoFour && twoSix && five.isEnabled()) {
                delayedMove(five);
            } else if (twoFive && twoSix && four.isEnabled()) {
                delayedMove(four);
            }
            // 3 5 7
            else if (twoThree && twoFive && seven.isEnabled()) {
                delayedMove(seven);
            } else if (twoThree && twoSeven && five.isEnabled()) {
                delayedMove(five);
            } else if (twoFive && twoSeven && three.isEnabled()) {
                delayedMove(three);
            }
            // 2 5 8
            else if (twoTwo && twoFive && eight.isEnabled()) {
                delayedMove(eight);
            } else if (twoTwo && twoEight && five.isEnabled()) {
                delayedMove(five);
            } else if (twoFive && twoEight && two.isEnabled()) {
                delayedMove(two);
            } else if (oneThree && six.isEnabled())
                delayedMove(six);
            else if (oneSix && three.isEnabled())
                delayedMove(three);
            else if (oneFive && one.isEnabled())
                delayedMove(one);
            else if (oneOne && five.isEnabled())
                delayedMove(five);
            else if (oneSeven && eight.isEnabled())
                delayedMove(eight);
            else if (oneEight && seven.isEnabled())
                delayedMove(seven);
            else {
                if (one.isEnabled())
                    delayedMove(one);
                else if (five.isEnabled())
                    delayedMove(five);
                else if (four.isEnabled())
                    delayedMove(four);
                else if (two.isEnabled())
                    delayedMove(two);
                else if (three.isEnabled())
                    delayedMove(three);
                else if (six.isEnabled())
                    delayedMove(six);
                else if (seven.isEnabled())
                    delayedMove(seven);
                else if (eight.isEnabled())
                    delayedMove(eight);
            }

            nine.setEnabled(false);
        }
        if (!oneOne && !oneTwo && !oneThree && !oneFour && !oneFive && !oneSix
                && !oneSeven && !oneEight && !oneNine && five.isEnabled()) {
            int rnd = ((GlobalVariable) this.getApplication()).randomNumber();
            if (rnd == 1) {
                delayedMove(five);
            } else if (rnd == 7) {
                delayedMove(eight);
            } else if (rnd == 2) {
                delayedMove(two);
            } else if (rnd == 4) {
                delayedMove(one);
            } else if (rnd == 8) {
                delayedMove(seven);
            } else if (rnd == 3) {
                delayedMove(four);
            } else if (rnd == 5) {
                delayedMove(one);
            } else if (rnd == 6) {
                delayedMove(six);
            } else if (rnd == 9) {
                delayedMove(three);
            }

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
    }

    public void delayedMove(final Button buton) {
        Handler handlr = new Handler();
        Runnable mMyRunnable = new Runnable() {
            @Override
            public void run() {
                buton.performClick();
                buton.setEnabled(false);
            }
        };
        handlr.postDelayed(mMyRunnable, 600);
    }

}

