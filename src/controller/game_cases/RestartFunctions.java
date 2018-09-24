package controller.game_cases;

import com.almasb.fxgl.GameWorld;
import javafx.beans.property.IntegerProperty;
import javafx.scene.text.Text;
import utils.Assets;
import com.almasb.fxgl.physics.PhysicsEntity;
import controller.key_actions.RestartGameAction;


import static hockey.HockeyRunner.*;
import static model.components.TextFields.PLAYER1_WINS_TEXT;
import static model.components.TextFields.PLAYER2_WINS_TEXT;
import static model.components.TextFields.PRESS_SPACE_TO_RESTART;

public class RestartFunctions {
    private static boolean restartReadyStatus = false;

    public static void restartBall( GameWorld gameWorld, Assets assets) {
        if (RestartGameAction.getBallRestart()) {
            RestartGameAction.setBallRestart(false);
            PhysicsEntity ball  = getBallinitializer().getBall(assets);
            setBall(ball);
            gameWorld.addEntity(ball);
        }
    }

    public static void startNewGame(boolean startReadyStatus, Text player1controls, Text player2controls,
                                    Text startText, Text pauseControlText ) {
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
        if (score1.get() == FINAL_SCORE || getScore2().get() == FINAL_SCORE) {
            restartReadyStatus = true;
            PhysicsEntity ball = getBall();
            ball.setPosition(200, -200);
            getRestartText().setText(PRESS_SPACE_TO_RESTART);
            if (score1.get()==FINAL_SCORE){
                getWinText().setText(PLAYER1_WINS_TEXT);
            } else   getWinText().setText(PLAYER2_WINS_TEXT);
        }
    }


    public static boolean isRestartReadyStatus() {
        return restartReadyStatus;
    }

    public static void setRestartReadyStatus(boolean restartReadyStatus) {
        RestartFunctions.restartReadyStatus = restartReadyStatus;
    }
}
