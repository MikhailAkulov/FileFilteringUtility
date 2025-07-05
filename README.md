## Утилита фильтрации содержимого файлов

---

### Описание:

При запуске утилиты в командной строке подается несколько файлов, содержащих в перемешку целые числа, строки и 
вещественные числа. В качестве разделителя используется перевод строки. Строки из файлов читаются по очереди в 
соответствии с их перечислением в командной строке.

Задача утилиты записать разные типы данных в разные файлы. Целые числа в один выходной файл, вещественные в другой, 
строки в третий. По умолчанию файлы с результатами располагаются в текущей папке с именами integers.txt, ﬂoats.txt, 
strings.txt.

Помимо исходных файлов, при запуске утилиты, в командной строке можно передавать следующие `флаги / опции`, влияющие на
ход и результат работы утилиты:
1. Опция `-o` задаёт путь для записи выходных файлов;
2. Опция `-p` задает префикс имен выходных файлов;
    
    Например: `-o /some/path -p result_` задают вывод в файлы `/some/path/result_integers.txt, 
/some/path/result_strings.txt` и тд.

3. Опция `-a` задаёт режим добавления в существующие файлы. По умолчанию файлы результатов перезаписываются;

   Файлы с результатами создаются по мере необходимости. Если какого-то типа данных во входящих файлах нет, то и исходящий файл, который будет заведомо пустым, создаваться не будет.

4. Опции вывода статистики по обработанным входящим данным. Статистика поддерживается двух видов: краткая и полная. 
Выбор статистики производится опциями `-s` и `-f` соответственно. 

   4.1. Краткая статистика содержит только количество элементов записанных в исходящие файлы;

   4.2. Полная статистика для чисел дополнительно содержит минимальное и максимальное значения, сумма и среднее;
   
   4.3. Полная статистика для строк, помимо их количества, содержит также размер самой короткой строки и самой длинной;

Статистика по каждому типу отфильтрованных данных выводится в консоль перед завершением работы утилиты.

---

### Структура утилиты:

1. Ключевая сущность - класс [ProgramConfig](https://github.com/MikhailAkulov/FileFilteringUtility/blob/main/src/main/java/org/example/ProgramConfig.java), поля которого описывают конфигурацию.
2. В первую очередь начинает работу [InputParameterHandler](https://github.com/MikhailAkulov/FileFilteringUtility/blob/main/src/main/java/org/example/InputParameterHandler.java) с единственным методом `configure()`, который анализирует массив входящих аргументов и передавая их конструктору `ProgramConfig` возвращает новый экземпляр конфигурации программы для исполнения.
3. [UtilFileReader](https://github.com/MikhailAkulov/FileFilteringUtility/blob/main/src/main/java/org/example/UtilFileReader.java) с помощью метода `readFiles()` читает строки из списка файлов по очереди в соответствии с их перечислением в командной строке. 
Для каждой считанной строки вызывает метод `distribute()` класса `StringAllocator`.
4. [StringAllocator](https://github.com/MikhailAkulov/FileFilteringUtility/blob/main/src/main/java/org/example/StringAllocator.java) содержит статические списки требующихся типов данных, а метод `distribute()` заполняет эти списки соответсвующими данными, анализируя полученную строку.
5. [UtilFileWriter](https://github.com/MikhailAkulov/FileFilteringUtility/blob/main/src/main/java/org/example/UtilFileWriter.java) методом `writingProcess()` инициализирует полный цикл процедур записи выходных файлов, исходя из текущей конфигурации утилиты.
6. [StatisticsGenerator](https://github.com/MikhailAkulov/FileFilteringUtility/blob/main/src/main/java/org/example/StatisticsGenerator.java) содержит методы для анализирования списков `StringAllocator` и формирования на основе их статистических данных. 
Метод `showStatistics()`, в зависимости от текущей конфигурации программы, выводит требующийся вид статистики.
7. Точка входа - [Main](https://github.com/MikhailAkulov/FileFilteringUtility/blob/main/src/main/java/org/example/Main.java)

---

### Инструкция по запуску

Запустить утилиту можно несколькими способами:
1. Посредством запуска метода `main` в [Точке входа](https://github.com/MikhailAkulov/FileFilteringUtility/blob/main/src/main/java/org/example/Main.java). 
Предварительно потребуется указать `Program arguments` в настройках `Run/Debug Configurations`;
2. Запустить [jar](https://github.com/MikhailAkulov/FileFilteringUtility/blob/main/out/artifacts/fileFilterUtil_jar/fileFilterUtil.jar) - файл из командной строки с указанием требующейся конфигурации программы, например: `java -jar fileFilterUtil.jar -s -a -p sample- in1.txt in2.txt`

- **версия Java: 17**
- **система сборки: Maven (version: 3.9.5)**
- [pom.xml](https://github.com/MikhailAkulov/FileFilteringUtility/blob/main/pom.xml)
