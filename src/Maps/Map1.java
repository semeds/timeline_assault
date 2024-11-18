package Maps;


import Enemies.*;
import Engine.ImageLoader;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import GameObject.Rectangle;
import Level.*;
import NPCs.Walrus;
import NPCs.MPistolPickup;
import NPCs.MAssaultRiflePickup;
import NPCs.MShotgunPickup;
import Tilesets.CommonTileset;
import Utils.Direction;
import java.util.ArrayList;


// Represents a test map to be used in a level
public class Map1 extends Map {


   public Map1() {
       super("map1.txt", new CommonTileset());
       this.playerStartPosition = getMapTile(2, 11).getLocation();
   }


   @Override
   public ArrayList<Enemy> loadEnemies() {
       ArrayList<Enemy> enemies = new ArrayList<>();


       BugEnemy bugEnemy = new BugEnemy(getMapTile(16, 10).getLocation().subtractY(25), Direction.LEFT);
       enemies.add(bugEnemy);


       DinosaurEnemy dinosaurEnemy = new DinosaurEnemy(getMapTile(19, 1).getLocation().addY(2), getMapTile(22, 1).getLocation().addY(2), Direction.RIGHT);
       enemies.add(dinosaurEnemy);


       return enemies;
   }


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

       MPistolPickup mpistolPickup = new MPistolPickup(getMapTile(12, (int)8).getLocation(), this);
       npcs.add(mpistolPickup);




      //MAssaultRiflePickup masaultriflePickup = new MAssaultRiflePickup(getMapTile(13, (int)11).getLocation(), this);
      //npcs.add(masaultriflePickup);




      //MShotgunPickup mshotgunPickup = new MShotgunPickup(getMapTile(11, (int)11).getLocation(), this);
      //npcs.add(mshotgunPickup);    


       Coin coin = new Coin(getMapTile(10, 11).getLocation(), this);
       npcs.add(coin);
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


       // Define Wave 2: Add more ZombieEnemies
       ArrayList<Enemy> wave2 = new ArrayList<>();
       wave2.add(new BaseHumanEnemy(new Point(250, 523), Direction.RIGHT));
       wave2.add(new BaseHumanEnemy(new Point(300, 523), Direction.RIGHT));
       wave2.add(new BaseHumanEnemy(new Point(850, 523), Direction.LEFT));
       wave2.add(new BaseHumanEnemy(new Point(950, 523), Direction.LEFT));
       wave2.add(new BaseHumanEnemy(new Point(750, 523), Direction.LEFT));
       wave2.add(new BaseHumanEnemy(new Point(200, 523), Direction.RIGHT));
       wave2.add(new BaseHumanEnemy(new Point(350, 523), Direction.RIGHT));
       wave2.add(new BaseHumanEnemy(new Point(650, 523), Direction.LEFT));
       wave2.add(new BaseHumanEnemy(new Point(800, 523), Direction.LEFT));
       wave2.add(new BaseHumanEnemy(new Point(750, 523), Direction.LEFT));

       waves.add(wave2);


       // Define Wave 3: Add even more ZombieEnemies
       ArrayList<Enemy> wave3 = new ArrayList<>();
       wave3.add(new BaseHumanEnemy(new Point(250, 523), Direction.RIGHT));
       wave3.add(new BaseHumanEnemy(new Point(300, 523), Direction.RIGHT));
       wave3.add(new BaseHumanEnemy(new Point(1850, 523), Direction.LEFT));
       wave3.add(new BaseHumanEnemy(new Point(1950, 523), Direction.LEFT));
       wave3.add(new BaseHumanEnemy(new Point(1750, 523), Direction.LEFT));
       wave3.add(new BaseHumanEnemy(new Point(250, 523), Direction.RIGHT));
       wave3.add(new BaseHumanEnemy(new Point(300, 523), Direction.RIGHT));
       wave3.add(new BaseHumanEnemy(new Point(1800, 523), Direction.LEFT));
       wave3.add(new BaseHumanEnemy(new Point(1900, 523), Direction.LEFT));
       wave3.add(new BaseHumanEnemy(new Point(1700, 523), Direction.LEFT));
       wave3.add(new BaseHumanEnemy(new Point(250, 523), Direction.RIGHT));
       wave3.add(new BaseHumanEnemy(new Point(300, 523), Direction.RIGHT));
       wave3.add(new BaseHumanEnemy(new Point(2000, 523), Direction.LEFT));
       wave3.add(new BaseHumanEnemy(new Point(2050, 523), Direction.LEFT));
       wave3.add(new BaseHumanEnemy(new Point(2100, 523), Direction.LEFT));
       
       waves.add(wave3);
       return waves;
   }
}