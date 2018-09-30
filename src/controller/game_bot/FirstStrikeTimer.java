package controller.game_bot;

import java.util.Date;

public class FirstStrikeTimer extends Thread {


    private int pauseTime;
    private static FirstStrikeTimer instance = new FirstStrikeTimer();
    private static boolean isTimerWorks = false;

    private FirstStrikeTimer() {

    }

    @Override
    public void run() {

        int t1 = (int) new Date().getTime();
        int t2 = t1;
        while (t2 - t1 < pauseTime) {
            t2 = (int) new Date().getTime();
        }
        BotControl.setIsFirstStrikeReady(true);


    }

    public static FirstStrikeTimer getTimer() {
        return instance;
    }


    public void setPauseTime(int pauseTime) {
        this.pauseTime = pauseTime;
    }
}
