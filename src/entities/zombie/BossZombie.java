package entities.zombie;

public class BossZombie extends Zombie {
    private boolean enraged = false;

    public BossZombie() {
        super("The Boss", 500, 35);
    }

    @Override
    public void takeDamage(int dmg) {
        super.takeDamage(dmg);
        if (!enraged && health <= 200) {
            enraged = true;
            damage *= 2;
        }
    }
}
