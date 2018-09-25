package controller.collisions;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityType;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.PhysicsEntity;
import hockey.HockeyRunner;
import model.Type;

import java.util.Date;

public class BallBatCollision extends CollisionHandler {

    private PhysicsEntity ball;

    private static BallBatCollision instanceLeft = new BallBatCollision(Type.LEFT_BAT,
            Type.BALL);

    private static BallBatCollision instanceRight = new BallBatCollision(Type.RIGHT_BAT,
            Type.BALL);


    private BallBatCollision(EntityType a, EntityType b) {
        super(a, b);
          }


    @Override
    protected void onCollisionEnd(Entity a, Entity b) {
        PhysicsEntity ball = HockeyRunner.getBall();
               timer(15);
        double ballXSpeed = ball.getLinearVelocity().getX();
        double ballYSpeed = ball.getLinearVelocity().getY();
        if (ballXSpeed > -12 && ballXSpeed < 0) ball.setLinearVelocity(-12, ballYSpeed);
        if (ballXSpeed < 12 && ballXSpeed > 0) ball.setLinearVelocity(12, ballYSpeed);
               }

    public static BallBatCollision getLeftBatCollision() {
        return instanceLeft;
    }

    public static BallBatCollision getRightBatCollision() {
        return instanceRight;
    }

    private void timer(int time){
        int t1 = (int) new Date().getTime();
        int t2 = t1;
        while (t2-t1<time){
            t2 = (int) new Date().getTime();
        }
    }


}
