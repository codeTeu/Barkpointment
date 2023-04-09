--drop table dogs;
--drop table owners;

CREATE TABLE owners(
  id INT 		PRIMARY KEY,
  first_name 	VARCHAR(50),
  last_name 	VARCHAR(50),
  address 		VARCHAR(50),
  city 			VARCHAR(50),
  province 		VARCHAR(50),
  postalCode 	VARCHAR(50),
  email 		VARCHAR(50),
  phone 		VARCHAR(50)
);

CREATE TABLE dogs(
  id INT 		PRIMARY KEY,
  name 			VARCHAR(50),
  gender 		VARCHAR(50),
  birthday 		DATE,
  breed 		VARCHAR(50),
  ownerId		INT,
  
  FOREIGN KEY  (ownerId) REFERENCES owners(id)
);
  


CREATE TABLE appointments(
  id INT 		PRIMARY KEY,
  date 			DATE,
  time 			TIME,
  ownerId		INT,
  dogId 		INT,
  reason 		VARCHAR(300),
  
  FOREIGN KEY  (ownerId) REFERENCES owners(id),
  FOREIGN KEY  (dogId) REFERENCES dogs(id)
);



