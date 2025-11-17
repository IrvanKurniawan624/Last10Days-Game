package managers;

import java.util.Scanner;

import actions.*;
import entities.Survivor;
import entities.zombie.Zombie;
import console.UI;
import console.TextColor;
import console.InputValidator;

public class CombatSystem {

    private UI ui;
    private Scanner sc = new Scanner(System.in);
    private InputValidator validator = new InputValidator();

    private AttackAction attackAction = new AttackAction();
    private UseItemAction useItemAction = new UseItemAction();
    private RunAction runAction = new RunAction();
    private HideAction hideAction = new HideAction();

    public CombatSystem(UI ui) {
        this.ui = ui;
    }

    public boolean startCombat(Survivor survivor, Zombie zombie) {

        System.out.println(TextColor.color(
            TextColor.YELLOW,
            "A zombie appears: " + zombie.getName() + " (HP: " + zombie.getHealth() + ")"
        ));

        while (!zombie.isDead() && !survivor.isDead()) {

            System.out.println(TextColor.color(TextColor.CYAN, "\nYour Actions:"));
            System.out.println("1) Attack");
            System.out.println("2) Use Food");
            System.out.println("3) Run");
            System.out.println("4) Hide");

            System.out.print("> ");
            int choice = validator.getChoiceInRange(sc.nextLine().trim(), 1, 4);

            switch (choice) {
                case 1:
                    attackAction.execute(survivor, zombie, ui);
                    break;

                case 2:
                    useItemAction.execute(survivor, zombie, ui);
                    break;

                case 3:
                    if (runAction.execute(survivor, zombie, ui))
                        return true; 
                    break;

                case 4:
                    hideAction.execute(survivor, zombie, ui);
                    break;
            }

            if (!zombie.isDead()) {
                System.out.println(TextColor.color(TextColor.RED, "\nZombie attacks!"));
                zombie.attack(survivor);
                System.out.println("Your HP: " + survivor.getHealth());
            }
        }

        return survivor.getHealth() > 0;
    }
}
