package controller.collisions;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityType;
import com.almasb.fxgl.physics.CollisionHandler;
import controller.corrections.BallSpeedCorrection;
import model.Type;
import utils.Utils;

import static hockey.HockeyRunner.getBall;

public class BallBatCollision extends CollisionHandler {


    private static BallBatCollision instanceLeft = new BallBatCollision(Type.LEFT_BAT,
            Type.BALL);

    private static BallBatCollision instanceRight = new BallBatCollision(Type.RIGHT_BAT,
            Type.BALL);


    private BallBatCollision(EntityType a, EntityType b) {
        super(a, b);
    }


    @Override
    protected void onCollisionEnd(Entity a, Entity b) {
        Utils.timer(15);
        double ballXSpeed = getBall().getLinearVelocity().getX();
        double ballYSpeed = getBall().getLinearVelocity().getY();
        if (ballXSpeed > -12 && ballXSpeed < 0) getBall().setLinearVelocity(-12, ballYSpeed);
        if (ballXSpeed < 12 && ballXSpeed > 0) getBall().setLinearVelocity(12, ballYSpeed);
        BallSpeedCorrection.fixBallOutBug();

    }

    public static BallBatCollision getLeftBatCollision() {
        return instanceLeft;
    }

    public static BallBatCollision getRightBatCollision() {
        return instanceRight;
    }


}
