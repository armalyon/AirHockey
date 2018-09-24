package controller.collisions;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityType;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.PhysicsEntity;
import hockey.HockeyRunner;
import model.Type;
import javafx.geometry.Point2D;


public class BatBoundsCollision extends CollisionHandler {

    private static BatBoundsCollision instance;
    private PhysicsEntity bat;

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

    public static BatBoundsCollision getLeftBatCollision(PhysicsEntity bat) {
        instance = new BatBoundsCollision(Type.LEFT_BAT, Type.HORIZONTAL_BOUNDS, bat);
        return instance;
    }

    public static BatBoundsCollision getRightBatCollision(PhysicsEntity bat) {
        instance = new BatBoundsCollision(Type.RIGHT_BAT, Type.HORIZONTAL_BOUNDS, bat);
        return instance;
    }

    private void reboundBat() {
        Point2D p = bat.getPosition();
        if (p.getY() < HockeyRunner.SCREEN_HEIGHT / 2) bat.setLinearVelocity(0, 4);
        else bat.setLinearVelocity(0, -4);
    }


}
