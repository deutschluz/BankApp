drop table if exists customers;
drop table if exists accounts;
drop table if exists transactions;

create table customers(
	customer_id serial primary key,
	firstN varchar(20),
	lastN varchar(20),
	email varchar(30),
	isEmployee varchar(10),
	passwd varchar(20)
);


insert into customers (customer_id, firstN, lastN, email,isEmployee,passwd)
values(default,'l','o','x','false','pass');

insert into customers (customer_id, firstN, lastN, email,isEmployee,passwd)
values(default,'admin','admin','admin@hatemail','true','admin');

select * from customers;

select c.customer_id, c.firstN, c.lastN, c.email, c.isEmployee, c.passwd from customers c where c.customer_id = 2;

create table accounts(
	acct_id serial primary key,
	balance dec(15,2) not null,
	status varchar(10),
	customer_id integer references customers (customer_id)
);

insert into accounts (acct_id, balance, status, customer_id)
values(default,1000.00,'active',1);

insert into accounts (acct_id, balance, status, customer_id)
values(default,1000.00,'active',2);

select * from accounts;

select a.acct_id, a.balance, a.status, a.customer_id from accounts a where a.acct_id = 1;

create table transactions(
	trans_id serial,
	status varchar(10),
	amt dec(15,2) not null,
	src_id integer references accounts(acct_id),
	dst_id integer references accounts(acct_id),
	primary key(src_id,dst_id)
);

insert into transactions (trans_id, status, amt, src_id, dst_id)
values(default,'accepted',100,1,1);

select * from transactions;
language plpgsql
as $$
begin 
	--subtracting amt from sender acct.
	update account
	set balance = balance - amount
	where id = sender;

	--adding amout to receiver acct.
	update account
	set balance = balance + amount
	where id = receiver;

	commit;
end;$$

call transfer(1,2,1000);

select * from account;