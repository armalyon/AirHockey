package controller.key_actions;

import com.almasb.fxgl.event.UserAction;
import com.almasb.fxgl.physics.PhysicsEntity;
import hockey.HockeyRunner;

import static controller.game_cases.RestartFunctions.isRestartReadyStatus;
import static hockey.HockeyRunner.BAT_SPEED;

public class RightBatDownAction extends UserAction {

    private PhysicsEntity bat;
    private static RightBatDownAction instance;
    private final static String name = "Right bat down";


    private RightBatDownAction(String name, PhysicsEntity bat) {
        super(name);
        this.bat = bat;
    }

    @Override
    protected void onActionBegin() {
        if (!HockeyRunner.isStartReadyStatus() && !isRestartReadyStatus() &&
                !PauseAction.isPausePerformed())
            bat.setLinearVelocity(0, BAT_SPEED);
    }


    @Override
    protected void onActionEnd() {
        bat.setLinearVelocity(0,0);
    }

    public static RightBatDownAction getRightBatDownAction(PhysicsEntity bat) {
        instance = new RightBatDownAction(name, bat);
        return instance;
    }


}
