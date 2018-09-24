package controller.key_actions;

import com.almasb.fxgl.event.UserAction;
import com.almasb.fxgl.physics.PhysicsEntity;
import hockey.HockeyRunner;

import static controller.game_cases.RestartFunctions.isRestartReadyStatus;

public class LeftBatDownAction extends UserAction {

    private PhysicsEntity bat;
    private static LeftBatDownAction instance;
    private final static String name = "Left bat down";
    private final static int DOWN_VELOCITY = 12;



    private LeftBatDownAction(String name, PhysicsEntity bat) {
        super(name);
        this.bat = bat;
    }

    @Override
    protected void onActionBegin() {
            if (!HockeyRunner.isStartReadyStatus() && !isRestartReadyStatus()&&
                    !PauseAction.isPausePerformed())
        bat.setLinearVelocity(0, DOWN_VELOCITY);
            }


    @Override
    protected void onActionEnd() {
        bat.setLinearVelocity(0,0);
    }

    public static LeftBatDownAction getLeftBatDownAction(PhysicsEntity bat) {
        instance = new LeftBatDownAction(name, bat);
        return instance;
    }


}
