import org.junit.Test;
import static org.junit.Assert.*;

public class FoodTest {
    @Test
    public void testEquals(){
        Food x = new Food("Food", 44, true, FoodType.RECIPE);
        Food y = new Food("Food", 44, true, FoodType.RECIPE);

        assertTrue(x.equals(y));
    }

    @Test
    public void testIllegalCalories(){
        try{
            Food negativeCalories = new Food("Test", -15f, true, FoodType.RECIPE);
            assertNull(negativeCalories);
        }catch (IllegalArgumentException e){
            assertTrue(e.getMessage().contains("Food cannot have negative calories!"));
        }
        try{
            Food moreThanMaxCalories = new Food("Test", 10001f, true, FoodType.RECIPE);
            assertNull(moreThanMaxCalories);
        }catch (IllegalArgumentException e){
            assertTrue(e.getMessage().contains("Food cannot have more than 10 000 calories!"));
        }
    }

    @Test
    public void testZeroCalories(){
        Food zeroCalories = new Food("Test", 0.0f, true, FoodType.RECIPE);
        assertNotNull(zeroCalories);
    }
}
