USE `soa_library_db`;

CREATE TABLE `authors` (
	`id` INTEGER NOT NULL AUTO_INCREMENT,
	`first_name` VARCHAR(200) NOT NULL,
	`last_name` VARCHAR(200) NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `books` (
	`id` INTEGER NOT NULL AUTO_INCREMENT,
	`title` VARCHAR(255) NOT NULL,
	`author_id` INTEGER,
	PRIMARY KEY (`id`),
	FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`) ON UPDATE RESTRICT ON DELETE RESTRICT
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;
