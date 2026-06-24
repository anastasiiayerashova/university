# Консольна система управління університетом (University Management System)

Застосунок є консольною CLI-системою для автоматизації процесів університету: ведення обліку студентів, викладачів, курсів, а також керування зарахуваннями, виставленням оцінок та розрахунком академічної успішності

> 💡 **Цей репозиторій налаштовано як шаблон (Template Repository)** > Ви можете використовувати його як готову архітектурну основу для створення власних консольних систем управління на Java. Для цього просто натисніть кнопку **"Use this template"** у верхній частині сторінки GitHub, щоб створити копію структури у своєму профілі

## 🚀 Функціонал програми

* **Студенти:** додавання, перегляд (з сортуванням *Bubble Sort* за ПІБ), оновлення, зміна статусів (`ACTIVE`, `ON_LEAVE` тощо), видалення та фільтрація за курсом чи статусом
* **Викладачі:** повний CRUD-цикл, використання посад через `Enum`
* **Курси:** створення та закріплення за викладачем, фільтрація за кредитами або викладачами
* **Зарахування (Enrollment):** прив'язка студентів до курсів, виставлення оцінок (`A`, `B`, `C`...), зміна статусів оплати через інтерфейс `Payable`
* **Звіти:** пошук по String API, список боржників по оплаті та автоматичний розрахунок **GPA** студента для виведення академічного транскрипту
* **Автоматичне наповнення даних (Data Seeding):** система містить вбудований генератор (`DataSeeder`), який при запуску автоматично створює 15 випадкових студентів, 3 викладачів, 4 курси та випадковим чином заліковує їх на предмети, виставляє оцінки та статус оплати. Це дозволяє миттєво тестувати сортування, пошук та звіти без ручного введення

## 🛠️ Технологічний стек

* **Мова:** Java (ООП: наслідування, інкапсуляція, поліморфізм)
* **Зберігання даних:** Робота виключно з динамічно керованими **масивами** в пам'яті (без використання `ArrayList` / `List`)
* **Збірка проєкту:** Maven
* **Обробка помилок:** Валідація через винятки (`IllegalArgumentException`) з виведенням зрозумілих повідомлень користувачу

## 📁 Структура проєкту

Проєкт структуровано за пакетами відповідно до архітектурних вимог:

```text
src/
 └── main/
      └── java/
           └── university/
                ├── Main.java                    # Головний клас із консольним меню та тестовими даними
                ├── entities/                    # Класи-сутності (моделі даних)
                │    ├── Person.java             # Базовий абстрактний клас (інкапсуляція ПІБ, email)
                │    ├── Student.java            # Клас студента (наслідує Person, містить статус та рік)
                │    ├── Teacher.java            # Клас викладача (наслідує Person, містить посаду)
                │    ├── Course.java             # Клас курсу (зв'язаний з викладачем)
                │    └── Enrollment.java         # Клас зарахування (зв'язок студента, курсу та оцінки)
                ├── enums/                       # Перерахування
                │    ├── Grade.java              # Оцінки (A, B, C, D, F, NA) та їхня вага для GPA
                │    ├── StudentStatus.java      # Статуси студента (ACTIVE, ON_LEAVE, EXPELLED...)
                │    └── TeacherPosition.java    # Посади викладачів (ASSISTANT, LECTURER...)
                ├── interfaces/                  # Інтерфейси
                │    └── Payable.java            # Інтерфейс для керування статусом оплати за курси
                ├── services/                    # Логіка та CRUD-операції (робота з масивами)
                │    ├── StudentService.java     # Керування студентами, пошук та фільтрація
                │    ├── TeacherService.java     # Керування викладачами
                │    ├── CourseService.java      # Керування курсами та фільтри за кредитами/викладачами
                │    └── EnrollmentService.java  # Логіка зарахувань, виставлення оцінок та оплати
                └── util/                        # Допоміжні утиліти
                     └── GPAUtils.java           # Розрахунок GPA та ручне сортування Bubble Sort за ПІБ
                     └── DataSeeder.java         # Автоматичний генератор випадкових тестових даних
 ```                    

## 💻 Як запустити проєкт

1. Склонуйте репозиторій:
   ```bash
   git clone https://github.com/anastasiiayerashova/university.git

2. Відкрийте проєкт у вашій IDE (наприклад, IntelliJ IDEA)

3. Перейдіть до файлу src/main/java/university/Main.java

4. Запустіть метод main (натисніть зелений трикутник Run)

## 👩‍💻 About the Author

**Anastasiia Yerashova** — Junior Backend Developer passionate about writing clean and efficient code, growing professionally, and collaborating with purpose-driven teams

> "I'm a Junior Backend Developer with a strong commitment to building reliable and maintainable solutions.
> I strive to grow professionally by taking on meaningful challenges and collaborating with motivated teams.
> I approach each task with responsibility and dedication, always aiming to deliver my best work.
> My adaptability and eagerness to learn help me stay effective in dynamic, fast-paced environments"

## 📫 Contact

- [LinkedIn](www.linkedin.com/in/anastasia-yerashova)
- [GitHub](https://github.com/anastasiiayerashova)
- Email: yerashova.a@gmail.com

> Thank you for checking out the project! If you found it helpful or interesting, feel free to leave a ⭐ on the repository.