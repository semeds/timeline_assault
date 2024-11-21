package Maps;


import Enemies.BaseHumanEnemy;
import Enemies.BaseHumanEnemy;
import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Enemies.StrongHumanEnemy;
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
    




   @Override
   protected ArrayList<ArrayList<Enemy>> loadEnemyWaves() {
    ArrayList<ArrayList<Enemy>> waves = new ArrayList<>();

    ArrayList<Enemy> wave1 = new ArrayList<>();
    wave1.add(new BaseHumanEnemy(new Point(250, 523), Direction.RIGHT));
    wave1.add(new BaseHumanEnemy(new Point(300, 523), Direction.RIGHT));
    wave1.add(new BaseHumanEnemy(new Point(1850, 523), Direction.LEFT));
    wave1.add(new BaseHumanEnemy(new Point(1950, 523), Direction.LEFT));
    wave1.add(new BaseHumanEnemy(new Point(1750, 523), Direction.LEFT));

    waves.add(wave1);


       // Define Wave 2: Add more HumanEnemyEnemies
       ArrayList<Enemy> wave2 = new ArrayList<>();
       wave2.add(new BaseHumanEnemy(new Point(250, 523), Direction.RIGHT));
       wave2.add(new BaseHumanEnemy(new Point(300, 523), Direction.RIGHT));
       wave2.add(new BaseHumanEnemy(new Point(850, 523), Direction.LEFT));
       wave2.add(new BaseHumanEnemy(new Point(950, 523), Direction.LEFT));
       wave2.add(new BaseHumanEnemy(new Point(750, 523), Direction.LEFT));
       wave2.add(new BaseHumanEnemy(new Point(200, 523), Direction.RIGHT));
       wave2.add(new BaseHumanEnemy(new Point(350, 523), Direction.RIGHT));

       waves.add(wave2);


       // Define Wave 3: Add even more HumanEnemyEnemies
       ArrayList<Enemy> wave3 = new ArrayList<>();
       wave3.add(new BaseHumanEnemy(new Point(100, 523), Direction.LEFT));
       wave3.add(new BaseHumanEnemy(new Point(200, 523), Direction.LEFT));
       wave3.add(new BaseHumanEnemy(new Point(300, 523), Direction.LEFT));
       wave3.add(new BaseHumanEnemy(new Point(400, 523), Direction.LEFT));
       wave3.add(new BaseHumanEnemy(new Point(1600, 523), Direction.RIGHT));
       wave3.add(new BaseHumanEnemy(new Point(2000, 523), Direction.RIGHT));
       wave3.add(new BaseHumanEnemy(new Point(1900, 523), Direction.RIGHT));
       wave3.add(new BaseHumanEnemy(new Point(1800, 523), Direction.RIGHT));
       wave3.add(new ZoomerHumanEnemy(new Point(500, 523), Direction.LEFT));
       wave3.add(new ZoomerHumanEnemy(new Point(1700, 523), Direction.RIGHT));
       waves.add(wave3);

       // Define Wave 4
        ArrayList<Enemy> wave4 = new ArrayList<>();
        wave4.add(new BaseHumanEnemy(new Point(100, 523), Direction.LEFT));
        wave4.add(new BaseHumanEnemy(new Point(200, 523), Direction.LEFT));
        wave4.add(new BaseHumanEnemy(new Point(300, 523), Direction.LEFT));
        wave4.add(new BaseHumanEnemy(new Point(400, 523), Direction.LEFT));
        wave4.add(new BaseHumanEnemy(new Point(2000, 523), Direction.RIGHT));
        wave4.add(new BaseHumanEnemy(new Point(1900, 523), Direction.RIGHT));
        wave4.add(new BaseHumanEnemy(new Point(1800, 523), Direction.RIGHT));
        wave4.add(new ZoomerHumanEnemy(new Point(500, 523), Direction.LEFT));
        wave4.add(new ZoomerHumanEnemy(new Point(1700, 523), Direction.RIGHT));
        waves.add(wave4);

        // Define Wave 5
        ArrayList<Enemy> wave5 = new ArrayList<>();
        wave5.add(new BaseHumanEnemy(new Point(100, 523), Direction.LEFT));
        wave5.add(new BaseHumanEnemy(new Point(200, 523), Direction.LEFT));
        wave5.add(new BaseHumanEnemy(new Point(300, 523), Direction.LEFT));
        wave5.add(new BaseHumanEnemy(new Point(400, 523), Direction.LEFT));
        wave5.add(new BaseHumanEnemy(new Point(2000, 523), Direction.RIGHT));
        wave5.add(new BaseHumanEnemy(new Point(1900, 523), Direction.RIGHT));
        wave5.add(new BaseHumanEnemy(new Point(1800, 523), Direction.RIGHT));
        wave5.add(new BaseHumanEnemy(new Point(1700, 523), Direction.RIGHT));
        wave5.add(new ZoomerHumanEnemy(new Point(500, 523), Direction.LEFT));
        wave5.add(new ZoomerHumanEnemy(new Point(1600, 523), Direction.RIGHT));
        waves.add(wave5);

        // Define Wave 6
        ArrayList<Enemy> wave6 = new ArrayList<>();
        wave6.add(new BaseHumanEnemy(new Point(100, 523), Direction.LEFT));
        wave6.add(new BaseHumanEnemy(new Point(200, 523), Direction.LEFT));
        wave6.add(new BaseHumanEnemy(new Point(300, 523), Direction.LEFT));
        wave6.add(new BaseHumanEnemy(new Point(400, 523), Direction.LEFT));
        wave6.add(new BaseHumanEnemy(new Point(2000, 523), Direction.RIGHT));
        wave6.add(new BaseHumanEnemy(new Point(1900, 523), Direction.RIGHT));
        wave6.add(new BaseHumanEnemy(new Point(1800, 523), Direction.RIGHT));
        wave6.add(new BaseHumanEnemy(new Point(1700, 523), Direction.RIGHT));
        wave6.add(new StrongHumanEnemy(new Point(500, 523), Direction.LEFT));
        wave6.add(new StrongHumanEnemy(new Point(1600, 523), Direction.RIGHT));
        wave6.add(new ZoomerHumanEnemy(new Point(600, 523), Direction.LEFT));
        wave6.add(new ZoomerHumanEnemy(new Point(700, 523), Direction.LEFT));
        wave6.add(new ZoomerHumanEnemy(new Point(1500, 523), Direction.RIGHT));
        wave6.add(new ZoomerHumanEnemy(new Point(1400, 523), Direction.RIGHT));
        waves.add(wave6);

        // Define Wave 7
        ArrayList<Enemy> wave7 = new ArrayList<>();
        wave7.add(new BaseHumanEnemy(new Point(100, 523), Direction.LEFT));
        wave7.add(new BaseHumanEnemy(new Point(200, 523), Direction.LEFT));
        wave7.add(new BaseHumanEnemy(new Point(300, 523), Direction.LEFT));
        wave7.add(new BaseHumanEnemy(new Point(400, 523), Direction.LEFT));
        wave7.add(new BaseHumanEnemy(new Point(500, 523), Direction.LEFT));
        wave7.add(new BaseHumanEnemy(new Point(600, 523), Direction.LEFT));
        wave7.add(new BaseHumanEnemy(new Point(2000, 523), Direction.RIGHT));
        wave7.add(new BaseHumanEnemy(new Point(1900, 523), Direction.RIGHT));
        wave7.add(new BaseHumanEnemy(new Point(1800, 523), Direction.RIGHT));
        wave7.add(new BaseHumanEnemy(new Point(1700, 523), Direction.RIGHT));
        wave7.add(new BaseHumanEnemy(new Point(1400, 523), Direction.RIGHT));
        wave7.add(new BaseHumanEnemy(new Point(1300, 523), Direction.RIGHT));
        wave7.add(new StrongHumanEnemy(new Point(500, 523), Direction.LEFT));
        wave7.add(new StrongHumanEnemy(new Point(1600, 523), Direction.RIGHT));
        wave7.add(new StrongHumanEnemy(new Point(700, 523), Direction.LEFT));
        wave7.add(new StrongHumanEnemy(new Point(1200, 523), Direction.RIGHT));
        wave7.add(new ZoomerHumanEnemy(new Point(600, 523), Direction.LEFT));
        wave7.add(new ZoomerHumanEnemy(new Point(1100, 523), Direction.RIGHT));
        waves.add(wave7);

        // Define Wave 8
        ArrayList<Enemy> wave8 = new ArrayList<>();
        wave8.add(new BaseHumanEnemy(new Point(100, 523), Direction.LEFT));
        wave8.add(new BaseHumanEnemy(new Point(200, 523), Direction.LEFT));
        wave8.add(new BaseHumanEnemy(new Point(300, 523), Direction.LEFT));
        wave8.add(new BaseHumanEnemy(new Point(400, 523), Direction.LEFT));
        wave8.add(new BaseHumanEnemy(new Point(2000, 523), Direction.RIGHT));
        wave8.add(new BaseHumanEnemy(new Point(1900, 523), Direction.RIGHT));
        wave8.add(new BaseHumanEnemy(new Point(1800, 523), Direction.RIGHT));
        wave8.add(new BaseHumanEnemy(new Point(1700, 523), Direction.RIGHT));
        wave8.add(new StrongHumanEnemy(new Point(500, 523), Direction.LEFT));
        wave8.add(new StrongHumanEnemy(new Point(1600, 523), Direction.RIGHT));
        wave8.add(new ZoomerHumanEnemy(new Point(600, 523), Direction.LEFT));
        wave8.add(new ZoomerHumanEnemy(new Point(1500, 523), Direction.RIGHT));
        waves.add(wave8);

        ArrayList<Enemy> wave9 = new ArrayList<>();
        wave9.add(new BaseHumanEnemy(new Point(100, 523), Direction.LEFT));
        wave9.add(new BaseHumanEnemy(new Point(200, 523), Direction.LEFT));
        wave9.add(new BaseHumanEnemy(new Point(300, 523), Direction.LEFT));
        wave9.add(new BaseHumanEnemy(new Point(400, 523), Direction.LEFT));
        wave9.add(new BaseHumanEnemy(new Point(700, 523), Direction.LEFT));
        wave9.add(new BaseHumanEnemy(new Point(900, 523), Direction.LEFT));
        wave9.add(new BaseHumanEnemy(new Point(800, 523), Direction.LEFT));
        wave9.add(new BaseHumanEnemy(new Point(850, 523), Direction.LEFT));
        wave9.add(new BaseHumanEnemy(new Point(2300, 523), Direction.RIGHT));
        wave9.add(new BaseHumanEnemy(new Point(2200, 523), Direction.RIGHT));
        wave9.add(new BaseHumanEnemy(new Point(2100, 523), Direction.RIGHT));
        wave9.add(new BaseHumanEnemy(new Point(2000, 523), Direction.RIGHT));
        wave9.add(new BaseHumanEnemy(new Point(2400, 523), Direction.RIGHT));
        wave9.add(new BaseHumanEnemy(new Point(2500, 523), Direction.RIGHT));
        wave9.add(new StrongHumanEnemy(new Point(1700, 523), Direction.RIGHT));
        wave9.add(new StrongHumanEnemy(new Point(1800, 523), Direction.RIGHT));
        wave9.add(new StrongHumanEnemy(new Point(500, 523), Direction.LEFT));
        wave9.add(new StrongHumanEnemy(new Point(1600, 523), Direction.RIGHT));
        wave9.add(new ZoomerHumanEnemy(new Point(600, 523), Direction.LEFT));
        wave9.add(new ZoomerHumanEnemy(new Point(1500, 523), Direction.RIGHT));
        wave9.add(new ZoomerHumanEnemy(new Point(750, 523), Direction.LEFT));
        wave9.add(new ZoomerHumanEnemy(new Point(1650, 523), Direction.RIGHT));


       return waves;
   }
}
