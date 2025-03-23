INSERT INTO client (id_client, name_client, lastname_client, dni) VALUES
(1, 'Juan', 'Pérez', '123456789'),
(2, 'María', 'Gómez', '987654321');

-- Insertar datos en la tabla Product
INSERT INTO product (cod_product, name_product, cost, stock) VALUES
(1, 'Laptop', 1500.00, 20),
(2, 'Mouse', 20.00, 100),
(3, 'Keyboard', 50.00, 50);

-- Insertar datos en la tabla Sale
INSERT INTO sale (cod_sale, sale_date, id_client, total_sale) VALUES
(1, '2024-12-10', 1, 1700.00),
(2, '2024-12-11', 2, 70.00);

-- Insertar datos en la tabla SaleProduct
INSERT INTO sale_product (id_sale_product, cod_sale, cod_product, quantity) VALUES
(1, 1, 1, 1), -- Venta 1, Laptop
(2, 2, 2, 2), -- Venta 2, 2 Mouse
(3, 2, 3, 1); -- Venta 2, 1 Keyboard