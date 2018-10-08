package controller.corrections;

import com.almasb.fxgl.physics.PhysicsEntity;
import hockey.HockeyRunner;
import javafx.geometry.Point2D;

import static hockey.HockeyRunner.SCREEN_HEIGHT;
import static hockey.HockeyRunner.SCREEN_WIDTH;
import static model.components.Ballinitializer.BALL_RADIUS;
import static model.components.BatInitializer.*;
import static model.components.BoundsInitialization.HORIZONTAL_BOUND_HEIGHT;

public class BallSpeedCorrection {
    private Point2D ballSpeed;
    private static int ballXDirection;


    public void ballSpeedCorrection(PhysicsEntity ball) {
        ballSpeed = ball.getLinearVelocity();
        double ballX = ball.getX();
        double ballY = ball.getY();
        double xSpeed = ballSpeed.getX();
        double ySpeed = ballSpeed.getY();
        if (xSpeed < 0) ballXDirection = -1;
        if (xSpeed > 0) ballXDirection = 1;
        if (Math.abs(xSpeed) <= 3 && ySpeed != 0) ball.setLinearVelocity(ballXDirection * 6, ySpeed);
        if (isBallOutLeftUp(ballX, ballY)) ball.setLinearVelocity(-10, 10);
        if (isBallInLeftUp(ballX, ballY)) ball.setLinearVelocity(10, 10);
        if (isBallOutLeftDown(ballX, ballY)) ball.setLinearVelocity(-10, -10);
        if (isBallInLeftDown(ballX, ballY)) ball.setLinearVelocity(10, -10);
        if (isBallOutRightUp(ballX, ballY)) ball.setLinearVelocity(10, -10);
        if (isBallInRightUp(ballX, ballY)) ball.setLinearVelocity(-10, 10);
        if (isBallOutRightDown(ballX, ballY)) ball.setLinearVelocity(10, -10);
        if (isBallInRightDown(ballX, ballY)) ball.setLinearVelocity(-10, -10);
    }


    private static boolean isBallOutLeftUp(double x, double y) {
        PhysicsEntity leftBat = HockeyRunner.getLeftBat();
        return x < LEFT_BAT_X + BAT_WIDTH / 2 && x > 0 &&
                y >= 0 && y <= HORIZONTAL_BOUND_HEIGHT + 1 &&
                leftBat.getY() <= HORIZONTAL_BOUND_HEIGHT + BALL_RADIUS * 2 + 10;
    }

    private static boolean isBallInLeftUp(double x, double y) {
        PhysicsEntity leftBat = HockeyRunner.getLeftBat();
        return x >= LEFT_BAT_X + BAT_WIDTH / 2 && x <= LEFT_BAT_X + BAT_WIDTH &&
                y >= 0 && y <= HORIZONTAL_BOUND_HEIGHT + 1 &&
                leftBat.getY() <= HORIZONTAL_BOUND_HEIGHT + BALL_RADIUS * 2 + 10;
    }


    private static boolean isBallOutLeftDown(double x, double y) {
        PhysicsEntity leftBat = HockeyRunner.getLeftBat();
        return x < LEFT_BAT_X + BAT_WIDTH / 2 && x > 0 &&
                y <= SCREEN_HEIGHT && y >= SCREEN_HEIGHT - HORIZONTAL_BOUND_HEIGHT - 1 &&
                leftBat.getY() + BAT_HEIGHT >= SCREEN_HEIGHT - HORIZONTAL_BOUND_HEIGHT - BALL_RADIUS * 2 - 10;
    }

    private static boolean isBallInLeftDown(double x, double y) {
        PhysicsEntity leftBat = HockeyRunner.getLeftBat();
        return x >= LEFT_BAT_X + BAT_WIDTH / 2 && x <= LEFT_BAT_X + BAT_WIDTH &&
                y <= SCREEN_HEIGHT && y >= SCREEN_HEIGHT - HORIZONTAL_BOUND_HEIGHT - 1 &&
                leftBat.getY() + BAT_HEIGHT >= SCREEN_HEIGHT - HORIZONTAL_BOUND_HEIGHT - BALL_RADIUS * 2 - 10;
    }


    private static boolean isBallInRightUp(double x, double y) {
        PhysicsEntity rightBat = HockeyRunner.getRightBat();
        return x >= RIGHT_BAT_X && x <= RIGHT_BAT_X - BAT_WIDTH / 2 &&
                y >= 0 && y <= HORIZONTAL_BOUND_HEIGHT + 1 &&
                rightBat.getY() <= HORIZONTAL_BOUND_HEIGHT + BALL_RADIUS * 2 + 10;
    }

    private static boolean isBallOutRightUp(double x, double y) {
        PhysicsEntity rightBat = HockeyRunner.getRightBat();
        return x > RIGHT_BAT_X - BAT_WIDTH / 2 && x <= SCREEN_WIDTH &&
                y >= 0 && y <= HORIZONTAL_BOUND_HEIGHT + 1 &&
                rightBat.getY() <= HORIZONTAL_BOUND_HEIGHT + BALL_RADIUS * 2 + 10;
    }


    private static boolean isBallOutRightDown(double x, double y) {
        PhysicsEntity rightBat = HockeyRunner.getRightBat();
        return x > RIGHT_BAT_X - BAT_WIDTH / 2 && x <= SCREEN_WIDTH &&
                y <= SCREEN_HEIGHT && y >= SCREEN_HEIGHT - HORIZONTAL_BOUND_HEIGHT - 1 &&
                rightBat.getY() + BAT_HEIGHT >= SCREEN_HEIGHT - HORIZONTAL_BOUND_HEIGHT - BALL_RADIUS * 2 - 10;
    }

    private static boolean isBallInRightDown(double x, double y) {
        PhysicsEntity rightBat = HockeyRunner.getRightBat();
        return x >= RIGHT_BAT_X && x <= RIGHT_BAT_X - BAT_WIDTH / 2 &&
                y <= SCREEN_HEIGHT && y >= SCREEN_HEIGHT - HORIZONTAL_BOUND_HEIGHT - 1 &&
                rightBat.getY() + BAT_HEIGHT >= SCREEN_HEIGHT - HORIZONTAL_BOUND_HEIGHT - BALL_RADIUS * 2 - 10;
    }


}
