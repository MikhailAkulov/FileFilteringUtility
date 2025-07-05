package org.example;

public class Main {

    public static void main(String[] args){
        try {
            // Определение текущей конфигурации утилиты
            ProgramConfig config = InputParameterHandler.configure(args);

            // Чтение входящих файлов с распределением по типам данных
            UtilFileReader.readFiles(config.fileList);

            // Запись распределённых по типам данных в соответствующие отдельные файлы
            UtilFileWriter utilFileWriter = new UtilFileWriter(config);
            utilFileWriter.writingProcess();

            // Вывод статистики
            StatisticsGenerator statisticsGenerator = new StatisticsGenerator(config);
            statisticsGenerator.showStatistics();
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка аргументов: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ошибка выполнения: " + e.getMessage());
            e.printStackTrace();
        }
    }
}