select first_name from customer;


select * from customer;

select first_name || ' ' || last_name, email
from customer;

-- where clause examples
select last_name,first_name
from customer where first_name='Jamie';

select first_name,
	length(first_name) name_length
from
	customer
where 
	first_name like 'A%' and 
	length(first_name) between 3 and 5
	
order by name_length desc;


select customer_id, sum(amount)
from payment
group by customer_id
having sum(amount) > 200;


select store_id,
	count(customer_id)
from customer
group by store_id
having count(customer_id)>300;

select customer_id,
	sum(amount)
from payment p 
group by customer_id
order by sum(amount) desc;

select first_name || ' ' || last_name full_name,
	sum(amount) amount
from payment p 
inner join customer using (customer_id)
group by full_name
order by amount;

select * from actor;

select * from customer;

-- JOIN EXERCISES

select f.film_id, title, inventory_id
from film f 
left join inventory 
	on inventory.film_id = f.film_id 
order by title;

-- we can negate this by adding a where clause 
select f.film_id, title, inventory_id
from film f 
left join inventory i 
	on i.film_id = f.film_id
where i.film_id is null 
order by title;
--checking for null negates the table
--above because null in the context above stands
--for a film with no inventory_id
-------USING clause
--to shorten the above even more you 
-- can introduce the USING clause 
-- on film_id, like so:
select f.film_id, title, inventory_id
from film f 
left join inventory i 
	using (film_id)
where i.film_id is null 
order by title;
--RIGHT JOIN
select f.film_id, title, inventory_id
from film f 
right join inventory i 
	using (film_id)
order by title;

--INNER JOIN
select c.customer_id,
		first_name,
		last_name,
		amount,
		payment_date
	from customer c

	inner join payment p
		on p.customer_id = c.customer_id
	order by payment_date;