package entities.event;

import java.util.List;

public class EventChoice {

    public Object dialog;

    public Integer health;
    public Integer stamina;

    public List<LootItem> foods;
    public List<LootItem> weapons;
    public List<LootItem> items;

    public RemoveFood removeFood;
    public String removeWeapon;

    public AmmoReward ammo;

    public static class RemoveFood {
        public int amount;
    }

    public static class AmmoReward {
        public String weapon;
        public int amount;
    }
}
