----Table Creation
create table wordbank
	( Word_ID	int not null,
	Word		VarChar(50),
	Characters	VarChar(50),
	Clue		VarChar(200),
	Length		int,
	primary key (Word_ID)
	);

-----Seeded Data
insert into wordbank values (1, 'Cat', 'C,a,t', 'Domestic Feline', 3);
insert into wordbank values (2, 'Dog', 'D,o,g', 'A mans best friend', 3);
insert into wordbank values (3, 'Pan', 'P,a,n', 'Used for cooking', 3);
insert into wordbank values (4, 'Hat', 'H,a,t', 'Head accesory', 3);
insert into wordbank values (5, 'Dig', 'D,i,g', 'Taking out dirt', 3);
insert into wordbank values (6, 'Air', 'A,i,r', 'What we breath', 3);
insert into wordbank values (7, 'Can', 'C,a,n', 'They store food', 3);
insert into wordbank values (8, 'Dam', 'D,a,m', 'Used to create energy', 3);
insert into wordbank values (9, 'Art', 'A,r,t', 'Painting is considered', 3);
insert into wordbank values (10, 'Age', 'A,g,e', 'It increases by 1 each year', 3);
insert into wordbank values (11, 'Ace', 'A,c,e', 'Best or worst card in card games', 3);
insert into wordbank values (12, 'Add', 'A,d,d', 'One of the 4 arithmetic operations', 3);
insert into wordbank values (13, 'Ice', 'I,c,e', 'It keeps things cool', 3);
insert into wordbank values (14, 'Fax', 'F,a,x', 'Old way of sending documents electronically', 3);
insert into wordbank values (15, 'Bio', 'B,i,o', 'Story of someone', 3);
insert into wordbank values (16, 'Axe', 'A,x,e', 'Used by Lumberjacks', 3);
insert into wordbank values (17, 'Sun', 'S,u,n', 'Lights up the solar system', 3);
insert into wordbank values (18, 'Bet', 'B,e,t', 'Monetary compromise', 3);
insert into wordbank values (19, 'Box', 'B,o,x', 'Used to store things', 3);
insert into wordbank values (20, 'Car', 'C,a,r', 'Main method of transportation', 3);
insert into wordbank values (21, 'Dye', 'D,y,e', 'Used to changed color', 3);
insert into wordbank values (22, 'Eat', 'E,a,t', 'We cant survive without it', 3);
insert into wordbank values (23, 'Fun', 'F,u,n', 'You play games to have', 3);
