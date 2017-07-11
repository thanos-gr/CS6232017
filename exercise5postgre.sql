CREATE DATABASE "EXERCISE4";

\connect "EXERCISE4";

Create table Product( prod_id    char(30), pname      varchar(30), price      decimal);


ALTER TABLE Product ADD PRIMARY KEY(prod_id);

ALTER TABLE Product add constraint price_greater_than_zero CHECK(price > 0);


Create table Depot( dep_id  char(5), addr varchar(30), volume int);

ALTER TABLE Depot ADD PRIMARY KEY(dep_id);

ALTER TABLE Depot ADD CONSTRAINT volume_positive CHECK (volume >0);


Create table Stock(
prod_id char(30),
dep_id  char(5),
 quantity int);


ALTER TABLE Stock ADD PRIMARY KEY(prod_id,dep_id);



INSERT INTO Product (prod_id, pname,price) VALUES ('p1','tape',2.5), ('p2','tv',250), ('p3','vcr',80);

INSERT INTO Depot (dep_id, addr,volume) VALUES ('d1','New York',9000), ('d2','Syracuse',6000), ('d3','New York',2000);

INSERT INTO Stock (prod_id, dep_id,quantity) VALUES ('p1','d1',1000),('p1','d2',-100),('p1','d4',1200),('p3','d1',3000),('p3','d4',2000),('p2','d4',2000), ('p2','d1',-400), ('p2','d2',2000);


 
select prod_id from product where pname like 'p%' and price < 300;
select p.pname from stock s, product p where s.prod_id = p.prod_id and s.dep_id = 'd2';
select pname from product where prod_id in (select prod_id from stock where dep_id = 'd2');
select p.prod_id, p.pname from stock s, product p where p.prod_id = s.prod_id and s.quantity <= 0;
select prod_id, pname from product where prod_id in (select prod_id from stock where quantity <= 0);
select addr from stock s, depot d where s.dep_id = d.dep_id and prod_id = 'p1';
select addr from depot where dep_id in (select dep_id from stock where prod_id = 'p1');
select addr from depot d where exists (select 1 from stock where prod_id = 'p1' and dep_id = d.dep_id);
select prod_id from product where price >= 250 intersect select prod_id from product where price <= 400;
select prod_id from product where price >= 250 and price <= 400;
select count(distinct prod_id) from stock where quantity <= 0;
select avg(price) from product where prod_id in (select prod_id from stock where dep_id = 'd2');
select dep_id from depot where volume = (select max(volume) from depot);
select prod_id, sum(quantity) from stock group by prod_id;
select pname from product where prod_id in (select prod_id from stock group by prod_id having count(dep_id) >= 3);
select distinct s1.prod_id from stock s1, stock s2, stock s3 where s1.prod_id = s2.prod_id and s1.prod_id = s3.prod_id and s1.dep_id <> s2.dep_id and s1.dep_id <> s3.dep_id and s2.dep_id <> s3.dep_id;
select pname from product where prod_id in (select prod_id from stock group by prod_id having count(dep_id) = (select count(*) from depot));
select pname from product where prod_id in (select prod_id from stock group by prod_id having count(dep_id) = (select count(dep_id) from depot));
