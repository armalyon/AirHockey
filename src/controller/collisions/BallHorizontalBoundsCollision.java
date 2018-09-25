package controller.collisions;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityType;
import com.almasb.fxgl.physics.CollisionHandler;

import controller.corrections.BallSpeedCorrection;
import model.Type;
import utils.Utils;



public class BallHorizontalBoundsCollision extends CollisionHandler {
    private static BallHorizontalBoundsCollision instance =
            new BallHorizontalBoundsCollision(Type.BALL, Type.HORIZONTAL_BOUNDS);

    public static BallHorizontalBoundsCollision getBallHorizontalBoundsCollision() {
        return instance;
    }

    private BallHorizontalBoundsCollision(EntityType a, EntityType b) {
        super(a, b);
    }


    @Override
    protected void onCollisionEnd(Entity a, Entity b) {
        BallSpeedCorrection.fixBallOutBug();
    }




}
