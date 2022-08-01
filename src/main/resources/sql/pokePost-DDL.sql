<<<<<<< HEAD:pokePost-DDL.sql
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
	reports int4 NULL,
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
	title text NULL,
	tags text NULL,
	image text NULL,
	likes int4 NULL,
	reports int4 NULL,
	is_flagged bool NULL DEFAULT false,
	uploaded_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT pokemon_fanart_pkey PRIMARY KEY (id),
	CONSTRAINT pokemon_fanart_pokemon_id_fkey FOREIGN KEY (pokemon_id) REFERENCES pokemon_db.pokemon(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT pokemon_fanart_user_id_fkey FOREIGN KEY (user_id) REFERENCES pokemon_db.users(id) ON DELETE CASCADE ON UPDATE CASCADE
);

select * from pokemon_fanart


-- pokemon_db.pokemon_wishlists definition

-- Drop table

-- DROP TABLE pokemon_db.pokemon_wishlists;

CREATE TABLE pokemon_db.pokemon_wishlists (
	pokemon_id int4 NULL,
	user_id int8 NULL,
	on_wishlist bool NULL,
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
	reports int4 NULL,
	is_flagged bool null default false,
	posted_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT fanart_comments_pkey PRIMARY KEY (id),
	CONSTRAINT fanart_comments_fanart_id_fkey FOREIGN KEY (fanart_id) REFERENCES pokemon_db.pokemon_fanart(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fanart_comments_user_id_fkey FOREIGN KEY (user_id) REFERENCES pokemon_db.users(id) ON DELETE CASCADE ON UPDATE CASCADE
);

--pokemon_db.rate_fanart definition

--Drop table

--DROP TABLE pokemon_db.rate_fanart

CREATE TABLE pokemon_db.rate_fanart (
	id bigserial NOT NULL,
	fanart_id int8 NULL,
	user_id int8 NULL,
	is_liked bool NULL,
	CONSTRAINT rate_fanart_pkey PRIMARY KEY (id),
	CONSTRAINT rate_fanart_fanart_id_fkey FOREIGN KEY (fanart_id) REFERENCES pokemon_db.pokemon_fanart(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT rate_fanart_user_id_fkey FOREIGN KEY (user_id) REFERENCES pokemon_db.users(id) ON DELETE CASCADE ON UPDATE CASCADE
);

--pokemon_db.report_fanart definition

--Drop table

--DROP TABLE pokemon_db.report_fanart

CREATE TABLE pokemon_db.report_fanart (
	id bigserial NOT NULL,
	fanart_id int8 NULL,
	user_id int8 NULL,
	is_reported bool NULL,
	report_reason text NULL,
	CONSTRAINT report_fanart_pkey PRIMARY KEY (id),
	CONSTRAINT report_fanart_fanart_id_fkey FOREIGN KEY (fanart_id) REFERENCES pokemon_db.pokemon_fanart(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT report_fanart_user_id_fkey FOREIGN KEY (user_id) REFERENCES pokemon_db.users(id) ON DELETE CASCADE ON UPDATE CASCADE
);

--pokemon_db.rate_art_comm definition

--Drop table

--DROP TABLE pokemon_db.rate_art_comm

CREATE TABLE pokemon_db.rate_art_comm (
	id bigserial NOT NULL,
	comment_id int8 NULL,
	user_id int8 NULL,
	is_liked bool NULL,
	CONSTRAINT rate_art_comm_pkey PRIMARY KEY (id),
	CONSTRAINT rate_art_comm_comment_id_fkey FOREIGN KEY (comment_id) REFERENCES pokemon_db.fanart_comments(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT rate_art_comm_user_id_fkey FOREIGN KEY (user_id) REFERENCES pokemon_db.users(id) ON DELETE CASCADE ON UPDATE CASCADE
);

--pokemon_db.report_art_comm definition

--Drop table

--DROP TABLE pokemon_db.report_art_comm

CREATE TABLE pokemon_db.report_art_comm (
	id bigserial NOT NULL,
	comment_id int8 NULL,
	user_id int8 NULL,
	is_reported bool NULL,
	report_reason text NULL,
	CONSTRAINT report_art_comm_pkey PRIMARY KEY (id),
	CONSTRAINT report_art_comm_comment_id_fkey FOREIGN KEY (comment_id) REFERENCES pokemon_db.fanart_comments(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT report_art_comm_user_id_fkey FOREIGN KEY (user_id) REFERENCES pokemon_db.users(id) ON DELETE CASCADE ON UPDATE CASCADE
);

--pokemon_db.rate_poke_comm definition

--Drop table

-- DROP TABLE pokemon_db.rate_poke_comm

CREATE TABLE pokemon_db.rate_poke_comm (
	id bigserial NOT NULL,
	comment_id int8 NULL,
	user_id int8 NULL,
	is_liked bool NULL,
	CONSTRAINT rate_poke_comm_pkey PRIMARY KEY (id),
	CONSTRAINT rate_poke_comm_comment_id_fkey FOREIGN KEY (comment_id) REFERENCES pokemon_db.pokemon_comments(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT rate_poke_comm_user_id_fkey FOREIGN KEY (user_id) REFERENCES pokemon_db.users(id) ON DELETE CASCADE ON UPDATE CASCADE
);

--pokemon_db.report_poke_comm definition

--Drop table

-- DROP TABLE pokemon_db.report_poke_comm

CREATE TABLE pokemon_db.report_poke_comm (
	id bigserial NOT NULL,
	comment_id int8 NULL,
	user_id int8 NULL,
	is_reported bool NULL,
	report_reason text NULL,
	CONSTRAINT report_poke_comm_pkey PRIMARY KEY (id),
	CONSTRAINT report_poke_comm_comment_id_fkey FOREIGN KEY (comment_id) REFERENCES pokemon_db.pokemon_comments(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT report_poke_comm_user_id_fkey FOREIGN KEY (user_id) REFERENCES pokemon_db.users(id) ON DELETE CASCADE ON UPDATE CASCADE
=======
-- pokemon_db.pokemon definition

-- Drop table

-- DROP TABLE pokemon_db.pokemon;

CREATE TABLE pokemon_db.pokemon (
	id int4 NOT NULL,
	"name" varchar NOT NULL,
	gen int4 NULL,
	sprite text NULL,
	CONSTRAINT pokemon_pkey PRIMARY KEY (id)
);


-- pokemon_db.users definition

-- Drop table

-- DROP TABLE pokemon_db.users;

CREATE TABLE pokemon_db.users (
	id bigserial NOT NULL,
	username varchar(40) NOT NULL,
	email varchar(100) NOT NULL,
	is_admin bool NOT NULL DEFAULT false,
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
	reports int4 NULL,
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
	title text NULL,
	tags text NULL,
	image text NULL,
	likes int4 NULL,
	reports int4 NULL,
	is_flagged bool NULL DEFAULT false,
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
	on_wishlist bool NULL,
	created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT pokemon_wishlists_pokemon_id_fkey FOREIGN KEY (pokemon_id) REFERENCES pokemon_db.pokemon(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT pokemon_wishlists_user_id_fkey FOREIGN KEY (user_id) REFERENCES pokemon_db.users(id) ON DELETE CASCADE ON UPDATE CASCADE
);


-- pokemon_db.rate_fanart definition

-- Drop table

-- DROP TABLE pokemon_db.rate_fanart;

CREATE TABLE pokemon_db.rate_fanart (
	id bigserial NOT NULL,
	fanart_id int8 NULL,
	user_id int8 NULL,
	is_liked bool NULL,
	CONSTRAINT rate_fanart_pkey PRIMARY KEY (id),
	CONSTRAINT rate_fanart_fanart_id_fkey FOREIGN KEY (fanart_id) REFERENCES pokemon_db.pokemon_fanart(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT rate_fanart_user_id_fkey FOREIGN KEY (user_id) REFERENCES pokemon_db.users(id) ON DELETE CASCADE ON UPDATE CASCADE
);


-- pokemon_db.rate_poke_comm definition

-- Drop table

-- DROP TABLE pokemon_db.rate_poke_comm;

CREATE TABLE pokemon_db.rate_poke_comm (
	id bigserial NOT NULL,
	comment_id int8 NULL,
	user_id int8 NULL,
	is_liked bool NULL,
	CONSTRAINT rate_poke_comm_pkey PRIMARY KEY (id),
	CONSTRAINT rate_poke_comm_comment_id_fkey FOREIGN KEY (comment_id) REFERENCES pokemon_db.pokemon_comments(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT rate_poke_comm_user_id_fkey FOREIGN KEY (user_id) REFERENCES pokemon_db.users(id) ON DELETE CASCADE ON UPDATE CASCADE
);


-- pokemon_db.report_fanart definition

-- Drop table

-- DROP TABLE pokemon_db.report_fanart;

CREATE TABLE pokemon_db.report_fanart (
	id bigserial NOT NULL,
	fanart_id int8 NULL,
	user_id int8 NULL,
	is_reported bool NULL,
	report_reason text NULL,
	CONSTRAINT report_fanart_pkey PRIMARY KEY (id),
	CONSTRAINT report_fanart_fanart_id_fkey FOREIGN KEY (fanart_id) REFERENCES pokemon_db.pokemon_fanart(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT report_fanart_user_id_fkey FOREIGN KEY (user_id) REFERENCES pokemon_db.users(id) ON DELETE CASCADE ON UPDATE CASCADE
);


-- pokemon_db.report_poke_comm definition

-- Drop table

-- DROP TABLE pokemon_db.report_poke_comm;

CREATE TABLE pokemon_db.report_poke_comm (
	id bigserial NOT NULL,
	comment_id int8 NULL,
	user_id int8 NULL,
	is_reported bool NULL,
	report_reason text NULL,
	CONSTRAINT report_poke_comm_pkey PRIMARY KEY (id),
	CONSTRAINT report_poke_comm_comment_id_fkey FOREIGN KEY (comment_id) REFERENCES pokemon_db.pokemon_comments(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT report_poke_comm_user_id_fkey FOREIGN KEY (user_id) REFERENCES pokemon_db.users(id) ON DELETE CASCADE ON UPDATE CASCADE
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
	reports int4 NULL,
	is_flagged bool NULL DEFAULT false,
	posted_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT fanart_comments_pkey PRIMARY KEY (id),
	CONSTRAINT fanart_comments_fanart_id_fkey FOREIGN KEY (fanart_id) REFERENCES pokemon_db.pokemon_fanart(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fanart_comments_user_id_fkey FOREIGN KEY (user_id) REFERENCES pokemon_db.users(id) ON DELETE CASCADE ON UPDATE CASCADE
);


-- pokemon_db.rate_art_comm definition

-- Drop table

-- DROP TABLE pokemon_db.rate_art_comm;

CREATE TABLE pokemon_db.rate_art_comm (
	id bigserial NOT NULL,
	comment_id int8 NULL,
	user_id int8 NULL,
	is_liked bool NULL,
	CONSTRAINT rate_art_comm_pkey PRIMARY KEY (id),
	CONSTRAINT rate_art_comm_comment_id_fkey FOREIGN KEY (comment_id) REFERENCES pokemon_db.fanart_comments(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT rate_art_comm_user_id_fkey FOREIGN KEY (user_id) REFERENCES pokemon_db.users(id) ON DELETE CASCADE ON UPDATE CASCADE
);


-- pokemon_db.report_art_comm definition

-- Drop table

-- DROP TABLE pokemon_db.report_art_comm;

CREATE TABLE pokemon_db.report_art_comm (
	id bigserial NOT NULL,
	comment_id int8 NULL,
	user_id int8 NULL,
	is_reported bool NULL,
	report_reason text NULL,
	CONSTRAINT report_art_comm_pkey PRIMARY KEY (id),
	CONSTRAINT report_art_comm_comment_id_fkey FOREIGN KEY (comment_id) REFERENCES pokemon_db.fanart_comments(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT report_art_comm_user_id_fkey FOREIGN KEY (user_id) REFERENCES pokemon_db.users(id) ON DELETE CASCADE ON UPDATE CASCADE
>>>>>>> 643ca3d8472d72565bf2fcb7de414a4a2d36f408:src/main/resources/sql/pokePost-DDL.sql
);