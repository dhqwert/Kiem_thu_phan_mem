describe('Cart & Checkout Test - Kiểm tra Giỏ hàng và Thanh toán', () => {

    beforeEach(() => {
        // Đăng nhập nhanh trước mỗi bài test
        cy.visit('https://www.saucedemo.com');
        cy.get('[data-test="username"]').type('standard_user');
        cy.get('[data-test="password"]').type('secret_sauce');
        cy.get('[data-test="login-button"]').click();

        // Đảm bảo đã vào trang chủ trước khi test
        cy.url().should('include', '/inventory.html');
    });

    // Kịch bản 3: Thêm sản phẩm vào giỏ
    it('Should add a product to the cart', () => {
        // Lưu selector vào biến (Alias) nếu dùng lại nhiều lần, hoặc dùng trực tiếp
        // Tìm nút "Add to cart" của sản phẩm đầu tiên
        cy.get('.inventory_item').first().find('button').click();

        // Assert 1: Nút bấm phải đổi text thành "Remove" -> Chứng tỏ đã thêm thành công
        cy.get('.inventory_item').first().find('button').should('have.text', 'Remove');

        // Assert 2: Icon giỏ hàng phải hiện số 1
        cy.get('.shopping_cart_badge').should('have.text', '1');
    });

    // Kịch bản 4: Sắp xếp giá từ Thấp đến Cao
    it('Should sort products by price low to high', () => {
        // 1. Chọn filter "lohi" (Low to High)
        // SỬA LỖI: Dùng class selector thay vì data-test
        cy.get('.product_sort_container').select('lohi');

        // 2. BƯỚC QUAN TRỌNG: Kiểm tra xem dropdown đã hiển thị đúng text chưa
        // Bước này giúp Cypress "đợi" một chút để UI kịp cập nhật
        cy.get('.active_option').should('have.text', 'Price (low to high)');

        // 3. Kiểm tra TÊN sản phẩm đầu tiên phải là "Sauce Labs Onesie"
        cy.get('.inventory_item_name').first().should('have.text', 'Sauce Labs Onesie');

        // 4. Kiểm tra GIÁ của sản phẩm đầu tiên là $7.99
        cy.get('.inventory_item_price').first().should('have.text', '$7.99');
    });

    // --- PHẦN NÂNG CAO ---

    // Kịch bản 5: Xóa sản phẩm khỏi giỏ (Logic chặt chẽ)
    it('Should remove a product from the cart', () => {
        // 1. Thêm vào giỏ
        cy.get('.inventory_item').first().find('button').click();
        cy.get('.shopping_cart_badge').should('have.text', '1');

        // 2. Nhấn nút đó lần nữa (lúc này là nút Remove) để xóa
        cy.get('.inventory_item').first().find('button').click();

        // 3. Kiểm tra nút bấm quay lại thành "Add to cart"
        cy.get('.inventory_item').first().find('button').should('have.text', 'Add to cart');

        // 4. Kiểm tra Icon số lượng trên giỏ hàng biến mất
        cy.get('.shopping_cart_badge').should('not.exist');
    });

    // Kịch bản 6: Quy trình thanh toán (E2E Flow)
    it('Should complete the checkout flow', () => {
        // 1. Thêm hàng
        cy.get('.inventory_item').first().find('button').click();

        // 2. Vào giỏ hàng
        cy.get('.shopping_cart_link').click();
        cy.url().should('include', '/cart.html'); // Kiểm tra URL giỏ hàng

        // 3. Bấm Checkout
        cy.get('[data-test="checkout"]').click();

        // 4. Điền thông tin giao hàng (Dùng data-test chuẩn)
        cy.get('[data-test="firstName"]').type('Nguyen');
        cy.get('[data-test="lastName"]').type('Van A');
        cy.get('[data-test="postalCode"]').type('10000');

        // 5. Bấm Continue
        cy.get('[data-test="continue"]').click();

        // 6. Kiểm tra: Đã chuyển sang trang xác nhận (Step Two)
        cy.url().should('include', '/checkout-step-two.html');
        cy.get('.title').should('contain', 'Overview');
    });
});