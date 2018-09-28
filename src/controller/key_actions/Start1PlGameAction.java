package controller.key_actions;

import com.almasb.fxgl.event.UserAction;

import hockey.HockeyRunner;

public class Start1PlGameAction extends UserAction {



    private final static String name = "Start 1 player game";

    private static Start1PlGameAction instance = new Start1PlGameAction(name);;
    private static boolean onePlayerMode = false;




    private Start1PlGameAction(String name) {
        super(name);
        }

    @Override
    protected void onActionBegin() {
        HockeyRunner.setStartReadyStatus(false);
        onePlayerMode =true;
    }

    public static boolean isOnePlayerMode() {
        return onePlayerMode;
    }

    public static Start1PlGameAction getStartGameAction() {
               return instance;
    }
}


