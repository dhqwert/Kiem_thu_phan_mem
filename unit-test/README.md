# Student Analyzer Project

Dự án này cung cấp công cụ phân tích điểm số học sinh, bao gồm tính năng đếm số lượng học sinh giỏi và tính điểm trung bình, có cơ chế lọc dữ liệu rác (điểm không hợp lệ).

## Chức năng

1. **countExcellentStudents**: Đếm số học sinh có điểm >= 8.0. Bỏ qua các điểm < 0 hoặc > 10.
2. **calculateValidAverage**: Tính trung bình cộng của các điểm số hợp lệ (từ 0 đến 10).

## Cấu trúc thư mục

- `src/`: Chứa mã nguồn Java (`StudentAnalyzer.java`).
- `test/`: Chứa mã kiểm thử Unit Test (`StudentAnalyzerTest.java`).

## Yêu cầu hệ thống

- Java Development Kit (JDK) 8 trở lên.
- Thư viện JUnit 5 để chạy kiểm thử.

## Cách chạy kiểm thử

Nếu sử dụng IDE (IntelliJ, Eclipse):
1. Mở project.
2. Chuột phải vào file `StudentAnalyzerTest.java`.
3. Chọn **Run 'StudentAnalyzerTest'**.

Kết quả mong đợi: Tất cả các test case đều hiện màu xanh (Pass).