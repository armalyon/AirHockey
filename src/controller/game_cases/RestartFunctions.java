package controller.game_cases;

import com.almasb.fxgl.GameWorld;
import javafx.scene.text.Text;
import utils.Assets;
import com.almasb.fxgl.physics.PhysicsEntity;
import controller.key_actions.RestartGameAction;


import static hockey.HockeyRunner.*;

public class RestartFunctions {


    public static void restartBall(PhysicsEntity ball, GameWorld gameWorld, Assets assets) {
        if (RestartGameAction.getBallRestart()) {
            RestartGameAction.setBallRestart(false);
            ball = getBallinitializer().getBall(assets);
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
}
