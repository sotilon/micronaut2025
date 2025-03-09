CREATE TABLE TB_CUSTOMER (
    id BIGINT NOT NULL PRIMARY KEY,
    accountNumber VARCHAR(255),
    address VARCHAR(255),
    code VARCHAR(255),
    names VARCHAR(255),
    phone VARCHAR(255)
);

CREATE TABLE TB_PRODUCT (
    id BIGINT NOT NULL PRIMARY KEY,
    product_id BIGINT,
    customer_id BIGINT,
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES TB_CUSTOMER(id) ON DELETE CASCADE
);

INSERT INTO TB_CUSTOMER (id, accountNumber, address, code, names, phone) VALUES(1, 'ACC12345', '123 Main St', 'CUST001', 'John Doe', '555-1234');
INSERT INTO TB_CUSTOMER (id, accountNumber, address, code, names, phone) VALUES(2, 'ACC67890', '456 Elm St', 'CUST002', 'Jane Smith', '555-5678');
INSERT INTO TB_CUSTOMER (id, accountNumber, address, code, names, phone) VALUES(3, 'ACC54321', '789 Oak St', 'CUST003', 'Alice Johnson', '555-9876');

INSERT INTO TB_PRODUCT (id, product_id, customer_id) VALUES(1, 101, 1);
INSERT INTO TB_PRODUCT (id, product_id, customer_id) VALUES(2, 102, 1);
INSERT INTO TB_PRODUCT (id, product_id, customer_id) VALUES(3, 103, 2);
INSERT INTO TB_PRODUCT (id, product_id, customer_id) VALUES(4, 104, 3);




