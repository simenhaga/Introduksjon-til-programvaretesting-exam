import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VeganDietTest {
    private VeganDiet veganDiet;
    private Food favoriteFood;
    private Food genericVeganFood;
    private Person nonVeganPerson;
    private Person veganPerson;

    @Before
    public void testObjects(){
        genericVeganFood = new Food("Salad", 200, true, FoodType.RECIPE);
        favoriteFood = new Food("Mac and Cheese", 164, false, FoodType.RECIPE);
        veganDiet = new VeganDiet(12, "Vegan Diet", 40, genericVeganFood);
        nonVeganPerson = new Person(65, favoriteFood);
        veganPerson = new Person(65, genericVeganFood);
    }

    @Test
    public void veganDietConstructorTest(){
        Food veganFood = new Food("Potato", 50, true, FoodType.CARB);
        Food nonVeganFood1 = new Food("Chili con Carne", 350, false, FoodType.RECIPE);
        Food nonVeganFood2 = new Food("Chicken wings", 160, false, FoodType.PROTEIN);

        //Constructing vegan diet with vegan food (expected successful)
        VeganDiet d1 = new VeganDiet(2, "Vegan test diet", 45, veganFood);
        assertNotNull(d1);

        //Constructing veganDiet with nonVegan food (expected fail)
        IllegalArgumentException iee;
        try{
            VeganDiet d2 = new VeganDiet(3, "Losing weight", 45, nonVeganFood1);
            assertNull(d2);
        }catch (IllegalArgumentException e){
            assertTrue(e.getMessage().contains("Cannot construct vegan diet containing any non-vegan food"));
        }

        //Constructing veganDiet with several nonVegan food (expected fail)
        try{
            VeganDiet d3 = new VeganDiet(3, "Losing weight", 45, nonVeganFood1, nonVeganFood2);
            assertNull(d3);
        }catch (IllegalArgumentException e){
            assertTrue(e.getMessage().contains("Cannot construct vegan diet containing any non-vegan food"));
        }

        //Constructing veganDiet with both vegan and nonVegan foods (expected fail)
        try{
            VeganDiet d4 = new VeganDiet(3, "Losing weight", 45, veganFood, nonVeganFood1, nonVeganFood2);
            assertNull(d4);
        }catch (IllegalArgumentException e){
            assertTrue(e.getMessage().contains("Cannot construct vegan diet containing any non-vegan food"));
        }
    }

    //Test compatibility for person with non-vegan favourite food with a VeganDiet
    @Test
    public void compatibilityTest(){
        assertFalse(DietManager.areCompatible(nonVeganPerson, veganDiet));
        assertTrue(DietManager.areCompatible(veganPerson, veganDiet));
    }

    @Test
    public void getterAndSetterTest(){
        Food food = new Food("Salad", 164, true, FoodType.RECIPE);
        VeganDiet diet = new VeganDiet(12, "Purpose", 12, food);

        assertEquals(12, diet.getMinWeightKg(), 0.001);
    }

    //Test invalid constructor inputs
    @Test
    public void testNegativeMinWeight(){
        try{
            VeganDiet negativeWeight = new VeganDiet(12, "Test", -1f, genericVeganFood);
            assertNull(negativeWeight);
        }catch (IllegalArgumentException e){
            assertTrue(e.getMessage().contains("Min weight cannot be a negative number!"));
        }

    }
}
