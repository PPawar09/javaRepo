 *  onlne MySql DB Free
 	https://www.db4free.net/phpMyAdmin/db_sql.php?db=playsoft
  
 	Database: playsoft
	Username: prakashnew
	Email: prakash09pawar@gmail.com
	password : prak1234
	
*******************

1) Table : login

	CREATE TABLE IF NOT EXISTS `login` (
	  `id` int(11) NOT NULL AUTO_INCREMENT,
	  `username` varchar(255) NOT NULL,
	  `password` varchar(255) NOT NULL,
	  `old_password` varchar(1000) NOT NULL,
	  `wrong_login_attempt` int(11) NOT NULL,
	  `today_login_attempt` int(11) NOT NULL,
	  `is_now_login` varchar(10) NOT NULL DEFAULT 'no',
	  `date` date NOT NULL,
	  `time` time NOT NULL,
	  `timestamp` varchar(20) NOT NULL,
	  PRIMARY KEY (`id`)
	) 

	INSERT INTO `login`(`id`, `username`, `password`, `old_password`, `wrong_login_attempt`, `today_login_attempt`, `is_now_login`, `date`, `time`, `timestamp`) 
	VALUES (12,'ppawar2','prak1234','prak1234',1,3,'yes','2020-04-06','4:40','828282882')