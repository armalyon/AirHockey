package controller.collisions;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityType;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.PhysicsEntity;
import controller.corrections.BallSpeedCorrection;
import controller.game_bot.BotControl;
import model.Type;
import utils.Utils;

import static hockey.HockeyRunner.SCREEN_HEIGHT;
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

        double ballXSpeed = getBall().getLinearVelocity().getX();
        double ballYSpeed = getBall().getLinearVelocity().getY();
        if (ballXSpeed > -12 && ballXSpeed < 0) getBall().setLinearVelocity(-12, ballYSpeed);
        if (ballXSpeed < 12 && ballXSpeed > 0) getBall().setLinearVelocity(12, ballYSpeed);
        BotControl.setIsStrikeCalculated(false);
        ballCorrection();

    }

    private void ballCorrection(){
        PhysicsEntity ball = getBall();

        if (ball.getX()>SCREEN_HEIGHT/2 && ball.getLinearVelocity().getY()==0)
            ball.setLinearVelocity(ball.getLinearVelocity().getX(), 2);
        if (ball.getX()<SCREEN_HEIGHT/2 && ball.getLinearVelocity().getY()==0)
            ball.setLinearVelocity(ball.getLinearVelocity().getX(), -2);

    }




    public static BallBatCollision getLeftBatCollision() {
        return instanceLeft;
    }

    public static BallBatCollision getRightBatCollision() {
        return instanceRight;
    }


}
