#DDL - create, alter, drop
#create --- database
#create database dbanme;
create database ecart;

use ecart;

#create table tbname(
#colname datatype constraint,
#.....
#);

create table products(
prodId int primary key,
prodName varchar(40) not null unique,
prodPrice double not null ,
brand char(10),
prodDesc varchar(50),
prodCategory char(15) not null,
prodAval boolean,
prodManfDate date not null
);

create table customers(
custId int primary key,
custName varchar(20) not null,
custSurname varchar(30),
custPhone bigint not null unique,
custEmail varchar(20) not null unique,
custAddr varchar(30)
);

#drop table 
drop table customers;

#drop database
drop database ecart;

#alter -- an changes with existing structure
#add(add an new col), modify(change datatype,size,constraint),
#drop(remove col), rename(col, table)

#alter table tbname ADD colname datatype constraints;
alter table customers add custdob date not null;

#alter table tbname MODIFY colname ? ?
# modifying datatype size of email
alter table customers modify custEmail varchar(25) not null unique;

#modifying constraint of addr
alter table customers modify custAddr varchar(30) not null;

#alter table tbname drop column colname;
alter table customers drop column custdob;

#alter table tbname rename column oldcolname to new colname (column)
alter table customers rename column custName to custFirstName;

#alter table tbname rename to newtbname (table)
alter table products rename to productInfo;

alter table customers add custdob date;

#DML --- manipulation
#insert, update, delete
#insert into tbname(cols) values();

#inserting one row
insert into productinfo(prodid,prodName,prodPrice,brand,
prodDesc,prodCategory,prodAval,prodManfDate)
 values(11,'XYZ M2',50000,'OnePlus','Smart phone',
 'Mobiles Phone',1,'2023-01-30');

#inserting multiple rows
insert into productinfo(prodid,prodName,prodPrice,brand,
prodDesc,prodCategory,prodAval,prodManfDate) values 
(12, 'Boat Rocker', 1000, 'Boat','Wireless& smart','Accessories',1,'2022-10-04'),
(13, 'X2 Smart Tv',67000,'Sony','LED Smart TV','Television',1,'2021-12-31'),
(14,'M2 LG AC',35000,'LG','latest ac,comfart','Home Appliances',1,'2023-04-20'),
(15,'intel i7 HP',68000,'HP','core latest gen','Laptop',0,'2020-12-20');

insert into productinfo(prodid,prodName,prodPrice,brand,
prodDesc,prodCategory,prodAval,prodManfDate) values 
(16, 'Boat Rocker1', 1000, 'Boat','Wireless& smart','Accessories',1,'2022-10-04'),
(17, 'X22 Smart Tv',67000,'Sony','LED Smart TV','Television',1,'2021-12-31'),
(18,'M LG AC',35000,'LG','latest ac,comfart','Home Appliances',1,'2023-04-20'),
(19,'intel i6 HP',68000,'HP','core latest gen','Laptop',1,'2020-12-20');

insert into productinfo(prodid,prodName,prodPrice,brand,
prodDesc,prodCategory,prodAval,prodManfDate) values 
(20, 'Boat Rockerrs', 1000, 'Boat','Wireless& smart','Accessories',1,'2022-10-04'),
(21, 'X1 Smart Tv',67000,'Sony','LED Smart TV','Television',1,'2021-12-31'),
(22,'M3 LG AC',35000,'LG','latest ac,comfart','Home Appliances',1,'2023-04-20'),
(23,'M4 Sony AC',35000,'SONy','core latest gen','Home Appliances',1,'2020-12-20');

#inserting in customers
insert into customers(custId, custFirstName, custSurname,custPhone
,custEmail, custAddr, custdob) values
(101,'tom','jack', 9865123545,'Tom@gmail.com', 'Dombivili','1999-10-07');
insert into customers values(102,' Abhishek','Jaiswal',932168857,'jaiswal02@gmail.com','kagljakj','2003-05-02');

