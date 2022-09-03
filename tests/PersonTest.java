import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class PersonTest {
    private Food Beans;
    private Food Salad;

    @Before
    public void setUp() {
        Salad = new Food("Salad", 17, true, FoodType.CARB);
        Beans = new Food("Beans", 30, true, FoodType.CARB);
    }

    //Test persons constructed with unreasonable weight
    @Test
    public void testIllegalWeight(){
        try{
            Person underMinWeight = new Person(9.0f, Salad);
            assertNull(underMinWeight); //Only executed if no exception was thrown
        }catch (IllegalArgumentException e){
            assertTrue(e.getMessage().contains("Person cannot be under 10 kg!"));
        }
        try{
            Person overMaxWeight = new Person(651.0f, Salad);
            assertNull(overMaxWeight);
        }catch(IllegalArgumentException e){
            e.getMessage().contains("Person cannot be over 650 kg!");
        }
    }


    //Cannot follow a veganDiet if  the person's weight < the minWeightKg.
    @Test
    public void testVeganDietOverMinWeight(){
        Person person = new Person(60, Beans);
        Diet diet = new VeganDiet(60, "Loose weight", 45, Salad, Beans);

        assertTrue(DietManager.areCompatible(person, diet));
    }

    //Can't follow veganDiet if the person's weight < the minWeightKg.
    @Test
    public void testVeganDietUnderMinWeight() {
        Person personUnderWeight = new Person(43, Salad);
        Person personMinWeight = new Person(45, Salad);
        VeganDiet diet = new VeganDiet(60, "Loose weight", 45, Salad, Beans);

        assertFalse(DietManager.areCompatible(personUnderWeight, diet));
        assertTrue(DietManager.areCompatible(personMinWeight, diet));
    }

    //Cannot follow a LowCarbDiet if the person's weight > the minWeightKg.
    @Test
    public void testLowCarbDietOverMinWeight() {
        Person person = new Person(60, Beans);
        LowCarbDiet diet = new LowCarbDiet(125, "Loose weight", 50, Salad);

        assertTrue(DietManager.areCompatible(person, diet));
    }

    //Can follow a LowCarbDiet if the person's weight >= the minWeightKg.
    @Test
    public void testLowCarbDietUnderMinWeight() {
        Person person = new Person(43, Salad);
        LowCarbDiet diet = new LowCarbDiet(125, "Loose weight", 50, Salad);

        assertFalse(DietManager.areCompatible(person, diet));
    }

    @Test
    public void testGetterAndSetters(){
        Person person = new Person(43, Salad, Beans);

        assertEquals(Beans, person.getAllergies()[0]);
        assertEquals(43, person.getWeight(), 0.001);
        assertEquals(Salad, person.getFavoriteFood());
    }

}
