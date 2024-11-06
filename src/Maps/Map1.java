package Maps;

import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Engine.ImageLoader;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import GameObject.Rectangle;
import Level.*;
import NPCs.AAsaultRiflePickup;
import NPCs.APistolPickup;
import NPCs.AShotgunPickup;
import NPCs.Walrus;
import Tilesets.CommonTileset;
import Utils.Direction;
import java.util.ArrayList;

// Represents a test map to be used in a level
public class Map1 extends Map {

    public Map1() {
        super("map1.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(32, 7).getLocation();
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();
        /* 
        BugEnemy bugEnemy = new BugEnemy(getMapTile(16, 10).getLocation().subtractY(25), Direction.LEFT);
        enemies.add(bugEnemy);

        DinosaurEnemy dinosaurEnemy = new DinosaurEnemy(getMapTile(19, 1).getLocation().addY(2), getMapTile(22, 1).getLocation().addY(2), Direction.RIGHT);
        enemies.add(dinosaurEnemy);
        */
        return enemies;
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
                new Rectangle(0, 6,16,4),
                Direction.RIGHT
        );
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
    
        APistolPickup apistolPickup = new APistolPickup(getMapTile(9, (int)8).getLocation(), this);
       npcs.add(apistolPickup);


       AAsaultRiflePickup aasaultriflePickup = new AAsaultRiflePickup(getMapTile(16, (int)6).getLocation(), this);
       npcs.add(aasaultriflePickup);


       AShotgunPickup ashotgunPickup = new AShotgunPickup(getMapTile(12, (int)11).getLocation(), this);
       npcs.add(ashotgunPickup); 
    
        return npcs;
    }
}