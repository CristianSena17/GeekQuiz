-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Quiz
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Quiz
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Quiz` DEFAULT CHARACTER SET utf8 ;
USE `Quiz` ;

-- -----------------------------------------------------
-- Table `Quiz`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Quiz`.`Usuario` (
  `Nome` VARCHAR(200) NOT NULL,
  `Senha` VARCHAR(200) NOT NULL,
  `Email` VARCHAR(200) NOT NULL,
  `Tipo` VARCHAR(1) NULL,
  PRIMARY KEY (`Nome`),
  UNIQUE INDEX `Nome_UNIQUE` (`Nome` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Quiz`.`Tema`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Quiz`.`Tema` (
  `idTema` INT NOT NULL,
  `nmTema` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idTema`),
  UNIQUE INDEX `nmTema_UNIQUE` (`nmTema` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Quiz`.`Questionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Quiz`.`Questionario` (
  `Correta` INT NOT NULL,
  `Tema_idTema` INT NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  `Descricao` VARCHAR(200) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Questionario_Tema1_idx` (`Tema_idTema` ASC),
  CONSTRAINT `fk_Questionario_Tema1`
    FOREIGN KEY (`Tema_idTema`)
    REFERENCES `Quiz`.`Tema` (`idTema`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Quiz`.`Respostas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Quiz`.`Respostas` (
  `idRespostas` INT NOT NULL AUTO_INCREMENT,
  `Descricao` VARCHAR(200) NOT NULL,
  `Questionario_Tema_idTema` INT NOT NULL,
  `Questionario_id` INT NOT NULL,
  PRIMARY KEY (`idRespostas`, `Questionario_Tema_idTema`, `Questionario_id`),
  INDEX `fk_Respostas_Questionario1_idx` (`Questionario_Tema_idTema` ASC, `Questionario_id` ASC),
  CONSTRAINT `fk_Respostas_Questionario1`
    FOREIGN KEY (`Questionario_Tema_idTema` , `Questionario_id`)
    REFERENCES `Quiz`.`Questionario` (`Tema_idTema` , `id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
