package components;

import com.almasb.fxgl.physics.PhysicsEntity;
import com.almasb.fxgl.physics.PhysicsManager;
import hockey.HockeyRunner;
import hockey.Type;
import javafx.geometry.Point2D;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import utils.Assets;

public class Ballinitializer {

    private static final String BALL_FILE = "ball.png";
    private final static int BALL_RADIUS = 14;
    private final static Point2D ballRightPosition = new Point2D(HockeyRunner.SCREEN_WIDTH - 68 - BALL_RADIUS, 220);
    private final static Point2D ballLeftPosition = new Point2D(50, 220);


    public PhysicsEntity getBall(Assets assets) {
        PhysicsEntity ball = new PhysicsEntity(Type.BALL);
        ball.setBodyType(BodyType.DYNAMIC);
        ball.setSceneView(assets.getAssets().loadTexture(BALL_FILE));
        double positionIdentifier = Math.random();
        if (positionIdentifier < 0.5) ball.setPosition(ballLeftPosition);
        else ball.setPosition(ballRightPosition);
        ball.setCollidable(true);
        FixtureDef fxdef = new FixtureDef();
        fxdef.restitution = 1f;
        fxdef.shape = new CircleShape();
        fxdef.shape.setRadius(PhysicsManager.toMeters(BALL_RADIUS));
        ball.setFixtureDef(fxdef);
        ball.setCollidable(true);
        return ball;
    }


    public static Point2D getBallLeftPosition() {
        return ballLeftPosition;
    }

    public static Point2D getBallRightPosition() {
        return ballRightPosition;
    }



}

