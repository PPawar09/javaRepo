 *  onlne MySql DB Free
 	https://www.db4free.net/phpMyAdmin/db_sql.php?db=playsoft
  
 	Database: playsoft
	Username: prakashnew
	Email: prakash09pawar@gmail.com
	password : prak1234
	
*******************

1) Table : login

	CREATE TABLE IF NOT EXISTS `LOGIN_USER_DATA` (
	  `ID` int(11) NOT NULL AUTO_INCREMENT,
	  `FIRST_NAME` varchar(20) NOT NULL,
	  `LAST_NAME` varchar(20) NOT NULL,
	  `EMAIl_ID` varchar(30) NOT NULL,
	  `LOGIN_ID` varchar(20) NOT NULL,
	  `PASSWORD` varchar(15) NOT NULL,
	  `OLD_PASSWORD` varchar(15),
	  `WRONG_LOGIN_ATTEMPT` int(2),
	  `REC_DATE` DATE NOT NULL,
	  `REC_TS` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	  PRIMARY KEY (`ID`)
	) 
	
	INSERT INTO `LOGIN_USER_DATA`(`FIRST_NAME`, `LAST_NAME`, `EMAIl_ID`, `LOGIN_ID`, `PASSWORD`, `REC_DATE`) 
	VALUES ('Prakash','Pawar','ppawar2@pp.com','ppawar2','prak1234','2020-04-06');

		
2) Table : TIME_SHEET_REC

	CREATE TABLE IF NOT EXISTS `TIME_SHEET_REC` (
	  `USER_ID` int(11) REFERENCES LOGIN_USER_DATA(ID),
	  `USER_NAME` varchar(30) NOT NULL,
	  `CLIENT` varchar(50) NOT NULL,
	  `TASK_DESC` varchar(500) NOT NULL,
	  `START_DATE` date NOT NULL,
	  `BILLING_STATUS` varchar(10) NOT NULL,
	  `REC_TS` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	)
	
	INSERT INTO `TIME_SHEET_REC`(`USER_ID`, `USER_NAME`, `CLIENT`, `TASK_DESC`, `START_DATE`, `BILLING_STATUS`) 
	VALUES (1,'PrakashPawar','TestClient','Java Work','2020-04-06','Pending')

3) Table : User

	CREATE TABLE IF NOT EXISTS `User` (
	  `id` int(11) REFERENCES login(id),
	  `Name` varchar(30) NOT NULL,
	  `emailid` varchar(50) NOT NULL,
	  `registerDate` date NOT NULL,
	  `timestamp` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	  PRIMARY KEY (`id`)
	)
	
4) Table : PERSON

CREATE TABLE `PERSON` (
`Id` INT(11) NOT NULL AUTO_INCREMENT,
`name` VARCHAR(255) DEFAULT NULL,
`country` VARCHAR(255) DEFAULT NULL,
PRIMARY KEY (`Id`)
)
	
*************** Reference command alter

DROP TABLE User;
	