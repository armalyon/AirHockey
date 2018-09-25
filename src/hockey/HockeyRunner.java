package hockey;


import com.almasb.fxgl.GameApplication;
import com.almasb.fxgl.event.InputManager;
import com.almasb.fxgl.physics.PhysicsEntity;
import com.almasb.fxgl.settings.GameSettings;
import controller.collisions.BallBatCollision;
import controller.collisions.BallHorizontalBoundsCollision;
import controller.corrections.BallSpeedCorrection;
import controller.game_cases.PauseFunction;
import controller.key_actions.StartGameAction;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import model.components.BackgroundInitializator;
import model.components.Ballinitializer;
import model.components.BatInitializer;
import model.components.BoundsInitialization;
import model.Assets;

import static controller.collisions.BatBoundsCollision.getLeftBatCollision;
import static controller.collisions.BatBoundsCollision.getRightBatCollision;
import static controller.game_cases.RestartFunctions.*;
import static controller.game_cases.ScoreCounter.countScore1;
import static controller.game_cases.ScoreCounter.countScore2;
import static controller.key_actions.LeftBatDownAction.getLeftBatDownAction;
import static controller.key_actions.LeftBatUpAction.getLeftBatUpAction;
import static controller.key_actions.PauseAction.getPauseAction;
import static controller.key_actions.RestartGameAction.getRestartGameAction;
import static controller.key_actions.RightBatDownAction.getRightBatDownAction;
import static controller.key_actions.RightBatUpAction.getRightBatUpAction;
import static model.components.TextFields.*;


public class HockeyRunner extends GameApplication {
    public static final int FINAL_SCORE = 5;
    public static final int SCREEN_WIDTH = 1200;
    public static final int SCREEN_HEIGHT = 675;
    public  static final int BAT_SPEED = 15;
    private static final String TITLE = "Hockey";
    private static final String ICON_FILE = "icon.png";
    private static final String FONT_FILE_NAME = "Halogen Gas Lights.otf";
    private Assets assets;
    private InputManager input;
    private static PhysicsEntity leftBat;
    private static PhysicsEntity rightBat;
    private static PhysicsEntity ball;
    private static IntegerProperty score1 = new SimpleIntegerProperty(0);
    private static IntegerProperty score2 = new SimpleIntegerProperty(0);
    private static boolean startReadyStatus = true;
    private static Text player1controls;
    private static Text player2controls;
    private static Text startText;
    private static Text winText;
    private static Text restartText;
    private static Text pausetext;
    private static Text pauseControlText;
    private BallSpeedCorrection ballSpeedCorrection;
    private PauseFunction pauseFunction;


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
        leftBat = BatInitializer.getLeftBat(assets);
        rightBat = BatInitializer.getRightBat(assets);
        ball = Ballinitializer.getBall(assets);
        getGameWorld().addEntities(leftBat, rightBat, ball);
        initKeyInput();
        initCollisions();
        ballSpeedCorrection = new BallSpeedCorrection();
        pauseFunction = new PauseFunction();
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
        getGameScene().addUINode(initScoreDividerText());
        getGameScene().addUINode(initScore1Text());
        getGameScene().addUINode(initScore2Text());
        pausetext = initPauseText();
        restartText = initRestartText();
        winText = initPlayerWinsText();
        player1controls = initControls1Text();
        player2controls = initControls2Text();
        startText = initStartText();
        pauseControlText = initPauseControlText();
        getGameScene().addUINodes(player1controls, player2controls,
                startText, winText, restartText, pausetext, pauseControlText);
    }

    @Override
    protected void onUpdate() {
        startNewGame(startReadyStatus);
        ballSpeedCorrection.ballSpeedCorrection(ball);
        countScore1(getGameWorld(), assets);
        countScore2(getGameWorld(), assets);
        setGameEnd();
        restartBall(getGameWorld(), assets);
        pauseFunction.pauseGame();
    }


    private void initKeyInput() {
        input.addAction(getLeftBatUpAction(leftBat), KeyCode.A);
        input.addAction(getLeftBatDownAction(leftBat), KeyCode.Z);
        input.addAction(getRightBatUpAction(rightBat), KeyCode.UP);
        input.addAction(getRightBatDownAction(rightBat), KeyCode.DOWN);
        input.addAction(getRestartGameAction(), KeyCode.SPACE);
        input.addAction(getPauseAction(), KeyCode.P);
    }

    public void initCollisions() {
        getPhysicsManager().addCollisionHandler(getLeftBatCollision());
        getPhysicsManager().addCollisionHandler(getRightBatCollision());
        getPhysicsManager().addCollisionHandler(BallBatCollision.getLeftBatCollision());
        getPhysicsManager().addCollisionHandler(BallBatCollision.getRightBatCollision());
        getPhysicsManager().addCollisionHandler(BallHorizontalBoundsCollision.getBallHorizontalBoundsCollision());

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

    public static void setStartReadyStatus(boolean startReadyStatus) {
        HockeyRunner.startReadyStatus = startReadyStatus;
    }

    public static Text getWinText() {
        return winText;
    }

    public static Text getRestartText() {
        return restartText;
    }

    public static PhysicsEntity getLeftBat() {
        return leftBat;
    }

    public static PhysicsEntity getRightBat() {
        return rightBat;
    }

    public static PhysicsEntity getBall() {
        return ball;
    }

    public static void setBall(PhysicsEntity ball) {
        HockeyRunner.ball = ball;
    }


    public static Text getPlayer1controls() {
        return player1controls;
    }

    public static Text getPlayer2controls() {
        return player2controls;
    }

    public static Text getPauseControlText() {
        return pauseControlText;
    }

    public static Text getStartText() {
        return startText;
    }

    public static Text getPausetext() {
        return pausetext;
    }


    public static void main(String[] args) {
        launch(args);
    }
}