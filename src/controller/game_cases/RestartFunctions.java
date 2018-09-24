package controller.game_cases;

import com.almasb.fxgl.GameWorld;
import hockey.HockeyRunner;
import javafx.scene.text.Text;
import utils.Assets;
import com.almasb.fxgl.physics.PhysicsEntity;
import controller.key_actions.RestartGameAction;


import static hockey.HockeyRunner.*;

public class RestartFunctions {
    private static boolean restartReadyStatus = false;





    public static void restartBall( GameWorld gameWorld, Assets assets) {
        if (RestartGameAction.getBallRestart()) {
            RestartGameAction.setBallRestart(false);
            PhysicsEntity ball  = getBallinitializer().getBall(assets);
            HockeyRunner.setBall(ball);
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

    public static boolean isRestartReadyStatus() {
        return restartReadyStatus;
    }

    public static void setRestartReadyStatus(boolean restartReadyStatus) {
        RestartFunctions.restartReadyStatus = restartReadyStatus;
    }
}
