package Maps;


import Enemies.BaseHumanEnemy;
import Enemies.BaseHumanEnemy;
import Enemies.BaseHumanEnemy;
import Enemies.BaseHumanEnemy;
import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Enemies.StrongHumanEnemy;
import Enemies.StrongHumanEnemy;
import Enemies.StrongHumanEnemy;
import Enemies.ZoomerHumanEnemy;
import Enemies.ZoomerHumanEnemy;
import Enemies.ZoomerHumanEnemy;
import Engine.ImageLoader;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import GameObject.Rectangle;
import Level.*;
import NPCs.Walrus;
import NPCs.MPistolPickup;
import NPCs.MAssaultRiflePickup;
import NPCs.MShotgunPickup;
import NPCs.Walrus;
import Tilesets.CommonTileset;
import Utils.Direction;
import Utils.Point;

import java.util.ArrayList;

import Collectibles.Coin;


// Represents a test map to be used in a level
public class Map2 extends Map {


   public Map2() {
       super("map2.txt", new CommonTileset());
       this.playerStartPosition = getMapTile(2, 11).getLocation();
   }

   @Override
    protected ArrayList<EnemyWave> loadEnemyWaves() {
        ArrayList<EnemyWave> waves = new ArrayList<>();

        // Define waves
        waves.add(new EnemyWave(1,1000)
                .addEnemy(new BaseHumanEnemy(new Point(200, 523), Direction.LEFT))
                .addEnemy(new BaseHumanEnemy(new Point(300, 523), Direction.LEFT))
        );

        waves.add(new EnemyWave(2,1000)
                .addEnemy(new ZoomerHumanEnemy(new Point(400, 523), Direction.RIGHT))
                .addEnemy(new BaseHumanEnemy(new Point(500, 523), Direction.RIGHT))
        );

        waves.add(new EnemyWave(3,1000)
                .addEnemy(new StrongHumanEnemy(new Point(600, 523), Direction.LEFT))
                .addEnemy(new BaseHumanEnemy(new Point(700, 523), Direction.RIGHT))
        );

        waves.add(new EnemyWave(4,1000) // Boss wave
                .addEnemy(new StrongHumanEnemy(new Point(350, 523), Direction.RIGHT))
                .addEnemy(new StrongHumanEnemy(new Point(500, 523), Direction.LEFT))
        );

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

      MPistolPickup mpistolPickup = new MPistolPickup(getMapTile(9, (int)7).getLocation(), this);
      npcs.add(mpistolPickup);




    //  MAssaultRiflePickup masaultriflePickup = new MAssaultRiflePickup(getMapTile(13, (int)11).getLocation(), this);
    //  npcs.add(masaultriflePickup);




    //  MShotgunPickup mshotgunPickup = new MShotgunPickup(getMapTile(11, (int)11).getLocation(), this);
    //  npcs.add(mshotgunPickup); 
  
       return npcs;
   }
}
