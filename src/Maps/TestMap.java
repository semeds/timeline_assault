package Maps;

import Collectibles.Coin;
import Enemies.ZombieEnemy;
import Engine.ImageLoader;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import GameObject.Rectangle;
import Level.*;
import NPCs.Walrus;
import NPCs.WeaponPickup;
import Tilesets.CommonTileset;
import Utils.Direction;
import Utils.Point;
import java.util.ArrayList;

// Represents a test map to be used in a level
public class TestMap extends Map {

    public TestMap() {
        super("test_map.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(2, 11).getLocation();
    }

    /*
     * @Override
     * public ArrayList<Enemy> loadEnemies() {
     * ArrayList<Enemy> enemies = new ArrayList<>();
     * 
     * BugEnemy bugEnemy = new BugEnemy(getMapTile(16,
     * 10).getLocation().subtractY(25), Direction.LEFT);
     * enemies.add(bugEnemy);
     * 
     * DinosaurEnemy dinosaurEnemy = new DinosaurEnemy(getMapTile(19,
     * 1).getLocation().addY(2), getMapTile(22, 1).getLocation().addY(2),
     * Direction.RIGHT);
     * enemies.add(dinosaurEnemy);
     * 
     * ZombieEnemy zombieEnemy = new ZombieEnemy(getMapTile(0,10).getLocation(),
     * getMapTile(22,9).getLocation(), Direction.RIGHT);
     * enemies.add(zombieEnemy);
     * 
     * return enemies;
     * }
     */
    @Override
    protected ArrayList<ArrayList<Enemy>> loadEnemyWaves() {
        ArrayList<ArrayList<Enemy>> waves = new ArrayList<>();

        // Define Wave 1: Add a few ZombieEnemies
        ArrayList<Enemy> wave1 = new ArrayList<>();
        wave1.add(new ZombieEnemy(new Point(450, 530), new Point(300, 530), Direction.RIGHT));
        wave1.add(new ZombieEnemy(new Point(600, 530), new Point(400, 530), Direction.LEFT));
        waves.add(wave1);

        // Define Wave 2: Add more ZombieEnemies
        ArrayList<Enemy> wave2 = new ArrayList<>();
        wave2.add(new ZombieEnemy(new Point(100, 530), new Point(200, 530), Direction.RIGHT));
        waves.add(wave2);

        // Define Wave 3: Add even more ZombieEnemies
        ArrayList<Enemy> wave3 = new ArrayList<>();
        wave3.add(new ZombieEnemy(new Point(200, 530), new Point(400, 530), Direction.RIGHT));
        wave3.add(new ZombieEnemy(new Point(250, 530), new Point(350, 530), Direction.LEFT));
        waves.add(wave3);

        return waves;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        HorizontalMovingPlatform hmp = new HorizontalMovingPlatform(
                ImageLoader.load("GreenPlatform.png"),
                getMapTile(24, 6).getLocation(),
                getMapTile(27, 6).getLocation(),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6, 16, 4),
                Direction.RIGHT);
        enhancedMapTiles.add(hmp);

        EndLevelBox endLevelBox = new EndLevelBox(getMapTile(32, 7).getLocation());
        enhancedMapTiles.add(endLevelBox);

        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        // Adding the Walrus NPC
        Walrus walrus = new Walrus(getMapTile(30, 10).getLocation().subtractY(13));
        npcs.add(walrus);

        // Adding the WeaponPickup NPC
        WeaponPickup weaponPickup = new WeaponPickup(getMapTile(16, 7).getLocation(), this);
        npcs.add(weaponPickup);

        Coin coin = new Coin(getMapTile(10, 11).getLocation(), this);
        npcs.add(coin);
        return npcs;
    }

}
