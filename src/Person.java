public class Person {
    private Food favoriteFood;
    private Food[] allergies;
    private Diet diet;
    private float weight;

    public Person(float weight, Food favoriteFood, Food... allergies) {
        if(weight < 10.0f){
            throw new IllegalArgumentException("Person cannot be under 10 kg!");
        }
        if(weight > 650.0f){
            throw new IllegalArgumentException("Person cannot be over 650 kg!");
        }
        this.favoriteFood = favoriteFood;
        this.allergies = allergies;
        this.weight = weight;
    }

    public Food getFavoriteFood() {
        return favoriteFood;
    }

    public Food[] getAllergies() {
        return allergies;
    }

    public void setDiet(Diet d){
        if(DietManager.areCompatible(this, d)){
            this.diet = d;
        }else{
            System.out.println("Person cannot follow this diet");
        }
    }

    public Diet getDiet() {
        return diet;
    }

    public float getWeight() {
        return weight;
    }
}
