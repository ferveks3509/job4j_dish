# job4j_dish

## Микросервисный проект - Доставка еды "Голодный волк".
Общение происходит через брокера сообщений. Принятие ордера на заказ. Уведомление о статусе блюда.
## Микросервисы
+ [kitchen](https://github.com/ferveks3509/job4j_kitchen) - Кухня
+ [order](https://github.com/ferveks3509/job4j_order) - Управление блюдом
+ [notification](https://github.com/ferveks3509/job4j_notification) - Уведомления

### API dish
`POST /dish/` - создать блюдо

`PUT /dish/{id}` - обновить блюдо

`DELETE /dish/{id}` - удалить блюдо

`GET /dish/` - загрузить все блюда

`GET /dish/{id}` - загрузить блюдо по id

## Технологии

+ **Java 18**
+ **Spring (Boot, Data, WEB)**
+ **PostgreSQL**
+ **Maven**
