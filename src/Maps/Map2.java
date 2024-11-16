package Maps;


import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Engine.ImageLoader;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import GameObject.Rectangle;
import Level.*;
import NPCs.AAsaultRiflePickup;
import NPCs.FPistolPickup;
import NPCs.FShotgunPickup;
import NPCs.FAssaultRiflePickup;
import NPCs.Walrus;
import Tilesets.CommonTileset;
import Utils.Direction;
import java.util.ArrayList;


// Represents a test map to be used in a level
public class Map2 extends Map {


   public Map2() {
       super("map2.txt", new CommonTileset());
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
       // TODO Auto-generated method stub
       throw new UnsupportedOperationException("Unimplemented method 'loadEnemyWaves'");
   }
}
