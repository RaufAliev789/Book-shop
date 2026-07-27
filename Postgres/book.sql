/*
CREATE TABLE book(
	book_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	story_id BIGINT,
	isbn VARCHAR(60),
	publisher VARCHAR(50),
	year_release SMALLINT,
	price DECIMAL,
	status VARCHAR(40),

	CONSTRAINT story_id_fk FOREIGN KEY (story_id) REFERENCES story (story_id) ON DELETE CASCADE
)
*/

INSERT INTO book(story_id, isbn, publisher, year_release, price, status)
	VALUES
		(1, '978-5-389-08846-7', 'Азбука', 2023, 350, 'AVAILABLE'),
		(1, '978-5-04-181594-3', 'Эксмо', 2024, 700, 'AVAILABLE'),
		(2, '978-5-389-04732-7', 'Азбука', 2022, 370, 'AVAILABLE'),
		(2, '978-5-389-04732-9', 'Эксмо', 2022, 370, 'AVAILABLE'),
		(3, '978-5-389-06256-6', 'Азбука', 2020, 300, 'AVAILABLE'),
		(3, '978-5-17-090468-6', 'Аст', 2025, 600, 'AVAILABLE'),
		(4, '978-5-89355-664-3', 'Ридерз Дайджест', 2026, 800, 'AVAILABLE'),
		(5, '978-5-17-133590-8', 'Аст', 2024, 420, 'AVAILABLE'),
		(6, '978-5-389-02083-2', 'Азбука', 2023, 360, 'AVAILABLE'),
		(6, '978-5-17-096334-8', 'Аст', 2025, 500, 'AVAILABLE')


		
		
		
		