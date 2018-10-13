package controller.game_bot;


import com.almasb.fxgl.physics.PhysicsEntity;
import hockey.HockeyRunner;
import javafx.geometry.Point2D;

import static controller.key_actions.PauseAction.isPausePerformed;
import static controller.key_actions.Start1PlGameAction.isOnePlayerMode;
import static hockey.HockeyRunner.*;
import static hockey.HockeyRunner.getLeftBat;
import static model.components.Ballinitializer.BALL_LEFT_POSITION;
import static model.components.Ballinitializer.BALL_RADIUS;
import static model.components.BatInitializer.*;
import static model.components.BoundsInitialization.HORIZONTAL_BOUND_HEIGHT;


public class BotControl {

    private static final double UPPER_BOUND = HORIZONTAL_BOUND_HEIGHT + 10;
    private static final double LOWER_BOUND = SCREEN_HEIGHT - HORIZONTAL_BOUND_HEIGHT - 10;
    private static boolean isLowerCollision = false;
    private static boolean isUpperCollision = false;
    private static boolean isFirstStrikeReady = false;
    private static boolean isTimerWorks = false;
    private static final Point2D zero = new Point2D(0, 0);
    private static double strikePointY;
    private static boolean isStrikeCalculated;
    private static final double CALC_BALL_POS = ((double) SCREEN_WIDTH) / 5;
    private static final double STRIKE_POS_X = LEFT_BAT_X + BAT_WIDTH;


    public static void botScript() {
        if (isOnePlayerMode() && getBall().isActive() && !isPausePerformed()) {
            firstStrike();
            moveToCenterAfterStrike();
            moveToStrikePosition();


        }

    }


    private static void calcStrikePosition() {
        PhysicsEntity ball = getBall();
        if (ball.getX() < CALC_BALL_POS && !isStrikeCalculated && ball.getLinearVelocity().getX() < 0) {
            if (ball.getLinearVelocity().getY() == 0) {
                if (ball.getY() >= SCREEN_HEIGHT / 2) strikePointY = ball.getY() - BAT_HEIGHT / 2 - 1;
                else strikePointY = ball.getY() + BAT_HEIGHT / 2 + 1;

            } else {
                if (isNoRebounds(ball.getX(), ball.getY(), ball.getLinearVelocity().getY())) {
                    strikePointY = calcY(ball.getX(), ball.getY(), ball.getLinearVelocity().getY()) +
                            randomOffset(0.8);

                } else {

                    double speedX = ball.getLinearVelocity().getX();
                    double speedY = ball.getLinearVelocity().getY();
                    double y = ball.getY();
                    double x = ball.getX();

                    while (!isNoRebounds(x, y, speedY)) {
                        if (speedY < 0) {
                            x += ((y - UPPER_BOUND) / Math.abs(speedY)) * speedX;
                            y = UPPER_BOUND;
                        }
                        if (speedY > 0) {
                            x += ((LOWER_BOUND - y - BALL_RADIUS * 2) / Math.abs(speedY)) * speedX;
                            y = LOWER_BOUND - BALL_RADIUS * 2;
                        }
                        speedY *= -1;
                    }
                    strikePointY = calcY(x, y, speedY) + randomOffset(1);
                }
            }
            isStrikeCalculated = true;

        }

    }

    private static double randomOffset(double factor) {
        double offset = factor * BAT_HEIGHT * Math.random();
        int signFactor;
        double x = Math.random();
        if (x < 0.5) signFactor = -1;
        else signFactor = 1;
        offset *= signFactor;
        return offset;
    }


    public static void setIsStrikeCalculated(boolean isStrikeCalculated) {
        BotControl.isStrikeCalculated = isStrikeCalculated;
    }

    private static boolean isNoRebounds(double ballX, double ballY, double ballYSpeed) {
        double y = calcY(ballX, ballY, ballYSpeed);
        return y > HORIZONTAL_BOUND_HEIGHT && y < SCREEN_HEIGHT - HORIZONTAL_BOUND_HEIGHT;
    }

    private static double calcY(double ballX, double ballY, double ballYSpeed) {
        return ballY + ((ballX - STRIKE_POS_X) / Math.abs(getBall().getLinearVelocity().getX())) * ballYSpeed;
    }


    private static void moveToStrikePosition() {
        if (getBall().getX() < CALC_BALL_POS && getBall().getLinearVelocity().getX() < 0) {
            calcStrikePosition();
            move(strikePointY);
        }

    }


    private static void firstStrike() {
        PhysicsEntity ball = HockeyRunner.getBall();

        if (ball.getLinearVelocity().equals(zero) &&
                ball.getX() == BALL_LEFT_POSITION.getX()) {
            FirstStrikeTimer timer = new FirstStrikeTimer(1500);

            if (!isTimerWorks && timer.getState() == Thread.State.NEW) {
                timer.start();
                isTimerWorks = true;

            }
            if (isFirstStrikeReady) {
                move(ball.getY());

            }

            if (ball.getX() > BALL_LEFT_POSITION.getX()) {
                isTimerWorks = false;
                isFirstStrikeReady = false;

            }
        }

    }

    private static void moveToCenterAfterStrike() {
        if (getBall().getLinearVelocity().getX() > 1 && getBall().getX() > SCREEN_WIDTH / 5)
            move(SCREEN_HEIGHT / 2);
    }

    private static void stop() {
        getLeftBat().setLinearVelocity(0, 0);
    }


    private static void move(double yCenter) {
        if (!(getBatCanterY() > yCenter - 8 && getBatCanterY() < yCenter + 8)) {
            if (yCenter - 25 < getBatCanterY()) moveUp(yCenter);
            if (yCenter + 25 > getBatCanterY()) moveDown(yCenter);
        }
    }

    private static void moveUp(double yCenter) {
        if (yCenter - BAT_HEIGHT / 2 < UPPER_BOUND) yCenter = UPPER_BOUND + BAT_HEIGHT / 2;
        if (getBatUpperY() <= UPPER_BOUND) isUpperCollision = true;
        if (getBatCanterY() > yCenter && !isUpperCollision) {
            getLeftBat().setLinearVelocity(0, -1 * BAT_SPEED);
        }
        if (getBatCanterY() <= yCenter) getLeftBat().setLinearVelocity(0, 0);
        if (getBatUpperY() > UPPER_BOUND + 5) isUpperCollision = false;


    }


    private static void moveDown(double yCenter) {
        if (yCenter + BAT_HEIGHT / 2 > LOWER_BOUND) yCenter = LOWER_BOUND - BAT_HEIGHT / 2;
        if (getBatLowerY() >= LOWER_BOUND) isLowerCollision = true;
        if (getBatCanterY() < yCenter && !isLowerCollision) {
            getLeftBat().setLinearVelocity(0, BAT_SPEED);
        }
        if (getBatCanterY() >= yCenter) getLeftBat().setLinearVelocity(0, 0);
        if (getBatLowerY() < LOWER_BOUND - 5) isLowerCollision = false;
    }


    private static double getBatUpperY() {
        return getLeftBat().getY();
    }

    private static double getBatLowerY() {
        return getLeftBat().getY() + BAT_HEIGHT;
    }

    private static double getBatCanterY() {
        return getLeftBat().getY() + BAT_HEIGHT / 2;
    }

    public synchronized static void setIsFirstStrikeReady(boolean isFirstStrikeReady) {
        BotControl.isFirstStrikeReady = isFirstStrikeReady;
    }
}
