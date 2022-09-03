public class LowCarbDiet extends Diet{
    private float minWeightKg;

    public LowCarbDiet(int daysDuration, String purpose, float minWeightKg, Food... allowedFood) {
        super(daysDuration, purpose, allowedFood);
        if(minWeightKg < 0){
            throw new IllegalArgumentException("Min weight cannot be a negative number!");
        }
        int numberOfCarb = 0;
        for (Food f:allowedFood) {
            if (f.getType() == FoodType.CARB){
                numberOfCarb++;
            } if(numberOfCarb > 2) {
                throw new IllegalArgumentException("Cannot have more than two carb type foods!");
            }
        }

        this.minWeightKg = minWeightKg;
    }

    public float getMinWeightKg() {
        return minWeightKg;
    }
}
