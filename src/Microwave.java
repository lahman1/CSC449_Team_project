import java.util.Scanner;

public class Microwave {

    private ControlModule controlModule;
    private CookingEngine cookingEngine;
    private SafetyModule safetyModule;
    private StatisticsTracker statisticsTracker;
    private MicrowaveState currentState;
    private boolean doorClosed;
    private String childLockPin;
    private boolean isChildModeOn;
    private boolean isOverheating;

    public Microwave(String childLockPin) {
        this.controlModule = new ControlModule();
        this.cookingEngine = new CookingEngine();
        this.safetyModule = new SafetyModule();
        this.statisticsTracker = new StatisticsTracker();
        this.childLockPin = childLockPin;
        this.isChildModeOn = false;
        this.doorClosed = false;
        this.isOverheating = false;
        this.currentState = MicrowaveState.IDLE;
    }

    // Buttons
    public void start() {
        if (safetyModule.isSafeToStart(doorClosed, isOverheating, isChildModeOn,childLockPin)) {
            // Check for child lock pin
            if (isChildModeOn && !checkChildPin("1234")) { // Example pin check
                System.out.println("Incorrect PIN. Cannot start.");
                return;
            }
            cookingEngine.startCooking();
            currentState = MicrowaveState.COOKING;
            System.out.println("Microwave cooking started.");
        } else {
            System.out.println("Microwave cannot start due to a safety issue.");
        }
    }

    public void pause() {
        if (currentState == MicrowaveState.COOKING) {
            cookingEngine.pauseCooking();
            currentState = MicrowaveState.PAUSED;
            System.out.println("Microwave paused.");
        }
    }

    public void stop() {
        if (currentState == MicrowaveState.COOKING || currentState == MicrowaveState.PAUSED) {
            cookingEngine.stopCooking();
            currentState = MicrowaveState.IDLE;
            System.out.println("Microwave stopped.");
        }
    }

    // Other functionality
    public void openDoor() {
        this.doorClosed = false;
        System.out.println("Door opened.");
        // Stop cooking if door is opened while running
        if (currentState == MicrowaveState.COOKING) {
            stop();
        }
    }

    public void closeDoor() {
        this.doorClosed = true;
        System.out.println("Door closed.");
    }

    public void displayStatistics() {
        System.out.println("Microwave Usage Statistics:");
        System.out.println("Total time used: " + statisticsTracker.getTotalTimeUsed() + " seconds.");
    }

    // Helper method for child pin check
    private boolean checkChildPin(String pin) {
        return this.childLockPin.equals(pin);
    }

    public void setChildModeOn(boolean childModeOn) {
        isChildModeOn = childModeOn;
    }

    // Main method with interactive menu
    public static void main(String[] args) {
        Microwave microwave = new Microwave("1234");
        Scanner scanner = new Scanner(System.in);

        int choice = -1;
        while (choice != 0) {
            System.out.println("\n--- Microwave Menu ---");
            System.out.println("1. Close Door");
            System.out.println("2. Open Door");
            System.out.println("3. Set Cooking Parameters (Timer & Power)");
            System.out.println("4. Start Cooking");
            System.out.println("5. Pause Cooking");
            System.out.println("6. Stop Cooking");
            System.out.println("7. Display Statistics");
            System.out.println("8. Toggle Child Mode (Currently " + (microwave.isChildModeOn ? "On" : "Off") + ")");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        microwave.closeDoor();
                        break;
                    case 2:
                        microwave.openDoor();
                        break;
                    case 3:
                        System.out.print("Enter timer in seconds: ");
                        int timer = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter power level (1-10): ");
                        int powerLevel = Integer.parseInt(scanner.nextLine());
                        microwave.cookingEngine.setTimer(timer);
                        microwave.cookingEngine.setPowerLevel(powerLevel);
                        break;
                    case 4:
                        microwave.start();
                        break;
                    case 5:
                        microwave.pause();
                        break;
                    case 6:
                        microwave.stop();
                        break;
                    case 7:
                        microwave.displayStatistics();
                        break;
                    case 8:
                        microwave.setChildModeOn(!microwave.isChildModeOn);
                        System.out.println("Child mode is now " + (microwave.isChildModeOn ? "On" : "Off") + ".");
                        break;
                    case 0:
                        System.out.println("Exiting microwave program.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        scanner.close();
    }
}

// Enum for Microwave state
enum MicrowaveState {
    IDLE,
    COOKING,
    PAUSED
}

// Component classes







