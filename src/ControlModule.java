class ControlModule {
    private String selectedMode;

    public void selectMode(String mode) {
        this.selectedMode = mode;
        System.out.println("Cooking mode selected: " + mode);
    }
}