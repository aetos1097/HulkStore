
INSERT INTO clientes(cedula,nombre, apellido, email, create_at) VALUES ('9876543','María', 'García','maria.garcia@gmail.com','2020-01-15');
INSERT INTO clientes(cedula,nombre, apellido, email, create_at) VALUES ('5678901','Pedro', 'López','pedro.lopez@gmail.com','2019-06-02');
INSERT INTO clientes(cedula,nombre, apellido, email, create_at) VALUES ('3214567','Ana', 'Martínez','ana.martinez@gmail.com','2020-11-30');
INSERT INTO clientes(cedula,nombre, apellido, email, create_at) VALUES ('7890123','Carlos', 'Sánchez','carlos.sanchez@gmail.com','2021-03-17');
INSERT INTO clientes(cedula,nombre, apellido, email, create_at) VALUES ('3456789','Laura', 'Ramírez','laura.ramirez@gmail.com','2019-09-10');
INSERT INTO clientes(cedula,nombre, apellido, email, create_at) VALUES ('9012345','Andrés', 'Hernández','andres.hernandez@gmail.com','2022-05-05');
INSERT INTO clientes(cedula,nombre, apellido, email, create_at) VALUES ('2345678','Sofía', 'Pérez','sofia.perez@gmail.com','2021-08-12');
INSERT INTO clientes(cedula,nombre, apellido, email, create_at) VALUES ('6789012','Luis', 'Torres','luis.torres@gmail.com','2022-02-25');
INSERT INTO clientes(cedula,nombre, apellido, email, create_at) VALUES ('0123456','Marta', 'González','marta.gonzalez@gmail.com','2020-04-07');
INSERT INTO clientes(cedula,nombre, apellido, email, create_at) VALUES ('4567890','Roberto', 'Díaz','roberto.diaz@gmail.com','2023-01-20');

-- productos

INSERT INTO productos(nombre, create_at, precio) VALUES ('Buzo Marvel', '2023-04-20', 49.99);
INSERT INTO productos(nombre, create_at, precio) VALUES ('Camisa deportiva Marvel', '2023-02-20', 29.99);
INSERT INTO productos(nombre, create_at, precio) VALUES ('Vaso de Marvel', '2023-01-17', 9.99);
INSERT INTO productos(nombre, create_at, precio) VALUES ('Gorra de Marvel', '2023-08-11', 19.99);
INSERT INTO productos(nombre, create_at, precio) VALUES ('Juguete de Marvel', '2022-09-27', 14.99);
INSERT INTO productos(nombre, create_at, precio) VALUES ('Camisa deportiva DC Comics', '2022-11-01', 24.99);
INSERT INTO productos(nombre, create_at, precio) VALUES ('Vaso de DC Comics', '2022-11-08', 7.99);
INSERT INTO productos(nombre, create_at, precio) VALUES ('Gorra de DC Comics', '2022-11-11', 16.99);
INSERT INTO productos(nombre, create_at, precio) VALUES ('Juguete de DC Comics', '2022-12-15', 12.99);
INSERT INTO productos(nombre, create_at, precio) VALUES ('Camisa deportiva Marvel vs. DC', '2023-06-04', 34.99);
INSERT INTO productos(nombre, create_at, precio) VALUES ('Juguete Marvel vs. DC', '2023-07-23', 17.99);


INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura numero 1', null, 1, NOW());
INSERT INTO items (cantidad, factura_id, producto_id) VALUES(1, 1, 1);
INSERT INTO items (cantidad, factura_id, producto_id) VALUES(2, 1, 4);
INSERT INTO items (cantidad, factura_id, producto_id) VALUES(1, 1, 5);
