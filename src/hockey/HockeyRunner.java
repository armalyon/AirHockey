package hockey;


import com.almasb.fxgl.GameApplication;
import com.almasb.fxgl.event.InputManager;
import com.almasb.fxgl.physics.PhysicsEntity;
import com.almasb.fxgl.settings.GameSettings;
import components.BackgroundInitializator;
import components.Ballinitializer;
import components.BatInitializer;
import components.BoundsInitialization;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import key_actions.RestartGameAction;
import key_actions.StartGameAction;
import utils.Assets;

import static collisions.BatBoundsCollision.getLeftBatCollision;
import static collisions.BatBoundsCollision.getRightBatCollision;
import static components.Ballinitializer.*;
import static components.TextFields.*;
import static key_actions.LeftBatDownAction.getLeftBatDownAction;
import static key_actions.LeftBatUpAction.getLeftBatUpAction;
import static key_actions.RestartGameAction.getRestartGameAction;
import static key_actions.RightBatDownAction.getRightBatDownAction;
import static key_actions.RightBatUpAction.getRightBatUpAction;


public class HockeyRunner extends GameApplication {
    private static BatInitializer batInitializer = new BatInitializer();
    private Ballinitializer ballinitializer = new Ballinitializer();
    public static final int SCREEN_WIDTH = 1200;
    public static final int SCREEN_HEIGHT = 675;
    private static final String TITLE = "Hockey";
    private static final String ICON_FILE = "icon.png";
    private static final String FONT_FILE_NAME = "Halogen Gas Lights.otf";
    private Assets assets;
    private InputManager input;
    private PhysicsEntity leftBat;
    private PhysicsEntity rightBat;
    private PhysicsEntity ball;
    private static int ballDirection;
    private Point2D ballSpeed;
    private static IntegerProperty score1 = new SimpleIntegerProperty(0);
    private static IntegerProperty score2 = new SimpleIntegerProperty(0);
    private static final int FINAL_SCORE = 5;
    private static boolean startReadyStatus = true;
    private static boolean restartReadyStatus = false;
    private static Text player1controls;
    private static Text player2controls;
    private static Text startText;
    private static Text winText;
    private static Text restartText;


    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setTitle(TITLE);
        gameSettings.setWidth(SCREEN_WIDTH);
        gameSettings.setHeight(SCREEN_HEIGHT);
        gameSettings.setIconFileName(ICON_FILE);
        gameSettings.setMenuEnabled(false);
        gameSettings.setIntroEnabled(false);
        gameSettings.setShowFPS(false);
        gameSettings.setIconFileName(ICON_FILE);
        gameSettings.setDefaultFontName(FONT_FILE_NAME);

    }


    @Override
    protected void initGame() {
        getPhysicsManager().setGravity(0, 0);
        new BackgroundInitializator(assets, this);
        new BoundsInitialization(assets, this);
        leftBat = batInitializer.getLeftBat(assets);
        rightBat = batInitializer.getRightBat(assets);
        ball = ballinitializer.getBall(assets);
        getGameWorld().addEntities(leftBat, rightBat, ball);
        initKeyInput();
        initCollisions();
    }


    @Override
    protected void initInput() {
        input = getInputManager();
        input.addAction(StartGameAction.getStartGameAction(), KeyCode.ENTER);
    }

    @Override
    protected void initAssets() {
        assets = new Assets(this);
    }


    @Override
    protected void initPhysics() {

    }


    @Override
    protected void initUI() {
        getGameScene().addUINode(initScoreText());
        getGameScene().addUINode(initScoreDivider());
        getGameScene().addUINode(initScore1());
        getGameScene().addUINode(initScore2());
        restartText = initRestartText();
        winText = initPlayerWins();
        player1controls = initShowControls1();
        player2controls = initShowControls2();
        startText = initStartText();
        getGameScene().addUINodes(player1controls, player2controls, startText, winText, restartText);

    }

    @Override
    protected void onUpdate() {
        startNewGame();
        ballHorizontalSpeedCorrection();
        countScore1();
        countScore2();
        setGameEnd();
        restartBall();

    }


    private void initKeyInput() {
        input.addAction(getLeftBatUpAction(leftBat), KeyCode.A);
        input.addAction(getLeftBatDownAction(leftBat), KeyCode.Z);
        input.addAction(getRightBatUpAction(rightBat), KeyCode.UP);
        input.addAction(getRightBatDownAction(rightBat), KeyCode.DOWN);
        input.addAction(getRestartGameAction(), KeyCode.SPACE);
    }

    public void initCollisions() {
        getPhysicsManager().addCollisionHandler(getLeftBatCollision(leftBat));
        getPhysicsManager().addCollisionHandler(getRightBatCollision(rightBat));

    }

    private void ballHorizontalSpeedCorrection() {
        ballSpeed = ball.getLinearVelocity();
        double xSpeed = ballSpeed.getX();
        double ySpeed = ballSpeed.getY();
        if (xSpeed < 0) ballDirection = -1;
        if (xSpeed > 0) ballDirection = 1;
        if (Math.abs(xSpeed) <= 3 && ySpeed != 0) ball.setLinearVelocity(ballDirection * 4, ySpeed);

    }

    private void countScore1() {
        if ((ball.getX() > SCREEN_WIDTH + 100)||
                (ball.getX()>SCREEN_WIDTH/2&&(ball.getY()>SCREEN_HEIGHT || ball.getY()<0))) {
            ball = ballinitializer.getBall(assets);
            if (rightBat.getY() < SCREEN_HEIGHT / 2) {
                ball.setPosition(BALL_RIGHT_ALT_POSITION);
            } else {
                ball.setPosition(BALL_RIGHT_POSITION);
            }
            score1.setValue(score1.get() + 1);
            getGameWorld().addEntity(ball);
        }

    }


    private void countScore2() {
        if (ball.getX() < -100 ||
                (ball.getX()<SCREEN_WIDTH/2&&(ball.getY()>SCREEN_HEIGHT || ball.getY()<0))) {
            ball = ballinitializer.getBall(assets);
            if (leftBat.getY() < SCREEN_HEIGHT / 2) {
                ball.setPosition(BALL_LEFT_ALT_POSITION);
            } else {
                ball.setPosition(BALL_LEFT_POSITION);
            }
            score2.setValue(score2.get() + 1);
            getGameWorld().addEntity(ball);
        }

    }

    private void setGameEnd() {
        if (score1.get() == FINAL_SCORE || score2.get() == FINAL_SCORE) {
            restartReadyStatus = true;
            ball.setPosition(200, -200);
            winText.setText(PLAYER2_WINS_TEXT);
            restartText.setText(PRESS_SPACE_TO_RESTART);
        }


    }

    private void startNewGame() {
        if (!startReadyStatus) {
            player1controls.setText("");
            player2controls.setText("");
            startText.setText("");
        }
    }

    private void restartBall() {
        if (RestartGameAction.getBallRestart()) {
            ball = ballinitializer.getBall(assets);
            getGameWorld().addEntity(ball);
            RestartGameAction.setBallRestart(false);
        }
    }

    public static IntegerProperty getScore1() {
        return score1;
    }

    public static IntegerProperty getScore2() {
        return score2;
    }

    public static boolean isStartReadyStatus() {
        return startReadyStatus;
    }

    public static boolean isRestartReadyStatus() {
        return restartReadyStatus;
    }

    public static Text getWinText() {
        return winText;
    }

    public static Text getRestartText() {
        return restartText;
    }

    public static void setStartReadyStatus(boolean startReadyStatus) {
        HockeyRunner.startReadyStatus = startReadyStatus;
    }

    public static void setRestartReadyStatus(boolean restartReadyStatus) {
        HockeyRunner.restartReadyStatus = restartReadyStatus;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
