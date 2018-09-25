package model.components;

import com.almasb.fxgl.physics.PhysicsEntity;
import hockey.HockeyRunner;
import model.Type;
import org.jbox2d.dynamics.BodyType;
import model.Assets;

public class BatInitializer {

    private static final int BAT_HEIGHT = 84;
    private static final int BAT_WIDTH = 29;
    private static final double LEFT_BAT_X = 26;
    private static final double RIGHT_BAT_X = HockeyRunner.SCREEN_WIDTH - 55;
    private final static String LEFT_BAT_FILE = "pinkbat.png";
    private final static String RIGH_BAT_FILE = "greenbat.png";
    private static double BATS_Y = HockeyRunner.SCREEN_HEIGHT / 2 - BAT_HEIGHT / 2;


    public BatInitializer() {
    }

    public static PhysicsEntity getLeftBat(Assets assets) {
        PhysicsEntity leftBat = new PhysicsEntity(Type.LEFT_BAT);
        leftBat.setPosition(LEFT_BAT_X, BATS_Y);
        leftBat.setSceneView(assets.getAssets().loadTexture(LEFT_BAT_FILE));
        leftBat.setBodyType(BodyType.KINEMATIC);
        leftBat.setCollidable(true);
        return leftBat;
    }

    public static PhysicsEntity getRightBat(Assets assets) {
        PhysicsEntity rightBat = new PhysicsEntity(Type.RIGHT_BAT);
        rightBat.setPosition(RIGHT_BAT_X, BATS_Y);
        rightBat.setSceneView(assets.getAssets().loadTexture(RIGH_BAT_FILE));
        rightBat.setBodyType(BodyType.KINEMATIC);
        rightBat.setCollidable(true);
        return rightBat;
    }

}
