### Тестирование входа в систему интернет-банка
#### Запуск
 1. Запускаем Docker Toolbox.  
 Вам нужно запустить docker контейнер.
 Параметры прописаны в ```docker-compose.yml```.
 Volumes настроены таким образом, что sql схема запустится и на вашей локальной машине.
 Для запуска в терминале IDEA запустите команду   ```docker-compose up``` и дождитесь в строке лога:  
 ```X Plugin ready for connections```

 2. Подключаемся к базе данных. Открываем новую вкладку в терминале и запускаем команду:   ```docker-compose exec mysql mysql -u app -p app -v```
 3. Запускаем SUT Для этого открываем новую вкладку в терминале и используем команду:   ```java -jar artifacts/app-deadline.jar -P:jdbc.url=jdbc:mysql://192.168.99.100:3306/app -P:jdbc.user=app -P:jdbc.password=pass```.
 4. Теперь запускаем тесты стандартной командой ```gradlew clean test```. Они должны проходить.
 5. Если возникла необходимость повторного запуска тестов - перезапустите приложение вручную. В терминале с запущенным приложением нажмите ```Ctrl+C``` 
 и выполните заново команду ```java -jar artifacts/app-deadline.jar -P:jdbc.url=jdbc:mysql://192.168.99.100:3306/app -P:jdbc.user=app -P:jdbc.password=pass```
 Контейнер и базу перезапускать при этом нет необходимости - в тестах заложен метод очистки таблиц, чтобы SUT запускался повторно.
 
#### Окружение
IntelliJ IDEA 2020.2 EAP (Community Edition)
Build #IC-202.5958.24, built on June 25, 2020
Runtime version: 11.0.7+10-b944.13 amd64
VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
Windows 8.1 6.3
Docker Toolbox.