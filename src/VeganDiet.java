import java.util.ArrayList;

public class VeganDiet extends Diet {
    private float minWeightKg;

    public VeganDiet(int daysDuration, String purpose, float minWeightKg, Food... allowedFood){
        super(daysDuration, purpose, allowedFood);
        if(minWeightKg < 0){
            throw new IllegalArgumentException("Min weight cannot be a negative number!");
        }
        for(Food f : allowedFood){
            if(!f.getVegan()){
                throw new IllegalArgumentException("Cannot construct vegan diet containing any non-vegan food");
            }
        }
        this.minWeightKg = minWeightKg;
    }
    public float getMinWeightKg() {
        return minWeightKg;
    }
}
