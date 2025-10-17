import java.util.Scanner; // Import the Scanner class

class SafetyModule {
    public boolean isSafeToStart(boolean doorClosed, boolean isOverheating, boolean isChildModeOn, String ChildPin) {
        if (!doorClosed) {
            System.out.println("Safety Check: Door is open.");
            return false;
        }
        if (isOverheating) {
            System.out.println("Safety Check: Microwave is overheating.");
            return false;
        }
        if (isChildModeOn) {
            System.out.println("Safety Check: Child mode is on. PIN required.");
            System.out.println("Please type in PIN:");

            int maxAttempts = 3;       // Maximum allowed incorrect attempts
            int attempts = 0;
            Scanner scanner = new Scanner(System.in);

            while (attempts < maxAttempts) {
                System.out.print("Enter your PIN: ");
                String enteredPin = scanner.nextLine();

                if (enteredPin.equals(ChildPin)) {
                    System.out.println("PIN accepted. Access granted.");
                    return true;
                } else {
                    attempts++;
                    int remainingAttempts = maxAttempts - attempts;
                    if (remainingAttempts > 0) {
                        System.out.println("Incorrect PIN. You have " + remainingAttempts + " attempts remaining.");
                    } else {
                        System.out.println("Incorrect PIN. Please try again later.");
                        return false;
                    }
                }
            }




        }
        return true;
    }
}