#inserting multiple rows in customers
insert into customers(custId, custFirstName, custSurname,custPhone
,custEmail, custAddr, custdob) values
(103,'mamta','boga', 9865123005,'mamta@gmail.com', 'mumbai','1998-10-07'),
(104,'priya','gupta',987654321,'pri@gm.com','pune','2000-10-18'),
(105,'ram','kumar',876543210,'ram@gm','mumbai','1998-12-05');

#update tbname set colname='value' where colname;
#change existing value 
update customers set custAddr='vashi' where custId=105;

#delete from tbname where colname;
delete from productinfo where prodId=15;

#DQL/DRL - showing/fetch the data or resultset
#Select colname/* from tbname;

#showing all detaild from customers
select * from customers;

#showing only customers id,firstname,phone details
select custid, custfirstname, custphone from customers;

#showing all product details 
select * from productinfo limit 1,3;

#fetch customer details whose name is mamta
select * from customers where custfirstname='mamta';

#fetch customer details who stay in mumbai
select custfirstname, custphone,custemail from customers
where custaddr='mumbai';

#operators
#show product details of price 10k
select * from productinfo where prodprice=1100;

#show product details of price <40k
select * from productinfo where prodprice<40000;

#showing product details of price=40 & greater
select * from productinfo where prodprice>=40000;

#select product details of mobile phone & price is 50k
select * from productinfo where prodcategory='mobiles phone'
and prodprice=50000;

#select product details of mobile phone or price is 50k
select * from productinfo where prodcategory='mobiles phone'
or prodprice=50000;

#fetch customer details who do not stay in mumbai
select * from customers where custaddr !='mumbai';
  #or
select * from customers where custaddr <>'mumbai';

#give discount 5% on product price
select prodName, prodPrice as 'totalPrice', prodPrice-(prodPrice*0.05) 
as 'discountPrice' from productInfo;

#give an delivery charges on product of Rs.500
select prodName, prodPrice, prodPrice+500 as 'totalAmount'
 from productInfo;
 
 #add an 18% tax on product 
 select prodName, prodPrice, prodPrice*0.18 as 'TaxAmt',
 prodPrice + (prodPrice*0.18) as 'PayableAmount'
 from ProductInfo;

#order by -- sorting in asc or desc
select * from customers order by custAddr desc;

# range on product price
select * from productInfo where prodPrice 
between 20000 and 50000;

select * from customers where custdob between
'2000-01-01' and '2003-12-31';

#list --- instead of multiple or 
select * from productInfo where prodPrice in(10000,20000,35000);

#group by -- collection of similar things
select prodCategory from productInfo group by prodCategory;

#aggregrate function - count, sum, min, max, avg
#count of total products
select count(prodId) from productInfo;

#max price of product
select max(prodPrice) from productInfo;

#count of product according to category
select prodCategory, count(prodId) from productInfo
group by prodCategory;

#count, sum, max, min of product according to category
select prodCategory, count(prodId), sum(prodPrice), max(prodPrice),
min(prodPrice) from productInfo group by prodCategory;

create table orderss(
oid int primary key,
prodName varchar(30),
totalPrice double,
orderDate date,
orderStatus varchar(20),
customerId int
);

#add foreign key 
alter table orderss ADD Foreign key(customerId)
references Customers(custId); 

insert into orderss values
(1, 'XYZ M2',50000,'2023-05-10','delivered',103),
(2, 'Boat Rockers',800,'2023-07-23','in process', 101),
(3, 'ABCC S1',40000,'2023-01-21','cancelled',103),
(4, 'M2 LG AC',43000,'2023-06-30','out for delivery',105);
#joins
#select cols from table1 joiningtype table2 condition;

#fetch orderdetails of customer who placed orders
select custId,custFirstName, custPhone, prodName, orderStatus
from customers inner join orderss where custId = customerId;
#or
select c.custId,c.custFirstName, c.custPhone, o.prodName,
o.orderStatus from customers c inner join orderss o where
c.custId = o.customerId;

#fetch orderdetails of customer who havent placed orders
select c.custId,c.custFirstName, c.custPhone, o.prodName,
o.orderStatus from customers c left join orderss o
on c.custId = o.customerID 
where o.prodName is null;

select c.custId,c.custFirstName, c.custPhone, o.prodName,
o.orderStatus from customers c cross join orderss o ;


