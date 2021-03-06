package controller.key_actions;

import com.almasb.fxgl.event.UserAction;
import javafx.beans.property.IntegerProperty;

import static controller.game_cases.RestartFunctions.isRestartReadyStatus;
import static controller.game_cases.RestartFunctions.setRestartReadyStatus;
import static hockey.HockeyRunner.*;

;

public class RestartGameAction extends UserAction {
    private static final String name = "restart";
    private static RestartGameAction ourInstance = new RestartGameAction();
    private static boolean ballRestart = false;

    private RestartGameAction() {
        super(name);
    }


    @Override
    protected void onActionBegin() {
        if (isRestartReadyStatus()) {
            getScore1().set(0);
            getScore2().set(0);
            getWinText().setText("");
            getRestartText().setText("");
            setRestartReadyStatus(false);
            ballRestart = true;
        }

    }

    public static boolean getBallRestart() {
        return ballRestart;
    }

    public static void setBallRestart(boolean ballRestart) {
        RestartGameAction.ballRestart = ballRestart;
    }

    public static RestartGameAction getRestartGameAction() {
        return ourInstance;
    }
}
