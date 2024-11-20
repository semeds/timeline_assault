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

       ArrayList<Enemy> wave1 = new ArrayList<>();
       wave1.add(new BaseZombie(new Point(250, 523), Direction.RIGHT));
       wave1.add(new BaseZombie(new Point(300, 523), Direction.RIGHT));
       wave1.add(new BaseZombie(new Point(1850, 523), Direction.LEFT));
       wave1.add(new BaseZombie(new Point(1950, 523), Direction.LEFT));
       wave1.add(new BaseZombie(new Point(1750, 523), Direction.LEFT));

      waves.add(wave1);


      // Define Wave 2: Add more ZombieEnemies
      ArrayList<Enemy> wave2 = new ArrayList<>();
      wave2.add(new BaseZombie(new Point(250, 523), Direction.RIGHT));
      wave2.add(new BaseZombie(new Point(300, 523), Direction.RIGHT));
      wave2.add(new BaseZombie(new Point(850, 523), Direction.LEFT));
      wave2.add(new BaseZombie(new Point(950, 523), Direction.LEFT));
      wave2.add(new BaseZombie(new Point(750, 523), Direction.LEFT));
      wave2.add(new BaseZombie(new Point(200, 523), Direction.RIGHT));
      wave2.add(new BaseZombie(new Point(350, 523), Direction.RIGHT));
      wave2.add(new BaseZombie(new Point(650, 523), Direction.LEFT));
      wave2.add(new BaseZombie(new Point(800, 523), Direction.LEFT));
      wave2.add(new BaseZombie(new Point(750, 523), Direction.LEFT));

      waves.add(wave2);


       // Define Wave 3: Add even more ZombieEnemies
       ArrayList<Enemy> wave3 = new ArrayList<>();
       wave3.add(new BaseZombie(new Point(100, 523), Direction.LEFT));
       wave3.add(new BaseZombie(new Point(200, 523), Direction.LEFT));
       wave3.add(new BaseZombie(new Point(300, 523), Direction.LEFT));
       wave3.add(new BaseZombie(new Point(400, 523), Direction.LEFT));
       wave3.add(new BaseZombie(new Point(1600, 523), Direction.RIGHT));
       wave3.add(new BaseZombie(new Point(2000, 523), Direction.RIGHT));
       wave3.add(new BaseZombie(new Point(1900, 523), Direction.RIGHT));
       wave3.add(new BaseZombie(new Point(1800, 523), Direction.RIGHT));
       wave3.add(new ZoomerZombie(new Point(500, 523), Direction.LEFT));
       wave3.add(new ZoomerZombie(new Point(1700, 523), Direction.RIGHT));
       waves.add(wave3);

       // Define Wave 4
        ArrayList<Enemy> wave4 = new ArrayList<>();
        wave4.add(new BaseZombie(new Point(100, 523), Direction.LEFT));
        wave4.add(new BaseZombie(new Point(200, 523), Direction.LEFT));
        wave4.add(new BaseZombie(new Point(300, 523), Direction.LEFT));
        wave4.add(new BaseZombie(new Point(400, 523), Direction.LEFT));
        wave4.add(new BaseZombie(new Point(2000, 523), Direction.RIGHT));
        wave4.add(new BaseZombie(new Point(1900, 523), Direction.RIGHT));
        wave4.add(new BaseZombie(new Point(1800, 523), Direction.RIGHT));
        wave4.add(new ZoomerZombie(new Point(500, 523), Direction.LEFT));
        wave4.add(new ZoomerZombie(new Point(1700, 523), Direction.RIGHT));
        waves.add(wave4);

        // Define Wave 5
        ArrayList<Enemy> wave5 = new ArrayList<>();
        wave5.add(new BaseZombie(new Point(100, 523), Direction.LEFT));
        wave5.add(new BaseZombie(new Point(200, 523), Direction.LEFT));
        wave5.add(new BaseZombie(new Point(300, 523), Direction.LEFT));
        wave5.add(new BaseZombie(new Point(400, 523), Direction.LEFT));
        wave5.add(new BaseZombie(new Point(2000, 523), Direction.RIGHT));
        wave5.add(new BaseZombie(new Point(1900, 523), Direction.RIGHT));
        wave5.add(new BaseZombie(new Point(1800, 523), Direction.RIGHT));
        wave5.add(new BaseZombie(new Point(1700, 523), Direction.RIGHT));
        wave5.add(new ZoomerZombie(new Point(500, 523), Direction.LEFT));
        wave5.add(new ZoomerZombie(new Point(1600, 523), Direction.RIGHT));
        waves.add(wave5);

        // Define Wave 6
        ArrayList<Enemy> wave6 = new ArrayList<>();
        wave6.add(new BaseZombie(new Point(100, 523), Direction.LEFT));
        wave6.add(new BaseZombie(new Point(200, 523), Direction.LEFT));
        wave6.add(new BaseZombie(new Point(300, 523), Direction.LEFT));
        wave6.add(new BaseZombie(new Point(400, 523), Direction.LEFT));
        wave6.add(new BaseZombie(new Point(2000, 523), Direction.RIGHT));
        wave6.add(new BaseZombie(new Point(1900, 523), Direction.RIGHT));
        wave6.add(new BaseZombie(new Point(1800, 523), Direction.RIGHT));
        wave6.add(new BaseZombie(new Point(1700, 523), Direction.RIGHT));
        wave6.add(new StrongZombie(new Point(500, 523), Direction.LEFT));
        wave6.add(new StrongZombie(new Point(1600, 523), Direction.RIGHT));
        wave6.add(new ZoomerZombie(new Point(600, 523), Direction.LEFT));
        wave6.add(new ZoomerZombie(new Point(700, 523), Direction.LEFT));
        wave6.add(new ZoomerZombie(new Point(1500, 523), Direction.RIGHT));
        wave6.add(new ZoomerZombie(new Point(1400, 523), Direction.RIGHT));
        waves.add(wave6);

        // Define Wave 7
        ArrayList<Enemy> wave7 = new ArrayList<>();
        wave7.add(new BaseZombie(new Point(100, 523), Direction.LEFT));
        wave7.add(new BaseZombie(new Point(200, 523), Direction.LEFT));
        wave7.add(new BaseZombie(new Point(300, 523), Direction.LEFT));
        wave7.add(new BaseZombie(new Point(400, 523), Direction.LEFT));
        wave7.add(new BaseZombie(new Point(2000, 523), Direction.RIGHT));
        wave7.add(new BaseZombie(new Point(1900, 523), Direction.RIGHT));
        wave7.add(new BaseZombie(new Point(1800, 523), Direction.RIGHT));
        wave7.add(new BaseZombie(new Point(1700, 523), Direction.RIGHT));
        wave7.add(new StrongZombie(new Point(500, 523), Direction.LEFT));
        wave7.add(new StrongZombie(new Point(1600, 523), Direction.RIGHT));
        wave7.add(new ZoomerZombie(new Point(600, 523), Direction.LEFT));
        wave7.add(new ZoomerZombie(new Point(1500, 523), Direction.RIGHT));
        waves.add(wave7);

        // Define Wave 8
        ArrayList<Enemy> wave8 = new ArrayList<>();
        wave8.add(new BaseZombie(new Point(100, 523), Direction.LEFT));
        wave8.add(new BaseZombie(new Point(200, 523), Direction.LEFT));
        wave8.add(new BaseZombie(new Point(300, 523), Direction.LEFT));
        wave8.add(new BaseZombie(new Point(400, 523), Direction.LEFT));
        wave8.add(new BaseZombie(new Point(2000, 523), Direction.RIGHT));
        wave8.add(new BaseZombie(new Point(1900, 523), Direction.RIGHT));
        wave8.add(new BaseZombie(new Point(1800, 523), Direction.RIGHT));
        wave8.add(new BaseZombie(new Point(1700, 523), Direction.RIGHT));
        wave8.add(new StrongZombie(new Point(500, 523), Direction.LEFT));
        wave8.add(new StrongZombie(new Point(1600, 523), Direction.RIGHT));
        wave8.add(new ZoomerZombie(new Point(600, 523), Direction.LEFT));
        wave8.add(new ZoomerZombie(new Point(1500, 523), Direction.RIGHT));
        waves.add(wave8);

        

    return waves;
  }
}