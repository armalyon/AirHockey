package controller.game_cases;

import com.almasb.fxgl.GameWorld;
import com.almasb.fxgl.physics.PhysicsEntity;
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

    public static void restartBall(GameWorld gameWorld, Assets assets) {
        if (RestartGameAction.getBallRestart()) {
            RestartGameAction.setBallRestart(false);
            PhysicsEntity ball = Ballinitializer.getBall(assets);
            setBall(ball);
            gameWorld.addEntity(ball);
        }
    }

    public static void startNewGame(boolean startReadyStatus) {
        Text player1controls = getPlayer1controls();
        Text player2controls = getPlayer2controls();
        Text startText = getStartText();
        Text pauseControlText = getPauseControlText();
        if (!startReadyStatus) {
            player1controls.setText("");
            player2controls.setText("");
            startText.setText("");
            pauseControlText.setText("");
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
}
