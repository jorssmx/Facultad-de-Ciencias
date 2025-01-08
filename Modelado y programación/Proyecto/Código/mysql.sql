CREATE DATABASE proyecto;
USE proyecto;
CREATE TABLE TABLA(
    id INT(50) AUTO_INCREMENT PRIMARY KEY,
    terminal VARCHAR(2) NOT NULL,
    parada VARCHAR(5) NOT NULL,
    destino VARCHAR(5) NOT NULL,
    tarifa INT(250) NOT NULL,
    asientosDisponibles INT(2) NOT NULL
);

INSERT INTO TABLA(terminal, parada, destino, tarifa, asientosDisponibles) VALUES('AB','A', 'B', 10, 10);
INSERT INTO TABLA(terminal, parada, destino, tarifa, asientosDisponibles) VALUES('AC','A', 'C', 15, 10);
INSERT INTO TABLA(terminal, parada, destino, tarifa, asientosDisponibles) VALUES('AD','A', 'D', 20, 10);
INSERT INTO TABLA(terminal, parada, destino, tarifa, asientosDisponibles) VALUES('BC','B', 'C', 10, 10);
INSERT INTO TABLA(terminal, parada, destino, tarifa, asientosDisponibles) VALUES('BD','B', 'D', 15, 10);
INSERT INTO TABLA(terminal, parada, destino, tarifa, asientosDisponibles) VALUES('CD','C', 'D', 10, 10);

SELECT * FROM Tabla;





 




