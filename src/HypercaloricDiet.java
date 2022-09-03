public class HypercaloricDiet extends Diet{
    private float maxWeightKg;
    private float minCaloriesPerDay;

    public HypercaloricDiet(int daysDuration, String purpose, float maxWeightKg, float minCaloriesPerDay, Food... allowedFood) {
        super(daysDuration, purpose, allowedFood);
        if (maxWeightKg < 0){
            throw new IllegalArgumentException("Max weight cannot be a negative number!");
        }
        this.maxWeightKg = maxWeightKg;
        this.minCaloriesPerDay = minCaloriesPerDay;
    }

    public float getMaxWeightKg() {
        return maxWeightKg;
    }

    public float getMinCaloriesPerDay() {
        return minCaloriesPerDay;
    }
}
