package Maps;


import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Enemies.ZombieEnemy;
import Engine.ImageLoader;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import GameObject.Rectangle;
import Level.*;
import NPCs.Walrus;
import NPCs.APistolPickup;
import NPCs.AAsaultRiflePickup;
import NPCs.FPistolPickup;
import NPCs.FShotgunPickup;
import NPCs.FAssaultRiflePickup;
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

   /* 
   @Override
   public ArrayList<Enemy> loadEnemies() {
       ArrayList<Enemy> enemies = new ArrayList<>();


       BugEnemy bugEnemy = new BugEnemy(getMapTile(16, 10).getLocation().subtractY(25), Direction.LEFT);
       enemies.add(bugEnemy);


       DinosaurEnemy dinosaurEnemy = new DinosaurEnemy(getMapTile(19, 1).getLocation().addY(2), getMapTile(22, 1).getLocation().addY(2), Direction.RIGHT);
       enemies.add(dinosaurEnemy);


       return enemies;
   }
    */

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

       FPistolPickup fpistolPickup = new FPistolPickup(getMapTile(15, (int)11).getLocation(), this);
       npcs.add(fpistolPickup);




       //FAssaultRiflePickup fasaultriflePickup = new FAssaultRiflePickup(getMapTile(13, (int)11).getLocation(), this);
     // npcs.add(fasaultriflePickup);




     // FShotgunPickup fshotgunPickup = new FShotgunPickup(getMapTile(11, (int)11).getLocation(), this);
      //npcs.add(fshotgunPickup);
  
       return npcs;
   }
    




   @Override
   protected ArrayList<ArrayList<Enemy>> loadEnemyWaves() {
       ArrayList<ArrayList<Enemy>> waves = new ArrayList<>();


       // Define Wave 1: Add a few ZombieEnemies
       ArrayList<Enemy> wave1 = new ArrayList<>();
       wave1.add(new ZombieEnemy(new Point(250, 523), new Point(350, 523), Direction.RIGHT));
       wave1.add(new ZombieEnemy(new Point(300, 523), new Point(400, 523), Direction.LEFT));
       wave1.add(new ZombieEnemy(new Point(350, 523), new Point(450, 523), Direction.RIGHT));
       wave1.add(new ZombieEnemy(new Point(400, 523), new Point(500, 523), Direction.LEFT));
       waves.add(wave1);


       // Define Wave 2: Add more ZombieEnemies
       ArrayList<Enemy> wave2 = new ArrayList<>();
       wave2.add(new ZombieEnemy(new Point(250, 523), new Point(350, 523), Direction.RIGHT));
       wave2.add(new ZombieEnemy(new Point(300, 523), new Point(400, 523), Direction.RIGHT));
       wave2.add(new ZombieEnemy(new Point(350, 523), new Point(450, 523), Direction.RIGHT));

       waves.add(wave2);


       // Define Wave 3: Add even more ZombieEnemies
       ArrayList<Enemy> wave3 = new ArrayList<>();
       wave3.add(new ZombieEnemy(new Point(200, 523), new Point(300, 523), Direction.RIGHT));
       wave3.add(new ZombieEnemy(new Point(250, 523), new Point(350, 523), Direction.LEFT));

       waves.add(wave3);

       ArrayList<Enemy> wave4 = new ArrayList<>();
       wave4.add(new ZombieEnemy(new Point(200, 523), new Point(400, 523), Direction.RIGHT));
       wave4.add(new ZombieEnemy(new Point(250, 523), new Point(350, 523), Direction.LEFT));
       wave4.add(new ZombieEnemy(new Point(300, 523), new Point(400, 523), Direction.RIGHT));
       wave4.add(new ZombieEnemy(new Point(350, 523), new Point(450, 523), Direction.LEFT));

       waves.add(wave4);

       ArrayList<Enemy> wave5 = new ArrayList<>();
       wave5.add(new ZombieEnemy(new Point(200, 523), new Point(400, 523), Direction.RIGHT));
       wave5.add(new ZombieEnemy(new Point(250, 523), new Point(350, 523), Direction.LEFT));
       wave5.add(new ZombieEnemy(new Point(300, 523), new Point(400, 523), Direction.RIGHT));
       wave5.add(new ZombieEnemy(new Point(400, 523), new Point(500, 523), Direction.LEFT));
       waves.add(wave5);


       return waves;
   }
}
