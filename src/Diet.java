public abstract class Diet {
    private Boolean isVegan;
    private int daysDuration;
    private String purpose;
    private Food[] allowedFood;

    //varargs constructor
    public Diet(int daysDuration, String purpose, Food... allowedFood) {
        if(daysDuration <= 0){
            throw new IllegalArgumentException("Diet cannot have duration less than zero!");
        }
        if(allowedFood.length == 0){
            throw new IllegalArgumentException("Diet must have at least one allowed food!");
        }
        this.daysDuration = daysDuration;
        this.purpose = purpose;
        this.allowedFood = allowedFood;
        isVegan = true;
        for (Food f : allowedFood) {
            if (!f.getVegan()) {
                isVegan = false;
                break;
            }
        }
    }

    public String writeDuration() {
        int years = daysDuration / 365;
        int daysRem = daysDuration % 365;
        int months = daysRem / 30;
        int days = daysRem % 30;
        return String.format("This %s lasts for %d years, %d months and %d days", getClass().getName(), years, months, days);
    }

    public String writeAllowedFood() {
        String result = "The following food is allowed in this" + getClass().getName() + ": ";
        for (Food af:allowedFood) {
            result += af.getName() + ", ";
        }
        return result;
    }

    public int getDuration() {
        return daysDuration;
    }

    public Boolean getVegan() {
        return isVegan;
    }

    public String getPurpose() {
        return purpose;
    }

    public Food[] getAllowedFood() {
        return allowedFood;
    }
}
