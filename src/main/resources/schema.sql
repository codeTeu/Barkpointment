--drop table dogs;
--drop table owners;

CREATE TABLE owners(
  ownerID 		INT 	PRIMARY KEY AUTO_INCREMENT,
  fname 	VARCHAR(50),
  lname 	VARCHAR(50),
  address 		VARCHAR(50),
  city 			VARCHAR(50),
  province 		VARCHAR(50),
  postalCode 	VARCHAR(50),
  email 		VARCHAR(50),
  phone 		VARCHAR(50)
);

CREATE TABLE dogs(
  dogID 		INT 	PRIMARY KEY AUTO_INCREMENT,
  name 			VARCHAR(50),
  gender 		VARCHAR(50),
  birthday 		DATE,
  breed 		VARCHAR(50),
  ownerID		INT,
  
  FOREIGN KEY  (ownerID) REFERENCES owners(ownerID)
);
  


CREATE TABLE appointments(
  apptID 		INT 		PRIMARY KEY AUTO_INCREMENT,
  date 			DATE,
  time 			TIME,
  ownerID		INT,
  dogID 		INT,
  reason 		VARCHAR(300),
  
  FOREIGN KEY  (ownerID) REFERENCES owners(ownerID),
  FOREIGN KEY  (dogID) REFERENCES dogs(dogID)
);



