package controller.game_cases;

import com.almasb.fxgl.GameWorld;
import com.almasb.fxgl.physics.PhysicsEntity;
import hockey.HockeyRunner;
import javafx.beans.property.IntegerProperty;
import model.Assets;
import utils.Utils;

import static controller.game_cases.RestartFunctions.isRestartReadyStatus;
import static hockey.HockeyRunner.*;
import static model.components.Ballinitializer.*;
import static model.components.Ballinitializer.getBall;

public class ScoreCounter {


    public static void countScore1(GameWorld gameWorld, Assets assets) {
        PhysicsEntity ball = HockeyRunner.getBall();
        IntegerProperty score1 = HockeyRunner.getScore1();

        if (!isRestartReadyStatus() && ball.getX() > SCREEN_WIDTH + 50) {
            ball = getBall(assets);
            HockeyRunner.setBall(ball);
            if (getRightBat().getY() < SCREEN_HEIGHT / 2) {
                ball.setPosition(BALL_RIGHT_ALT_POSITION);
            } else {
                ball.setPosition(BALL_RIGHT_POSITION);
            }
            score1.setValue(score1.get() + 1);
            Utils.timer(500);
            gameWorld.addEntity(ball);
        }
    }


    public static void countScore2(GameWorld gameWorld, Assets assets) {
        IntegerProperty score2 = HockeyRunner.getScore2();
        PhysicsEntity ball = HockeyRunner.getBall();

        if (!isRestartReadyStatus() && ball.getX() < -50) {
            ball = getBall(assets);
            HockeyRunner.setBall(ball);
            if (getLeftBat().getY() < SCREEN_HEIGHT / 2) {
                ball.setPosition(BALL_LEFT_ALT_POSITION);
            } else {
                ball.setPosition(BALL_LEFT_POSITION);
            }

            score2.setValue(score2.get() + 1);
            Utils.timer(500);
            gameWorld.addEntity(ball);
        }
    }


}
