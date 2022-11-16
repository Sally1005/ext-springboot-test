/*DDL 信息*/

CREATE TABLE `t_user` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `username` varchar(50) NOT NULL,
                          `password` varchar(50) NOT NULL,
                          `phone` char(11) NOT NULL,
                          PRIMARY KEY (`id`),
                          UNIQUE KEY `username` (`username`),
                          UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
