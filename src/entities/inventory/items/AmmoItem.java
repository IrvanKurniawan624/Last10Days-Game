package entities.inventory.items;
import entities.inventory.weapons.Weapon;

class AmmoItem extends Item{
    public AmmoItem(int amount){
        super("Ammo", amount);
    }

    public void useAmmo(Weapon weapon){
        if(weapon == null || weapon.ammo >= 0) return;
        weapon.ammo += amount;
        amount = 0;
    }
}