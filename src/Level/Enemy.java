package Level;

import GameObject.Frame;
import GameObject.SpriteSheet;
import java.util.HashMap;
import java.util.Random;
import Utils.Direction;


// This class is a base class for all enemies in the game -- all enemies should extend from it
public class Enemy extends MapEntity {

    public int hitPoints = 1; // Default hitpoints

    protected boolean isInvincible = false;
    private long invincibilityStartTime;
    private static final long INVINCIBILITY_DURATION = 1000;
    private static final float SPEED = 0;
    protected Direction facingDirection;

    public Enemy(float x, float y, SpriteSheet spriteSheet, String startingAnimation) {
        super(x, y, spriteSheet, startingAnimation);
        // this.facingDirection = direction;
    }

    public Enemy(float x, float y, HashMap<String, Frame[]> animations, String startingAnimation) {
        super(x, y, animations, startingAnimation);
    }

    public Enemy(float x, float y, Frame[] frames) {
        super(x, y, frames);
    }

    public Enemy(float x, float y, Frame frame) {
        super(x, y, frame);
    }

    public Enemy(float x, float y) {
        super(x, y);
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    public void update(Player player) {
        super.update();
        if (intersects(player)) {
            touchedPlayer(player);
        }

        if (isInvincible && (System.currentTimeMillis() - invincibilityStartTime) >= INVINCIBILITY_DURATION) {
            isInvincible = false;
        }

        moveTowardsPlayer(player);

    }

    // A subclass can override this method to specify what it does when it touches
    // the player
    public void touchedPlayer(Player player) {
        if (!isInvincible) {
            player.hurtPlayer(this);
            takeDamage();
        }
    }

    public void takeDamage() {
        hitPoints--;

        if (hitPoints <= 0) {
            die();
        } else {
            isInvincible = true;
            invincibilityStartTime = System.currentTimeMillis();
        }
    }

    public void die() {
        Random random = new Random();
        int chance = random.nextInt(10);

        // Write the loot dropping logic here
        if (map != null) { // Ensure the map is assigned to this enemy
            if (chance < 9) {
                // 90% chance enemy drops a coin
                map.spawnCoin(this.getX(), this.getY());
            } else {
                // 10% chance it drops a powerup
                map.spawnpowerup(this.getX(), this.getY());
            }
            map.removeEnemy(this);
        }
    }

    public void moveTowardsPlayer(Player player) {
        float playerX = player.getX();

        // Move along the x-axis
        if (playerX < this.getX()) {
            moveX(-SPEED); // Move left towards the player
            facingDirection = Direction.LEFT;
        } else if (playerX > this.getX()) {
            moveX(SPEED); // Move right towards the player
            facingDirection = Direction.RIGHT;
        }

        // For if enemies can move vertically
        /* 
        float playerY = player.getY();
        if (playerY < this.getY()) {
            moveY(-SPEED); // Move up towards the player
        } else if (playerY > this.getY()) {
            moveY(SPEED); // Move down towards the player
        }
        */
    }

    public boolean isDefeated() {
        return hitPoints <= 0;
    }
}