import Enemies.ZombieEnemy;
import Level.Enemy;
import Utils.Direction;
import Utils.Point;

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
        wave1.add(new ZombieEnemy(new Point(50, 100), new Point(300, 100), Direction.RIGHT));
        wave1.add(new ZombieEnemy(new Point(200, 150), new Point(400, 150), Direction.RIGHT));
        waves.add(wave1);

        // Wave 2: One ZombieEnemy patrolling from right to left
        ArrayList<Enemy> wave2 = new ArrayList<>();
        wave2.add(new ZombieEnemy(new Point(500, 200), new Point(100, 200), Direction.LEFT));
        waves.add(wave2);

        // Wave 3: Three ZombieEnemies moving in opposite directions
        ArrayList<Enemy> wave3 = new ArrayList<>();
        wave3.add(new ZombieEnemy(new Point(50, 250), new Point(350, 250), Direction.RIGHT));
        wave3.add(new ZombieEnemy(new Point(400, 300), new Point(100, 300), Direction.LEFT));
        wave3.add(new ZombieEnemy(new Point(150, 350), new Point(450, 350), Direction.RIGHT));
        waves.add(wave3);

        return waves;
    }
}
