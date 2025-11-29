import java.util.*;
import console.*;
import entities.Survivor;
import entities.inventory.weapons.*;
import entities.inventory.foods.*;
import systems.*;
import entities.event.EventList;

public class Game {
    private UI ui;
    private Utils utils = new Utils();
    private InputValidator validator;
    private Survivor player;
    private ZombieSystem zombieSystem;

    private String difficulty;


    public Game() {
        ui = new UI();
        validator = new InputValidator();
        
        Scanner sc = new Scanner(System.in);

        Utils.clear();
        ui.displayBanner();
        Utils.sleep(200);
        Utils.type(">> SYSTEM READY\n>> OBJECTIVE: SURVIVE FOR 10 DAYS", 50);
        System.out.print(TextColor.color(TextColor.RED, "Enter your name: "));
        String tempName = sc.nextLine().trim();
        Utils.clear();

        ui.displayBanner();
        ui.displayDifficultySelection();
        int temp = validator.getChoiceInRange(sc.nextLine().trim(), 1, 2);
        if (temp == 1) this.difficulty = "BABY_MODE";
        else this.difficulty = "NORMAL_MODE";


        Utils.progressBar();
        player = new Survivor(tempName, 100, 15, 75);  
        this.zombieSystem = new ZombieSystem(ui);
        

        if (this.difficulty == "BABY_MODE") {
            player.getInventory().addWeapon(new Wand());
        } else {
            player.getInventory().addWeapon(new Pistol(10));
        }

        player.getInventory().addWeapon(new Knife());
        player.getInventory().addFood(new Bread(2));
        player.getInventory().addFood(new Noodle(1));
        player.getInventory().addFood(new Cracker(2));
    }

    public void start(){
        int day = 1;
        Scanner sc = new Scanner(System.in);
        EventList eventList = new EventList();
        EventSystem eventSystem = new EventSystem(ui, eventList);

        System.out.print(TextColor.color(TextColor.RED, "Skip Intro (y/n) ? : "));
        String choice = sc.nextLine().trim().toLowerCase();

        if (!choice.equals("y")) {
            ui.displayIntro(player.getName());
        }

        while (day <= 10) {
            DayBanner.printDay(day);
            ui.showStatus(player);
            Utils.sleep(300);

            System.out.println();

            if (day == 1) {
                zombieSystem.startEncounter(player, day);
            } 
            else {
                int roll = new Random().nextInt(100) + 1;

                if (roll <= 40) {
                    eventSystem.triggerRandomEvent(player);
                } else {
                    zombieSystem.startEncounter(player, day);
                }
            }

            if (player.isDead()) {
                System.out.println(TextColor.color(TextColor.RED, "\nGAME OVER."));
                return;
            }

            System.out.println(TextColor.color(TextColor.GREEN, "\n*** Press ENTER to continue... ***"));
            sc.nextLine();
            day++;
            utils.clear();
        }

    }
}