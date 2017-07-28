create table person (
  person_id int PRIMARY KEY,
  first_name VARCHAR,
  last_name VARCHAR
);

insert into person(person_id, first_name, last_name)
  VALUES (1, 'Dmitriy', 'Dubson'),
         (2, 'John', 'Doe');