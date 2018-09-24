package controller.key_actions;

import com.almasb.fxgl.event.UserAction;
import com.almasb.fxgl.physics.PhysicsEntity;
import hockey.HockeyRunner;

public class RightBatUpAction extends UserAction {

    private PhysicsEntity bat;
    private static RightBatUpAction instance;
    private final static String name = "Right bat up";
    private final static int DOWN_VELOCITY = -12;



    private RightBatUpAction(String name, PhysicsEntity bat) {
        super(name);
        this.bat = bat;
    }

    @Override
    protected void onActionBegin() {
        if (!HockeyRunner.isStartReadyStatus() && !HockeyRunner.isRestartReadyStatus() &&
                !PauseAction.isPausePerformed())
            bat.setLinearVelocity(0, DOWN_VELOCITY);
    }


    @Override
    protected void onActionEnd() {
        bat.setLinearVelocity(0,0);
    }

    public static RightBatUpAction getRightBatUpAction(PhysicsEntity bat) {
        instance = new RightBatUpAction(name, bat);
        return instance;
    }
}
