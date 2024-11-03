package Level;

import Enemies.ZombieEnemy;
import Level.Enemy;
import Utils.Direction;
import Utils.Point;
import NPCs.WeaponPickup;
import java.util.ArrayList;

public class EnemyWaves extends Map {

    public EnemyWaves(String mapFileName, Tileset tileset) {
        super(mapFileName, tileset);
    }

    @Override
    protected ArrayList<ArrayList<Enemy>> loadEnemyWaves() {
        ArrayList<ArrayList<Enemy>> waves = new ArrayList<>();

        // Wave 1: Two ZombieEnemies walking from left to right
        ArrayList<Enemy> wave1 = new ArrayList<>();
        wave1.add(new ZombieEnemy(new Point(250, 523), new Point(350, 523), Direction.RIGHT));
        wave1.add(new ZombieEnemy(new Point(300, 523), new Point(400, 523), Direction.LEFT));

        

        ArrayList<Enemy> wave2 = new ArrayList<>();
        wave2.add(new ZombieEnemy(new Point(100, 523), new Point(200, 523), Direction.LEFT));
        waves.add(wave2);

        // Wave 3: Three ZombieEnemies moving in opposite directions
        ArrayList<Enemy> wave3 = new ArrayList<>();
        //
        wave3.add(new ZombieEnemy(new Point(200, 540), new Point(400, 523), Direction.RIGHT));
        wave3.add(new ZombieEnemy(new Point(250, 523), new Point(350, 523), Direction.LEFT));

        waves.add(wave3);

        return waves;
    }
}
