CREATE TABLE Products (
    product_id BIGINT NOT NULL,
    user_id UUID NOT NULL,
    name VARCHAR(512) NOT NULL,
    PRIMARY KEY(product_id, user_id)
);