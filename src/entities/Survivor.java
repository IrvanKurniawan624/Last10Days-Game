package entities;
import entities.inventory.Inventory;

public class Survivor extends Character {
    private int stamina;
    private boolean infected = false;
    private int infectionDays = 0;
    private Inventory inventory = new Inventory();

    public Survivor(String name, int health, int damage, int stamina) {
        super(name, health, damage);
        this.stamina = stamina;
    }

    public Inventory getInventory() {
        return inventory; 
    }

    public int getStamina() {
        return stamina;
    }

    public void reduceStamina(int amount) {
        stamina = Math.max(0, stamina - amount);
    }

    public void restoreStamina(int amt) {
        stamina = Math.min(100, stamina + amt);
    }

    public boolean canRun() {
        return stamina >= 30;
    }

    public boolean isInfected() { return infected; }
    public void infect() { infected = true; infectionDays++; }
    public void cureInfection() { infected = false; infectionDays = 0; }

    public void updateStats() {
        if (infected) {
            takeDamage(15 * infectionDays);
            infectionDays++;
            if (infectionDays >= 4) health = 0;
        }
        restoreStamina(20);
    }
}