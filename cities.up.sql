DROP TABLE IF EXISTS cities;


CREATE TABLE cities (
id bigserial NOT NULL PRIMARY KEY,
nodes_id bigint,
name text,
lower_name text,
is_in text,
country text,
continent text
);

ALTER TABLE cities OWNER TO osmcities;


CREATE INDEX cities_name_idx ON cities(name);

CREATE INDEX cities_lowername_idx ON cities(lower_name);

