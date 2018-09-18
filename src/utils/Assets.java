package utils;



import com.almasb.fxgl.asset.AssetManager;
import hockey.HockeyRunner;


public class Assets {


    private AssetManager assets;



    public Assets(HockeyRunner hockey)  {
        assets = hockey.getAssetManager();
        try {
            assets.cache();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public AssetManager getAssets() {
        return assets;
    }
}


