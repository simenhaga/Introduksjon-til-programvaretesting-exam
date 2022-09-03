import org.junit.Test;
import static org.junit.Assert.*;


public class FlexitarianDietTest {

    @Test
    public void flexitarianDietTest() {
        Food veganMeat = new Food("VeganMeat", 44, true, FoodType.PROTEIN);
        Food nonVeganMeat = new Food("NonVeganMeat", 100, false, FoodType.PROTEIN);
        Food carb = new Food("VeganMeat", 60, false, FoodType.CARB);

        //Tests preferred meat that is vegan
        try {
            FlexitarianDiet vegan = new FlexitarianDiet(30, "Health", 70, veganMeat, veganMeat);
            assertNull(vegan);
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Preferred meat must be non-vegan"));
        }

        //Test preferred meat that is not protein type
        try {
            FlexitarianDiet vegan = new FlexitarianDiet(30, "Health", 100, carb, nonVeganMeat);
            assertNull(vegan);
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Preferred meat must be of FoodType.PROTEIN"));
        }
    }
}