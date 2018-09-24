package controller.key_actions;

import com.almasb.fxgl.event.UserAction;

import static hockey.HockeyRunner.isRestartReadyStatus;
import static hockey.HockeyRunner.isStartReadyStatus;

public class PauseAction extends UserAction {
    private static String name = "pause";
    private static PauseAction ourInstance = new PauseAction();
    private static boolean pausePerformed = false;
    private static int pauseFlag = -1;

    private PauseAction() {
        super(name);
    }

    @Override
    protected void onActionEnd() {
        if (!isStartReadyStatus() && !isRestartReadyStatus()) {
            pauseFlag *= -1;
            if (!pausePerformed && pauseFlag > 0) {
                pausePerformed = true;
            }

            if (pausePerformed && pauseFlag < 0) {
                pausePerformed = false;
            }
        }
    }

    public static PauseAction getPauseAction() {
        return ourInstance;
    }

    public static boolean isPausePerformed() {
        return pausePerformed;
    }

}
