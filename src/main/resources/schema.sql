DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;


CREATE TABLE users
(
   username VARCHAR(50)  NOT NULL PRIMARY KEY ,
   password VARCHAR(100) NOT NULL,
   enabled  int NOT NULL DEFAULT 1
 );

CREATE TABLE authorities
(
	username  VARCHAR(50) NOT NULL,
	authority VARCHAR(50) NOT NULL,
	FOREIGN KEY (username) REFERENCES users (username)
);



CREATE TABLE accounts (
    acctID int PRIMARY KEY AUTO_INCREMENT,
   	username VARCHAR(50),
   	password VARCHAR(100) NOT NULL,
   	authority VARCHAR(50) NOT NULL,
	fname varchar(50) NOT NULL,
	lname varchar(50) NOT NULL,
    phone char(10) NOT NULL,
   	email varchar(50) NOT NULL,
	address varchar(50),
	city varchar(50),
	province char(2),

    
    FOREIGN KEY(username) REFERENCES users(username)
 );
 
 CREATE TABLE dogs (
    dogID int  AUTO_INCREMENT ,
    name varchar(50) NOT NULL,
    gender char(1) NOT NULL,
	breed varchar(50) DEFAULT 'unknown', 
	birthday DATE, 
	ownerID int NOT NULL,
	PRIMARY KEY (dogID)  
--	FOREIGN KEY (ownerID) REFERENCES accounts(acctID) 
 );