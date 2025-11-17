package actions;

import entities.Survivor;
import entities.zombie.Zombie;
import console.UI;

public interface Action {
    boolean execute(Survivor survivor, Zombie zombie, UI ui);
}