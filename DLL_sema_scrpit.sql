-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema pcelinjak_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema pcelinjak_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pcelinjak_db` DEFAULT CHARACTER SET utf8 ;
USE `pcelinjak_db` ;

-- -----------------------------------------------------
-- Table `pcelinjak_db`.`VLASNIK`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pcelinjak_db`.`VLASNIK` ;

CREATE TABLE IF NOT EXISTS `pcelinjak_db`.`VLASNIK` (
  `IdVlasnika` INT NOT NULL AUTO_INCREMENT,
  `KorisničkoIme` VARCHAR(30) NOT NULL,
  `Lozinka` VARCHAR(128) NOT NULL,
  `JMBG` CHAR(13) NOT NULL,
  `Ime` VARCHAR(25) NOT NULL,
  `Prezime` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`IdVlasnika`),
  UNIQUE INDEX `KorisničkoIme_UNIQUE` (`KorisničkoIme` ASC) VISIBLE,
  UNIQUE INDEX `JMBG_UNIQUE` (`JMBG` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pcelinjak_db`.`PČELINJAK`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pcelinjak_db`.`PČELINJAK` ;

CREATE TABLE IF NOT EXISTS `pcelinjak_db`.`PČELINJAK` (
  `IdPčelinjaka` INT NOT NULL AUTO_INCREMENT,
  `NazivPčelinjaka` VARCHAR(45) NOT NULL UNIQUE,
  `AdresaPčelinjaka` VARCHAR(45) NOT NULL,
  `BrojDruštava` INT NOT NULL,
  `BrojVrcalica` INT ZEROFILL NOT NULL,
  `BrojTegliZaAmbalažu` INT NULL,
  `BrojZaposlenih` INT ZEROFILL NOT NULL,
  `VLASNIK_IdVlasnika` INT NOT NULL,
  PRIMARY KEY (`IdPčelinjaka`),
  INDEX `fk_PČELINJAK_VLASNIK1_idx` (`VLASNIK_IdVlasnika` ASC) VISIBLE,
  CONSTRAINT `fk_PČELINJAK_VLASNIK1`
    FOREIGN KEY (`VLASNIK_IdVlasnika`)
    REFERENCES `pcelinjak_db`.`VLASNIK` (`IdVlasnika`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pcelinjak_db`.`DRUŠTVO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pcelinjak_db`.`DRUŠTVO` ;

CREATE TABLE IF NOT EXISTS `pcelinjak_db`.`DRUŠTVO` (
  `IdDruštva` INT NOT NULL AUTO_INCREMENT,
  `BrojSanduka` TINYINT UNSIGNED NOT NULL,
  `ProizveloRoj` TINYINT UNSIGNED NOT NULL,
  `VeličinaLegla` TINYINT UNSIGNED NOT NULL,
  `KoličinaMedaURezervi` INT UNSIGNED NOT NULL,
  `Red` INT UNSIGNED NOT NULL,
  `PozicijaURedu` INT UNSIGNED NOT NULL,
  `PČELINJAK_IdPčelinjaka` INT NOT NULL,
  PRIMARY KEY (`IdDruštva`),
  INDEX `fk_DRUŠTVO_PČELINJAK1_idx` (`PČELINJAK_IdPčelinjaka` ASC) VISIBLE,
  CONSTRAINT `fk_DRUŠTVO_PČELINJAK1`
    FOREIGN KEY (`PČELINJAK_IdPčelinjaka`)
    REFERENCES `pcelinjak_db`.`PČELINJAK` (`IdPčelinjaka`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pcelinjak_db`.`SANDUK`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pcelinjak_db`.`SANDUK` ;

CREATE TABLE IF NOT EXISTS `pcelinjak_db`.`SANDUK` (
  `BrojSandukaUDruštvu` TINYINT UNSIGNED NOT NULL,
  `GodinaKupovine` YEAR NOT NULL,
  `Boja` VARCHAR(20) NOT NULL,
  `BrojRamova` TINYINT NOT NULL,
  `DRUŠTVO_IdDruštva` INT NOT NULL,
  PRIMARY KEY (`DRUŠTVO_IdDruštva`, `BrojSandukaUDruštvu`),
  CONSTRAINT `fk_SANDUK_DRUŠTVO1`
    FOREIGN KEY (`DRUŠTVO_IdDruštva`)
    REFERENCES `pcelinjak_db`.`DRUŠTVO` (`IdDruštva`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pcelinjak_db`.`IZVRCANI_MED`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pcelinjak_db`.`IZVRCANI_MED` ;

CREATE TABLE IF NOT EXISTS `pcelinjak_db`.`IZVRCANI_MED` (
  `IdIzvrcanogMeda` INT NOT NULL AUTO_INCREMENT,
  `Vrsta` VARCHAR(30) NOT NULL,
  `Količina` DOUBLE UNSIGNED NOT NULL,
  `Cijena` DECIMAL NOT NULL,
  PRIMARY KEY (`IdIzvrcanogMeda`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pcelinjak_db`.`POSJEDUJE_MED`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pcelinjak_db`.`POSJEDUJE_MED` ;

CREATE TABLE IF NOT EXISTS `pcelinjak_db`.`POSJEDUJE_MED` (
  `PČELINJAK_IdPčelinjaka` INT NOT NULL,
  `IZVRCANI_MED_MED_IdMeda` INT NOT NULL,
  `Količina` DOUBLE UNSIGNED NOT NULL,
  PRIMARY KEY (`PČELINJAK_IdPčelinjaka`, `IZVRCANI_MED_MED_IdMeda`),
  INDEX `fk_IZVRCANI_MED_has_PČELINJAK_PČELINJAK1_idx` (`PČELINJAK_IdPčelinjaka` ASC) VISIBLE,
  INDEX `fk_POSJEDUJE_IZVRCANI_MED1_idx` (`IZVRCANI_MED_MED_IdMeda` ASC) VISIBLE,
  CONSTRAINT `fk_IZVRCANI_MED_has_PČELINJAK_PČELINJAK1`
    FOREIGN KEY (`PČELINJAK_IdPčelinjaka`)
    REFERENCES `pcelinjak_db`.`PČELINJAK` (`IdPčelinjaka`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_POSJEDUJE_IZVRCANI_MED1`
    FOREIGN KEY (`IZVRCANI_MED_MED_IdMeda`)
    REFERENCES `pcelinjak_db`.`IZVRCANI_MED` (`IdIzvrcanogMeda`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pcelinjak_db`.`PROPOLIS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pcelinjak_db`.`PROPOLIS` ;

CREATE TABLE IF NOT EXISTS `pcelinjak_db`.`PROPOLIS` (
  `IdPropolisa` INT NOT NULL,
  `Vrsta` VARCHAR(30) NOT NULL,
  `Količina` INT NOT NULL,
  `Cijena` DECIMAL NOT NULL,
  PRIMARY KEY (`IdPropolisa`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pcelinjak_db`.`KUPAC`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pcelinjak_db`.`KUPAC` ;

CREATE TABLE IF NOT EXISTS `pcelinjak_db`.`KUPAC` (
  `IdKupca` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`IdKupca`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pcelinjak_db`.`KUPOVINA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pcelinjak_db`.`KUPOVINA` ;

CREATE TABLE IF NOT EXISTS `pcelinjak_db`.`KUPOVINA` (
  `IdKupovine` INT NOT NULL AUTO_INCREMENT,
  `KUPAC_IdKupca` INT NOT NULL,
  `PČELINJAK_IdPčelinjaka` INT NOT NULL,
  `Datum` DATE NOT NULL,
  `Cijena` DECIMAL NOT NULL,
  INDEX `fk_KUPOVINA_KUPAC1_idx` (`KUPAC_IdKupca` ASC) VISIBLE,
  INDEX `fk_KUPOVINA_PČELINJAK1_idx` (`PČELINJAK_IdPčelinjaka` ASC) VISIBLE,
  PRIMARY KEY (`IdKupovine`),
  CONSTRAINT `fk_KUPOVINA_KUPAC1`
    FOREIGN KEY (`KUPAC_IdKupca`)
    REFERENCES `pcelinjak_db`.`KUPAC` (`IdKupca`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_KUPOVINA_PČELINJAK1`
    FOREIGN KEY (`PČELINJAK_IdPčelinjaka`)
    REFERENCES `pcelinjak_db`.`PČELINJAK` (`IdPčelinjaka`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pcelinjak_db`.`FIZIČKO_LICE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pcelinjak_db`.`FIZIČKO_LICE` ;

CREATE TABLE IF NOT EXISTS `pcelinjak_db`.`FIZIČKO_LICE` (
  `KUPAC_IdKupca` INT NOT NULL,
  `JMBG` CHAR(13) NOT NULL,
  `Ime` VARCHAR(25) NOT NULL,
  `Prezime` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`KUPAC_IdKupca`),
  CONSTRAINT `fk_FIZIČKO_LICE_KUPAC1`
    FOREIGN KEY (`KUPAC_IdKupca`)
    REFERENCES `pcelinjak_db`.`KUPAC` (`IdKupca`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pcelinjak_db`.`TRGOVINA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pcelinjak_db`.`TRGOVINA` ;

CREATE TABLE IF NOT EXISTS `pcelinjak_db`.`TRGOVINA` (
  `KUPAC_IdKupca` INT NOT NULL,
  `Naziv` VARCHAR(30) NOT NULL,
  `Adresa` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`KUPAC_IdKupca`),
  CONSTRAINT `fk_TRGOVINA_KUPAC1`
    FOREIGN KEY (`KUPAC_IdKupca`)
    REFERENCES `pcelinjak_db`.`KUPAC` (`IdKupca`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pcelinjak_db`.`STAVKA_MED`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pcelinjak_db`.`STAVKA_MED` ;

CREATE TABLE IF NOT EXISTS `pcelinjak_db`.`STAVKA_MED` (
  `KUPOVINA_IdKupovine` INT NOT NULL,
  `IZVRCANI_MED_MED_IdMeda` INT NOT NULL,
  `Količina` DOUBLE UNSIGNED NOT NULL,
  `Cijena` DECIMAL NOT NULL,
  PRIMARY KEY (`KUPOVINA_IdKupovine`, `IZVRCANI_MED_MED_IdMeda`),
  INDEX `fk_STAVKA_MED_IZVRCANI_MED1_idx` (`IZVRCANI_MED_MED_IdMeda` ASC) VISIBLE,
  CONSTRAINT `fk_STAVKA_MED_KUPOVINA1`
    FOREIGN KEY (`KUPOVINA_IdKupovine`)
    REFERENCES `pcelinjak_db`.`KUPOVINA` (`IdKupovine`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_STAVKA_MED_IZVRCANI_MED1`
    FOREIGN KEY (`IZVRCANI_MED_MED_IdMeda`)
    REFERENCES `pcelinjak_db`.`IZVRCANI_MED` (`IdIzvrcanogMeda`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pcelinjak_db`.`STAVKA_PROPOLIS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pcelinjak_db`.`STAVKA_PROPOLIS` ;

CREATE TABLE IF NOT EXISTS `pcelinjak_db`.`STAVKA_PROPOLIS` (
  `KUPOVINA_IdKupovine` INT NOT NULL,
  `PROPOLIS_IdPropolisa` INT NOT NULL,
  `Količina` INT NOT NULL,
  `Cijena` DECIMAL NOT NULL,
  PRIMARY KEY (`KUPOVINA_IdKupovine`, `PROPOLIS_IdPropolisa`),
  INDEX `fk_STAVKA_PROPOLIS_PROPOLIS1_idx` (`PROPOLIS_IdPropolisa` ASC) VISIBLE,
  CONSTRAINT `fk_STAVKA_PROPOLIS_KUPOVINA1`
    FOREIGN KEY (`KUPOVINA_IdKupovine`)
    REFERENCES `pcelinjak_db`.`KUPOVINA` (`IdKupovine`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_STAVKA_PROPOLIS_PROPOLIS1`
    FOREIGN KEY (`PROPOLIS_IdPropolisa`)
    REFERENCES `pcelinjak_db`.`PROPOLIS` (`IdPropolisa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pcelinjak_db`.`POSJEDUJE_PROPOLIS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pcelinjak_db`.`POSJEDUJE_PROPOLIS` ;

CREATE TABLE IF NOT EXISTS `pcelinjak_db`.`POSJEDUJE_PROPOLIS` (
  `PČELINJAK_IdPčelinjaka` INT NOT NULL,
  `PROPOLIS_IdPropolisa` INT NOT NULL,
  `Količina` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`PČELINJAK_IdPčelinjaka`, `PROPOLIS_IdPropolisa`),
  INDEX `fk_PČELINJAK_has_PROPOLIS_PROPOLIS1_idx` (`PROPOLIS_IdPropolisa` ASC) VISIBLE,
  INDEX `fk_PČELINJAK_has_PROPOLIS_PČELINJAK1_idx` (`PČELINJAK_IdPčelinjaka` ASC) VISIBLE,
  CONSTRAINT `fk_PČELINJAK_has_PROPOLIS_PČELINJAK1`
    FOREIGN KEY (`PČELINJAK_IdPčelinjaka`)
    REFERENCES `pcelinjak_db`.`PČELINJAK` (`IdPčelinjaka`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PČELINJAK_has_PROPOLIS_PROPOLIS1`
    FOREIGN KEY (`PROPOLIS_IdPropolisa`)
    REFERENCES `pcelinjak_db`.`PROPOLIS` (`IdPropolisa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pcelinjak_db`.`ZAPOSLENI`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pcelinjak_db`.`ZAPOSLENI` ;

CREATE TABLE IF NOT EXISTS `pcelinjak_db`.`ZAPOSLENI` (
  `IdZaposlenog` INT NOT NULL AUTO_INCREMENT,
  `Plata` DECIMAL NOT NULL,
  `KorisničkoIme` VARCHAR(30) NOT NULL,
  `Lozinka` VARCHAR(128) NOT NULL,
  `JMBG` CHAR(13) NOT NULL,
  `Ime` VARCHAR(25) NOT NULL,
  `Prezime` VARCHAR(25) NOT NULL,
  `PČELINJAK_IdPčelinjaka` INT NOT NULL,
  PRIMARY KEY (`IdZaposlenog`),
  INDEX `fk_ZAPOSLENI_PČELINJAK1_idx` (`PČELINJAK_IdPčelinjaka` ASC) VISIBLE,
  UNIQUE INDEX `JMBG_UNIQUE` (`JMBG` ASC) VISIBLE,
  UNIQUE INDEX `KorisnickoIme_UNIQUE` (`KorisničkoIme` ASC) VISIBLE,
  CONSTRAINT `fk_ZAPOSLENI_PČELINJAK1`
    FOREIGN KEY (`PČELINJAK_IdPčelinjaka`)
    REFERENCES `pcelinjak_db`.`PČELINJAK` (`IdPčelinjaka`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pcelinjak_db`.`PREGLEDA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pcelinjak_db`.`PREGLEDA` ;

CREATE TABLE IF NOT EXISTS `pcelinjak_db`.`PREGLEDA` (
  `IdPregleda` INT NOT NULL AUTO_INCREMENT,
  `DatumPregleda` DATE NOT NULL,
  `VeličinaLegla` TINYINT NOT NULL,
  `KoličinaMedaURezervi` TINYINT NOT NULL,
  `ProizveloRoj` TINYINT UNSIGNED NOT NULL,
  `DRUŠTVO_IdDruštva` INT NOT NULL,
  `ZAPOSLENI_IdZaposlenog` INT NOT NULL,
  PRIMARY KEY (`IdPregleda`),
  INDEX `fk_PREGLEDA_DRUŠTVO1_idx` (`DRUŠTVO_IdDruštva` ASC) VISIBLE,
  INDEX `fk_PREGLEDA_ZAPOSLENI1_idx` (`ZAPOSLENI_IdZaposlenog` ASC) VISIBLE,
  CONSTRAINT `fk_PREGLEDA_DRUŠTVO1`
    FOREIGN KEY (`DRUŠTVO_IdDruštva`)
    REFERENCES `pcelinjak_db`.`DRUŠTVO` (`IdDruštva`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PREGLEDA_ZAPOSLENI1`
    FOREIGN KEY (`ZAPOSLENI_IdZaposlenog`)
    REFERENCES `pcelinjak_db`.`ZAPOSLENI` (`IdZaposlenog`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pcelinjak_db`.`LIJEČI`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pcelinjak_db`.`LIJEČI` ;

CREATE TABLE IF NOT EXISTS `pcelinjak_db`.`LIJEČI` (
  `IdLiječenja` INT NOT NULL AUTO_INCREMENT,
  `DatumLiječenja` DATE NOT NULL,
  `VrstaLijeka` VARCHAR(20) NOT NULL,
  `DRUŠTVO_IdDruštva` INT NOT NULL,
  `ZAPOSLENI_IdZaposlenog` INT NOT NULL,
  PRIMARY KEY (`IdLiječenja`),
  INDEX `fk_LIJEČI_DRUŠTVO1_idx` (`DRUŠTVO_IdDruštva` ASC) VISIBLE,
  INDEX `fk_LIJEČI_ZAPOSLENI1_idx` (`ZAPOSLENI_IdZaposlenog` ASC) VISIBLE,
  CONSTRAINT `fk_LIJEČI_DRUŠTVO1`
    FOREIGN KEY (`DRUŠTVO_IdDruštva`)
    REFERENCES `pcelinjak_db`.`DRUŠTVO` (`IdDruštva`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_LIJEČI_ZAPOSLENI1`
    FOREIGN KEY (`ZAPOSLENI_IdZaposlenog`)
    REFERENCES `pcelinjak_db`.`ZAPOSLENI` (`IdZaposlenog`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pcelinjak_db`.`VRCA_MED`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pcelinjak_db`.`VRCA_MED` ;

CREATE TABLE IF NOT EXISTS `pcelinjak_db`.`VRCA_MED` (
  `IdVrcanja` INT NOT NULL AUTO_INCREMENT,
  `DatumVrcanja` DATE NOT NULL,
  `VrstaMeda` VARCHAR(45) NOT NULL,
  `KoličinaMeda` DOUBLE NOT NULL,
  `DRUŠTVO_IdDruštva` INT NOT NULL,
  `ZAPOSLENI_IdZaposlenog` INT NOT NULL,
  PRIMARY KEY (`IdVrcanja`),
  INDEX `fk_VRCA_MED_DRUŠTVO1_idx` (`DRUŠTVO_IdDruštva` ASC) VISIBLE,
  INDEX `fk_VRCA_MED_ZAPOSLENI1_idx` (`ZAPOSLENI_IdZaposlenog` ASC) VISIBLE,
  CONSTRAINT `fk_VRCA_MED_DRUŠTVO1`
    FOREIGN KEY (`DRUŠTVO_IdDruštva`)
    REFERENCES `pcelinjak_db`.`DRUŠTVO` (`IdDruštva`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_VRCA_MED_ZAPOSLENI1`
    FOREIGN KEY (`ZAPOSLENI_IdZaposlenog`)
    REFERENCES `pcelinjak_db`.`ZAPOSLENI` (`IdZaposlenog`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
