package key_actions;

import com.almasb.fxgl.event.UserAction;

import hockey.HockeyRunner;

public class StartGameAction extends UserAction {



    private final static String name = "Start game";
    private static StartGameAction instance = new StartGameAction(name);;



    private StartGameAction(String name) {
        super(name);
        }

    @Override
    protected void onActionBegin() {
        HockeyRunner.setStartReadyStatus(false);
    }




    public static StartGameAction getStartGameAction() {
               return instance;
    }
}
