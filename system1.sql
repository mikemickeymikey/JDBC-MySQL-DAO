DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `COGNOMNOM`(IN `idEmpleat` INT, OUT `fullname` VARCHAR(40))
BEGIN

SELECT CONCAT(LASTNAME,", ",FIRSTNAME) INTO fullname FROM employee
WHERE id = idEmpleat;


END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `actualitzar_empleat`(IN `firstname` VARCHAR(50), IN `lastname` VARCHAR(50), IN `birthdate` DATE, IN `salary` FLOAT, IN `id` INT)
BEGIN
UPDATE employee
SET FIRSTNAME = firstname, LASTNAME = lastname, BIRTHDATE = birthdate, SALARY = salary
WHERE ID = id;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `afegir_empleat`(IN `id` INT, IN `firstname` VARCHAR(50), IN `lastname` VARCHAR(50), IN `birthdate` DATE, IN `salary` FLOAT)
BEGIN
INSERT INTO employee VALUES(id, firstname, lastname, birthdate, salary);
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminar_empleat`(IN `id` INT)
BEGIN
DELETE FROM employee WHERE ID = id;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `seleccionar_empleats`(IN `id` INT)
BEGIN
	SELECT * FROM employee;
END$$
DELIMITER ;
