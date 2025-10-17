class CookingEngine {
    private int timer;
    private int powerLevel;
    private long startTime;
    private long totalCookTime;

    public void setTimer(int seconds) {
        this.timer = seconds;
        System.out.println("Timer set to " + seconds + " seconds.");
    }

    public void setPowerLevel(int level) {
        this.powerLevel = level;
        System.out.println("Power level set to " + level + ".");
    }

    public void startCooking() {
        System.out.println("Cooking started with timer " + this.timer + "s and power level " + this.powerLevel + ".");
        this.startTime = System.currentTimeMillis();
    }

    public void pauseCooking() {
        System.out.println("Cooking paused.");
        this.totalCookTime += (System.currentTimeMillis() - startTime) / 1000;
    }

    public void stopCooking() {
        System.out.println("Cooking stopped.");
        this.totalCookTime += (System.currentTimeMillis() - startTime) / 1000;
        this.timer = 0;
        this.powerLevel = 0;
    }
}