package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class UtilFileWriter {
    final ProgramConfig programConfig;
    BufferedWriter intNumFileWriter = null;
    BufferedWriter floatNumFileWriter = null;
    BufferedWriter stringFileWriter = null;

    public UtilFileWriter(ProgramConfig programConfig) {
        this.programConfig = programConfig;
    }

    /**
     * Инициализирует полный цикл процедур записи файлов
     */
    public void writingProcess() {
        try {
            Files.createDirectories(programConfig.outputPath);

            writeFile(StringAllocator.integerList);
            writeFile(StringAllocator.floatList);
            writeFile(StringAllocator.stringList);

            closeWriters();
        } catch (IOException e) {
            System.err.println("Ошибка обработки: " + e.getMessage());
        }
    }

    /**
     * Записывает данные из списков в соответствующие файлы
     * @param list
     */
    public void writeFile(List<?> list) {
        if (!list.isEmpty()) {
            for (Object o : list) {
                if (o.getClass().equals(BigInteger.class)) {
                    try {
                        getIntNumFileWriter().write(o + System.lineSeparator());
                    } catch (IOException e) {
                        System.err.println("Ошибка при записи файла integers.txt: " + e.getMessage());
                    }
                } else if (o.getClass().equals(Float.class)) {
                    try {
                        getFloatNumFileWriter().write(o + System.lineSeparator());
                    } catch (IOException e) {
                        System.err.println("Ошибка при записи файла floats.txt: " + e.getMessage());
                    }
                } else if (o.getClass().equals(String.class)) {
                    try {
                        getStringFileWriter().write(o + System.lineSeparator());
                    } catch (IOException e) {
                        System.err.println("Ошибка при записи файла strings.txt: " + e.getMessage());
                    }
                }
            }
        }
    }

    /**
     * Создает экземпляр BufferedWriter исходя из текущей конфигурации утилиты
     * @param fileName в зависимости от записываемого типа данных
     * @return BufferedWriter
     * @throws IOException
     */
    public BufferedWriter createWriter(String fileName) throws IOException {
        String resultFileName = programConfig.prefix + fileName;
        Path path = programConfig.outputPath.resolve(resultFileName);
        return new BufferedWriter(new FileWriter(path.toFile(), programConfig.appendOption));
    }

    public BufferedWriter getIntNumFileWriter() throws IOException {
        if (intNumFileWriter == null) {
            intNumFileWriter = createWriter("integers.txt");
        }
        return intNumFileWriter;
    }

    public BufferedWriter getFloatNumFileWriter() throws IOException {
        if (floatNumFileWriter == null) {
            floatNumFileWriter = createWriter("floats.txt");
        }
        return floatNumFileWriter;
    }

    public BufferedWriter getStringFileWriter() throws IOException {
        if (stringFileWriter == null) {
            stringFileWriter = createWriter("strings.txt");
        }
        return stringFileWriter;
    }

    public void closeWriters() {
        try {
            if (intNumFileWriter != null) {
                intNumFileWriter.close();
            }
        } catch (IOException e) {
            System.err.println("Ошибка при закрытии intNumFileWriter: " + e.getMessage());
        }
        try {
            if (floatNumFileWriter != null) {
                floatNumFileWriter.close();
            }
        } catch (IOException e) {
            System.err.println("Ошибка при закрытии floatNumFileWriter: " + e.getMessage());
        }
        try {
            if (stringFileWriter != null) {
                stringFileWriter.close();
            }
        } catch (IOException e) {
            System.err.println("Ошибка при закрытии stringFileWriter: " + e.getMessage());
        }
    }
}
