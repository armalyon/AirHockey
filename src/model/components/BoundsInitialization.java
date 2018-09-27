package model.components;


import com.almasb.fxgl.physics.PhysicsEntity;
import hockey.HockeyRunner;
import model.Type;
import org.jbox2d.dynamics.BodyType;
import model.Assets;


public class BoundsInitialization {


    private final String TOP_BOUND_FILE = "topbound.png";
    private final String BOTTOM_BOUND_FILE = "bottombound.png";

    public final static int HORIZONTAL_BOUND_HEIGHT = 26;


    public BoundsInitialization(Assets assets, HockeyRunner hockey) {
        PhysicsEntity topBound;
        PhysicsEntity bottomBound;

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


        hockey.getGameWorld().addEntities(topBound, bottomBound);

    }
}
