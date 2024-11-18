package Maps;


import Collectibles.Coin;
import Engine.ImageLoader;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import GameObject.Rectangle;
import Level.*;
import Enemies.*;
import NPCs.Walrus;
import NPCs.APistolPickup;
import NPCs.AAsaultRiflePickup;
import NPCs.AShotgunPickup;
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
       // enhancedMapTiles.add(hmp);


       EndLevelBox endLevelBox = new EndLevelBox(getMapTile(32, 7).getLocation());
       // enhancedMapTiles.add(endLevelBox);


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




      //AAsaultRiflePickup aasaultriflePickup = new AAsaultRiflePickup(getMapTile(16, (int)6).getLocation(), this);
      //npcs.add(aasaultriflePickup);




      //AShotgunPickup ashotgunPickup = new AShotgunPickup(getMapTile(12, (int)11).getLocation(), this);
      //npcs.add(ashotgunPickup);


       Coin coin = new Coin(getMapTile(10, 11).getLocation(), this);
       npcs.add(coin);
       return npcs;
   }


   protected ArrayList<ArrayList<Enemy>> loadEnemyWaves() {
       ArrayList<ArrayList<Enemy>> waves = new ArrayList<>();


       // Define Wave 1: Add a few ZombieEnemies
       ArrayList<Enemy> wave1 = new ArrayList<>();
       wave1.add(new BaseZombie(new Point(800, 523), Direction.LEFT));
       wave1.add(new BaseZombie(new Point(750, 523), Direction.RIGHT));
       wave1.add(new BaseZombie(new Point(700, 523), Direction.LEFT));
       wave1.add(new BaseZombie(new Point(650, 523), Direction.LEFT));
       wave1.add(new BaseZombie(new Point(500, 523), Direction.LEFT));
       waves.add(wave1);


       // Define Wave 2: Add more ZombieEnemies
       ArrayList<Enemy> wave2 = new ArrayList<>();
       wave2.add(new BaseZombie(new Point(250, 523), Direction.LEFT));
       wave2.add(new BaseZombie(new Point(300, 523), Direction.RIGHT));
       wave2.add(new BaseZombie(new Point(350, 523), Direction.LEFT));
       wave2.add(new BaseZombie(new Point(450, 523), Direction.RIGHT));
       wave2.add(new BaseZombie(new Point(200, 523), Direction.LEFT));
       wave2.add(new BaseZombie(new Point(550, 523), Direction.RIGHT));
       waves.add(wave2);


       // Define Wave 3: Add even more ZombieEnemies
       ArrayList<Enemy> wave3 = new ArrayList<>();
      
       wave3.add(new BaseZombie(new Point(250, 523), Direction.RIGHT));
       wave3.add(new BaseZombie(new Point(300, 523), Direction.RIGHT));
       wave3.add(new BaseZombie(new Point(350, 523), Direction.RIGHT));
       wave3.add(new BaseZombie(new Point(450, 523), Direction.RIGHT));
       wave3.add(new BaseZombie(new Point(200, 523), Direction.RIGHT));
       wave3.add(new BaseZombie(new Point(550, 523), Direction.RIGHT));
       waves.add(wave3);

       ArrayList<Enemy> wave4 = new ArrayList<>();
       wave4.add(new ZoomerZombie(new Point(200, 523), Direction.RIGHT));
       wave4.add(new BaseZombie(new Point(250, 523), Direction.LEFT));
       wave4.add(new BaseZombie(new Point(300, 523), Direction.RIGHT));
       wave4.add(new BaseZombie(new Point(350, 523), Direction.LEFT));

       waves.add(wave4);

       ArrayList<Enemy> wave5 = new ArrayList<>();
       wave5.add(new ZoomerZombie(new Point(200, 523), Direction.RIGHT));
       wave5.add(new ZoomerZombie(new Point(250, 523), Direction.LEFT));
       wave5.add(new BaseZombie(new Point(300, 523), Direction.RIGHT));
       wave5.add(new BaseZombie(new Point(400, 523), Direction.LEFT));
       wave5.add(new BaseZombie(new Point(500, 523), Direction.RIGHT));
       wave5.add(new BaseZombie(new Point(600, 523), Direction.LEFT));

       waves.add(wave5);

       ArrayList<Enemy> wave6 = new ArrayList<>();
       wave6.add(new ZoomerZombie(new Point(200, 523), Direction.RIGHT));
       wave6.add(new BaseZombie(new Point(300, 523), Direction.RIGHT));
       wave6.add(new BaseZombie(new Point(400, 523), Direction.LEFT));
       wave6.add(new ZoomerZombie(new Point(250, 523), Direction.LEFT));
       wave6.add(new BaseZombie(new Point(500, 523), Direction.RIGHT));
       wave6.add(new BaseZombie(new Point(600, 523), Direction.LEFT));
       wave6.add(new ZoomerZombie(new Point(250, 523), Direction.LEFT));
       wave6.add(new BaseZombie(new Point(500, 523), Direction.RIGHT));
       wave6.add(new BaseZombie(new Point(600, 523), Direction.LEFT));

       waves.add(wave6);

       ArrayList<Enemy> bossWave = new ArrayList<>();
       bossWave.add(new StrongZombie(new Point(200,523), Direction.RIGHT));

       waves.add(bossWave);

       return waves;
   }
}
