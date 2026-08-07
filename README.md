# Book Shop API

Backend для онлайн-книжного магазина: авторы, произведения, книги, бронирование.

## Стек
- Java 21, Spring Boot
- Spring Data JPA (Hibernate)
- PostgreSQL
- Maven

## Доменная модель
- **Author** — автор (имя, фамилия, дата рождения)
- **Story** — произведение (абстрактная единица, может иметь несколько изданий)
- **Book** — конкретное издание произведения (издательство, год, цена, статус: `AVAILABLE` / `RESERVED` / `SOLD`)

## Запуск локально

Требуется Java 21+ и Docker (для PostgreSQL).

1. Поднять БД:

docker compose up -d

Поднимет PostgreSQL на `localhost:5432` (база `postgres`, пользователь `postgres`, пароль `root` — дефолты, которые можно переопределить через переменные окружения `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USER`, `DB_PASSWORD`).

2. Запустить приложение:

./mvnw spring-boot:run

Приложение поднимется на `http://localhost:8080`.

## Документация API

После запуска доступна по адресу `http://localhost:8080/swagger-ui.html` — интерактивная документация с возможностью тестового вызова эндпоинтов прямо из браузера. JSON-спецификация — `http://localhost:8080/v3/api-docs`.
## API

### Получить автора по ID
```
GET /api/authors/{id}
```
```json
{
  "authorId": 1,
  "firstname": "Fyodor",
  "lastname": "Dostoevsky",
  "birthday": "1821-11-11"
}
```

### Список книг автора (с пагинацией)
```
GET /api/authors/{id}/books?pageSize=10&pageNumber=0
```
```json
[
  {
    "bookId": 101,
    "storyId": 5,
    "ISBN": "978-5-699-10456-0",
    "publisher": "Eksmo",
    "year": 2010,
    "price": 450,
    "status": "AVAILABLE"
  }
]
```

### Забронировать книгу
```
POST /api/books/{id}/reserve
```
- `200` — книга забронирована, тело — обновлённая книга
- `404` — книга не найдена
- `409` — книга уже забронирована или продана

## Формат ошибок
```json
{
  "error": "Entity not found",
  "message": "Not found book with id = 999",
  "timestamp": "2026-08-06T12:00:00"
}
```