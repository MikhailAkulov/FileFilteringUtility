package org.example;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

public class StatisticsGenerator {
    final ProgramConfig config;

    public StatisticsGenerator(ProgramConfig config) {
        this.config = config;
    }

    /**
     * Вывод статистики требуемого типа, в зависимоти от заданной конфигурации утилиты
     */
    public void showStatistics() {
        if (config.fullStatistics) {
            getFullStatBigInt(StringAllocator.integerList);
            getFullStatFloat(StringAllocator.floatList);
            getFullStatString(StringAllocator.stringList);
        } else {
            getShortStatistic(StringAllocator.integerList);
            getShortStatistic(StringAllocator.floatList);
            getShortStatistic(StringAllocator.stringList);
        }
    }

    /**
     * Вывод полной статистики для целочисленного типа
     * @param list список целых чисел полученный из StringAllocator
     */
    private void getFullStatBigInt(List<BigInteger> list) {
        if (!list.isEmpty()) {
            BigInteger max = Collections.max(list);
            BigInteger min = Collections.min(list);
            BigInteger sum = BigInteger.valueOf(0);
            for (BigInteger num : list) {
                sum = sum.add(num);
            }
            BigInteger avg = sum.divide(BigInteger.valueOf(list.size()));
            System.out.println("Целые числа: количество элеметов = " + list.size() +
                    ", минимальное значение = " + min +
                    ", максимальное значение = " + max +
                    ", сумма = " + sum +
                    ", среднее = " + avg);
        }
    }

    /**
     * Вывод полной статистики для вещественного типа
     * @param list список вещестенных чисел полученный из StringAllocator
     */
    private void getFullStatFloat(List<Float> list) {
        if (!list.isEmpty()) {
            Float max = Collections.max(list);
            Float min = Collections.min(list);
            Float sum = 0f;
            for (Float num : list) {
                sum += num;
            }
            Float avg = sum / list.size();
            System.out.println("Вещественные числа: количество элеметов = " + list.size() +
                    ", минимальное значение = " + min +
                    ", максимальное значение = " + max +
                    ", сумма = " + sum +
                    ", среднее = " + avg);
        }
    }

    /**
     * Вывод полной статистики для строкового типа
     * @param list список строк полученный из StringAllocator
     */
    private void getFullStatString(List<String> list) {
        if (!list.isEmpty()) {
            int max = list.get(0).length();
            int min = list.get(0).length();
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i).length() > max) {
                    max = list.get(i).length();
                }
                if (list.get(i).length() < min) {
                    min = list.get(i).length();
                }
            }
            System.out.println("Строки: количество элеметов = " + list.size() +
                    ", размер самой короткой строки = " + min +
                    ", размер самой длинной строки = " + max);
        }
    }

    /**
     * Вывод краткой статистики для всех типов
     * @param list список строк полученный из StringAllocator
     */
    public static void getShortStatistic(List<?> list) {
        if (!list.isEmpty()) {
            if (list.get(0).getClass().equals(BigInteger.class)) {
                System.out.println("Целые числа: " + list.size());
            } else if (list.get(0).getClass().equals(Float.class)) {
                System.out.println("Вещественные числа: " + list.size());
            } else if (list.get(0).getClass().equals(String.class)) {
                System.out.println("Строки: " + list.size());
            }
        }
    }
}
