package controller.game_cases;

import com.almasb.fxgl.physics.PhysicsEntity;

import javafx.geometry.Point2D;

import static controller.key_actions.PauseAction.isPausePerformed;
import static hockey.HockeyRunner.*;
import static model.components.TextFields.PAUSED_TEXT;

public class PauseFunction {
    private static Point2D ballSpeedBeforePause;
    private static boolean pauseFlag = false;


    public void pauseGame() {
        PhysicsEntity ball = getBall();
        PhysicsEntity rightBat  = getRightBat();
        PhysicsEntity leftBat = getLeftBat();

        if (isPausePerformed() && !pauseFlag) {
            ballSpeedBeforePause = ball.getLinearVelocity();
            pauseFlag = true;
            if (pauseFlag){
                ball.setLinearVelocity(0, 0);
                leftBat.setLinearVelocity(0, 0);
                rightBat.setLinearVelocity(0, 0);}
            getPausetext().setText(PAUSED_TEXT);
        }
        if (!isPausePerformed() && pauseFlag) {
            ball.setLinearVelocity(ballSpeedBeforePause);
            pauseFlag = false;
            getPausetext().setText("");
        }

    }
}
