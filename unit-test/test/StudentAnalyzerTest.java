import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StudentAnalyzerTest {

    private final StudentAnalyzer analyzer = new StudentAnalyzer();

    // ==========================================
    // Test cho hàm countExcellentStudents
    // ==========================================

    @Test
    public void testCountExcellentStudents_NormalCase() {
        // Input: Hỗn hợp điểm giỏi, khá, điểm sai, điểm biên
        List<Double> scores = Arrays.asList(9.0, 8.5, 7.0, 11.0, -1.0, 8.0);
        // Logic: 9.0, 8.5, 8.0 là giỏi -> Kết quả mong đợi: 3
        assertEquals(3, analyzer.countExcellentStudents(scores), "Should count correct excellent students ignoring invalid scores");
    }

    @Test
    public void testCountExcellentStudents_EmptyList() {
        assertEquals(0, analyzer.countExcellentStudents(Collections.emptyList()), "Empty list should return 0");
    }

    @Test
    public void testCountExcellentStudents_NullList() {
        // Test tính ổn định (Robustness) - Không được crash khi null
        assertEquals(0, analyzer.countExcellentStudents(null), "Null list should be handled gracefully as 0");
    }

    @Test
    public void testCountExcellentStudents_NoExcellentStudents() {
        List<Double> scores = Arrays.asList(5.0, 6.0, 7.9);
        assertEquals(0, analyzer.countExcellentStudents(scores), "Should return 0 if no student is excellent");
    }

    // ==========================================
    // Test cho hàm calculateValidAverage
    // ==========================================

    @Test
    public void testCalculateValidAverage_NormalCase() {
        // Valid: 9.0, 7.0, 8.0. Invalid: 15.0. Sum = 24.0, Count = 3. Avg = 8.0
        List<Double> scores = Arrays.asList(9.0, 7.0, 8.0, 15.0);
        assertEquals(8.0, analyzer.calculateValidAverage(scores), 0.001);
    }

    @Test
    public void testCalculateValidAverage_WithDecimalResult() {
        // Valid: 10, 9. Sum = 19. Count = 2. Avg = 9.5
        List<Double> scores = Arrays.asList(10.0, 9.0);
        assertEquals(9.5, analyzer.calculateValidAverage(scores), 0.001);
    }

    @Test
    public void testCalculateValidAverage_AllInvalidScores() {
        // Mọi điểm đều sai -> validCount = 0 -> Return 0.0
        List<Double> scores = Arrays.asList(-5.0, 20.0);
        assertEquals(0.0, analyzer.calculateValidAverage(scores), 0.001);
    }

    @Test
    public void testCalculateValidAverage_BoundaryValues() {
        // Kiểm tra biên 0 và 10
        List<Double> scores = Arrays.asList(0.0, 10.0);
        // (0 + 10) / 2 = 5.0
        assertEquals(5.0, analyzer.calculateValidAverage(scores), 0.001);
    }
}