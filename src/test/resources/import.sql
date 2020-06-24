
--DELETE ACCOUNT
insert into account (account_number, balance, currency) values ('2', 0.00, 'ARS');
insert into account (account_number, balance, currency) values ('34', 0.00, 'ARS');

--DELETE ACCOUNT WITH TRANSACTION
insert into account (account_number, balance, currency) values ('3', 0.00, 'DOLAR');
insert into transaction values (100, 100.00, now(),'test','CREDIT', '3' );

--CREATE TRANSACTION
insert into account values ('5', 0.00, 'DOLAR');

--CREATE TRANSACTION WITH DIFFERENT CURRENCY
insert into account values ('6', 0.00, 'EURO');

--LIMIT EURO 150, DOLAR 300, ARS 1000
insert into account values ('7', -150.00, 'EURO');
insert into account values ('8', -300.00, 'DOLAR');
insert into account values ('9', -1000.00, 'ARS');


insert into account values ('10', -150.00, 'EURO');




