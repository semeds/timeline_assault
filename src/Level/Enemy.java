package Level;

import GameObject.Frame;
import GameObject.SpriteSheet;
import java.util.HashMap;

// This class is a base class for all enemies in the game -- all enemies should extend from it
public class Enemy extends MapEntity {

    public int hitPoints = 1; // Default hitpoints

    protected boolean isInvincible = false;
    private long invincibilityStartTime;
    private static final long INVINCIBILITY_DURATION = 1000;

    // public static float SPEED = 1f;
    // protected Direction facingDirection;

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

        /*
         * //Chase player along the x-axis
         * //if (player.getX() < this.getX()) {
         * //If the player is to the left, move the enemy left
         * this.moveX(-SPEED);
         * this.facingDirection = Direction.LEFT;
         * } else if (player.getX() > this.getX()) {
         * // If the player is to the right, move the enemy right
         * this.moveX(SPEED);
         * this.facingDirection = Direction.RIGHT;
         * }
         */
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
        // Write the loot dropping logic here
        if (map != null) { // Ensure the map is assigned to this enemy
            map.spawnCoin(this.getX(), this.getY());
            map.removeEnemy(this);
        }
    }
}