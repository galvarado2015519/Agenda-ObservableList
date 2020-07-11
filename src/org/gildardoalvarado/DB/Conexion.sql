-- Creador:	Gildardo Leonel Alvarado del Cid
-- Fecha de Creacion: 19/06/2019
-- Fecha de ultima modificaion: 19/06/2019

CREATE DATABASE Conexion;
use Conexion;
-- drop database conexion;

CREATE TABLE Persona(
	nombre varchar(20) not null,
    apellido varchar(20) not null,
    telefono varchar(20) not null
)

DELIMITER $$
create procedure sp_AgregarPersonas(nombre varchar(20),apellido varchar(20),telefono varchar(20)) 
	begin
		insert into Persona(nombre,apellido,telefono) values(nombre,apellido,telefono);
	end $$
DELIMITER $$

DELIMITER $$
CREATE PROCEDURE sp_ListarPersona()
	BEGIN
		Select nombre,apellido,telefono from Persona;
    END $$
DELIMITER $$


select * from Persona;