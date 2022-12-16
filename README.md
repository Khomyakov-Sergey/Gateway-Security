SPRING CLOUD GATEWAY + SECURITY(SPRING SECURITY + OAUTH2) + KEYCLOCK + SIMPLE APPLICATION
_________________________________________________________________________________________

Данный кластер представляет собой набор следующих сервисов на микросервисной архетектуре:
* Gateway(на основе SPRING CLOUD GATEWAY + OAUTH2-CLIENT) - представляет собой шлюз для проброса запросов в другие
   микросерисы с элементом авторизации. После того, как пользователь передаст свои учётные данные приложению 
   (Тип разрешения на авторизацию: учётные данные владельца ресурса), приложение запросит токен доступа у
   авторизационного сервера(keyclock).
   
* Keyclock(продукт с открытым кодом для реализации single sign-on с возможностью управления доступом) - представляет
   собой авторизационный сервер. В данном случае используется совместно с БД PostgreSQL взамен встроенной H2.
   
* Simple application - базовое приложение с несколькими endpoints для демонстрации функционала gateway.

Для настройки кластера необходимо выполнить следующие шаги:
1) В папке keyclock находится файл docker-compose.yml, c помощью которого необходимо поднять контейнеры keyclock
   и postgres. Для этого в терминале необходимо перейти в папку с docker-compose.yml и выполнить команду 
   **docker-compose up -d**.
   
2) Далее необходимо настроить keyclock. Для этого переходим по адресу http://localhost:8484/auth/ и выбираем 
   вкладку Administration Console. Keyclock предлагает нам авторизоваться. Вводим credentials, которые были указаны в
   файле docker-compose.yml.
   
3) Приступаем к созданию Realm. По умолчанию создается realm master. Для нашего случае создаем realm Clevertec.

4) После необходимо создать Сlient и User. Для этого в соответствующих вкладках создаем Client c именем **api** со следующими
   настройками: 
   - Access Type: confidential
   - OAuth 2.0 Device Authorization Grant Enabled: on
   - Service Accounts Enabled: on
   - Authorization Enabled: on  
   - Valid Redirect URIs: http://localhost:8080/login/oauth2/code/gateway
    и User c именем user. На вкладке Users->Credentials устанавливаем пароль для user. 
    
5) Копируем Secret для нашего Client api из вкладки Clients->Credentials и вставляем в наш gateway в файл настроек
   application.yml для поля client_secret.
   
6) Запустим gateway и simple-application
 
____________________________________________________________________________________________________________________

Теперь при обращении на gateway по адрессу http://localhost:8080/api/message или http://localhost:8080/api?name=Sergey
мы будем переадресованы на страницу авторизации, где необходимо ввести login и пароль нашего **user**, которого мы 
создали ранее. После успешно введенных credentials мы получим результат с simple-application. Попытка обратится к 
simple-application напрямую без передачи авторизационного токена будет возвращать 404 ошибку.

    


