package model.components;

import com.almasb.fxgl.effect.ParticleEntity;
import com.almasb.fxgl.entity.Entity;
import hockey.HockeyRunner;
import model.Type;
import utils.Assets;


public class BackgroundInitializator {


    private final String BACKGROUND_FILE_NAME = "background.jpg";


    public BackgroundInitializator(Assets assets, HockeyRunner hockey) {
        Entity background = new ParticleEntity(Type.BACKGROUND);
        background.setSceneView(assets.getAssets().loadTexture(BACKGROUND_FILE_NAME));
        background.setPosition(0,0);
        hockey.getGameWorld().addEntity(background);


    }


}