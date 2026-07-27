/*
CREATE TABLE author(
	author_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	firstname VARCHAR(30),
	lastname VARCHAR(30),
	birthday DATE
*/

INSERT INTO author(firstname, lastname, birthday)
	VALUES
		('Александр', 'Пушкин', '1799-06-06'),
		('Лев',	'Толстой',	'1828-09-09'),
		('Чарльз',	'Диккенс', '1812-02-07')