package entities.inventory.foods;
import entities.Survivor;

public abstract class Food{
    protected String name;
    protected int healAmount;
    protected int staminaAmount;
    protected int quantity;

    public Food(String name, int healAmount, int staminaAmount, int quantity){
        this.name = name;
        this.healAmount = healAmount;
        this.staminaAmount = staminaAmount;
        this.quantity = quantity;
    }

    public String getName(){
        return this.name;
    }

    public int getHealAmount(){
        return this.healAmount;
    }

    public int getStaminaAmount(){
        return this.staminaAmount;
    }  
    public int getQuantity(){
        return this.quantity;
    }

    public void consume(Survivor survivor){
        if(this.quantity > 0){
            survivor.heal(this.healAmount);
            survivor.restoreStamina(this.staminaAmount);
            this.quantity--;
        }
    }
}