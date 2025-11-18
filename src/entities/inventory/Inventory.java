package entities.inventory;

import entities.inventory.weapons.*;
import entities.inventory.foods.*;
import entities.inventory.items.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;

public class Inventory {

    private List<Weapon> weapons = new ArrayList<>();
    private List<Food> foods = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private Random rnd = new Random();

    public List<Weapon> getWeapons() { return weapons; }
    public List<Food> getFoods() { return foods; }
    public List<Item> getItems() { return items; }

    public void addWeapon(Weapon w) { weapons.add(w); }
    public void addFood(Food f) { foods.add(f); }
    public void addItem(Item i) { items.add(i); }

    public void addFoodByName(String name, int amount) {
        if (name == null) return;

        switch (name.toLowerCase()) {
            case "bread":
                foods.add(new Bread(amount));
                break;
            case "noodle":
                foods.add(new Noodle(amount));
                break;
            case "cracker":
                foods.add(new Cracker(amount));
                break;
            default:
                foods.add(new Food(name, 5, 5, amount) {});
                break;
        }
    }

    public void removeRandomFood(int amount) {
        for (int i = 0; i < amount && !foods.isEmpty(); i++) {
            int index = rnd.nextInt(foods.size());
            foods.remove(index);
        }
    }

    public String peekFoodName() {
        if (foods.isEmpty()) return null;
        return foods.get(0).getName();
    }

    public Weapon getWeaponByName(String name) {
        for (Weapon w : weapons) {
            if (w.getName().equalsIgnoreCase(name)) {
                return w;
            }
        }
        return null;
    }

    public void addWeaponByName(String name) {
        if (name == null) return;

        switch (name.toLowerCase()) {
            case "shotgun":
                weapons.add(new Shotgun(4));
                break;
            case "pistol":
                weapons.add(new Pistol(12));
                break;
            case "wand":
                weapons.add(new Wand());
                break;
            case "knife":
                weapons.add(new Knife());
                break;
            default:
                break;
        }
    }

    public void removeWeapon(String name) {
        Iterator<Weapon> it = weapons.iterator();
        while (it.hasNext()) {
            Weapon w = it.next();
            if (w.getName().equalsIgnoreCase(name)) {
                it.remove();
                return;
            }
        }
    }

    public boolean hasAnyWeapon() {
        return !weapons.isEmpty();
    }

    public void addItemByName(String name, int amount, String category) {

        if (name == null) return;

        switch (name.toLowerCase()) {
            case "antibiotic":
                items.add(new Antibiotic());
                break;

            default:
                break;
        }
    }


}
