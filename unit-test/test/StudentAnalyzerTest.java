import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Test class for StudentAnalyzer.
 * Uses JUnit 5.
 * Follows Naming Conventions and AAA (Arrange-Act-Assert) pattern.
 */
public class StudentAnalyzerTest {

    private final StudentAnalyzer analyzer = new StudentAnalyzer();

    // Delta for floating-point comparisons
    private static final double DELTA = 0.001;

    // ==========================================
    // Feature: Count Excellent Students
    // Method: countExcellentStudents
    // ==========================================

    @Test
    @DisplayName("Should count correct excellent students ignoring invalid scores")
    public void testCountExcellentStudents_WhenListHasMixedScores_ShouldReturnCorrectCount() {
        // Arrange: Prepare data (Excellent: 9.0, 8.5, 8.0; Invalid: -1.0, 11.0; Normal: 7.0)
        List<Double> scores = Arrays.asList(9.0, 8.5, 7.0, 11.0, -1.0, 8.0);

        // Act: Execute the method
        int result = analyzer.countExcellentStudents(scores);

        // Assert: Verify result (Expected: 3)
        assertEquals(3, result, "Should count 3 excellent students (9.0, 8.5, 8.0) and ignore invalid scores");
    }

    @Test
    @DisplayName("Should safely ignore null elements inside the list")
    public void testCountExcellentStudents_WhenListContainsNulls_ShouldIgnoreNulls() {
        // Arrange: List contains interspersed null elements
        List<Double> scores = Arrays.asList(9.0, null, 8.0, null);

        // Act
        int result = analyzer.countExcellentStudents(scores);

        // Assert: Expected 2 (9.0 and 8.0)
        assertEquals(2, result, "Should ignore null elements and not throw NullPointerException");
    }

    @Test
    @DisplayName("Should handle boundary values correctly (Precision check)")
    public void testCountExcellentStudents_WhenScoreIsCloseToBoundary_ShouldCheckPrecision() {
        // Arrange: 7.9999 is close to but less than 8.0
        List<Double> scores = Arrays.asList(7.9999, 8.0);

        // Act
        int result = analyzer.countExcellentStudents(scores);

        // Assert: Only 8.0 is excellent
        assertEquals(1, result, "7.9999 should not be counted as excellent, strict comparison required");
    }

    @Test
    @DisplayName("Should return 0 when list is empty")
    public void testCountExcellentStudents_WhenListIsEmpty_ShouldReturnZero() {
        // Arrange
        List<Double> emptyList = Collections.emptyList();

        // Act
        int result = analyzer.countExcellentStudents(emptyList);

        // Assert
        assertEquals(0, result, "Empty list should return 0");
    }

    @Test
    @DisplayName("Should return 0 when list is null")
    public void testCountExcellentStudents_WhenListIsNull_ShouldReturnZero() {
        // Arrange & Act & Assert
        assertEquals(0, analyzer.countExcellentStudents(null), "Null list should be handled safely and return 0");
    }

    @Test
    @DisplayName("Should return 0 when no student is excellent")
    public void testCountExcellentStudents_WhenNoScoreIsExcellent_ShouldReturnZero() {
        // Arrange: All scores are < 8.0
        List<Double> scores = Arrays.asList(5.0, 6.0, 7.9);

        // Act
        int result = analyzer.countExcellentStudents(scores);

        // Assert
        assertEquals(0, result, "Should return 0 if no score meets the threshold");
    }

    // ==========================================
    // Feature: Calculate Valid Average
    // Method: calculateValidAverage
    // ==========================================

    @Test
    @DisplayName("Should calculate average of valid scores only")
    public void testCalculateValidAverage_WhenListHasMixedScores_ShouldCalculateValidAvg() {
        // Arrange: Valid (9.0, 7.0, 8.0), Invalid (15.0). Sum = 24.0, Count = 3
        List<Double> scores = Arrays.asList(9.0, 7.0, 8.0, 15.0);

        // Act
        double result = analyzer.calculateValidAverage(scores);

        // Assert: Avg = 8.0
        assertEquals(8.0, result, DELTA, "Should calculate the average of the 3 valid scores");
    }

    @Test
    @DisplayName("Should ignore null elements in average calculation")
    public void testCalculateValidAverage_WhenListContainsNulls_ShouldIgnoreNulls() {
        // Arrange: Valid (10.0, 5.0), Null is ignored. Sum = 15.0, Count = 2
        List<Double> scores = Arrays.asList(10.0, null, 5.0);

        // Act
        double result = analyzer.calculateValidAverage(scores);

        // Assert: Avg = 7.5
        assertEquals(7.5, result, DELTA, "Should ignore null values during calculation");
    }

    @Test
    @DisplayName("Should return 0.0 when list contains only invalid scores")
    public void testCalculateValidAverage_WhenAllScoresAreInvalid_ShouldReturnZero() {
        // Arrange: No valid scores available for division
        List<Double> scores = Arrays.asList(-5.0, 20.0, null);

        // Act
        double result = analyzer.calculateValidAverage(scores);

        // Assert: Avoid division by zero (NaN)
        assertEquals(0.0, result, DELTA, "Should return 0.0 if no valid scores exist");
    }

    @Test
    @DisplayName("Should return 0.0 when list is empty")
    public void testCalculateValidAverage_WhenListIsEmpty_ShouldReturnZero() {
        // Arrange & Act & Assert
        assertEquals(0.0, analyzer.calculateValidAverage(Collections.emptyList()), DELTA, "Empty list should return 0.0");
    }

    @Test
    @DisplayName("Should return 0.0 when list is null")
    public void testCalculateValidAverage_WhenListIsNull_ShouldReturnZero() {
        // Arrange & Act & Assert
        assertEquals(0.0, analyzer.calculateValidAverage(null), DELTA, "Null list should return 0.0");
    }

    @Test
    @DisplayName("Should calculate correctly with decimal results")
    public void testCalculateValidAverage_WhenResultIsDecimal_ShouldReturnCorrectDouble() {
        // Arrange: 10 + 9 = 19; 19 / 2 = 9.5
        List<Double> scores = Arrays.asList(10.0, 9.0);

        // Act
        double result = analyzer.calculateValidAverage(scores);

        // Assert
        assertEquals(9.5, result, DELTA, "Should return exact decimal average");
    }
}