package org.example;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class StringAllocator {
    static List<BigInteger> integerList = new ArrayList<>();
    static List<Float> floatList = new ArrayList<>();
    static List<String> stringList = new ArrayList<>();

    /**
     * Анализирует содежримое строк файлов
     * и распределяет по соответствующим листам для последующей обработки
     * @param str строка файла
     */
    public static void distribute(String str) {
        try {
            BigInteger bigIntNum = new BigInteger(str);
            integerList.add(bigIntNum);
            return;
        } catch (NumberFormatException ignore) {
            /* NOP */
        }
        try {
            float floatNum = Float.parseFloat(str);
            if (str.matches("[-+]?\\d*\\.\\d+([eE][-+]?\\d+)?")) {
                floatList.add(floatNum);
                return;
            }
        } catch (NumberFormatException ignore) {
            /* NOP */
        }
        stringList.add(str);
    }
}
