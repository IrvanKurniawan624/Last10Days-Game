package entities.inventory.weapons;

public abstract class Weapon {
    protected String name;
    protected int damage;
    public int ammo; //? -1 = infinite

    public Weapon(String name, int damage, int ammo){
        this.name = name;
        this.damage = damage;
        this.ammo = ammo; 
    }

    public String getName(){
        return this.name;
    }

    public void addAmmo(int amount) {
        this.ammo += amount;
    }

    public int getDamage(){
        return this.damage;
    }   

    public int getAmmo(){
        return this.ammo;
    }   

    public boolean hasAmmo(){
        return this.ammo != -1;
    }   

    public void useAmmo() {
        if (ammo > 0) {
            ammo--;
        }
    }

    public boolean isUsable() {
        return ammo == -1 || ammo > 0;
    }
}


