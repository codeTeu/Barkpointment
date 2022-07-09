INSERT INTO accounts(fname, lname, phone, address, city, province, email, isAdmin)  VALUES ('John', 'Doe', '6470000000', '00 Yonge St.', 'Toronto', 'ON', 'john.doe@gmail.com','Y');
INSERT INTO accounts(fname, lname, phone, address, city, province, email)  VALUES ('Emma', 'Watson', '6470000000', '01 Orange St.', 'Toronto', 'ON', 'emma.watson@gmail.com');
INSERT INTO dogs(name, gender, breed, birthday, ownerID) VALUES ('Shiba','F', 'Shiba Inu', '2022-11-11',1);
INSERT INTO dogs(name, gender, breed, birthday, ownerID) VALUES ('Loki','M', 'Border Collie ', '2022-11-11',1);
INSERT INTO dogs(name, gender, breed, birthday, ownerID) VALUES ('Patch','M', 'Pitbul ', '2022-11-11',1);


INSERT INTO appointments(
appt_date, appt_time, ownerID, dogID) VALUES ( '2022-11-11','1:00',  1, 1);

INSERT INTO appointments(
appt_date, appt_time, ownerID, dogID) VALUES ( '2022-11-11','2:00',  2, 1);

INSERT INTO appointments(
appt_date, appt_time, ownerID, dogID) VALUES ( '2022-11-11','3:00',  1, 2);
