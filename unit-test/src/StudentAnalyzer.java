import java.util.List;

public class StudentAnalyzer {

    // Định nghĩa hằng số để tránh Hard-code (Magic Numbers)
    private static final double MIN_SCORE = 0.0;
    private static final double MAX_SCORE = 10.0;
    private static final double EXCELLENT_SCORE_THRESHOLD = 8.0;

    /**
     * Phân tích điểm số và trả về số lượng học sinh đạt loại Giỏi.
     * @param scores danh sách điểm số
     * @return số học sinh đạt loại Giỏi (>= 8.0)
     */
    public int countExcellentStudents(List<Double> scores) {
        if (isEmptyOrNull(scores)) {
            return 0;
        }

        int count = 0;
        for (Double score : scores) {
            // Sử dụng hàm helper để kiểm tra tính hợp lệ
            if (isValidScore(score)) {
                if (score >= EXCELLENT_SCORE_THRESHOLD) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Tính điểm trung bình hợp lệ (từ 0 đến 10)
     * @param scores danh sách điểm
     * @return điểm trung bình của các điểm hợp lệ
     */
    public double calculateValidAverage(List<Double> scores) {
        if (isEmptyOrNull(scores)) {
            return 0.0;
        }

        double sum = 0;
        int validCount = 0;

        for (Double score : scores) {
            if (isValidScore(score)) {
                sum += score;
                validCount++;
            }
        }

        // Tránh lỗi chia cho 0 (ArithmeticException hoặc ra NaN)
        if (validCount == 0) {
            return 0.0;
        }

        return sum / validCount;
    }

    // --- Helper Methods (Private) ---

    /**
     * Kiểm tra một điểm số có hợp lệ hay không.
     * Tối ưu nguyên tắc DRY (Don't Repeat Yourself).
     */
    private boolean isValidScore(Double score) {
        // Kiểm tra null để an toàn nếu List chứa phần tử null
        return score != null && score >= MIN_SCORE && score <= MAX_SCORE;
    }

    /**
     * Kiểm tra danh sách null hoặc rỗng.
     */
    private boolean isEmptyOrNull(List<Double> scores) {
        return scores == null || scores.isEmpty();
    }
}