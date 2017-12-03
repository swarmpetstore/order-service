DROP TABLE IF EXISTS orders CASCADE;
CREATE TABLE orders (id SERIAL PRIMARY KEY, customer_id INTEGER, state VARCHAR);

DROP TABLE IF EXISTS order_item;
CREATE TABLE order_item (id SERIAL PRIMARY KEY, order_id INTEGER REFERENCES orders (id), item_id INT, quantity INT);

