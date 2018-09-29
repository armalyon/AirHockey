package controller.game_bot;


import controller.key_actions.Start1PlGameAction;

import static controller.game_cases.RestartFunctions.isRestartReadyStatus;
import static hockey.HockeyRunner.*;
import static model.components.BatInitializer.BAT_HEIGHT;
import static model.components.BoundsInitialization.HORIZONTAL_BOUND_HEIGHT;

public class BotControl {

    private static final double UPPER_BOUND = HORIZONTAL_BOUND_HEIGHT + 3;
    private static final double LOWER_BOUND = SCREEN_HEIGHT - HORIZONTAL_BOUND_HEIGHT - 3;
    private static boolean isLowerCollision = false;
    private static boolean isUpperCollision = false;

    public static void botScript() {
        if (Start1PlGameAction.isOnePlayerMode()) {
            // firstStrike();
            if (!isStartReadyStatus() && !isRestartReadyStatus()) ;

            System.out.println(getBatCanterY());


        }

    }


    private static void firstStrike() {
        if (!isStartReadyStatus() && !isRestartReadyStatus()) {


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


    private static void move(double yCenter) {

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


}
