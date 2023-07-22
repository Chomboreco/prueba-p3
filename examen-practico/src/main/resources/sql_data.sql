-- TIENDAS
INSERT INTO tienda (nombre, direccion) VALUES ('Av. Siempreviva 123', 'Patito Siempreviva');
INSERT INTO tienda (nombre, direccion) VALUES ('Blvd. Júpiter', 'Patito Júpiter');

-- VENDEDOR
INSERT INTO vendedor (nombre, email, [password], tienda_id) VALUES('Eduardo Chombo', 'edu@gmail.com', '$2a$10$RraOah2lTE2CWNMhBrOmz.M1L7j8/ZcYbkxbEk.A08h0dZ1sx1dyO', 1);

-- CLIENTE
INSERT INTO cliente (nombre, telefono, direccion) VALUES('Pancho Pistolas', '55-7612-2990', 'Av. Springfield 117');
INSERT INTO cliente (nombre, telefono, direccion) VALUES('Jhon Doe', '55-1234-5678', 'Calle Paris 32');

-- CAMIONETAS
INSERT INTO camioneta (placas, [status]) VALUES('RJ324', 'A');

-- PRODUCTOS
INSERT INTO producto (hawa, nombre, precio, descuento, existencia) VALUES('1', 'Camara Sony Alpha III', 30000, 0, 7);
INSERT INTO producto (hawa, nombre, precio, descuento, existencia) VALUES('2', 'Scooter Xiaomi', 25000, 0.1, 12);
INSERT INTO producto (hawa, nombre, precio, descuento, existencia) VALUES('3', 'Aspiradora Koblenz', 890, 0.15, 25);

--  PEDIDOS
INSERT INTO pedido ()