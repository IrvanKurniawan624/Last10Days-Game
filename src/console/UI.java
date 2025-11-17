package console;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import entities.Survivor;
import entities.inventory.weapons.Weapon;
import entities.inventory.foods.Food;
import java.util.List;
import java.util.ArrayList;

public class UI{
    public void displayBanner() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/txt/Banner.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Failed to load banner! : " + e.getMessage());
        }
    }

    public void displayDifficultySelection(){
        System.out.println(TextColor.color(TextColor.BOLD, "Select Difficulty: "));
        System.out.println(TextColor.color(TextColor.BLUE, "1. Baby Mode 👶"));
        System.out.println(TextColor.color(TextColor.RED, "2. Normal Mode 🧟"));
        System.out.print(TextColor.color(TextColor.BOLD, "Choice: "));
    }

    public void displayIntro(String name) {
        Utils.clear();

        String intro = TextColor.color(TextColor.BOLD,
            "My name is " + name + "... and this city is dying.") +
            "\n\n" +
            "It's been months since the outbreak turned everything into a feeding ground for the dead.\n" +
            "Every street I once walked is now a graveyard.\n" +
            "Every building holds more bodies than memories.\n\n" +
            "But yesterday... something changed.\n\n" +
            "While scavenging an abandoned radio tower, I picked up a signal—weak, broken, but real.\n" +
            TextColor.color(TextColor.CYAN,
                "\"...Safe Zone Arcadia... final refuge... coordinates attached... only reachable on foot... " +
                "estimated travel: ten days...\"") +
            "\n\n" +
            TextColor.color(TextColor.YELLOW, "Ten days. Only ten days to reach freedom.") +
            "\n\n" +
            "Ten days through ruined districts and zombie-infested tunnels.\n" +
            "Ten days across burning highways and collapsing neighborhoods.\n" +
            "Ten days where one wrong choice means never seeing sunrise again.\n\n" +
            "But Arcadia… the last safe place… still stands.\n" +
            "A place untouched by infection. A place with walls. A place to finally breathe again.\n\n" +
            TextColor.color(TextColor.GREEN,
                "If I can survive just ten more days... I'll escape this nightmare.") +
            "\n\n" +
            "So I packed what little I had left—\n" +
            "a few scraps of food, a worn weapon, and one promise:\n\n" +
            TextColor.color(TextColor.BOLD, "\"I will reach Arcadia. I will survive.\"") +
            "\n\n" +
            TextColor.color(TextColor.RED, "The journey begins now...") +
            "\n\n" +
            TextColor.color(TextColor.BOLD, "Survive the next 10 days, " + name + ".");

        Utils.type(intro, 15);
    }

    public void displayWeapons(List<Weapon> weapons, boolean numbered) {
        System.out.println(TextColor.color(TextColor.GREEN, "Weapons:"));

        if (weapons.isEmpty()) {
            System.out.println("  " + TextColor.color(TextColor.RED, "None"));
            return;
        }

        for (int i = 0; i < weapons.size(); i++) {
            Weapon weapon = weapons.get(i);

            String prefix = numbered ? ( (i + 1) + ") " ) : " - ";
            String ammoInfo = weapon.hasAmmo() ? ", Ammo: " + weapon.getAmmo(): "";

            System.out.println(prefix +
                TextColor.color(TextColor.BOLD, weapon.getName()) +
                TextColor.color(TextColor.CYAN, " (DMG: " + weapon.getDamage() + ammoInfo + ")")
            );
        }
    }

    public void displayFoods(List<Food> foods, boolean numbered) {
        System.out.println(TextColor.color(TextColor.GREEN, "Food:"));

        if (foods.isEmpty()) {
            System.out.println("  " + TextColor.color(TextColor.RED, "None"));
            return;
        }

        for (int i = 0; i < foods.size(); i++) {
            Food food = foods.get(i);

            String prefix = numbered ? ( (i + 1) + ") " ) : " - ";

            System.out.println(prefix +
                TextColor.color(TextColor.BOLD, food.getName()) +
                TextColor.color(TextColor.YELLOW, " x" + food.getQuantity()) +
                TextColor.color(TextColor.GREEN, " (Heal: " + food.getHealAmount() + ", Stamina: " + food.getStaminaAmount() + ")")
            );
        }
    }

    public void showStatus(Survivor survivor) {
        System.out.println(TextColor.color(TextColor.BOLD, "====== PLAYER STATUS ======"));

        System.out.println(TextColor.color(TextColor.CYAN, "Name: ") + survivor.getName());
        System.out.println(TextColor.color(TextColor.RED, "Health: ") + survivor.getHealth() + "/100");
        System.out.println(TextColor.color(TextColor.YELLOW, "Stamina: ") + survivor.getStamina() + "/100\n");

        displayWeapons(survivor.getInventory().getWeapons(), false);

        System.out.println();

        displayFoods(survivor.getInventory().getFoods(), false);
        System.out.println(TextColor.color(TextColor.BOLD, "\n===========================\n"));
    }

}