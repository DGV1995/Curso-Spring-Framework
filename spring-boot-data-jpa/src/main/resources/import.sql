insert into CLIENTES (nombre, apellido, email, create_at, foto) values ('Diego', 'García-Viana', 'diegogarciaviana@correo.com', '1995-08-20', '')
insert into CLIENTES (nombre, apellido, email, create_at, foto) values ('Pepe', 'Gómez', 'pepegomez@correo.com', '1998-08-15', '')
insert into CLIENTES (nombre, apellido, email, create_at, foto) values ('Juana', 'Álvarez', 'juanaalvarez@correo.com', '2003-05-28', '')
insert into CLIENTES (nombre, apellido, email, create_at, foto) values ('Roberta', 'Martínez', 'robertamartinez@correo.com', '2010-11-05', '')
insert into CLIENTES (nombre, apellido, email, create_at, foto) values ('Alicia', 'Caballero', 'aliciacaballero@correo.com', '1995-11-10', '')
insert into CLIENTES (nombre, apellido, email, create_at, foto) values ('Ángel', 'Cumplido', 'angelcumplido@correo.com', '1995-01-13', '')
insert into CLIENTES (nombre, apellido, email, create_at, foto) values ('Rodolfo', 'Suárez', 'rodolfosuarez@correo.com', '2004-12-28', '')
insert into CLIENTES (nombre, apellido, email, create_at, foto) values ('Andrea', 'Martínez', 'andreamartinez@correo.com', '2007-03-05', '')
insert into CLIENTES (nombre, apellido, email, create_at, foto) values ('Javier', 'Martínez', 'javiermartinez@correo.com', '1993-04-09', '')
insert into CLIENTES (nombre, apellido, email, create_at, foto) values ('Sergio', 'Sobrevilla', 'sergiosobrevilla@correo.com', '1994-03-05', '')
insert into CLIENTES (nombre, apellido, email, create_at, foto) values ('David', 'Ramos', 'davidramos@correo.com', '1997-01-22', '')
insert into CLIENTES (nombre, apellido, email, create_at, foto) values ('Daniel', 'Marciel', 'danielmarcielz@correo.com', '1994-05-16', '')
insert into CLIENTES (nombre, apellido, email, create_at, foto) values ('Yaiza', 'Hernández', 'yaizahernandez@correo.com', '1995-07-04', '')
insert into CLIENTES (nombre, apellido, email, create_at, foto) values ('Jose', 'Laserna', 'joselaserna@correo.com', '1995-07-15', '')
insert into CLIENTES (nombre, apellido, email, create_at, foto) values ('Irene', 'Montérdez', 'jirenemonterdez@correo.com', '1995-09-19', '')
insert into CLIENTES (nombre, apellido, email, create_at, foto) values ('Enrique', 'Caballero', 'enriquecaballero@correo.com', '1967-08-15', '')
insert into CLIENTES (nombre, apellido, email, create_at, foto) values ('Víctor', 'Caballero', 'victorcaballero@correo.com', '1998-05-05', '')
insert into CLIENTES (nombre, apellido, email, create_at, foto) values ('Carlos', 'De Diego', 'carlosdediego@correo.com', '1993-01-01', '')
insert into CLIENTES (nombre, apellido, email, create_at, foto) values ('Ángela', 'García', 'angelagarcia@correo.com', '1992-05-28', '')
insert into CLIENTES (nombre, apellido, email, create_at, foto) values ('Javier', 'Álvarez', 'javieralvarez@correo.com', '1995-11-05', '')

insert into productos (nombre, precio, create_at) values ('Samsung Galaxy S10', 800, NOW());
insert into productos (nombre, precio, create_at) values ('Iphone XS', 1000, NOW());
insert into productos (nombre, precio, create_at) values ('OnePlus 7', 600, NOW());
insert into productos (nombre, precio, create_at) values ('Razer Deathader Elite', 99, NOW());
insert into productos (nombre, precio, create_at) values ('Samsung TV 32"', 350, NOW());
insert into productos (nombre, precio, create_at) values ('Google Chromecast 2', 39, NOW());
insert into productos (nombre, precio, create_at) values ('God of War PS4', 45, NOW());
insert into productos (nombre, precio, create_at) values ('Th Witcher 3: Wild Hunt GOTY', 25, NOW());
insert into productos (nombre, precio, create_at) values ('Ipad Pro 2019', 800, NOW());
insert into productos (nombre, precio, create_at) values ('Nikon D5300', 500, NOW());
insert into productos (nombre, precio, create_at) values ('Xiami MiPad 4', 250, NOW());

insert into facturas (descripcion, observacion, cliente_id, create_at) values ('Factura equipos de oficina', null, 1, NOW());
insert into facturas (descripcion, observacion, cliente_id, create_at) values ('Factura de tecnología', 'Se han comprado muchas cosas', 2, NOW());

insert into facturas_items (cantidad, factura_id, producto_id) values (1, 1, 1);
insert into facturas_items (cantidad, factura_id, producto_id) values (2, 1, 4);
insert into facturas_items (cantidad, factura_id, producto_id) values (1, 1, 5);
insert into facturas_items (cantidad, factura_id, producto_id) values (1, 1, 7);
insert into facturas_items (cantidad, factura_id, producto_id) values (3, 2, 6);
insert into facturas_items (cantidad, factura_id, producto_id) values (2, 2, 1);
insert into facturas_items (cantidad, factura_id, producto_id) values (2, 2, 4);
insert into facturas_items (cantidad, factura_id, producto_id) values (6, 1, 5);
insert into facturas_items (cantidad, factura_id, producto_id) values (4, 2, 7);
insert into facturas_items (cantidad, factura_id, producto_id) values (1, 2, 2);

