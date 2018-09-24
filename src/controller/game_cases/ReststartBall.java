package controller.game_cases;

import com.almasb.fxgl.GameWorld;
import utils.Assets;
import com.almasb.fxgl.physics.PhysicsEntity;
import controller.key_actions.RestartGameAction;


import static hockey.HockeyRunner.*;

public class ReststartBall {


    public static void restartBall(PhysicsEntity ball, GameWorld gameWorld, Assets assets) {
        if (RestartGameAction.getBallRestart()) {
            RestartGameAction.setBallRestart(false);
            ball = getBallinitializer().getBall(assets);
            gameWorld.addEntity(ball);
        }
    }
}
