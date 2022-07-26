--create schema pokemon_db;

create table pokemon ( 
	id int primary key not null,
	pokemon varchar not null
);

create table users (
	id serial8 primary key,
	username varchar(40) not null unique,
	email varChar(100) not null unique,
	is_admin boolean not null,
	passwd varChar(20) not null,
	salt bytea,
	token_issued_at timestamp default current_TIMESTAMP,
	registered_at timestamp default current_TIMESTAMP
);

create table pokemon_comments (
	id serial8 primary key,
	pokemon_id int references pokemon  on delete cascade,
	user_id int8 references users on delete cascade,
	comment_content text,
	is_flagged boolean,
	likes int,
	posted_at timestamp default current_TIMESTAMP
);

create table pokemon_wishlists (
	pokemon_id int references pokemon on delete cascade,
	user_id int8 references users on delete cascade,
	created_at timestamp default current_TIMESTAMP
);

create table pokemon_fanart ( 
	id serial8 primary key,
	pokemon_id int references pokemon on delete cascade,
	user_id int8 references users on delete cascade,
	image text,
	is_flagged boolean,
	uploaded_at timestamp default current_TIMESTAMP
);

create table banned_users (
	user_id int8 references users on delete cascade,
	ban_duration timestamp default current_TIMESTAMP,
	ban_reason text,
	banned_at timestamp default current_TIMESTAMP
);

create table fanart_comments (
	id serial8 primary key,
	fanart_id int8 references pokemon_fanart on delete cascade,
	user_id int8 references users on delete cascade,
	comment_content text,
	likes int,
	posted_at timestamp default current_TIMESTAMP
);


