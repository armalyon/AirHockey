package key_actions;

import com.almasb.fxgl.event.UserAction;
import com.almasb.fxgl.physics.PhysicsEntity;
import hockey.HockeyRunner;

public class LeftBatUpAction extends UserAction {

    private PhysicsEntity bat;
    private static LeftBatUpAction instance;
    private final static String name = "Left bat up";
    private final static int UP_VELOCITY = -12;


    private LeftBatUpAction(String name, PhysicsEntity bat) {
        super(name);
        this.bat = bat;

    }

    @Override
    protected void onActionBegin() {
        if (!HockeyRunner.isStartReadyStatus() && !HockeyRunner.isRestartReadyStatus() &&
                !PauseAction.isPausePerformed())
            bat.setLinearVelocity(0, UP_VELOCITY);

    }


    @Override
    protected void onActionEnd() {
        bat.setLinearVelocity(0, 0);
    }

    public static LeftBatUpAction getLeftBatUpAction(PhysicsEntity bat) {
        instance = new LeftBatUpAction(name, bat);
        return instance;
    }
}
