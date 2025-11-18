package systems;

import java.util.*;

import console.InputValidator;
import console.TextColor;
import console.UI;

import entities.Survivor;
import entities.event.*;
import entities.inventory.weapons.Weapon;


public class EventSystem {

    private UI ui;
    private Random random = new Random();
    private InputValidator validator = new InputValidator();
    private Scanner sc = new Scanner(System.in);

    private List<Event> events;

    public EventSystem(UI ui, EventList eventList) {
        this.ui = ui;
        this.events = eventList.getEvents();
    }

    public void triggerRandomEvent(Survivor survivor) {

        if (events == null || events.isEmpty()) {
            System.out.println(TextColor.color(TextColor.RED, "No events available."));
            return;
        }

        Event event = events.get(random.nextInt(events.size()));

        System.out.println(TextColor.color(
            TextColor.YELLOW, 
            "\n--- Event: " + event.getName() + " ---"
        ));

        String desc = event.getDescription().replace("<name>", survivor.getName());
        System.out.println(TextColor.color(TextColor.CYAN, desc));
        System.out.println();

        Map<String, EventChoice> choices = event.getChoices();
        List<String> keys = new ArrayList<>(choices.keySet());

        for (int i = 0; i < keys.size(); i++) {
            System.out.println((i + 1) + ") " + keys.get(i));
        }

        System.out.print("> ");
        int choiceIndex = validator.getChoiceInRange(sc.nextLine().trim(), 1, keys.size());

        if (choiceIndex == -1) {
            System.out.println(TextColor.color(TextColor.RED, "Invalid choice. Event skipped."));
            return;
        }

        EventChoice choice = choices.get(keys.get(choiceIndex - 1));

        applyOutcome(survivor, choice);
        System.out.println();
    }

    private void applyOutcome(Survivor survivor, EventChoice c) {

        if (c.dialog != null) {

            if (c.dialog instanceof String) {
                String d = (String) c.dialog;
                d = d.replace("<name>", survivor.getName());
                d = parseFoodPlaceholder(d, survivor);
                System.out.println(TextColor.color(TextColor.GREEN, d));
            }

            else if (c.dialog instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, Object> dObj = (Map<String, Object>) c.dialog;

                boolean hasWeapon = survivor.getInventory().hasAnyWeapon();

                String d;
                if (hasWeapon && dObj.containsKey("hasWeapon")) {
                    d = dObj.get("hasWeapon").toString();
                } else if (!hasWeapon && dObj.containsKey("noWeapon")) {
                    d = dObj.get("noWeapon").toString();
                } else {
                    d = "You proceed cautiously...";
                }

                d = d.replace("<name>", survivor.getName());
                d = parseFoodPlaceholder(d, survivor);
                System.out.println(TextColor.color(TextColor.GREEN, d));
            }
        }

        if (c.health != null) {
            if (c.health < 0) survivor.takeDamage(-c.health);
            else survivor.heal(c.health);
        }

        if (c.stamina != null) {
            if (c.stamina < 0) survivor.reduceStamina(-c.stamina);
            else survivor.restoreStamina(c.stamina);
        }

        if (c.foods != null) {
            for (LootItem f : c.foods) {
                int amt = f.amount == null ? 1 : f.amount;
                survivor.getInventory().addFoodByName(f.name, amt);
                System.out.println(TextColor.color(TextColor.CYAN, "+ Food: " + f.name + " x" + amt));
            }
        }

        if (c.weapons != null) {
            for (LootItem w : c.weapons) {
                survivor.getInventory().addWeaponByName(w.name);
                System.out.println(TextColor.color(TextColor.CYAN, "+ Weapon: " + w.name));
            }
        }

        if (c.items != null) {
            for (LootItem it : c.items) {
                int amt = it.amount == null ? 1 : it.amount;
                survivor.getInventory().addItemByName(it.name, amt, it.category);
                System.out.println(TextColor.color(TextColor.CYAN, "+ Item: " + it.name + " x" + amt));
            }
        }

        if (c.removeFood != null) {
            survivor.getInventory().removeRandomFood(c.removeFood.amount);
            System.out.println(TextColor.color(TextColor.RED, "- Lost Food x" + c.removeFood.amount));
        }

        if (c.removeWeapon != null) {
            survivor.getInventory().removeWeapon(c.removeWeapon);
            System.out.println(TextColor.color(TextColor.RED, "- Lost Weapon: " + c.removeWeapon));
        }

        if (c.ammo != null) {
            Weapon w = survivor.getInventory().getWeaponByName(c.ammo.weapon);

            if (w != null) {
                w.addAmmo(c.ammo.amount);
                System.out.println(TextColor.color(TextColor.CYAN,
                    "+ Ammo: " + c.ammo.weapon + " +" + c.ammo.amount
                ));
            } else {
                System.out.println(TextColor.color(TextColor.RED,
                    "You found ammo for " + c.ammo.weapon + ", but you don't have that weapon."
                ));
            }
        }
    }

    private String parseFoodPlaceholder(String dialog, Survivor survivor) {
        String foodName = survivor.getInventory().peekFoodName();
        if (foodName != null) {
            dialog = dialog.replace("{foodName}", foodName);
        }
        return dialog;
    }
}
