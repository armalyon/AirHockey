package model.components;


import com.almasb.fxgl.physics.PhysicsEntity;
import hockey.HockeyRunner;
import model.Type;
import org.jbox2d.dynamics.BodyType;
import utils.Assets;


public class BoundsInitialization {


    private final String TOP_BOUND_FILE = "topbound.png";
    private final String BOTTOM_BOUND_FILE = "bottombound.png";
    private final String LT_BOUND_FILE = "ltbound.png";
    private final String LB_BOUND_FILE = "lbbound.png";
    private final String RB_BOUND_FILE = "rbbound.png";
    private final String RT_BOUND_FILE = "rtbound.png";
    public final static int HORIZONTAL_BOUND_HEIGHT = 26;
    public final static int VERTICAL_BOUND_WIDTH = 24;
    private final static int BOUND_HEIGHT = 118;


    public BoundsInitialization(Assets assets, HockeyRunner hockey) {
        PhysicsEntity topBound;
        PhysicsEntity bottomBound;
        PhysicsEntity ltBound;
        PhysicsEntity lbBound;
        PhysicsEntity rtBound;
        PhysicsEntity rbBound;
        topBound = new PhysicsEntity(Type.HORIZONTAL_BOUNDS);
        topBound.setPosition(0, 0);
        topBound.setSceneView(assets.getAssets().loadTexture(TOP_BOUND_FILE));
        topBound.setBodyType(BodyType.STATIC);
        topBound.setCollidable(true);

        bottomBound = new PhysicsEntity(Type.HORIZONTAL_BOUNDS);
        bottomBound.setPosition(0, HockeyRunner.SCREEN_HEIGHT - HORIZONTAL_BOUND_HEIGHT);
        bottomBound.setSceneView(assets.getAssets().loadTexture(BOTTOM_BOUND_FILE));
        bottomBound.setBodyType(BodyType.STATIC);
        bottomBound.setCollidable(true);

        ltBound = new PhysicsEntity(Type.VERTICAL_BOUNDS);
        ltBound.setPosition(0, 0);
        ltBound.setSceneView(assets.getAssets().loadTexture(LT_BOUND_FILE));
        ltBound.setBodyType(BodyType.STATIC);
        ltBound.setCollidable(true);

        lbBound = new PhysicsEntity(Type.VERTICAL_BOUNDS);
        lbBound.setPosition(0, HockeyRunner.SCREEN_HEIGHT - BOUND_HEIGHT);
        lbBound.setSceneView(assets.getAssets().loadTexture(LB_BOUND_FILE));
        lbBound.setBodyType(BodyType.STATIC);
        lbBound.setCollidable(true);

        rbBound = new PhysicsEntity(Type.VERTICAL_BOUNDS);
        rbBound.setPosition(HockeyRunner.SCREEN_WIDTH - VERTICAL_BOUND_WIDTH, HockeyRunner.SCREEN_HEIGHT - BOUND_HEIGHT);
        rbBound.setSceneView(assets.getAssets().loadTexture(RB_BOUND_FILE));
        rbBound.setBodyType(BodyType.STATIC);
        rbBound.setCollidable(true);

        rtBound = new PhysicsEntity(Type.VERTICAL_BOUNDS);
        rtBound.setPosition(HockeyRunner.SCREEN_WIDTH - VERTICAL_BOUND_WIDTH, 0);
        rtBound.setSceneView(assets.getAssets().loadTexture(RT_BOUND_FILE));
        rtBound.setBodyType(BodyType.STATIC);
        rtBound.setCollidable(true);

        hockey.getGameWorld().addEntities(topBound, bottomBound, ltBound, lbBound, rbBound, rtBound);

    }
}
