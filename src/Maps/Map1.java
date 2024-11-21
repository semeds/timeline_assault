package Maps;


import Enemies.*;
import Engine.ImageLoader;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import GameObject.Rectangle;
import Level.*;
import NPCs.Walrus;
import NPCs.MPistolPickup;
import NPCs.APistolPickup;
import NPCs.MAssaultRiflePickup;
import NPCs.MShotgunPickup;
import Tilesets.CommonTileset;
import Utils.Direction;
import Utils.Point;

import java.util.ArrayList;

import Collectibles.Coin;


// Represents a test map to be used in a level
public class Map1 extends Map {


  public Map1() {
      super("map1.txt", new CommonTileset());
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

      EndLevelBox endLevelBox = new EndLevelBox(getMapTile(32, 7).getLocation());
      //enhancedMapTiles.add(endLevelBox);


      return enhancedMapTiles;
  }


  @Override
  public ArrayList<NPC> loadNPCs() {
      ArrayList<NPC> npcs = new ArrayList<>();
   

     APistolPickup apistolPickup = new APistolPickup(getMapTile(9, (int)8).getLocation(), this);
      npcs.add(apistolPickup);




      //AAsaultRiflePickup aasaultriflePickup = new AAsaultRiflePickup(getMapTile(16, (int)6).getLocation(), this);
      //npcs.add(aasaultriflePickup);




      //AShotgunPickup ashotgunPickup = new AShotgunPickup(getMapTile(12, (int)11).getLocation(), this);
      //npcs.add(ashotgunPickup);


      Coin coin = new Coin(getMapTile(10, 11).getLocation(), this);
      npcs.add(coin);
      return npcs;
  }
   




  @Override
  protected ArrayList<ArrayList<Enemy>> loadEnemyWaves() {
      ArrayList<ArrayList<Enemy>> waves = new ArrayList<>();

      // Use a helper method to generate waves
      waves.add(generateWave(5, BaseZombie.class, 300, 523, Direction.LEFT));
      waves.add(generateWave(6, BaseZombie.class, 400, 523, Direction.RIGHT));
      waves.add(generateWave(7, BaseZombie.class, 500, 523, Direction.LEFT));

      // Add Zoomer Zombies starting in Wave 4
      ArrayList<Enemy> wave4 = generateWave(4, BaseZombie.class, 200, 523, Direction.LEFT);
      wave4.add(new ZoomerZombie(new Point(800, 523), Direction.RIGHT));
      waves.add(wave4);

      // Add more Zoomer Zombies and Base Zombies
      ArrayList<Enemy> wave5 = generateWave(4, BaseZombie.class, 200, 523, Direction.RIGHT);
      wave5.add(new ZoomerZombie(new Point(700, 523), Direction.LEFT));
      wave5.add(new ZoomerZombie(new Point(600, 523), Direction.LEFT));
      waves.add(wave5);

      // Add Strong Zombie in later waves
      ArrayList<Enemy> wave6 = generateWave(6, BaseZombie.class, 200, 523, Direction.LEFT);
      wave6.add(new StrongZombie(new Point(900, 523), Direction.RIGHT));
      wave6.add(new ZoomerZombie(new Point(1000, 523), Direction.RIGHT));
      waves.add(wave6);

      // Boss Wave
      ArrayList<Enemy> bossWave = new ArrayList<>();
      bossWave.add(new StrongZombie(new Point(200, 523), Direction.RIGHT));
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