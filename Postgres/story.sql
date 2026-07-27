/*
CREATE TABLE story(
	story_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	author_id BIGINT,
	title VARCHAR(100),

	CONSTRAINT author_id_fk FOREIGN KEY (author_id) REFERENCES author(author_id) ON DELETE CASCADE
);
*/

INSERT INTO story(author_id, title)
	VALUES
		(1, 'Евгений Онегин'),
		(1, 'Капитанская дочка'),
		(2, 'Война и мир'),
		(2, 'Анна Каренина'),
		(3, 'Приключения Оливера Твиста'),
		(3, 'Большие надежды')