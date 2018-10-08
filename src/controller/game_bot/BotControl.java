package controller.game_bot;


import com.almasb.fxgl.physics.PhysicsEntity;
import hockey.HockeyRunner;
import javafx.geometry.Point2D;

import static controller.key_actions.Start1PlGameAction.isOnePlayerMode;
import static hockey.HockeyRunner.*;
import static model.components.Ballinitializer.BALL_LEFT_POSITION;
import static model.components.BatInitializer.BAT_HEIGHT;
import static model.components.BoundsInitialization.HORIZONTAL_BOUND_HEIGHT;


public class BotControl {

    private static final double UPPER_BOUND = HORIZONTAL_BOUND_HEIGHT + 10;
    private static final double LOWER_BOUND = SCREEN_HEIGHT - HORIZONTAL_BOUND_HEIGHT - 10;
    private static boolean isLowerCollision = false;
    private static boolean isUpperCollision = false;
    private static boolean isFirstStrikeReady = false;
    private static boolean isTimerWorks = false;
    private static final Point2D zero = new Point2D(0, 0);


    public static void botScript() {
        if (isOnePlayerMode() && getBall().isActive()) {
            firstStrike();
            moveToCenterAfterStrike();
            moveToStrikePosition();


        }

    }


    private static void moveToStrikePosition(){
        if (getBall().getX()<SCREEN_WIDTH/6 && getBall().getLinearVelocity().getX()<0){
            move(getBall().getY());
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
        if (getBall().getLinearVelocity().getX() > 1 && getBall().getX() > SCREEN_WIDTH/5)
            move(SCREEN_HEIGHT/2);
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
