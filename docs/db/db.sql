CREATE TABLE connection (
	id integer PRIMARY KEY AUTOINCREMENT,
	service_id integer not null references service(id),
	user_id integer not null references user(id),
	date datetime not null
);

CREATE TABLE service (
	id integer PRIMARY KEY AUTOINCREMENT,
	name text not null,
	cost double not null
);

CREATE TABLE invoice (
	id integer PRIMARY KEY AUTOINCREMENT,
	invoice double not null,
	user_id integer not null references user(id)
);

CREATE TABLE user (
	id integer PRIMARY KEY AUTOINCREMENT,
	name text not null,
	surname text not null,
	birth_date date not null,
	role boolean not null
);





