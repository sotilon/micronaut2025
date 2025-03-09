USE micronaut_mysql_product;

CREATE TABLE PRODUCT (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255)
);

INSERT INTO PRODUCT (code, name, description) VALUES ('0002', 'Crédito', 'Visa Clásica');
INSERT INTO PRODUCT (code, name, description) VALUES ('0003', 'Interbank', 'Visa Oro');
INSERT INTO PRODUCT (code, name, description) VALUES ('0060', 'Interbank', 'Visa Infinite');


