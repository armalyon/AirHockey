package controller.game_bot;


import hockey.HockeyRunner;

public class PointersTimer extends Thread {


    private int pauseTime;

    private static boolean isTimerWorks = false;

    public PointersTimer(int pauseTime) {
        this.pauseTime = pauseTime;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(pauseTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HockeyRunner.getLeftPointer().setText("");
        HockeyRunner.getRightPointer().setText("");
    }
}
