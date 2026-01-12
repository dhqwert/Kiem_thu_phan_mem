# 📘 Portfolio Môn Kiểm thử Phần mềm (Software Testing)

> **Kho lưu trữ các bài tập thực hành, mã nguồn và báo cáo kết quả học tập.**

* **Sinh viên:** Đoàn Hoàng Quân
* **Mã sinh viên:** BCS230070
* **Lớp:** 23CS2
* **Trạng thái:** 🟢 Đang cập nhật (In Progress)

---

## Mục Lục (Table of Contents)

1. [Giới thiệu chung](#-giới-thiệu-chung)
2. [Cấu trúc Repository](#-cấu-trúc-repository)
3. [Chương 1: Nguyên lý kiểm thử](#-chương-1-nguyên-lý-kiểm-thử)
4. [Chương 2: Quy trình kiểm thử](#-chương-2-quy-trình-kiểm-thử)
5. [Chương 3: Kiểm thử tĩnh](#-chương-3-kiểm-thử-tĩnh)

---

## 🌐 Giới thiệu chung

Dự án này là nơi tổng hợp toàn bộ quá trình thực hành môn Kiểm thử phần mềm. Mục tiêu là đi từ việc rèn luyện tư duy phát hiện lỗi giao diện, đến việc viết code kiểm thử đơn vị chặt chẽ, và cuối cùng là xây dựng kịch bản kiểm thử tự động quy trình (End-to-End).

**Công nghệ sử dụng:**
* **Ngôn ngữ:** Java (JUnit), JavaScript (Cypress).
* **Công cụ:** IntelliJ IDEA/Eclipse, VS Code, Git.
* **Frameworks:** JUnit 5, Cypress.io.

---

## 📂 Cấu trúc Repository

```text
Kiem_thu_phan_mem/
├── cantunsee/              # Bài tập Chương 1
├── unit-test/              # Bài tập Chương 2 (Java Project)
│   ├── src/                # Source code chức năng
│   └── test/               # Source code test case
├── cypress-exercise/       # Bài tập Chương 3 (Cypress Project)
│   ├── cypress/
│   │   ├── e2e/            # Các kịch bản test
│   │   └── ...
│   └── ...
├── images/                 # Thư mục lưu trữ bằng chứng kiểm thử (Evidence)
└── README.md               # File tài liệu này

```

---

## 🎨 Chương 1: Nguyên lý kiểm thử

**Mục tiêu:** Rèn luyện mắt quan sát ("Pixel Perfect") và phân biệt lỗi thiết kế giao diện thông qua trò chơi [Can't Unsee](https://www.google.com/search?q=https://cantunsee.space/).

* **Nội dung thực hiện:** Phân tích Contrast, Typography, Alignment, Padding.
* **Kết quả:** Hoàn thành các cấp độ từ cơ bản đến nâng cao.

**📸 Bằng chứng (Evidence):**

![Kết quả Cant Unsee](images/chapter1/cantunsee_hoangquandoan.png)

---

## ☕ Chương 2: Quy trình kiểm thử

**Mục tiêu:** Áp dụng JUnit 5 để kiểm thử logic xử lý điểm số học sinh (`StudentAnalyzer`). Đảm bảo code đạt chuẩn Clean Code và xử lý tốt các trường hợp biên.

* **Bài toán:** Xử lý danh sách điểm số (List Double), đếm số học sinh giỏi và tính điểm trung bình hợp lệ.
* **Kỹ thuật áp dụng:**
* Boundary Value Analysis (Phân tích giá trị biên).
* Equivalence Partitioning (Phân vùng tương đương).
* Refactoring & Clean Code (Tách hàm, dùng hằng số).



**✅ Danh sách Test Cases:**

| ID | Chức năng | Input (Dữ liệu vào) | Kỳ vọng (Expect) | Kết quả |
| --- | --- | --- | --- | --- |
| TC_01 | `countExcellentStudents` | `[9.0, 8.5, 7.0, 11.0, -1.0]` | `2` (Bỏ qua điểm sai) | PASS |
| TC_02 | `countExcellentStudents` | `[]` (Rỗng) | `0` | PASS |
| TC_03 | `countExcellentStudents` | `null` | `0` (Không crash) | PASS |
| TC_04 | `calculateValidAverage` | `[0.0, 10.0]` | `5.0` | PASS |
| TC_05 | `calculateValidAverage` | `[-5.0, 15.0]` (Toàn điểm sai) | `0.0` | PASS |

**📸 Bằng chứng (Evidence):**

![Kết quả JUnit](images/chapter2/junit_result.png)

---

## 🌲 Chương 3: Kiểm thử tĩnh

**Mục tiêu:** Sử dụng Cypress để kiểm thử tự động quy trình mua hàng trên trang [SauceDemo](https://www.saucedemo.com).

* **Phạm vi:** Kiểm thử chức năng, giao diện và luồng nghiệp vụ (Business Flow).
* **Điểm nổi bật:**
* Sử dụng **Best Practices**: Dùng Selector `[data-test]`, tránh hard-coded wait.
* Xử lý bài tập nâng cao: Flow xóa sản phẩm và Flow thanh toán đầy đủ.



**✅ Các kịch bản kiểm thử (Scenarios):**

1. **Login Flow:** Kiểm tra đăng nhập đúng/sai.
2. **Shopping Flow:** Thêm sản phẩm -> Icon giỏ hàng cập nhật số lượng.
3. **Filter Flow:** Sắp xếp giá từ Thấp -> Cao (Kiểm tra dữ liệu hiển thị đúng).
4. **Cart Management (Nâng cao):** Thêm sản phẩm -> Xóa sản phẩm -> Icon biến mất.
5. **Checkout Process (Nâng cao):** Cart -> Checkout Info -> Overview Page.

**Hướng dẫn chạy test:**

```bash
cd cypress-exercise
npm install
npx cypress open

```

**📸 Bằng chứng (Evidence):**

![Cấu trúc dự án](images/chapter3/project_structure.png)
![Kết quả Cypress](images/chapter3/cart_test_pass.png)
![Kết quả Cypress](images/chapter3/login_test_pass.png)

---

*Last updated: 12/01/2026*