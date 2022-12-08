create table brands
(
    id   int primary key auto_increment,
    name varchar(40) not null unique
);

create table categories
(
    id   int primary key auto_increment,
    name varchar(40) not null unique
);

create table reviews
(
    id           int primary key auto_increment,
    content      TEXT,
    rating       decimal(10, 2) not null,
    picture_url  varchar(80)    not null,
    published_at datetime       not null
);

create table products
(
    id                int primary key auto_increment,
    name              varchar(40)    not null,
    price             decimal(19, 2) not null,
    quantity_in_stock int,
    description       TEXT,
    brand_id          int            not null,
    constraint fk_products_brands
        foreign key (brand_id)
            references brands (id),
    review_id         int            not null,
    constraint fk_products_reviews
        foreign key (review_id)
            references reviews (id),
    category_id       int            not null,
    constraint fk_products_categories
        foreign key (category_id)
            references categories (id)
);

create table customers
(
    id            int primary key auto_increment,
    first_name    varchar(20) not null,
    last_name     varchar(20) not null,
    phone         varchar(30) not null unique,
    address       varchar(60) not null,
    discount_card bit         not null default 0
);

create table orders
(
    id             int primary key auto_increment,
    order_datetime datetime not null,
    customer_id    int      not null,
    constraint fk_orders_customers
        foreign key (customer_id)
            references customers (id)
);

CREATE TABLE `orders_products`
(
    `order_id`   INT,
    `product_id` INT,
    KEY `pk_orders_products` (`order_id`, `product_id`),
    CONSTRAINT `fk_orders_product_orders` FOREIGN KEY (`order_id`)
        REFERENCES `orders` (`id`),
    CONSTRAINT `fk_orders_products_products` FOREIGN KEY (`product_id`)
        REFERENCES `products` (`id`)
);

-- 2

insert into reviews (content, picture_url, published_at, rating)
select left(p.description, 15), reverse(p.name), (DATE '2010/10/10'), p.price / 8
from products as p
where id >= 5;

-- 3

UPDATE products AS p
set p.quantity_in_stock = p.quantity_in_stock - 5
where p.quantity_in_stock >= 60
  AND p.quantity_in_stock <= 70;

-- 4

delete c
from customers AS c
         LEFT JOIN orders o on c.id = o.customer_id
where customer_id is null;

-- 5

select id, name
from categories AS c
order by c.name desc;

-- 6

select id, brand_id, name, quantity_in_stock
from products AS p
where p.price > 1000
  and p.quantity_in_stock < 30
order by p.quantity_in_stock, id;

-- 7

select id, content, rating, picture_url, published_at
from reviews AS r
where (select r.content LIKE 'My%')
  and LENGTH(r.content) > 61
order by rating desc;

-- 8

select concat(c.first_name, ' ', c.last_name) AS 'full name', c.address, o.order_datetime
from customers AS c
         join orders o on c.id = o.customer_id
where year(o.order_datetime) <= 2018
order by `full name` DESC;

-- 9

select count(c.id) as 'items_count', c.name, sum(p.quantity_in_stock) as 'total_quantity'
from products AS p
         join categories c on c.id = p.category_id
group by c.id
order by items_count desc, total_quantity
limit 5;

-- 10

DELIMITER $$
create function udf_customer_products_count(name VARCHAR(30))
    returns int
    deterministic
begin
    declare product_count int;
    set product_count := (select count(c.id)
                          from customers c
                                   join orders o on c.id = o.customer_id
                                   join orders_products op on o.id = op.order_id
                          where c.first_name = name);
    return product_count;
end $$
delimiter ;

DELIMITER $$
CREATE PROCEDURE `udp_reduce_price`(`category_name` VARCHAR(50))
BEGIN
    UPDATE products p
        join reviews r on r.id = p.review_id
        JOIN categories c on c.id = p.category_id
    SET p.price = price * 0.70
    WHERE c.name = category_name
      AND r.rating < 4;
END $$
DELIMITER ;

call udp_reduce_price('Phones and tablets');