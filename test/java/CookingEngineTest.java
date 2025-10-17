import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CookingEngineTest {
    
    private CookingEngine cookingEngine;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    
    @BeforeEach
    void setUp() {
        cookingEngine = new CookingEngine();
        // Capture System.out for testing console output
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }
    
    void tearDown() {
        // Restore original System.out
        System.setOut(originalOut);
    }
    
    @Test
    @DisplayName("Should set timer correctly")
    void testSetTimer() {
        // Given
        int expectedSeconds = 120;
        
        // When
        cookingEngine.setTimer(expectedSeconds);
        
        // Then
        String output = outputStream.toString();
        assertTrue(output.contains("Timer set to 120 seconds."));
    }
    
    @Test
    @DisplayName("Should set power level correctly")
    void testSetPowerLevel() {
        // Given
        int expectedPowerLevel = 8;
        
        // When
        cookingEngine.setPowerLevel(expectedPowerLevel);
        
        // Then
        String output = outputStream.toString();
        assertTrue(output.contains("Power level set to 8."));
    }
    
    @Test
    @DisplayName("Should start cooking with correct parameters")
    void testStartCooking() {
        // Given
        cookingEngine.setTimer(60);
        cookingEngine.setPowerLevel(5);
        outputStream.reset(); // Clear previous output
        
        // When
        cookingEngine.startCooking();
        
        // Then
        String output = outputStream.toString();
        assertTrue(output.contains("Cooking started with timer 60s and power level 5."));
    }
    
    @Test
    @DisplayName("Should pause cooking correctly")
    void testPauseCooking() {
        // Given
        cookingEngine.setTimer(60);
        cookingEngine.setPowerLevel(5);
        cookingEngine.startCooking();
        outputStream.reset(); // Clear previous output
        
        // When
        cookingEngine.pauseCooking();
        
        // Then
        String output = outputStream.toString();
        assertTrue(output.contains("Cooking paused."));
    }
    
    @Test
    @DisplayName("Should stop cooking and reset parameters")
    void testStopCooking() {
        // Given
        cookingEngine.setTimer(60);
        cookingEngine.setPowerLevel(5);
        cookingEngine.startCooking();
        outputStream.reset(); // Clear previous output
        
        // When
        cookingEngine.stopCooking();
        
        // Then
        String output = outputStream.toString();
        assertTrue(output.contains("Cooking stopped."));
    }
    
    @Test
    @DisplayName("Should handle zero timer value")
    void testSetTimerZero() {
        // When
        cookingEngine.setTimer(0);
        
        // Then
        String output = outputStream.toString();
        assertTrue(output.contains("Timer set to 0 seconds."));
    }
    
    @Test
    @DisplayName("Should handle negative timer value")
    void testSetTimerNegative() {
        // When
        cookingEngine.setTimer(-30);
        
        // Then
        String output = outputStream.toString();
        assertTrue(output.contains("Timer set to -30 seconds."));
    }
    
    @Test
    @DisplayName("Should handle zero power level")
    void testSetPowerLevelZero() {
        // When
        cookingEngine.setPowerLevel(0);
        
        // Then
        String output = outputStream.toString();
        assertTrue(output.contains("Power level set to 0."));
    }
    
    @Test
    @DisplayName("Should handle maximum power level")
    void testSetPowerLevelMax() {
        // When
        cookingEngine.setPowerLevel(10);
        
        // Then
        String output = outputStream.toString();
        assertTrue(output.contains("Power level set to 10."));
    }
}