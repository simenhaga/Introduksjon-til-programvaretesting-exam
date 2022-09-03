import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HypercaloricDietTest {
    private HypercaloricDiet hypercaloricDiet;
    private Food favoriteFood;

    @Before
    public void setUp() {
        favoriteFood = new Food("Spaghetti", 158.f, false, FoodType.CARB);
        hypercaloricDiet = new HypercaloricDiet(90,"Gaining muscle", 100.f, 3500.f, favoriteFood);
    }
    @Test
    public void testHypercaloricOverMaxWeight() {
        Person person = new Person(120, favoriteFood);
        assertFalse(DietManager.areCompatible(person, hypercaloricDiet));
    }
    @Test
    public void testHypercaloricEqualMaxWeight() {
        Person person = new Person(100, favoriteFood);
        assertTrue(DietManager.areCompatible(person, hypercaloricDiet));
    }
    @Test
    public void testHypercaloricUnderMaxWeight() {
        Person person = new Person(70, favoriteFood);
        assertTrue(DietManager.areCompatible(person, hypercaloricDiet));
    }

    //Test invalid constructor inputs
    @Test
    public void testNegativeMaxWeight(){
        try{
            HypercaloricDiet negativeWeight = new HypercaloricDiet(12, "Test", -1f, 1000, favoriteFood);
            assertNull(negativeWeight);
        }catch (IllegalArgumentException e){
            assertTrue(e.getMessage().contains("Max weight cannot be a negative number!"));
        }

    }
}
