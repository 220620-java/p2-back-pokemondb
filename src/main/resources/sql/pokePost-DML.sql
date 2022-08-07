-- users dml
insert into users values (default, 'Barry912', 'barry912@revature.net', true, 'password', null, default, default);

-- pokemon_fanart dml
insert into pokemon_fanart values (default, null, 19, 'A Wild Pikachu', 'pikachu, BarritoN78', 'images/sad_pikachu.png', 0, 0, default, default);
insert into pokemon_fanart values (default, null, 19, 'Imma Firin ma Lazar', 'tyranitar, BarritoN78', 'images/topFanTile.jpg', 0, 0, default, default);
insert into pokemon_fanart values (default, null, 19, 'Spheal With It!', 'spheal, BarritoN78', 'images/fanart/spheal.png', 0, 0, default, default);
insert into pokemon_fanart values (default, null, 19, 'Bad Spheal', 'spheal, BarritoN78', 'images/fanart/bad_spheal.jpg', 0, 0, default, default);
insert into pokemon_fanart values (default, null, 19, 'Battle Ready Blastoise', 'blastoise, BarritoN78', 'images/fanart/blastoise.jpg', 0, 0, default, default);
insert into pokemon_fanart values (default, null, 19, 'Bulbasaur in Their Element', 'bulbasaur, BarritoN78', 'images/fanart/bulbasaur.jpg', 0, 0, default, default);
insert into pokemon_fanart values (default, null, 19, 'Charizard Would Be Proud', 'charmander, BarritoN78', 'images/fanart/charmander.png', 0, 0, default, default);
insert into pokemon_fanart values (default, null, 19, 'Squirtle: The Support Heavy', 'squirtle, charmander, BarritoN78', 'images/fanart/charmanderSquirtle.jpg', 0, 0, default, default);
insert into pokemon_fanart values (default, null, 19, 'The Eevilution', 'eevie, BarritoN78', 'images/fanart/eevie.jpg', 0, 0, default, default);
insert into pokemon_fanart values (default, null, 19, 'Mew Too Cute', 'mew, BarritoN78', 'images/fanart/mew.jpg', 0, 0, default, default);
insert into pokemon_fanart values (default, null, 19, 'Mewtwo Plus Ultra', 'mewtwo, BarritoN78', 'images/fanart/mewtwo.jpg', 0, 0, default, default);
insert into pokemon_fanart values (default, null, 19, 'To Battle Pikachu!', 'pikachu, BarritoN78', 'images/fanart/pikachu_knight.png', 0, 0, default, default);insert into pokemon_fanart values (default, null, 1, 'Squirtle: The Support Heavy', 'squirtle, charmander, BarritoN78', 'images/fanart/charmanderSquirtle.jpg', 0, 0, default, default);
insert into pokemon_fanart values (default, null, 19, 'An Intense Battle', 'pikachu, eevie, BarritoN78', 'images/fanart/pikachuEevie.jpg', 0, 0, default, default);
insert into pokemon_fanart values (default, null, 19, 'A Lazy Day', 'snorlax, BarritoN78', 'images/fanart/snorlax.png', 0, 0, default, default);
insert into pokemon_fanart values (default, null, 19, 'Squirtle', 'squirtle, BarritoN78', 'images/fanart/squirtle.jpg', 0, 0, default, default);



-- fanart_comments dml
insert into fanart_comments values (default, 1, 1, 'Y he so sad', 0, 0, false, default);
insert into fanart_comments values (default, 1, 1, 'who hurt you pikachu', 0, 0, false, default);
insert into fanart_comments values (default, 2, 1, 'Shoop de Whoop!', 0, 0, false, default);
insert into fanart_comments values (default, 2, 1, 'One powerful boi', 0, 0, false, default);

select * from fanart_comments;

select * from report_art_comm;

select * from pokemon_fanart;

select * from pokemon_comments pc;

select * from users;
