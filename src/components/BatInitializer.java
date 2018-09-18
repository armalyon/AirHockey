package components;

import com.almasb.fxgl.physics.PhysicsEntity;
import hockey.HockeyRunner;
import hockey.Type;
import org.jbox2d.dynamics.BodyType;
import utils.Assets;

public class BatInitializer {

    private final int BAT_HEIGHT = 84;
    private final int BAT_WIDTH = 29;
    private double leftBatX = 26;
    private double rightBatX = HockeyRunner.SCREEN_WIDTH - 55;
    private final static String LEFT_BAT_FILE = "pinkbat.png";
    private final static String RIGH_BAT_FILE = "greenbat.png";
    private double BatY = HockeyRunner.SCREEN_HEIGHT / 2 - BAT_HEIGHT / 2;

    PhysicsEntity rightBat;

    public BatInitializer() {
    }

    public PhysicsEntity getLeftBat(Assets assets) {


        PhysicsEntity leftBat = new PhysicsEntity(Type.LEFT_BAT);
        leftBat.setPosition(leftBatX, BatY);
        leftBat.setSceneView(assets.getAssets().loadTexture(LEFT_BAT_FILE));
        leftBat.setBodyType(BodyType.KINEMATIC);
        leftBat.setCollidable(true);
        return leftBat;
    }

    public PhysicsEntity getRightBat(Assets assets) {
        PhysicsEntity rightBat = new PhysicsEntity(Type.RIGHT_BAT);
        rightBat.setPosition(rightBatX, BatY);
        rightBat.setSceneView(assets.getAssets().loadTexture(RIGH_BAT_FILE));
        rightBat.setBodyType(BodyType.KINEMATIC);
        rightBat.setCollidable(true);
        return rightBat;
    }


}
