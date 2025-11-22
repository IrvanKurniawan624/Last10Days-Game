package systems;

import java.util.Scanner;

import actions.*;
import entities.Survivor;
import entities.zombie.Zombie;
import console.UI;
import console.Utils;
import console.TextColor;
import console.InputValidator;

public class CombatSystem {
    private UI ui;
    private Utils utils = new Utils();
    private Scanner sc = new Scanner(System.in);
    private InputValidator validator = new InputValidator();

    private Attack attack = new Attack();
    private UseItem useItem = new UseItem();
    private Run run = new Run();
    private Hide hide = new Hide();

    public CombatSystem(UI ui) {
        this.ui = ui;
    }

    public boolean startCombat(Survivor survivor, Zombie zombie) {

        System.out.println(TextColor.color(
            TextColor.YELLOW,
            "A zombie appears: " + zombie.getName() + " (HP: " + zombie.getHealth() + ")"
        ));

        while (!zombie.isDead() && !survivor.isDead()) {
            boolean notLoopCombat = true;
            boolean skipAttack = false;
            System.out.println(TextColor.color(TextColor.CYAN, "\nYour Actions:"));
            System.out.println("1) Attack");
            System.out.println("2) Use Food");
            System.out.println("3) Run");
            System.out.println("4) Hide");

            System.out.print("> ");
            int choice = validator.getChoiceInRange(sc.nextLine().trim(), 1, 4);
            if (choice == -1) {
                continue; 
            }

            switch (choice) {
                case 1:
                    notLoopCombat = attack.execute(survivor, zombie, ui);
                    break;

                case 2:
                    useItem.execute(survivor, zombie, ui);
                    break;

                case 3:
                    skipAttack = run.execute(survivor, zombie, ui);
                    break;

                case 4:
                    skipAttack = hide.execute(survivor, zombie, ui);
                    break;
                default:
                    utils.clear();
                    break;
            }

            if(!notLoopCombat){continue;}

            if (!skipAttack && !zombie.isDead()) {
                System.out.println(TextColor.color(TextColor.RED, "\nZombie attacks dealt : " + zombie.getDamage() + " damage" ));
                zombie.attack(survivor);
            }
            System.out.println("Your HP: " + survivor.getHealth());
            System.out.println("Your Stamina: " + survivor.getStamina());
        }

        return survivor.getHealth() > 0;
    }
}
