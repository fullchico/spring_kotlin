CREATE TABLE customer_roles(
    customer_id int not null,
    role VARCHAR (50) not null,
    FOREIGN KEY (customer_id) references customer(id)
);

