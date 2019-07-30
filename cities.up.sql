CREATE TABLE cities (
id bigserial NOT NULL PRIMARY KEY,
nodes_id bigint,
name text,
is_in text,
country text,
continent text
);

ALTER TABLE users OWNER TO osmcities;

