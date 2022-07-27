-- pokemon_db.pokemon definition

-- Drop table

-- DROP TABLE pokemon_db.pokemon;

CREATE TABLE pokemon_db.pokemon (
	id int4 NOT NULL,
	pokemon varchar NOT NULL,
	CONSTRAINT pokemon_pkey PRIMARY KEY (id)
);


-- pokemon_db.users definition

-- Drop table

-- DROP TABLE pokemon_db.users;

CREATE TABLE pokemon_db.users (
	id bigserial NOT NULL,
	username varchar(40) NOT NULL,
	email varchar(100) NOT NULL,
	is_admin bool NOT NULL,
	passwd varchar(20) NOT NULL,
	salt bytea NULL,
	token_issued_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	registered_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT users_email_key UNIQUE (email),
	CONSTRAINT users_pkey PRIMARY KEY (id),
	CONSTRAINT users_username_key UNIQUE (username)
);


-- pokemon_db.banned_users definition

-- Drop table

-- DROP TABLE pokemon_db.banned_users;

CREATE TABLE pokemon_db.banned_users (
	user_id int8 NULL,
	ban_duration timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	ban_reason text NULL,
	banned_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT banned_users_user_id_fkey FOREIGN KEY (user_id) REFERENCES pokemon_db.users(id) ON DELETE CASCADE ON UPDATE CASCADE
);


-- pokemon_db.pokemon_comments definition

-- Drop table

-- DROP TABLE pokemon_db.pokemon_comments;

CREATE TABLE pokemon_db.pokemon_comments (
	id bigserial NOT NULL,
	pokemon_id int4 NULL,
	user_id int8 NULL,
	comment_content text NULL,
	is_flagged bool NULL,
	likes int4 NULL,
	posted_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT pokemon_comments_pkey PRIMARY KEY (id),
	CONSTRAINT pokemon_comments_pokemon_id_fkey FOREIGN KEY (pokemon_id) REFERENCES pokemon_db.pokemon(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT pokemon_comments_user_id_fkey FOREIGN KEY (user_id) REFERENCES pokemon_db.users(id) ON DELETE CASCADE ON UPDATE CASCADE
);


-- pokemon_db.pokemon_fanart definition

-- Drop table

-- DROP TABLE pokemon_db.pokemon_fanart;

CREATE TABLE pokemon_db.pokemon_fanart (
	id bigserial NOT NULL,
	pokemon_id int4 NULL,
	user_id int8 NULL,
	image text NULL,
	is_flagged bool NULL,
	uploaded_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT pokemon_fanart_pkey PRIMARY KEY (id),
	CONSTRAINT pokemon_fanart_pokemon_id_fkey FOREIGN KEY (pokemon_id) REFERENCES pokemon_db.pokemon(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT pokemon_fanart_user_id_fkey FOREIGN KEY (user_id) REFERENCES pokemon_db.users(id) ON DELETE CASCADE ON UPDATE CASCADE
);


-- pokemon_db.pokemon_wishlists definition

-- Drop table

-- DROP TABLE pokemon_db.pokemon_wishlists;

CREATE TABLE pokemon_db.pokemon_wishlists (
	pokemon_id int4 NULL,
	user_id int8 NULL,
	created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT pokemon_wishlists_pokemon_id_fkey FOREIGN KEY (pokemon_id) REFERENCES pokemon_db.pokemon(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT pokemon_wishlists_user_id_fkey FOREIGN KEY (user_id) REFERENCES pokemon_db.users(id) ON DELETE CASCADE ON UPDATE CASCADE
);


-- pokemon_db.fanart_comments definition

-- Drop table

-- DROP TABLE pokemon_db.fanart_comments;

CREATE TABLE pokemon_db.fanart_comments (
	id bigserial NOT NULL,
	fanart_id int8 NULL,
	user_id int8 NULL,
	comment_content text NULL,
	likes int4 NULL,
	posted_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT fanart_comments_pkey PRIMARY KEY (id),
	CONSTRAINT fanart_comments_fanart_id_fkey FOREIGN KEY (fanart_id) REFERENCES pokemon_db.pokemon_fanart(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fanart_comments_user_id_fkey FOREIGN KEY (user_id) REFERENCES pokemon_db.users(id) ON DELETE CASCADE ON UPDATE CASCADE
);