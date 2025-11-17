package entities.inventory;

import entities.inventory.weapons.Weapon;
import entities.inventory.foods.Food;
import entities.inventory.items.Item;

import java.util.List;
import java.util.ArrayList;

public class Inventory {

    private List<Weapon> weapons = new ArrayList<>();
    private List<Food> foods = new ArrayList<>();
    private List<Item> items = new ArrayList<>();

    public void addWeapon(Weapon weapon) {
        this.weapons.add(weapon);
    }

    public void addFood(Food food) {
        this.foods.add(food);
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public List<Weapon> getWeapons() {
        return this.weapons;
    }

    public List<Food> getFoods() {
        return this.foods;
    }

    public List<Item> getItems() {
        return this.items;
    }
}
