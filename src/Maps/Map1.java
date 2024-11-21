package Maps;


import Enemies.*;
import Engine.ImageLoader;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import GameObject.Rectangle;
import Level.*;
import NPCs.Walrus;
import NPCs.APistolPickup;
import NPCs.AAsaultRiflePickup;
import NPCs.AShotgunPickup;
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

  @Override
    protected ArrayList<EnemyWave> loadEnemyWaves() {
        ArrayList<EnemyWave> waves = new ArrayList<>();

        // Define waves
        waves.add(new EnemyWave(1,1000)
                .addEnemy(new BaseZombie(new Point(100, 523), Direction.LEFT))
                .addEnemy(new BaseZombie(new Point(200, 523), Direction.LEFT))
        );

        waves.add(new EnemyWave(2,1000)
                .addEnemy(new ZoomerZombie(new Point(150, 523), Direction.RIGHT))
                .addEnemy(new BaseZombie(new Point(250, 523), Direction.RIGHT))
        );

        waves.add(new EnemyWave(3,1000)
                .addEnemy(new StrongZombie(new Point(600, 523), Direction.LEFT))
                .addEnemy(new BaseZombie(new Point(350, 523), Direction.RIGHT))
        );

        waves.add(new EnemyWave(4,1000) // Boss wave
                .addEnemy(new StrongZombie(new Point(350, 523), Direction.RIGHT))
                .addEnemy(new StrongZombie(new Point(500, 523), Direction.LEFT))
        );

        waves.add(new EnemyWave(5,1000)
                .addEnemy(new StrongZombie(new Point(150, 523), Direction.RIGHT))
                .addEnemy(new BaseZombie(new Point(500, 523), Direction.LEFT))
                .addEnemy(new ZoomerZombie(new Point(250, 523), Direction.RIGHT))
                .addEnemy(new StrongZombie(new Point(600, 523), Direction.LEFT))
                .addEnemy(new BaseZombie(new Point(350, 523), Direction.RIGHT))
                .addEnemy(new BaseZombie(new Point(400, 523), Direction.LEFT))
                .addEnemy(new BaseZombie(new Point(100, 523), Direction.RIGHT))
                .addEnemy(new StrongZombie(new Point(200, 523), Direction.LEFT))
        );

    
        return waves;
    }

  @Override
  public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
      ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

      EndLevelBox endLevelBox = new EndLevelBox(getMapTile(32, 7).getLocation());
      enhancedMapTiles.add(endLevelBox);


      return enhancedMapTiles;
  }


  @Override
  public ArrayList<NPC> loadNPCs() {
      ArrayList<NPC> npcs = new ArrayList<>();
   

     APistolPickup apistolPickup = new APistolPickup(getMapTile(10, (int)7).getLocation(), this);
      npcs.add(apistolPickup);


    //   AAsaultRiflePickup aasaultriflePickup = new AAsaultRiflePickup(getMapTile(16, (int)6).getLocation(), this);
    //   npcs.add(aasaultriflePickup);

    //   AShotgunPickup ashotgunPickup = new AShotgunPickup(getMapTile(12, (int)11).getLocation(), this);
    //   npcs.add(ashotgunPickup);


      Coin coin = new Coin(getMapTile(10, 11).getLocation(), this);
      npcs.add(coin);
      return npcs;
  }
}