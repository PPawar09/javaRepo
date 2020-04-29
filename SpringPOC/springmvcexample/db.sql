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

		
2) Table : TimeSheetRecord

	CREATE TABLE IF NOT EXISTS `TimeSheetRecord` (
	  `id` int(11) REFERENCES login(id),
	  `Name` varchar(30) NOT NULL,
	  `Client` varchar(50) NOT NULL,
	  `TaskDesc` varchar(100) NOT NULL,
	  `StartDate` date NOT NULL,
	  `BillingStatus` varchar(10) NOT NULL,
	  `timestamp` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	  PRIMARY KEY (`id`,`Name`)
	)

3) Table : User

	CREATE TABLE IF NOT EXISTS `User` (
	  `id` int(11) REFERENCES login(id),
	  `Name` varchar(30) NOT NULL,
	  `emailid` varchar(50) NOT NULL,
	  `registerDate` date NOT NULL,
	  `timestamp` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	  PRIMARY KEY (`id`)
	)
	
	
*************** Reference command alter

DROP TABLE User;
	