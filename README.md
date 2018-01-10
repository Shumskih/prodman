# ManufacturersProducts
Study project. https://prodman.herokuapp.com -->

This is a study project. Used technologies: Java, SQL, Hibernate, Servlet, JSP, Maven, Tomcat, Git

Task Description.
Необходимо создать веб-приложение, которое позволяет выполнять CRUD операции над следующими объектами:
* * * * *  
Product:
UUID id;
String name;
BigDecimal price;
Manufacturer manufacturer;
* * * * *  
Manufacturer
UUID id;
String name;
Set<Product> products;
* * * * *  
В базе данных хранятся данные о производителях (Manufacturer) и товарах (Product). Каждый товар имеет одного производителя, и каждый производитель имеет набор товаров.

Страницы:
1. Manufacturers (список производителей + возможность создавать новых, редактировать и удалять созданных производителей) 
2. Products (список товаров + возможность создавать новых, редактировать и удалять созданные товары)

При создании товара выбор производителя реализовать с помощью выпадающего меню.

Результатом выполнения задания должен быть отдельный репозиторий с описанием задачи, подробными инструкциями по запуску приложения на локальной машине, файлами для инициализации и заполнения БД и ссылкой на развренутым приложением на heroku

Необходимо придерживаться паттерна MVC (model, view, controller).
Все классы должны быть грамотно разложены по пакетам и грамотно именованы (model, dao, controller).

Технологии:
Java, SQL, Hibernate, Servlet, JSP, Maven, Tomcat, Git.

Functionality of the application is to creates Manufacturers and associated with them Products.

How to run app on your local machine.

1. First install SQL database and change settings in main/java/resources/hibernate.cfg.xml suitable for your DB.
2. Use files main/java/resources/database/initDB.sql and populateDB.sql for initialization database and fill it by data. initDB.sql contains requests for creating tables and populateDB.sql contains requests for fill database by data.
2. Then run the app. ))
