public class FlexitarianDiet extends Diet{
    private float maxMeatGramsPerWeek;
    private Food preferredMeat;

    public FlexitarianDiet(int daysDuration, String purpose, float maxMeatGramsPerWeek, Food preferredMeat, Food... allowedFood) {
            super(daysDuration, purpose, allowedFood);
        if (preferredMeat.getVegan() || !(preferredMeat.getType() == FoodType.PROTEIN)) {
            if(preferredMeat.getVegan()){
                throw new IllegalArgumentException("Preferred meat must be non-vegan");
            }else{
                throw new IllegalArgumentException("Preferred meat must be of FoodType.PROTEIN");
            }
        }
        this.maxMeatGramsPerWeek = maxMeatGramsPerWeek;
        this.preferredMeat = preferredMeat;
    }


    public float getMaxMeatGramsPerWeek() {
        return maxMeatGramsPerWeek;
    }

    public Food getPreferredMeat() {
        return preferredMeat;
    }
}
