#job4j_cinema

Сервис - Кинотеатр.

Cайт по покупки билетов в кино.

Используется:
1. JDK17
2. Maven 3.8
3. Spring boot 2.5.2
4. PostgreSQL 14.2
5. Thymeleaf.
6. Bootstrap.
7. JDBC.
8. MVC.

Функционал:
1. Аутентификация.
2. Регистрация.
3. CRUD-операции с JDBC и PostgreSQL.

Запуск проекта:
1. Создание БД - "cinema", схема в job4j_cinema/db/scripts/
2. Собрать проект - "mvn package"
3. Запуск проекта - "java -jar job4j_cinema-1.0.jar"

Главная страница:
![](https://raw.github.com/markmw/job4j_cinema/master/src/main/resources/images/main page.png)

Страница регистрации:
![](https://raw.github.com/markmw/job4j_cinema/master/src/main/resources/images/registration page.png)

Страница авторизации:
![](https://raw.github.com/markmw/job4j_cinema/master/src/main/resources/images/sign in page.png)

Результаты:
Успешная покупка билета:
![](https://raw.github.com/markmw/job4j_cinema/master/src/main/resources/images/success page.png)
Неуспешная покупка:
![](https://raw.github.com/markmw/job4j_cinema/master/src/main/resources/images/fail page.png)