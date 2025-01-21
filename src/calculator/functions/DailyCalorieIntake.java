package calculator.functions;

public class DailyCalorieIntake {
    private double weight;
    private double height;
    private int age;
    private int gender;
    private String goal;
    private String rate;
    private double BMR;
    private double dailyCalorieIntake;

    public DailyCalorieIntake(double weight, double height, int age, int gender, String goal, String rate) {
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
        this.goal = goal;
        this.rate = rate;
    }

    public void calculateBMR() {
        functionGL gl = new functionGL();
        this.BMR = gl.BMR_Calculator(this.weight, this.height, this.age, this.gender);
    }

    public void calculateDailyCalorieIntake() {
        calculateBMR();
        double adjustmentFactor = 1.0;

        if (goal.equalsIgnoreCase("Weight Loss")) {
            switch (rate.toLowerCase()) {
                case "mild":
                    adjustmentFactor = 0.9;
                    break;
                case "moderate":
                    adjustmentFactor = 0.8;
                    break;
                case "extreme":
                    adjustmentFactor = 0.7;
                    break;
            }
        } else if (goal.equalsIgnoreCase("Weight Gain")) {
            switch (rate.toLowerCase()) {
                case "mild":
                    adjustmentFactor = 1.1;
                    break;
                case "moderate":
                    adjustmentFactor = 1.2;
                    break;
                case "extreme":
                    adjustmentFactor = 1.3;
                    break;
            }
        }
        // For maintaining weight, no adjustment needed
        this.dailyCalorieIntake = this.BMR * adjustmentFactor;
    }

    public double getDailyCalorieIntake() {
        return this.dailyCalorieIntake;
    }
}
