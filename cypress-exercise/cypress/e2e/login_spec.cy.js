describe('Login Test - Kiểm tra chức năng Đăng nhập', () => {

    // URL trang web nên khai báo ở đầu hoặc trong file config (nhưng ở đây để cứng cho đơn giản)
    const BASE_URL = 'https://www.saucedemo.com';

    beforeEach(() => {
        cy.visit(BASE_URL);
    });

    // Kịch bản 1: Đăng nhập thành công
    it('Should login successfully with valid credentials', () => {
        // Sử dụng data-test selector (Best Practice của Cypress)
        cy.get('[data-test="username"]').type('standard_user');
        cy.get('[data-test="password"]').type('secret_sauce');
        cy.get('[data-test="login-button"]').click();

        // Assert: Kiểm tra URL và sự tồn tại của danh sách sản phẩm
        cy.url().should('include', '/inventory.html');
        cy.get('.inventory_list').should('be.visible');
    });

    // Kịch bản 2: Đăng nhập thất bại
    it('Should show error message with invalid credentials', () => {
        cy.get('[data-test="username"]').type('invalid_user');
        cy.get('[data-test="password"]').type('wrong_password');
        cy.get('[data-test="login-button"]').click();

        // Assert: Kiểm tra thông báo lỗi hiển thị chính xác
        cy.get('[data-test="error"]').should('be.visible')
            .and('contain', 'Username and password do not match');
    });
});