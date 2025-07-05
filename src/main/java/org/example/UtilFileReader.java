package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class UtilFileReader {

    /**
     * Чтение строк из файлов по очереди в соответствии с их перечислением в командной строке
     * @param filesList список файлов
     */
    public static void readFiles(List<Path> filesList) {
        List<BufferedReader> readersList = new ArrayList<>();
        for (Path filePath : filesList) {
            try {
                readersList.add(Files.newBufferedReader(filePath));
            } catch (IOException e) {
                System.err.println("Не удалось прочитать файл: " + e.getMessage());
            }
        }

        boolean hasMore = true;
        while (hasMore) {
            hasMore = false;
            for (BufferedReader reader : readersList) {
                if (reader == null) {
                    continue;
                }
                String currentLine = null;
                try {
                    currentLine = reader.readLine();
                } catch (IOException e) {
                    System.err.println("Ошибка чтения строки: " + e.getMessage());
                }
                if (currentLine != null) {
                    hasMore = true;
                    StringAllocator.distribute(currentLine);
                }
            }
        }

        for (BufferedReader reader : readersList) {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.err.println("Ошибка при закрытии ридера: " + e.getMessage());
                }
            }
        }
    }
}
