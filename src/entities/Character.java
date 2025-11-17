package entities;

public abstract class Character {
    protected String name;
    protected int health;
    protected int damage;

    public Character(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isDead() { 
        return health <= 0;
    }

    public void heal(int amount) {
        health = Math.min(100, health + amount);
    }

    public void takeDamage(int dmg){
        health = Math.max(0, health - dmg);
    }

    public void attack(Character target) {
        target.takeDamage(this.damage);
    }
}