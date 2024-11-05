package Maps;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Sprite;
import Level.Map;
import Tilesets.CommonTileset;
import Utils.Colors;
import Utils.Point;

// Represents the map that is used as a background for the main menu and credits menu screen
public class TitleScreenMap extends Map {

    private Sprite joe;

    public TitleScreenMap() {
        super("title_screen_map.txt", new CommonTileset());
        Point joeLocation = getMapTile(6, 8).getLocation().subtractX(24).subtractY(6);
        joe = new Sprite(ImageLoader.loadSubImage("Joe.png", Colors.MAGENTA, 0, 0, 24, 24));
        joe.setScale(3);
        joe.setLocation(joeLocation.x, joeLocation.y);
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        joe.draw(graphicsHandler);
    }
}
/* 
    @Override
    protected ArrayList<ArrayList<Enemy>> loadEnemyWaves() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadEnemyWaves'");
    }

}
*/