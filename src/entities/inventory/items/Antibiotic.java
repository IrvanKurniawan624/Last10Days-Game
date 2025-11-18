package entities.inventory.items;
import entities.Survivor;

public class Antibiotic extends Item{
    public Antibiotic(){
        super("Antibiotic", 1);
    }

    public boolean use(Survivor survivor){
        if(amount <= 0) return false;
        survivor.cureInfection();
        amount--;
        return true;
    }
}