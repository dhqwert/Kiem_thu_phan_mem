import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StudentAnalyzerTest {

    private final StudentAnalyzer analyzer = new StudentAnalyzer();

    // --- Test cho hàm countExcellentStudents ---

    @Test
    public void testCountExcellentStudents_NormalCase() {
        // Trường hợp bình thường: Có điểm giỏi, khá, và điểm không hợp lệ
        List<Double> scores = Arrays.asList(9.0, 8.5, 7.0, 11.0, -1.0);
        // 9.0 và 8.5 là giỏi (2 người). 11.0 và -1.0 bị bỏ qua.
        assertEquals(2, analyzer.countExcellentStudents(scores));
    }

    @Test
    public void testCountExcellentStudents_EmptyList() {
        // Trường hợp biên: Danh sách rỗng
        assertEquals(0, analyzer.countExcellentStudents(Collections.emptyList()));
    }

    @Test
    public void testCountExcellentStudents_AllInvalid() {
        // Trường hợp ngoại lệ: Tất cả điểm đều sai
        List<Double> scores = Arrays.asList(-5.0, 15.0, 10.1);
        assertEquals(0, analyzer.countExcellentStudents(scores));
    }

    @Test
    public void testCountExcellentStudents_Boundary() {
        // Trường hợp biên: 8.0 (vừa đủ giỏi) và 7.9 (suýt giỏi)
        List<Double> scores = Arrays.asList(8.0, 7.9);
        assertEquals(1, analyzer.countExcellentStudents(scores));
    }

    // --- Test cho hàm calculateValidAverage ---

    @Test
    public void testCalculateValidAverage_NormalCase() {
        // 9.0, 8.5, 7.0 hợp lệ. Tổng = 24.5. TB = 24.5 / 3 = 8.1666...
        List<Double> scores = Arrays.asList(9.0, 8.5, 7.0, 11.0, -1.0);
        assertEquals(8.166, analyzer.calculateValidAverage(scores), 0.01);
    }

    @Test
    public void testCalculateValidAverage_EmptyList() {
        // Trường hợp biên: Rỗng
        assertEquals(0.0, analyzer.calculateValidAverage(Collections.emptyList()), 0.001);
    }

    @Test
    public void testCalculateValidAverage_AllInvalid() {
        // Trường hợp: Có danh sách nhưng toàn bộ điểm không hợp lệ -> chia cho 0
        List<Double> scores = Arrays.asList(-1.0, 12.0);
        assertEquals(0.0, analyzer.calculateValidAverage(scores), 0.001);
    }

    @Test
    public void testCalculateValidAverage_ZeroAndTen() {
        // Trường hợp biên: 0 và 10
        List<Double> scores = Arrays.asList(0.0, 10.0);
        assertEquals(5.0, analyzer.calculateValidAverage(scores), 0.001);
    }
}