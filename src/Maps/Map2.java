package Maps;


import Enemies.BaseHumanEnemy;
import Enemies.BaseHumanEnemy;
import Enemies.BaseHumanEnemy;
import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Enemies.StrongHumanEnemy;
import Enemies.StrongHumanEnemy;
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


      // Use a helper method to generate waves
      waves.add(generateWave(5, BaseHumanEnemy.class, 300, 523, Direction.LEFT));
      waves.add(generateWave(6, BaseHumanEnemy.class, 400, 523, Direction.RIGHT));
      waves.add(generateWave(7, BaseHumanEnemy.class, 500, 523, Direction.LEFT));

      // Add Zoomer HumanEnemys starting in Wave 4
      ArrayList<Enemy> wave4 = generateWave(4, BaseHumanEnemy.class, 200, 523, Direction.LEFT);
      wave4.add(new ZoomerHumanEnemy(new Point(800, 523), Direction.RIGHT));
      waves.add(wave4);

      // Add more Zoomer HumanEnemys and Base HumanEnemys
      ArrayList<Enemy> wave5 = generateWave(4, BaseHumanEnemy.class, 200, 523, Direction.RIGHT);
      wave5.add(new ZoomerHumanEnemy(new Point(350, 523), Direction.LEFT));
      wave5.add(new ZoomerHumanEnemy(new Point(450, 523), Direction.LEFT));
      waves.add(wave5);

      // Add Strong HumanEnemy in later waves
      ArrayList<Enemy> wave6 = generateWave(6, BaseHumanEnemy.class, 200, 523, Direction.LEFT);
      wave6.add(new StrongHumanEnemy(new Point(650, 523), Direction.RIGHT));
      wave6.add(new ZoomerHumanEnemy(new Point(750, 523), Direction.RIGHT));
      waves.add(wave6);

      // Boss Wave
      ArrayList<Enemy> bossWave = new ArrayList<>();
      bossWave.add(new StrongHumanEnemy(new Point(300, 523), Direction.RIGHT));
      waves.add(bossWave);

      return waves;
  }

 
  private ArrayList<Enemy> generateWave(int count, Class<? extends Enemy> enemyType, int startX, int y, Direction direction) {
      ArrayList<Enemy> wave = new ArrayList<>();
      int spacing = 100; // Space between each enemy

      for (int i = 0; i < count; i++) {
          try {
              Enemy enemy = enemyType.getConstructor(Point.class, Direction.class)
                  .newInstance(new Point(startX + i * spacing, y), direction);
              wave.add(enemy);
          } catch (Exception e) {
              e.printStackTrace();
          }
      }

      return wave;
  }
}
