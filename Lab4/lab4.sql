-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema lab4
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema lab4
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `lab4` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `lab4` ;

-- -----------------------------------------------------
-- Table `lab4`.`colores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lab4`.`colores` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `lab4`.`tipos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lab4`.`tipos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `lab4`.`ocasiones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lab4`.`ocasiones` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `lab4`.`flores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lab4`.`flores` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `descripcion` TEXT NULL DEFAULT NULL,
  `precio` DECIMAL(10,2) NOT NULL,
  `imagen_url` VARCHAR(255) NULL DEFAULT NULL,
  `color_id` INT NULL DEFAULT NULL,
  `tipo_id` INT NULL DEFAULT NULL,
  `ocasion_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `color_id` (`color_id` ASC) VISIBLE,
  INDEX `tipo_id` (`tipo_id` ASC) VISIBLE,
  INDEX `ocasion_id` (`ocasion_id` ASC) VISIBLE,
  CONSTRAINT `flores_ibfk_1`
    FOREIGN KEY (`color_id`)
    REFERENCES `lab4`.`colores` (`id`),
  CONSTRAINT `flores_ibfk_2`
    FOREIGN KEY (`tipo_id`)
    REFERENCES `lab4`.`tipos` (`id`),
  CONSTRAINT `flores_ibfk_3`
    FOREIGN KEY (`ocasion_id`)
    REFERENCES `lab4`.`ocasiones` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `lab4`.`carrito`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lab4`.`carrito` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `flor_id` INT NULL DEFAULT NULL,
  `cantidad` INT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  INDEX `flor_id` (`flor_id` ASC) VISIBLE,
  CONSTRAINT `carrito_ibfk_1`
    FOREIGN KEY (`flor_id`)
    REFERENCES `lab4`.`flores` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO colores (nombre) VALUES
('Rojo'), 
('Verde'), 
('Azul'), 
('Blanco'), 
('Amarillo');

-- Insertar tipos de flores
INSERT INTO tipos (nombre) VALUES
('Rosa'), 
('Clavel'), 
('Hortencia'), 
('Girasol'), 
('Tulipán');

-- Insertar ocasiones
INSERT INTO ocasiones (nombre) VALUES
('Primavera'), 
('San Valentín'), 
('Aniversario'), 
('Día de la Amistad'), 
('Día del Padre'), 
('Día de la Madre'), 
('Condolencias');

-- Insertar flores en el catálogo
INSERT INTO flores (nombre, descripcion, precio, imagen_url, color_id, tipo_id, ocasion_id) VALUES
('Rosa', 'Una bella rosa roja ideal para el día de San Valentín.', 15.99, 'rosa_roja.jpg', 1, 1, 2),
('Clavel', 'Clavel blanco que simboliza pureza y elegancia.', 12.50, 'clavel_blanco.jpg', 4, 2, 6),
('Tulipán', 'Tulipán amarillo, perfecto para regalar en primavera.', 10.00, 'tulipan_amarillo.jpg', 5, 5, 1),
('Hortencia', 'Hortencia azul, símbolo de gratitud y abundancia.', 18.75, 'hortencia_azul.jpg', 3, 3, 3),
('Girasol', 'Girasol brillante que trae alegría en cualquier ocasión.', 13.99, 'girasol.jpg', 5, 4, 1);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
