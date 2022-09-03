import java.util.Objects;

public class Food {
    private String name;
    private float caloriesPer100g;
    private Boolean isVegan;
    private FoodType type;




    public Food(String name, float caloriesPer100g, Boolean isVegan, FoodType type) {
        if(caloriesPer100g < 0f){
            throw new IllegalArgumentException("Food cannot have negative calories!");
        }
        if(caloriesPer100g > 10000f){
            throw new IllegalArgumentException("Food cannot have more than 10 000 calories!");
        }
        this.name = name;
        this.caloriesPer100g = caloriesPer100g;
        this.isVegan = isVegan;
        this.type = type;
    }

    public String getName() { return name; }

    public Boolean getVegan() { return isVegan; }

    public FoodType getType() { return type; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Float.compare(food.caloriesPer100g, caloriesPer100g) == 0 && Objects.equals(name, food.name) && Objects.equals(isVegan, food.isVegan) && type == food.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, caloriesPer100g, isVegan, type);
    }

}
