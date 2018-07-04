package com.geekbrains;

import com.geekbrains.Exceptions.MyArrayDataException;
import com.geekbrains.Exceptions.MyArraySizeException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String[][] array = {
            {"1", "2", "3", "4"},
            {"5", "6", "7", "8"},
            {"9", "10", "11", "12"},
            {"13", "14", "15", "16"},
        };

        try {
            System.out.println("Сумма равна " + arraySum(array));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("Расчет рабочих часов до конца недели:");
        DayOfWeek[] days = DayOfWeek.values();
        for (DayOfWeek day : days) {
            System.out.println(day.getRuName() + ": " + getWorkingHours(day));
        }

        System.out.println();
        System.out.println("Вычисление индекса массы тела:");
        String BmiData = "118 2.05\n" +
            "106 1.77\n" +
            "87 1.83\n" +
            "45 1.12\n" +
            "70 1.87\n" +
            "54 1.57\n" +
            "105 1.76\n" +
            "50 1.96\n" +
            "114 1.76\n" +
            "72 2.45\n" +
            "53 2.10\n" +
            "66 2.25\n" +
            "54 1.50\n" +
            "95 1.62\n" +
            "86 1.72\n" +
            "62 1.57\n" +
            "65 2.24\n" +
            "72 1.43\n" +
            "93 2.01\n" +
            "109 3.01\n" +
            "106 2.97\n" +
            "77 1.69\n" +
            "114 2.09\n" +
            "98 1.72\n" +
            "85 2.46\n" +
            "113 1.94\n" +
            "53 1.77\n" +
            "106 2.30";

        Scanner scanner = new Scanner(BmiData);

        while (scanner.hasNext()) {
            try {
                int weight = scanner.nextInt();
                // тут пока не понял, почему не сработал scanner.nextFloat()
                // если сможете пояснить - буду благодарен ))
                // выкидывает InputMismatchException, хотя регулярка должна подходить...
                // правда она длинная и полностью не проверял, но под decimalNumeral из
                // Scanner::buildFloatAndDecimalPattern подходит
                float height = Float.parseFloat(scanner.next("[\\d\\.]+"));
                System.out.printf(
                    "%s %s %s\n",
                    weight,
                    height,
                    calcBmi(weight, height)
                );
            } catch (InputMismatchException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    private static int arraySum(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length != 4) {
            throw new MyArraySizeException("Размер массива не равен 4");
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i].length != 4) {
                throw new MyArraySizeException("Размерность строки " + i + " массива не равна 4");
            }
        }

        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Ошибка преобразования в int в ячейке " + i + " " + j);
                }
            }
        }

        return sum;
    }

    private static int getWorkingHours(DayOfWeek day) {
        if (day.ordinal() >= 5) return 0; // выходные
        return 8 * (5 - day.ordinal());
    }

    private static String calcBmi(int weight, float height) {
        double bmi = weight / Math.pow(height, 2);
        if (bmi < 18.5) return "under";
        if (bmi < 25) return "normal";
        if (bmi < 30) return "over";
        return "obese";
    }
}
