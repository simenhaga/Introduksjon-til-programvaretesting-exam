import org.junit.Test;
import static org.junit.Assert.*;

public class LowCarbDietTest {

    @Test
    public void lowCarbDietTest() {
        Food carb = new Food ("Carb",55, true, FoodType.CARB);
        Food fat = new Food ("Carb",55, true, FoodType.FAT);

        LowCarbDiet lcDiet1 = new LowCarbDiet (30,"test", 40, carb);
        LowCarbDiet lcDiet2 = new LowCarbDiet (30,"test", 40, carb, carb);
        LowCarbDiet lcDietFat = new LowCarbDiet (30,"test", 40, carb, fat);

        //Test that we can construct with one and two carb type foods
        assertNotNull(lcDiet1);
        assertNotNull(lcDiet2);

        //Test that we can still construct with two foods, even if one is a carb
        assertNotNull(lcDietFat);

        //Test that three (or more) carb type foods throws an exception
        try {
            LowCarbDiet lcDiet3 = new LowCarbDiet (30,"test", 40, carb, carb, carb);
            assertNull(lcDiet3);
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Cannot have more than two carb type foods!"));
        }
    }

    //Test invalid constructor inputs
    @Test
    public void testNegativeMinWeight(){
        Food fat = new Food ("Carb",55, true, FoodType.FAT);
        try{
            LowCarbDiet negativeWeight = new LowCarbDiet(12, "Test", -1f, fat);
            assertNull(negativeWeight);
        }catch (IllegalArgumentException e){
            assertTrue(e.getMessage().contains("Min weight cannot be a negative number!"));
        }

    }
}




