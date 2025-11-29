package actions;

import entities.Survivor;
import entities.zombie.Zombie;
import console.UI;
import console.TextColor;

public class Hide implements Action {

    @Override
    public boolean execute(Survivor survivor, Zombie zombie, UI ui) {

        if (Math.random() < 0.5) {
            System.out.println(TextColor.color(TextColor.GREEN, "You hid successfully!"));
            survivor.heal(25);
            survivor.restoreStamina(10);
            return false;
        }

        System.out.println(TextColor.color(TextColor.RED, "You failed to hide!"));

        int dmg = zombie.getDamage() / 2;
        survivor.takeDamage(dmg);

        System.out.println(TextColor.color(TextColor.RED, "Zombie hits you for " + dmg + " damage!"));

        return false;
    }
}
