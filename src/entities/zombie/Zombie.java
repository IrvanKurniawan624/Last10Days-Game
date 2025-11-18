package entities.zombie;
import entities.Character;
import entities.Survivor;

public abstract class Zombie extends Character {

    public Zombie(String name, int health, int damage) {
        super(name, health, damage);
    }

    public String getName() {
        return this.name;
    }

    public int getDamage() {
        return this.damage;
    }

    public int getHealth() {
        return this.health;
    }

    @Override
    public void attack(Character target) {
        super.attack(target);
        if (target instanceof Survivor) {
            Survivor survivor = (Survivor) target;
            if (Math.random() < 0.15) {
                survivor.infect();
            }
        }
    }
}
