package entities.inventory.items;
import entities.Survivor;

class Antibiotic extends Item{
    public Antibiotic(int amount){
        super("Antibiotic", amount);
    }

    public boolean use(Survivor survivor){
        if(amount <= 0) return false;
        survivor.cureInfection();
        amount--;
        return true;
    }
}