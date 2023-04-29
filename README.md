# landmarks
В данном приложении реализован справочник достопримечательностей с привязкой к городам и странам.
Связь Страна -> Города, Город -> Достопримечательности.
Можно создавать, редактировать, удалять каждый из объектов. При удалении города удаляются все его достопримечательности, при удалении страны, все ее города.
Можно получить списки всех сущностей, получить каждый объект по ID, получить список связанных с ID объектов.
*На данный момент проверки на совпадение элементов при создании новых объектов нет.
Все примеры запросов для тестирования лежат в корне проекта в папке Postman.
Использовалась база данных PostgreSQL
Для запуска приложения необхолимо скачать проект из Git-хранилища. 
Запустить его локально в своей среде разработки.
Подключить базу данных.
Команды создания базы данных и таблиц лежат в src/main/resources/database.

***
