package model.components;

import com.almasb.fxgl.physics.PhysicsEntity;
import com.almasb.fxgl.physics.PhysicsManager;
import hockey.HockeyRunner;
import model.Type;
import javafx.geometry.Point2D;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import model.Assets;

public class Ballinitializer {

    private static final String BALL_FILE = "ball.png";
    public final static int BALL_RADIUS = 14;
    public final static Point2D BALL_RIGHT_POSITION = new Point2D(HockeyRunner.SCREEN_WIDTH - 66 - BALL_RADIUS, 220);
    public final static Point2D BALL_LEFT_POSITION = new Point2D(50, 220);

    public final static Point2D BALL_RIGHT_ALT_POSITION = new Point2D(HockeyRunner.SCREEN_WIDTH - 66 - BALL_RADIUS, 420);
    public final static Point2D BALL_LEFT_ALT_POSITION = new Point2D(50, 420);




    public static PhysicsEntity getBall(Assets assets) {
        PhysicsEntity ball = new PhysicsEntity(Type.BALL);
        ball.setBodyType(BodyType.DYNAMIC);
        ball.setSceneView(assets.getAssets().loadTexture(BALL_FILE));
        double positionIdentifier = Math.random();
        if (positionIdentifier < 0.5) ball.setPosition(BALL_LEFT_POSITION);
        else ball.setPosition(BALL_RIGHT_POSITION);
        ball.setCollidable(true);
        FixtureDef fxdef = new FixtureDef();
        fxdef.restitution = 1f;
        fxdef.shape = new CircleShape();
        fxdef.shape.setRadius(PhysicsManager.toMeters(BALL_RADIUS));
        ball.setFixtureDef(fxdef);
        ball.setCollidable(true);
        return ball;
    }



}

