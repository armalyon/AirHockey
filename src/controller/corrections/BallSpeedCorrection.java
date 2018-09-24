package controller.corrections;

import com.almasb.fxgl.physics.PhysicsEntity;
import javafx.geometry.Point2D;

public class BallSpeedCorrection {
      private Point2D ballSpeed;
    private static int ballDirection;


    public void ballSpeedCorrection(PhysicsEntity ball) {
        ballSpeed = ball.getLinearVelocity();
        double xSpeed = ballSpeed.getX();
        double ySpeed = ballSpeed.getY();
        if (xSpeed < 0) ballDirection = -1;
        if (xSpeed > 0) ballDirection = 1;
        if (Math.abs(xSpeed) <= 3 && ySpeed != 0) ball.setLinearVelocity(ballDirection * 5, ySpeed);
        }


}
