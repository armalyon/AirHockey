package controller.key_actions;

import com.almasb.fxgl.event.UserAction;

import hockey.HockeyRunner;

public class Start2PlGameAction extends UserAction {



    private final static String name = "Start 2 player game";

    private static Start2PlGameAction instance = new Start2PlGameAction(name);;


    public Start2PlGameAction(String name) {
        super(name);
    }

    @Override
    protected void onActionBegin() {
        HockeyRunner.setStartReadyStatus(false);
    }




    public static Start2PlGameAction getStartGameAction() {
        return instance;
    }
}
