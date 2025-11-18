package systems;

import console.UI;
import console.TextColor;
import entities.Survivor;
import entities.zombie.*;

import java.util.Random;
import java.util.Scanner;

public class ZombieSystem {

    private UI ui;
    private CombatSystem combat;
    private Random random = new Random();
    private Scanner sc = new Scanner(System.in);

    public ZombieSystem(UI ui) {
        this.ui = ui;
        this.combat = new CombatSystem(ui);
    }

    public void startEncounter(Survivor survivor, int day) {

        Zombie zombie = generate(day);

        System.out.println(TextColor.color(TextColor.RED, "A zombie approaches...\n"));

        if (zombie instanceof BossZombie) {
            System.out.println(TextColor.color(
                    TextColor.YELLOW,
                    "⚠ FINAL CHALLENGE! A BOSS ZOMBIE EMERGES FROM THE SHADOWS! ⚠\n"
            ));
        } else if (zombie instanceof TankZombie) {
            System.out.println(TextColor.color(
                    TextColor.RED,
                    "A massive Tank Zombie stomps toward you!\n"
            ));
        } else if (zombie instanceof SlasherZombie) {
            System.out.println(TextColor.color(
                    TextColor.RED,
                    "A fast Slasher Zombie darts between rubble!\n"
            ));
        } else {
            System.out.println(TextColor.color(
                    TextColor.RED,
                    "A decaying walker slowly drags itself toward you...\n"
            ));
        }

        boolean alive = combat.startCombat(survivor, zombie);

        if (!alive) {
            System.out.println(TextColor.color(TextColor.RED,
                    "\nYou were killed by the zombie..."));
            return;
        }

        System.out.println(TextColor.color(TextColor.GREEN, "You defeated the zombie!"));
        System.out.println(TextColor.color(TextColor.GREEN, "\n*** Press ENTER to continue... ***"));
        sc.nextLine();
    }

    private Zombie generate(int day) {
        if (day == 10) {
            return new BossZombie();
        }

        if (day <= 3) {
            return new NormalZombie();
        }

        int r = random.nextInt(10);

        if (r < 5) return new NormalZombie();
        else if (r < 8) return new SlasherZombie();
        else return new TankZombie();
    }
}
