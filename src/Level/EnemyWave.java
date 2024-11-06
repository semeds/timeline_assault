package Level;
import Enemies.DinosaurEnemy; // Import specific enemy class
import Utils.Point; // Import the Point class
import java.util.ArrayList;
import Utils.Direction;

public class EnemyWave {
    private int waveNumber;
    private ArrayList<Enemy> enemies;
    private int baseEnemyCount = 3;
    private int maxEnemyCount = 5;
    private Player player;
    private int enemySpacing = 50;

    public EnemyWave(Player player) {
        this.player = player;
        this.waveNumber = 0;
        this.enemies = new ArrayList<>();
    }

    public void startWave() {
        waveNumber++;
        enemies.clear();

        int enemyCount = Math.min(baseEnemyCount + waveNumber, maxEnemyCount);
        for (int i = 0; i < enemyCount; i++) {
            spawnEnemy(i);
        }
    }

    private void spawnEnemy(int index) {
        int xPosition = 350 + (index * enemySpacing);
        enemies.add(new DinosaurEnemy(new Point(xPosition, 100), new Point(500, 100), Direction.RIGHT));
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public boolean isWaveComplete() {
        return enemies.isEmpty();
    }
}
