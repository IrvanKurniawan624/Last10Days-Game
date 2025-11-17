package actions;

import java.util.List;
import java.util.Scanner;

import entities.Survivor;
import entities.zombie.Zombie;
import entities.inventory.weapons.Weapon;
import console.UI;
import console.TextColor;
import console.InputValidator;

public class Attack implements Action {

    private Scanner sc = new Scanner(System.in);
    private InputValidator validator = new InputValidator();

    @Override
    public boolean execute(Survivor survivor, Zombie zombie, UI ui) {

        List<Weapon> weapons = survivor.getInventory().getWeapons();

        if (weapons.isEmpty()) {
            System.out.println(TextColor.color(TextColor.RED, "You have no weapons!"));
            return false;
        }

        System.out.println(TextColor.color(TextColor.CYAN, "Choose a weapon:"));
        ui.displayWeapons(weapons, true);

        System.out.print("> ");
        int choice = validator.getChoiceInRange(sc.nextLine().trim(), 1, weapons.size());

        Weapon weapon = weapons.get(choice - 1);

        if (!weapon.isUsable()) {
            System.out.println(TextColor.color(TextColor.RED, "This weapon has no ammo!"));
            return false;
        }

        int damage = weapon.getDamage();
        zombie.takeDamage(damage);

        System.out.println(TextColor.color(TextColor.YELLOW, "You dealt " + damage + " damage!"));
        System.out.println("Zombie HP: " + zombie.getHealth());

        weapon.useAmmo();
        survivor.reduceStamina(10);

        return true;
    }
}
