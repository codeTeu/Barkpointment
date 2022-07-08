CREATE TABLE accounts (
    acctID int PRIMARY KEY AUTO_INCREMENT,
    fname varchar(50) NOT NULL,
    lname varchar(50) NOT NULL,
    phone char(10) NOT NULL,
	address varchar(50),
	city varchar(50),
	province char(2),
	email varchar(50) NOT NULL UNIQUE,
    password varchar(50) DEFAULT '000',
    isAdmin char(1) DEFAULT 'N'
 );

CREATE TABLE dogs (
    dogID int  AUTO_INCREMENT ,
    name varchar(50) NOT NULL,
    gender char(1) NOT NULL,
	breed varchar(50) DEFAULT 'unknown', 
	birthday DATE, 
	ownerID int NOT NULL,
	PRIMARY KEY (dogID)  ,
	FOREIGN KEY (ownerID) REFERENCES accounts(acctID)
 );
 
 
CREATE TABLE appointments(
	apptID int AUTO_INCREMENT,
	appt_date date,
	appt_time time,
	ownerID int NOT NULL,
	dogID int NOT NULL,
	
	PRIMARY KEY (apptID)  ,
	FOREIGN KEY (ownerID) REFERENCES accounts(acctID),
	FOREIGN KEY (dogID) REFERENCES dogs(dogID)
);


--DROP  TABLE dogs;
--DROP  TABLE accounts;