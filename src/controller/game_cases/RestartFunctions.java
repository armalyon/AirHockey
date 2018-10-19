package controller.game_cases;

import com.almasb.fxgl.GameWorld;
import com.almasb.fxgl.physics.PhysicsEntity;
import controller.game_bot.PointersTimer;
import controller.key_actions.RestartGameAction;
import javafx.beans.property.IntegerProperty;
import javafx.scene.text.Text;
import model.Assets;
import model.components.Ballinitializer;

import static controller.key_actions.Start1PlGameAction.isOnePlayerMode;
import static hockey.HockeyRunner.*;
import static model.components.TextFields.*;

public class RestartFunctions {
    private static boolean restartReadyStatus = false;
    private static boolean textFlag = false;
    private static boolean pointersFlag = false;

    public static void restartBall(GameWorld gameWorld, Assets assets) {
        if (RestartGameAction.getBallRestart()) {
            RestartGameAction.setBallRestart(false);
            PhysicsEntity ball = Ballinitializer.getBall(assets);
            setBall(ball);
            gameWorld.addEntity(ball);
        }
    }

    public static void startNewGame(boolean startReadyStatus) {

        if (!startReadyStatus && !textFlag) {
            Text player1controls = getPlayer1controls();
            Text player2controls = getPlayer2controls();
            Text startText = getStartText();
            Text pauseControlText = getPauseControlText();

            player1controls.setText("");
            player2controls.setText("");
            startText.setText("");
            pauseControlText.setText("");
            textFlag = true;
        }
        startPointers();


    }

    private static void startPointers() {

        if (textFlag && !pointersFlag) {

            Text leftPointer = getLeftPointer();
            Text rightPointer = getRightPointer();
            if (isOnePlayerMode()) {
                leftPointer.setText(COMPUTER_POINTER);
                rightPointer.setText(YOU_POINTER);

            } else {
                leftPointer.setText(PLAYER_1_POINTER);
                rightPointer.setText(PLAYER_2_POINTER);
            }
            textFlag = false;
            endPointers();


        }
    }


    private static void endPointers() {
        if (!pointersFlag) {
            PointersTimer pointersTimer = new PointersTimer(1500);
            pointersTimer.start();
            pointersFlag = true;
        }

    }


    public static void setGameEnd() {
        IntegerProperty score1 = getScore1();
        IntegerProperty score2 = getScore2();
        if (score1.get() == FINAL_SCORE || score2.get() == FINAL_SCORE) {
            restartReadyStatus = true;
            PhysicsEntity ball = getBall();
            ball.setPosition(200, -200);
            getRestartText().setText(PRESS_SPACE_TO_RESTART);
            setWinText();
        }
    }


    private static void setWinText() {
        IntegerProperty score1 = getScore1();
        if (!isOnePlayerMode()) {
            if (score1.get() == FINAL_SCORE) {
                getWinText().setText(PLAYER1_WINS_TEXT);
            } else getWinText().setText(PLAYER2_WINS_TEXT);
        } else {
            if (score1.get() == FINAL_SCORE) {
                getWinText().setText(COMPUTER_WINS_TEXT);
            } else getWinText().setText(YOU_WIN_TEXT);

        }
    }


    public static boolean isRestartReadyStatus() {
        return restartReadyStatus;
    }

    public static void setRestartReadyStatus(boolean restartReadyStatus) {
        RestartFunctions.restartReadyStatus = restartReadyStatus;
    }

    public static void setPointersFlag(boolean pointersFlag) {
        RestartFunctions.pointersFlag = pointersFlag;
    }
}
