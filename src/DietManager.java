import java.util.ArrayList;

public class DietManager {
    public static boolean areCompatible(Person person, Diet diet){
        //Test if person has non-vegan favourite food
        if(!(person.getFavoriteFood().getVegan()) && diet.getClass().getName() == "VeganDiet"){
            return false;
        }

        //Loop through Person.allergies and check if more than 50% match with allowedFoods in Diet
        int numberOfAllergies = diet.getAllowedFood().length;
        int allergiesMatch = 0;
        for(Food allergy : person.getAllergies()){
            for(Food allowedFood : diet.getAllowedFood()){
                if(allergy.equals(allowedFood)){
                    allergiesMatch++;
                }
                if(allergiesMatch >= numberOfAllergies/2)
                    return false;
            }
        }

        {
            //Test if person weighs less than allowed limit for VeganDiet and LowCarbDiet
            float minWeight = 0.0f;
            Boolean isMinWeightDiet = false;
            if(diet.getClass().getName() == "VeganDiet"){
                isMinWeightDiet = true;
                VeganDiet vd = (VeganDiet) diet;
                minWeight = vd.getMinWeightKg();
            } else if(diet.getClass().getName() == "LowCarbDiet") {
                isMinWeightDiet = true;
                LowCarbDiet lcd = (LowCarbDiet) diet;
                minWeight = lcd.getMinWeightKg();
            }
            if(person.getWeight() < minWeight && isMinWeightDiet){
                return false;
            }
        }

        {
            // Test if person weighs more than allowed limit for HypercaloricDiet
            float maxWeight = 0.0f;
            boolean isMaxWeightDiet = false;
            if (diet.getClass().getName() == "HypercaloricDiet") {
                isMaxWeightDiet = true;
                HypercaloricDiet hd = (HypercaloricDiet) diet;
                maxWeight = hd.getMaxWeightKg();
            }
            if (person.getWeight() > maxWeight && isMaxWeightDiet) {
                return false;
            }
        }

        return true;
    }

    public static HypercaloricDiet randomDiet(Person person, Food... foods){
        ArrayList<Food> allowedFoods = new ArrayList<>();
        for(Food f: foods){
            allowedFoods.add(f);
        }

        for(Food allergies : person.getAllergies()){
            for(int i = 0; i < allowedFoods.size(); i++){
                if(allergies == allowedFoods.get(i)){
                    allowedFoods.remove(allergies);
                }
            }
        }

        int randomDuration = (int) (Math.random() * (100 - 1)) + 1;
        float randomWeight = (float) (Math.random()*(person.getWeight() + 20 - person.getWeight())) + person.getWeight();
        int minCalories = (int) (Math.random() * (4000 - 2000)) + 2000;

        HypercaloricDiet hp = new HypercaloricDiet(randomDuration, "Random diet", randomWeight, minCalories, allowedFoods.toArray(new Food[0]));
        return hp;
    }
}
