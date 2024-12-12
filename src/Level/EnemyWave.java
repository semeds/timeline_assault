package Level;
import java.util.ArrayList;
public class EnemyWave {
    private final ArrayList<Enemy> enemies = new ArrayList<>();
    private int waveId;
    private int spawnDelay; // in milliseconds
    public EnemyWave(int waveId, int spawnDelay) {
        this.waveId = waveId;
        this.spawnDelay = spawnDelay;
    }
    public EnemyWave addEnemy(Enemy enemy) {
        if (enemy == null) {
            throw new IllegalArgumentException("Enemy cannot be null");
        }
        enemies.add(enemy);
        return this;
    }
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
    public boolean isComplete() {
        return enemies.stream().allMatch(Enemy::isDefeated);
    }
    public int getWaveId() {
        return waveId;
    }
    public int getSpawnDelay() {
        return spawnDelay;
    }
}