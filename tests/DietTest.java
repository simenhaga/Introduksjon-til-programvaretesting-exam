import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DietTest {

    private Food genericFood;
    private Food orange;
    private Food apple;
    private Food steak;
    private Food chicken;

    @Before
    public void initTests() {
        genericFood = new Food("Salad", 12, true, FoodType.CARB);
        orange = new Food("Orange", 20, true, FoodType.CARB);
        apple = new Food("apple", 18, true, FoodType.CARB);
        steak = new Food("steak", 125, false, FoodType.PROTEIN);
        chicken = new Food("chicken", 67, false, FoodType.PROTEIN);
    }

    @Test
    public void testIsVegan() {
        Food x = new Food("Meat", 33, false, FoodType.PROTEIN);
        Food y = new Food("Salad", 12, true, FoodType.CARB);
        LowCarbDiet notVegan = new LowCarbDiet(12, "Test diet", 45, x, y);
        LowCarbDiet vegan = new LowCarbDiet(12, "Test diet", 45, y);


        assertTrue(!notVegan.getVegan());
        assertTrue(vegan.getVegan());
    }

    //Check that diet cannot be constructed with zero foods
    @Test
    public void testZeroFoods() {

        /*Tests that we expect to throw exceptions like this will actually pass, even though the feature might not be implemented.
        This is because the assertTrue in the catch-statement is never reached if the exception is never thrown
        */
        try {
            LowCarbDiet lowCarbDiet = new LowCarbDiet(20, "test", 40);
            assertNull(lowCarbDiet);
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Diet must have at least one allowed food!"));
        }
    }

    //writeAllowedFood tests
    @Test
    public void testOneFood() {
        LowCarbDiet oneFoodDiet = new LowCarbDiet(1, "Test diet", 44, orange);
        assertTrue(oneFoodDiet.writeAllowedFood().contains(": " + orange.getName()));
    }

    @Test
    public void testTwoFoods() {
        VeganDiet twoFoodDiet = new VeganDiet(2, "Test diet", 55, apple, orange);
        String s = String.format(": %s, %s", apple.getName(), orange.getName());
        assertTrue(twoFoodDiet.writeAllowedFood().contains(s));
    }

    @Test
    public void testThreeFoods() {
        Food[] foods = {apple, orange, steak};
        HypercaloricDiet threeFoodDiet = new HypercaloricDiet(30, "test diet", 115, 2000, foods);
        String s = String.format(": %s, %s, %s", foods[0].getName(), foods[1].getName(), foods[2].getName());
        assertTrue(threeFoodDiet.writeAllowedFood().contains(s));
    }

    @Test
    public void testAllowedFoodDietType() {
        LowCarbDiet lowCarbDiet = new LowCarbDiet(1, "Test diet", 44, orange);
        VeganDiet veganDiet = new VeganDiet(2, "Test diet", 55, apple);
        HypercaloricDiet hypercaloricDiet = new HypercaloricDiet(30, "test diet", 115, 2000, steak);
        FlexitarianDiet flexitarianDiet = new FlexitarianDiet(25, "Test diet", 500, chicken, chicken);

        assertTrue(lowCarbDiet.writeAllowedFood().contains("LowCarbDiet"));
        assertTrue(veganDiet.writeAllowedFood().contains("VeganDiet"));
        assertTrue(hypercaloricDiet.writeAllowedFood().contains("HypercaloricDiet"));
        assertTrue(flexitarianDiet.writeAllowedFood().contains("FlexitarianDiet"));

    }

    //Test diets with duration less than 1
    @Test
    public void testIllegalDuration(){
        try{
            VeganDiet diet = new VeganDiet(-1, "Diet with negative duration", 45, genericFood);
            assertNull(diet);
        }catch (IllegalArgumentException e){
            assertTrue(e.getMessage().contains("Diet cannot have duration less than zero!"));
        }

        try{
            VeganDiet diet = new VeganDiet(0, "Diet with negative duration", 45, genericFood);
            assertNull(diet);
        }catch(IllegalArgumentException e){
            assertTrue(e.getMessage().contains("Diet cannot have duration less than zero!"));
        }
    }


    //writeDuration tests

    @Test
    public void testWriteDurationLessThanMonth() {
        HypercaloricDiet diet = new HypercaloricDiet(15, "Diet test", 120, 2000, genericFood);
        assertTrue(diet.writeDuration().contains("lasts for 0 years, 0 months and 15 days"));
    }

    @Test
    public void testWriteDurationMonth() {
        HypercaloricDiet diet = new HypercaloricDiet(30, "Diet test", 120, 2000, genericFood);
        assertTrue(diet.writeDuration().contains("lasts for 0 years, 1 months and 0 days"));
    }

    @Test
    public void testWriteDurationMoreThanMonth() {
        HypercaloricDiet diet = new HypercaloricDiet(32, "Diet test", 120, 2000, genericFood);
        assertTrue(diet.writeDuration().contains("lasts for 0 years, 1 months and 2 days"));
    }

    @Test
    public void testWriteDurationOneYear() {
        HypercaloricDiet diet = new HypercaloricDiet(365, "Diet test", 120, 2000, genericFood);
        assertTrue(diet.writeDuration().contains("lasts for 1 years, 0 months and 0 days"));
    }

    @Test
    public void testWriteDurationMoreThanYear() {
        HypercaloricDiet diet = new HypercaloricDiet(380, "Diet test", 120, 2000, genericFood);
        assertTrue(diet.writeDuration().contains("lasts for 1 years, 0 months and 15 days"));
    }

    @Test
    public void testWriteDurationDietType() {
        ArrayList<Diet> dietArrayList = new ArrayList<>();
        dietArrayList.add(new FlexitarianDiet(12, "loose weight", 120, chicken, genericFood));
        dietArrayList.add(new VeganDiet(15, "loosse wight", 40.0f, apple));
        dietArrayList.add(new HypercaloricDiet(30, "gain musscle", 100.0f, 2000.0f, genericFood));
        dietArrayList.add(new LowCarbDiet(15, "loose belly fat", 40, genericFood));
        for (Diet d : dietArrayList) { assertTrue(d.writeDuration().contains(d.getClass().getName())); }
    }
}
