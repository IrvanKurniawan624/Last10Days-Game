package actions;

import java.util.List;
import java.util.Scanner;

import entities.Survivor;
import entities.zombie.Zombie;
import entities.inventory.foods.Food;
import console.UI;
import console.TextColor;
import console.InputValidator;

public class UseItem implements Action {

    private Scanner sc = new Scanner(System.in);
    private InputValidator validator = new InputValidator();

    @Override
    public boolean execute(Survivor survivor, Zombie zombie, UI ui) {

        List<Food> foods = survivor.getInventory().getFoods();

        if (foods.isEmpty()) {
            System.out.println(TextColor.color(TextColor.RED, "You have no food!"));
            return false;
        }

        System.out.println(TextColor.color(TextColor.CYAN, "Choose food:"));
        ui.displayFoods(foods, true);

        System.out.print("> ");
        int choice = validator.getChoiceInRange(sc.nextLine().trim(), 1, foods.size());

        Food selectedFood = foods.get(choice - 1);
        selectedFood.consume(survivor);

        System.out.println(TextColor.color(TextColor.GREEN, "You consumed " + selectedFood.getName()));

        if (selectedFood.getQuantity() <= 0) {
            foods.remove(selectedFood);
        }

        return false;
    }
}
