SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema periodicals_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema periodicals_db
-- -----------------------------------------------------

CREATE SCHEMA IF NOT EXISTS `periodicals_db` DEFAULT CHARACTER SET utf8 ;
USE `periodicals_db` ;

-- -----------------------------------------------------
-- Table `periodicals_db`.`user`
-- -----------------------------------------------------
##DROP TABLE  IF EXISTS `periodicals_db`.`users`;

CREATE TABLE IF NOT EXISTS `periodicals_db`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `registration_date` DATE NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `enabled` BOOLEAN DEFAULT true,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`username` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `periodicals_db`.`publication`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `periodicals_db`.`publication` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `locale` VARCHAR(10) NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `genre` VARCHAR(45) NOT NULL,
  `price` DECIMAL(13,2) NOT NULL,
  `description` VARCHAR(1000) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `title_UNIQUE` (`title` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `periodicals_db`.`subscription`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `periodicals_db`.`subscription` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `start_date` DATE NOT NULL,
  `status` ENUM('active', 'inactive') NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`, `user_id`),
  INDEX `fk_subscription_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_subscription_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `periodicals_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `periodicals_db`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `periodicals_db`.`payment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `sum` DECIMAL(13,2) NOT NULL,
  `state` ENUM('unpaid', 'paid') NOT NULL,
  `datetime_of_payment` DATETIME NOT NULL,
  `subscription_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`, `subscription_id`, `user_id`),
  INDEX `fk_payment_subscription1_idx` (`subscription_id` ASC),
  INDEX `fk_payment_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_payment_subscription1`
    FOREIGN KEY (`subscription_id`)
    REFERENCES `periodicals_db`.`subscription` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_payment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `periodicals_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `periodicals_db`.`subscription_has_publication`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `periodicals_db`.`subscription_has_publication` (
  `subscription_id` INT NOT NULL,
  `publication_id` INT NOT NULL,
  `start_date` DATE NOT NULL,
  `end_date` DATE NOT NULL,
  PRIMARY KEY (`subscription_id`, `publication_id`),
  INDEX `fk_subscription_has_publication_publication1_idx` (`publication_id` ASC),
  INDEX `fk_subscription_has_publication_subscription1_idx` (`subscription_id` ASC),
  CONSTRAINT `fk_subscription_has_publication_subscription1`
    FOREIGN KEY (`subscription_id`)
    REFERENCES `periodicals_db`.`subscription` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_subscription_has_publication_publication1`
    FOREIGN KEY (`publication_id`)
    REFERENCES `periodicals_db`.`publication` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `periodicals_db`.`authority`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `periodicals_db`.`authority` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `periodicals_db`.`user_authority`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `periodicals_db`.`user_authority` (
	`authority_id` INT NOT NULL,
    `user_id` INT NOT NULL,
    PRIMARY KEY (`user_id`, `authority_id`))
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
