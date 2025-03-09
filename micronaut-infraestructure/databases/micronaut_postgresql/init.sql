CREATE TABLE TB_CUSTOMER (
    id BIGINT NOT NULL PRIMARY KEY,
    accountNumber VARCHAR(255),
    address VARCHAR(255),
    code VARCHAR(255),
    names VARCHAR(255),
    phone VARCHAR(255)
);
CREATE SEQUENCE tb_customer_id_seq START WITH 1 INCREMENT BY 1;
ALTER TABLE tb_customer ALTER COLUMN id SET DEFAULT nextval('tb_customer_id_seq');

CREATE TABLE TB_PRODUCT (
    id BIGINT NOT NULL PRIMARY KEY,
    product_id BIGINT,
    customer_id BIGINT,
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES TB_CUSTOMER(id) ON DELETE CASCADE
);

CREATE SEQUENCE tb_product_id_seq START WITH 1 INCREMENT BY 1;
ALTER TABLE tb_product ALTER COLUMN id SET DEFAULT nextval('tb_product_id_seq');

INSERT INTO TB_CUSTOMER (accountNumber, address, code, names, phone) VALUES('ACC12345', '123 Main St', 'CUST001', 'John Doe', '555-1234');
INSERT INTO TB_CUSTOMER (accountNumber, address, code, names, phone) VALUES('ACC67890', '456 Elm St', 'CUST002', 'Jane Smith', '555-5678');
INSERT INTO TB_CUSTOMER (accountNumber, address, code, names, phone) VALUES('ACC54321', '789 Oak St', 'CUST003', 'Alice Johnson', '555-9876');

INSERT INTO TB_PRODUCT (product_id, customer_id) VALUES(101, 1);
INSERT INTO TB_PRODUCT (product_id, customer_id) VALUES(102, 1);
INSERT INTO TB_PRODUCT (product_id, customer_id) VALUES(103, 2);
INSERT INTO TB_PRODUCT (product_id, customer_id) VALUES(104, 3);




