-- -----------------------------------------------------
-- Table `mydb`
-- -----------------------------------------------------
DROP TABLE `User`;
CREATE TABLE IF NOT EXISTS `User` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `password`  VARCHAR(100) NULL,
  `email` VARCHAR(100) NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE `Notification`;
CREATE TABLE IF NOT EXISTS `Notification` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `message` VARCHAR(255) NULL,
  `date` DATETIME NOT NULL,
  PRIMARY KEY (`id`)
);


INSERT INTO `User`(username, password, email) VALUES ('admin', '$2a$06$4AYmbeO7ox/1R8ls.gL/L.nuiUnwMcIl9LYfm8su9sLspZfMAx7b2', 'admin@admin.net');