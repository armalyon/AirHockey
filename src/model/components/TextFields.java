package model.components;

import hockey.HockeyRunner;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static hockey.HockeyRunner.SCREEN_HEIGHT;
import static hockey.HockeyRunner.SCREEN_WIDTH;

public class TextFields {

    private static final String FONT_NAME = "Halogen Gas Lights";
    private static final String FONT_COLOR = "paleturquoise";
    public static final String PLAYER1_WINS_TEXT = "PLAYER 1 WINS!";
    public static final String PLAYER2_WINS_TEXT = "PLAYER 2 WINS!";
    public static final String YOU_WIN_TEXT = "\t YOU WIN!";
    public static final String COMPUTER_WINS_TEXT = "COMPUTER WINS!";

    private static final String PRESS_ENTER_TO_START = "Press \"1\" for 1 player game" +
            System.lineSeparator() + "Press \"2\" for 2 player game" ;

    public static final String PRESS_SPACE_TO_RESTART = "Press \"Space\" to restart the game";
    public static final String PAUSED_TEXT = "Paused";
    private static final String PLAYER_1_CONTROLS = " Up - \"A\"" + System.lineSeparator() + "Down - \"Z\"";
    private static final String PLAYER_2_CONTROLS = " Up - \"⬆\"" + System.lineSeparator() + "Down - \"⬇\"";
    private static final String PAUSE_CONTROL_TEXT = "Pause - \"P \"";


    private static final int BIGGEST_TEXT_SIZE = 60;
    private static final int SCORE_TEXT_Y = 60;
    private static final int SCORE_FONT_SIZE = 20;
    private static final int MIDDLE_FONT_SIZE = 40;


    public static Text initScoreText() {
        Text scoreText = new Text("SCORE:");
        scoreText.setTranslateY(SCORE_TEXT_Y);
        scoreText.setFont(Font.font(FONT_NAME, SCORE_FONT_SIZE));
        scoreText.fillProperty().set(Paint.valueOf(FONT_COLOR));
        scoreText.setTranslateX(SCREEN_WIDTH / 2 - 30);

        return scoreText;
    }

    public static Text initScoreDividerText() {
        Text scoreDivider = new Text("|");
        scoreDivider.setTranslateY(SCORE_TEXT_Y + SCORE_FONT_SIZE + 5);
        scoreDivider.setFont(Font.font(FONT_NAME, SCORE_FONT_SIZE));
        scoreDivider.fillProperty().set(Paint.valueOf(FONT_COLOR));
        scoreDivider.setTranslateX(SCREEN_WIDTH / 2);

        return scoreDivider;

    }

    public static Text initScore1Text() {
        Text score1 = new Text();
        score1.setTranslateY(SCORE_TEXT_Y + SCORE_FONT_SIZE + 5);
        score1.setFont(Font.font(FONT_NAME, SCORE_FONT_SIZE));
        score1.fillProperty().set(Paint.valueOf(FONT_COLOR));
        score1.setTranslateX(SCREEN_WIDTH / 2 - 25);
        score1.textProperty().bind(HockeyRunner.getScore1().asString());

        return score1;
    }

    public static Text initScore2Text() {
        Text score1 = new Text();
        score1.setTranslateY(SCORE_TEXT_Y + SCORE_FONT_SIZE + 5);
        score1.setFont(Font.font(FONT_NAME, SCORE_FONT_SIZE));
        score1.fillProperty().set(Paint.valueOf(FONT_COLOR));
        score1.setTranslateX(SCREEN_WIDTH / 2 + 17);
        score1.textProperty().bind(HockeyRunner.getScore2().asString());

        return score1;
    }


    public static Text initPlayerWinsText() {
        Text p1w = new Text();
        p1w.setFont(Font.font(FONT_NAME, BIGGEST_TEXT_SIZE));
        p1w.fillProperty().set(Paint.valueOf(FONT_COLOR));
        p1w.setTranslateX(SCREEN_WIDTH / 2 - 250);
        p1w.setTranslateY(300);
        return p1w;
    }


    public static Text initRestartText() {
        Text pressEnter = new Text();
        pressEnter.setFont(Font.font(FONT_NAME, MIDDLE_FONT_SIZE));
        pressEnter.fillProperty().set(Paint.valueOf(FONT_COLOR));
        pressEnter.setTranslateX(SCREEN_WIDTH / 2 - 390);
        pressEnter.setTranslateY(SCREEN_HEIGHT - 150);
        return pressEnter;
    }

    public static Text initStartText() {
        Text pressEnter = new Text(PRESS_ENTER_TO_START);
        pressEnter.setFont(Font.font(FONT_NAME, MIDDLE_FONT_SIZE));
        pressEnter.fillProperty().set(Paint.valueOf(FONT_COLOR));
        pressEnter.setTranslateX(SCREEN_WIDTH / 2 - 350);
        pressEnter.setTranslateY(150);
        return pressEnter;
    }

    public static Text initControls1Text() {
        Text c1 = new Text(PLAYER_1_CONTROLS);
        c1.setFont(Font.font(FONT_NAME, SCORE_FONT_SIZE));
        c1.fillProperty().set(Paint.valueOf(FONT_COLOR));
        c1.setTranslateX(65);
        c1.setTranslateY(SCREEN_HEIGHT / 2 - 10);
        return c1;
    }


    public static Text initControls2Text() {
        Text c1 = new Text(PLAYER_2_CONTROLS);
        c1.setFont(Font.font(FONT_NAME, SCORE_FONT_SIZE));
        c1.fillProperty().set(Paint.valueOf(FONT_COLOR));
        c1.setTranslateX(SCREEN_WIDTH - 185);
        c1.setTranslateY(SCREEN_HEIGHT / 2 - 10);
        return c1;
    }

    public static Text initPauseText() {
        Text c1 = new Text();
        c1.setFont(Font.font(FONT_NAME, MIDDLE_FONT_SIZE));
        c1.fillProperty().set(Paint.valueOf(FONT_COLOR));
        c1.setTranslateX(SCREEN_WIDTH/2 - 65);
        c1.setTranslateY(SCREEN_HEIGHT - 50);
        return c1;
    }

    public static Text initPauseControlText() {
        Text c1 = new Text(PAUSE_CONTROL_TEXT);
        c1.setFont(Font.font(FONT_NAME, MIDDLE_FONT_SIZE));
        c1.fillProperty().set(Paint.valueOf(FONT_COLOR));
        c1.setTranslateX(SCREEN_WIDTH/2 - 115);
        c1.setTranslateY(SCREEN_HEIGHT / 2 );
        return c1;
    }





}




