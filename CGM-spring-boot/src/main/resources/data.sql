insert into app_user (id,first_name,last_name,phone_number,email,password,role,app_user_id) values
(1,'Admin','Admin','000', 'admin','$2a$05$GIny7PaavzM8fcWL3ewJDeEFvTzd/L4lty7XxxclNeiFLfopL3rq2','ADMIN',1),
(2, 'Mihai','Dumitrescu','07949996', 'chef@gmail.com','$2a$05$GIny7PaavzM8fcWL3ewJDeEFvTzd/L4lty7XxxclNeiFLfopL3rq2','DOCTOR',2),
(3, 'George','Paun','07999697', 'geopa@gmail.com','$2a$05$GIny7PaavzM8fcWL3ewJDeEFvTzd/L4lty7XxxclNeiFLfopL3rq2','DOCTOR',3),
(4, 'Miruna','Pop','07543125', 'mirunapop@gmail.com','$2a$05$GIny7PaavzM8fcWL3ewJDeEFvTzd/L4lty7XxxclNeiFLfopL3rq2','PATIENT',4),
(5, 'Marius','Lazar','07432497', 'gasd@gmail.com','$2a$05$GIny7PaavzM8fcWL3ewJDeEFvTzd/L4lty7XxxclNeiFLfopL3rq2','PATIENT',5),
(6,'Mihai','Dumitrescu','07949996', 'chef@gmail.com','$2a$05$GIny7PaavzM8fcWL3ewJDeEFvTzd/L4lty7XxxclNeiFLfopL3rq2','PATIENT',6);

insert into admins (id) values
(1);

insert into doctor (id,clinic) values
(2, '10000'),
(3, '10000');

insert into patient (id,age,height_cm,weight_kg,gender) values
(4,32,154.7,54.3,'FEMALE'),
(5,54,176.4,78.9,'MALE'),
(6,46,162.2,88.6,'MALE');

insert into glucose_level (id,time_stamp,glucose_mg_per_dl,patient_id) values
(1,1681825650000,154.7,4),
(2,1681829250000,175.3,4),
(3,1681831230000,180.4,4),
(4,1681832430000,140.5,4),
(5,1681825650000,99.7,5),
(6,1681829250000,120.3,5),
(7,1681831230000,110.4,5),
(8,1681832430000,105.5,5),
(9,1681825650000,120.7,6),
(10,1681829250000,160.3,6),
(11,1681831230000,130.4,6),
(12,1681832430000,120.5,6);
