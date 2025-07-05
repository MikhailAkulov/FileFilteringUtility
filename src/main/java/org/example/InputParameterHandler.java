package org.example;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class InputParameterHandler {

    /**
     * На основе содержимого командной строки определяет конфигурацию программы
     * @param args массив аргументов командной строки
     * @return объект ProgramConfig
     */
    public static ProgramConfig configure(String[] args) {
        Path outputPath = Path.of(".");
        String prefix = "";
        boolean appendOption = false;
        boolean fullStatistics = false;
        List<Path> filesNamesList = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {
            String currentArg = args[i];
            switch (currentArg) {
                case "-o":
                    if (++i >= args.length) {
                        throw new IllegalArgumentException("отсутствует путь для записи результатов после параметра -o");
                    }
                    Path checkPath = Path.of(args[i]);
                    if (!checkPath.toFile().isDirectory()) {
                        throw new IllegalArgumentException("после параметра -o должен быть указан путь для записи " +
                                "результатов (например: /some/path)");
                    }
                    outputPath = checkPath;
                    break;
                case "-p":
                    if (++i >= args.length) {
                        throw new IllegalArgumentException("отсутствует префикс имени для результирующих файлов " +
                                "после параметра -p");
                    }
                    String checkStr = args[i];
                    if (checkStr.contains(".txt") ||
                            checkStr.contains("-o") ||
                            checkStr.contains("-p") ||
                            checkStr.contains("-a") ||
                            checkStr.contains("-s") ||
                            checkStr.contains("-f")) {
                        throw new IllegalArgumentException("после параметра -p должен быть указан префикс имени для " +
                                "результирующих файлов (например: result_)");
                    }
                    prefix = checkStr;
                    break;
                case "-a":
                    appendOption = true;
                    break;
                case "-s":
                    fullStatistics = false;
                    break;
                case "-f":
                    fullStatistics = true;
                    break;
                default:
                    try {
                        Path filePath = Path.of(currentArg);
                        if (filePath.getParent().toFile().isDirectory()) {
                            filesNamesList.add(filePath);
                        }
                    } catch (Exception ignore) {
                        /* NOP */
                    }
                    break;
            }
        }
        if (filesNamesList.isEmpty()) {
            throw new IllegalArgumentException("Не указаны исходные файлы! Программа остановлена");
        }
        return new ProgramConfig(outputPath, prefix, appendOption, fullStatistics, filesNamesList);
    }
}
