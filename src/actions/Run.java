package actions;

import entities.Survivor;
import entities.zombie.Zombie;
import console.UI;
import console.TextColor;

public class Run implements Action {

    @Override
    public boolean execute(Survivor survivor, Zombie zombie, UI ui) {

        if (!survivor.canRun()) {
            System.out.println(TextColor.color(TextColor.RED, "Not enough stamina to run!"));
            return false;
        }

        survivor.reduceStamina(20);

        if (Math.random() < 0.7) {
            System.out.println(TextColor.color(TextColor.GREEN, "You escaped successfully!"));
            return true;
        }

        System.out.println(TextColor.color(TextColor.RED, "You failed to escape!"));
        return false;
    }
}
