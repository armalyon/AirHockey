package controller.corrections;

import com.almasb.fxgl.physics.PhysicsEntity;
import javafx.geometry.Point2D;

import static hockey.HockeyRunner.SCREEN_WIDTH;
import static hockey.HockeyRunner.getBall;
import static model.components.Ballinitializer.BALL_RADIUS;
import static model.components.BoundsInitialization.VERTICAL_BOUND_WIDTH;

public class BallSpeedCorrection {
    private Point2D ballSpeed;
    private static int ballXDirection;


    public void ballSpeedCorrection(PhysicsEntity ball) {
        ballSpeed = ball.getLinearVelocity();
        double xSpeed = ballSpeed.getX();
        double ySpeed = ballSpeed.getY();
        if (xSpeed < 0) ballXDirection = -1;
        if (xSpeed > 0) ballXDirection = 1;
        if (Math.abs(xSpeed) <= 3 && ySpeed != 0) ball.setLinearVelocity(ballXDirection * 5, ySpeed);
    }

    private static boolean isBallInOutZone1(double x) {
        return x >= VERTICAL_BOUND_WIDTH && x < VERTICAL_BOUND_WIDTH + BALL_RADIUS*2  + 15;
    }

    private static boolean isBallInOutZone2(double x) {
        return x < SCREEN_WIDTH - VERTICAL_BOUND_WIDTH  &&
                x >= SCREEN_WIDTH - VERTICAL_BOUND_WIDTH - BALL_RADIUS*2 - 15;
    }

    public static void fixBallOutBug(){
        if (isBallInOutZone1(getBall().getX())) {
            double ballXSpeed = getBall().getLinearVelocity().getX();
            double ballYSpeed = getBall().getLinearVelocity().getY();
            if (Math.abs(ballXSpeed) < 1) getBall().setLinearVelocity(8, ballYSpeed);

        }
        if (isBallInOutZone2(getBall().getX())) {
            double ballXSpeed = getBall().getLinearVelocity().getX();
            double ballYSpeed = getBall().getLinearVelocity().getY();
            if (Math.abs(ballXSpeed) > -1) getBall().setLinearVelocity(-5, ballYSpeed);
            }



    }


}
