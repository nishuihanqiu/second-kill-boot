DROP DATABASE IF EXISTS `second_kill`;
CREATE DATABASE IF NOT EXISTS `second_kill`;
USE `second_kill`;


CREATE TABLE IF NOT EXISTS `sec_kill`(
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(120) NOT NULL,
  `count` int(11) NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `success_record` (
  `sec_kill_id` int(11) unsigned NOT NULL,
  `phone` varchar(15) NOT NULL,
  `state` tinyint(1) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`sec_kill_id`,`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `sec_kill` (`id`, `name`, `count`, `start_time`, `end_time`) VALUES
(1, '1000元秒杀iphone6', 91, '2019-03-01 01:50:44', '2019-03-31 01:51:19'),
(2, '500元秒杀ipad2', 193, '2019-03-01 01:49:46', '2019-03-31 01:49:46'),
(3, '300元秒杀小米4', 297, '2019-02-28 16:00:00', '2019-03-30 16:00:00'),
(4, '200元秒杀红米note', 92, '2019-03-01 05:14:00', '2019-03-30 16:00:00');

INSERT INTO `success_record` (`sec_kill_id`, `phone`, `state`) VALUES
(1, '03151189772', 0),
(1, '11051189778', 0),
(1, '11111111111', 0),
(1, '11151189772', 0),
(1, '13051189771', 0),
(1, '13051189772', 0),
(1, '13051189778', 0),
(1, '13051189779', 0),
(1, '13151189772', 0),
(1, '99999999997', 0),
(2, '10051188772', 0),
(2, '11111111111', 0),
(2, '12315648796', 0),
(2, '13051188772', 0),
(2, '13051189772', 0),
(2, '99999999997', 0),
(3, '11051189778', 0),
(3, '11151189772', 0),
(3, '15151189772', 0),
(4, '11111111111', 0),
(4, '11111111112', 0),
(4, '11111111113', 0),
(4, '11111111114', 0),
(4, '11111111115', 0),
(4, '11111111116', 0),
(4, '11111111117', 0),
(4, '11111111118', 0);

--
-- 存储过程
--
DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `execute_sec_kill`(IN id INT,IN phone VARCHAR (15),IN executedTime TIMESTAMP ,OUT result INT)
BEGIN
    DECLARE insertCount INT DEFAULT 0;
    START TRANSACTION ;
    INSERT IGNORE success_record(`sec_kill_id`,`phone`, `state`) VALUES(id,phone,0,executedTime);
    SELECT ROW_COUNT() INTO insertCount;
    IF(insertCount = 0) THEN
      ROLLBACK ;
      SET result = -1;
    ELSEIF(insertCount < 0) THEN
      ROLLBACK ;
      SET result = -2;
    ELSE
      UPDATE sec_kill SET `count` = `count` -1 WHERE `seck_ill_id` = id AND `start_time` < executedTime AND `end_time` > executedTime AND `count` > 0;
      SELECT ROW_COUNT() INTO insertCount;
      IF (insertCount = 0)  THEN
        ROLLBACK ;
        SET result = 0;
      ELSEIF (insertCount < 0) THEN
        ROLLBACK ;
        SET result = -2;
      ELSE
        COMMIT ;
        SET  result = 1;
      END IF;
    END IF;
  END$$

DELIMITER ;
