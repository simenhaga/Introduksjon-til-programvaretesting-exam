import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DietManagerTest{
    private ArrayList<Food> foods;
    private Person genericPerson;
    private Food genericFavouriteFood;
    private int numberOfIterations;

    @Before
    public void initTest(){
        //The number of times each looped test should run
        numberOfIterations = 10000;

        genericFavouriteFood = new Food("FavouriteFood", 140, true, FoodType.RECIPE);

        //Generic list of foods. Must have at least four elements for testAllergies to function properly
        foods = new ArrayList<>();
        foods.add(new Food("Food 1", 200, true, FoodType.CARB));
        foods.add(new Food("Food 2", 100, true, FoodType.PROTEIN));
        foods.add(new Food("Food 3", 400, false, FoodType.FAT));
        foods.add(new Food("Food 4", 150, true, FoodType.RECIPE));
        genericPerson = new Person(60, foods.get(0));

    }

    @Test
    public void testAllergies(){

        ArrayList<Food> allergies = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            allergies.add(foods.get(i));
        }

        Person noAllergies = new Person(45, genericFavouriteFood);
        Person quarterAllergies = new Person(45, genericFavouriteFood, allergies.get(0));
        Person halfAllergies = new Person(45, genericFavouriteFood, allergies.get(0), allergies.get(1));
        Person moreThanHalfAllergies = new Person(45, genericFavouriteFood, allergies.get(0), allergies.get(1), allergies.get(2));

        Food preferredMeat = new Food("Meat", 30, false, FoodType.PROTEIN);

        FlexitarianDiet diet = new FlexitarianDiet(12, "Purpose", 20, preferredMeat, foods.toArray(new Food[0]));

        assertTrue(DietManager.areCompatible(noAllergies, diet));
        assertTrue(DietManager.areCompatible(quarterAllergies, diet));
        assertFalse(DietManager.areCompatible(halfAllergies, diet));
        assertFalse(DietManager.areCompatible(moreThanHalfAllergies, diet));
    }

    @Test
    public void testRandomDietDuration(){

        /*Since we have random number generation inside randomDiet method that cannot be directly accessed, we have to
        perform the test multiple times*/
        for(int i = 0; i < numberOfIterations; i++){
            Diet d = DietManager.randomDiet(genericPerson, foods.subList(0, foods.size()-1).toArray(new Food[0]));
            assertFalse(d.getDuration() <= 0);
            assertTrue(d.getDuration() >= 1);
            assertFalse(d.getDuration() > 100);
        }
    }

    @Test
    public void testRandomDietPurpose(){
        Diet d = DietManager.randomDiet(genericPerson, foods.toArray(new Food[0]));
        assertEquals("Random diet", d.getPurpose());
    }

    @Test
    public void testRandomDietIsVegan(){
        Food veganFood = new Food("Vegan food", 22, true, FoodType.RECIPE);
        Food nonVeganFood = new Food("Non-vegan food", 22, false, FoodType.RECIPE);
        Diet veganDiet = DietManager.randomDiet(genericPerson, veganFood, veganFood);
        Diet nonVeganDiet = DietManager.randomDiet(genericPerson, veganFood, nonVeganFood);

        assertTrue(veganDiet.getVegan());
        assertFalse(nonVeganDiet.getVegan());
    }

    @Test
    public void testRandomDietAllowedFoods(){
        Person allergicPerson = new Person(60, foods.get(0), foods.get(1), foods.get(3));
        Diet d = DietManager.randomDiet(allergicPerson, foods.toArray(new Food[0]));

        for(Food allergies : allergicPerson.getAllergies()){
            for(Food allowedFood : d.getAllowedFood()){
                assertNotEquals(allergies, allowedFood);
            }
        }
    }

    @Test
    public void testRandomDietMaxWeight(){
        // maxWeightKg: random number between Person.weight and Person.weight + 20
        for(int i = 0; i < numberOfIterations; i++){
            HypercaloricDiet d = DietManager.randomDiet(genericPerson, foods.subList(0, foods.size()-1).toArray(new Food[0]));
            assertFalse(d.getMaxWeightKg() < genericPerson.getWeight());
            assertFalse(d.getMaxWeightKg() > genericPerson.getWeight() + 20);
        }
    }

    @Test
    public void testMinCalsPerDay(){
        // minCaloriesPerDay: random number between 2000 and 4000
        for(int i = 0; i < numberOfIterations; i++){
            HypercaloricDiet d = DietManager.randomDiet(genericPerson, foods.subList(0, foods.size()-1).toArray(new Food[0]));
            assertFalse(d.getMinCaloriesPerDay() < 2000);
            assertFalse(d.getMinCaloriesPerDay() > 4000);
        }
    }
}
