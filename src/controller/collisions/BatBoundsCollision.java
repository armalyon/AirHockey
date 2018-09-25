package controller.collisions;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityType;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.PhysicsEntity;
import hockey.HockeyRunner;
import javafx.geometry.Point2D;
import model.Type;


public class BatBoundsCollision extends CollisionHandler {

    private PhysicsEntity bat;

    private static BatBoundsCollision instanceLeft = new BatBoundsCollision(Type.LEFT_BAT,
            Type.HORIZONTAL_BOUNDS, HockeyRunner.getLeftBat());

    private static BatBoundsCollision instanceRight = new BatBoundsCollision(Type.RIGHT_BAT,
            Type.HORIZONTAL_BOUNDS, HockeyRunner.getRightBat());

    private BatBoundsCollision(EntityType a, EntityType b, PhysicsEntity bat) {
        super(a, b);
        this.bat = bat;
    }


    @Override
    protected void onCollisionBegin(Entity a, Entity b) {
        reboundBat();
    }

    @Override
    protected void onCollision(Entity a, Entity b) {
        reboundBat();

    }

    @Override
    protected void onCollisionEnd(Entity a, Entity b) {
        bat.setLinearVelocity(0, 0);
    }

    public static BatBoundsCollision getLeftBatCollision() {
        return instanceLeft;
    }

    public static BatBoundsCollision getRightBatCollision() {
        return instanceRight;
    }

    private void reboundBat() {
        Point2D p = bat.getPosition();
        if (p.getY() < HockeyRunner.SCREEN_HEIGHT / 2) bat.setLinearVelocity(0, 4);
        else bat.setLinearVelocity(0, -4);
    }


}
