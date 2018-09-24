package hockey;


import com.almasb.fxgl.GameApplication;
import com.almasb.fxgl.event.InputManager;
import com.almasb.fxgl.physics.PhysicsEntity;
import com.almasb.fxgl.settings.GameSettings;
import controller.corrections.BallSpeedCorrection;
import controller.game_cases.ReststartBall;
import model.components.BackgroundInitializator;
import model.components.Ballinitializer;
import model.components.BatInitializer;
import model.components.BoundsInitialization;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import controller.key_actions.RestartGameAction;
import controller.key_actions.StartGameAction;
import utils.Assets;

import static controller.collisions.BatBoundsCollision.getLeftBatCollision;
import static controller.collisions.BatBoundsCollision.getRightBatCollision;
import static model.components.Ballinitializer.*;
import static model.components.TextFields.*;
import static controller.key_actions.LeftBatDownAction.getLeftBatDownAction;
import static controller.key_actions.LeftBatUpAction.getLeftBatUpAction;
import static controller.key_actions.PauseAction.*;
import static controller.key_actions.RestartGameAction.getRestartGameAction;
import static controller.key_actions.RightBatDownAction.getRightBatDownAction;
import static controller.key_actions.RightBatUpAction.getRightBatUpAction;


public class HockeyRunner extends GameApplication {
    private static BatInitializer batInitializer = new BatInitializer();
    private static Ballinitializer ballinitializer = new Ballinitializer();
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
    private Point2D ballSpeedBeforePause;
    private static IntegerProperty score1 = new SimpleIntegerProperty(0);
    private static IntegerProperty score2 = new SimpleIntegerProperty(0);
    private static final int FINAL_SCORE = 3;
    private static boolean startReadyStatus = true;
    private static boolean restartReadyStatus = false;
    private static Text player1controls;
    private static Text player2controls;
    private static Text startText;
    private static Text winText;
    private static Text restartText;
    private static Text pausetext;
    private static Text pauseControlText;
    private BallSpeedCorrection ballSpeedCorrection;

    private static boolean pauseFlag = false;


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
        ballSpeedCorrection = new BallSpeedCorrection();

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
        startNewGame();
        ballSpeedCorrection.ballSpeedCorrection(ball);
        countScore1();
        countScore2();
        setGameEnd();
        ReststartBall.restartBall(ball, getGameWorld(), assets);
        pauseGame();

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
        getPhysicsManager().addCollisionHandler(getLeftBatCollision(leftBat));
        getPhysicsManager().addCollisionHandler(getRightBatCollision(rightBat));

    }


    private void countScore1() {
        if (!restartReadyStatus && ball.getX() > SCREEN_WIDTH + 50) {
            ball = ballinitializer.getBall(assets);
            if (rightBat.getY() < SCREEN_HEIGHT / 2) {
                ball.setPosition(BALL_RIGHT_ALT_POSITION);
            } else {
                ball.setPosition(BALL_RIGHT_POSITION);
            }
            getGameWorld().addEntity(ball);
            score1.setValue(score1.get() + 1);

        }

    }


    private void countScore2() {
        if (!restartReadyStatus && ball.getX() < -50) {
            ball = ballinitializer.getBall(assets);
            if (leftBat.getY() < SCREEN_HEIGHT / 2) {
                ball.setPosition(BALL_LEFT_ALT_POSITION);
            } else {
                ball.setPosition(BALL_LEFT_POSITION);
            }
            getGameWorld().addEntity(ball);
            score2.setValue(score2.get() + 1);

        }

    }

    private void setGameEnd() {
        if (score1.get() == FINAL_SCORE || score2.get() == FINAL_SCORE) {
            restartReadyStatus = true;
            ball.setPosition(200, -200);
            restartText.setText(PRESS_SPACE_TO_RESTART);
            if (score1.get()==FINAL_SCORE){
                winText.setText(PLAYER1_WINS_TEXT);
            } else   winText.setText(PLAYER2_WINS_TEXT);

        }


    }

    private void startNewGame() {
        if (!startReadyStatus) {
            player1controls.setText("");
            player2controls.setText("");
            startText.setText("");
            pauseControlText.setText("");
        }
    }


    private void pauseGame() {
        if (isPausePerformed() && !pauseFlag) {
            ballSpeedBeforePause = ball.getLinearVelocity();
            pauseFlag = true;
            if (pauseFlag){
                ball.setLinearVelocity(0, 0);
                leftBat.setLinearVelocity(0, 0);
                rightBat.setLinearVelocity(0, 0);}
            pausetext.setText(PAUSED_TEXT);
        }
        if (!isPausePerformed() && pauseFlag) {
            ball.setLinearVelocity(ballSpeedBeforePause);
            pauseFlag = false;
            pausetext.setText("");
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

    public static void setStartReadyStatus(boolean startReadyStatus) {
        HockeyRunner.startReadyStatus = startReadyStatus;
    }

    public static Text getWinText() {
        return winText;
    }

    public static Text getRestartText() {
        return restartText;
    }

    public static void setRestartReadyStatus(boolean restartReadyStatus) {
        HockeyRunner.restartReadyStatus = restartReadyStatus;
    }

    public PhysicsEntity getBall() {
        return ball;
    }

    public static Ballinitializer getBallinitializer() {
        return ballinitializer;
    }


    public static void main(String[] args) {
        launch(args);
    }
}