package controller.corrections;

import com.almasb.fxgl.physics.PhysicsEntity;
import javafx.geometry.Point2D;

import static hockey.HockeyRunner.*;
import static model.components.Ballinitializer.BALL_RADIUS;
import static model.components.BatInitializer.BAT_WIDTH;
import static model.components.BoundsInitialization.HORIZONTAL_BOUND_HEIGHT;
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
        if (isBallOutLeftUp(ball.getX(), ball.getY())) ball.setLinearVelocity(7, 7);
        if (isBallOutLeftDown(ball.getX(), ball.getY())) ball.setLinearVelocity(7, -7);
        if (isBallOutRightUp(ball.getX(), ball.getY())) ball.setLinearVelocity(-7, 7);
        if (isBallOutRightDown(ball.getX(),ball.getY()))ball.setLinearVelocity(-7, -7);

    }

    private static boolean isBallInOutZone1(double x) {
        return x >= VERTICAL_BOUND_WIDTH && x < VERTICAL_BOUND_WIDTH + BALL_RADIUS * 2 + 15;
    }

    private static boolean isBallInOutZone2(double x) {
        return x < SCREEN_WIDTH - VERTICAL_BOUND_WIDTH &&
                x >= SCREEN_WIDTH - VERTICAL_BOUND_WIDTH - BALL_RADIUS * 2 - 15;
    }

    private static boolean isBallOutLeftUp(double x, double y) {
        return x >= VERTICAL_BOUND_WIDTH && x <= VERTICAL_BOUND_WIDTH + BAT_WIDTH &&
                y >= 0 && y <= HORIZONTAL_BOUND_HEIGHT;
    }

    private static boolean isBallOutLeftDown(double x, double y) {
        return x >= VERTICAL_BOUND_WIDTH && x <= VERTICAL_BOUND_WIDTH + BAT_WIDTH &&
                y <= SCREEN_HEIGHT && y >= SCREEN_HEIGHT - HORIZONTAL_BOUND_HEIGHT;
    }

    private static boolean isBallOutRightUp(double x, double y) {
        return x >= SCREEN_WIDTH - VERTICAL_BOUND_WIDTH - BAT_WIDTH &&
                x <= SCREEN_WIDTH - VERTICAL_BOUND_WIDTH &&
                y >= 0 && y <= HORIZONTAL_BOUND_HEIGHT;
    }

    private static boolean isBallOutRightDown (double x, double y) {
        return x >= SCREEN_WIDTH - VERTICAL_BOUND_WIDTH - BAT_WIDTH &&
                x <= SCREEN_WIDTH - VERTICAL_BOUND_WIDTH &&
                y <= SCREEN_HEIGHT && y >= SCREEN_HEIGHT - HORIZONTAL_BOUND_HEIGHT;
    }


    public static void fixBallOutBug() {
        if (isBallInOutZone1(getBall().getX())) {
            double ballXSpeed = getBall().getLinearVelocity().getX();
            double ballYSpeed = getBall().getLinearVelocity().getY();
            if (Math.abs(ballXSpeed) < 1) getBall().setLinearVelocity(6, ballYSpeed);

        }
        if (isBallInOutZone2(getBall().getX())) {
            double ballXSpeed = getBall().getLinearVelocity().getX();
            double ballYSpeed = getBall().getLinearVelocity().getY();
            if (Math.abs(ballXSpeed) > -1) getBall().setLinearVelocity(-6, ballYSpeed);
        }


    }


}
