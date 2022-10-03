package com.example.calculator;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class Main {
    public static void main(String[] args) {
        System.out.println("Введите пример:");
        Scanner scanner = new Scanner(System.in);//добавляем ввод пользователя
        System.out.println(calc(scanner.nextLine()));
    }
    public static String calc(String input) {
        Roman roman = new Roman();//добавляем класс для работы с римскими числами
        int min = 1;//ограничения величины чисел
        int max = 10;
        List<String> romans = Arrays.asList("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X");//задаём ограничение для римских чисел
        String input1 = input;
        input1 = input1.replaceAll("\s+", "");//убираем пробелы
        String[] numbers = input1.split("\\+|-|\\*|/");//создаём массив значений
        Pattern pattern = Pattern.compile("\\+|-|\\*|/");//достаём арифметический знак
        Matcher matcher = pattern.matcher(input1);
        matcher.find();
        matcher.group(0);
        int result = 0;
        boolean isRoman = romans.containsAll(List.of(numbers));//определяем логическое выражение
        if (numbers.length != 2){//вводим ограничение на количество чисел(не более двух)
            throw new RuntimeException();
        }
        else if (isRoman) {//делаем проверку на римские числа
            int firstNumber = roman.romanToInt(numbers[0]);//переводим римские в арабские
            int secondNumber = roman.romanToInt(numbers[1]);
            switch (matcher.group(0)) {//производим арифметическую операцию по заранее определённому знаку
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                default:
                    result = firstNumber / secondNumber;
                    break;
            }
            return String.valueOf(roman.intToRoman(result));//выводим результат с переводом в римские
        }
        else{//достаём числа из массива
            int firstNumber = Integer.parseInt(numbers[0]);
            int secondNumber = Integer.parseInt(numbers[1]);
            if(min<=firstNumber && firstNumber<=max){//устанавливаем ограничения для величины чисел
                if(min<=secondNumber && secondNumber<=max ){
                    switch (matcher.group(0)) {//производим арифметическую операцию
                    case "+":
                        result = firstNumber + secondNumber;
                        break;
                    case "-":
                        result = firstNumber - secondNumber;
                        break;
                    case "*":
                        result = firstNumber * secondNumber;
                        break;
                    default:
                        result = firstNumber / secondNumber;
                        break;
                    }
                }
                else {throw new RuntimeException();}
            }
            else{throw new RuntimeException();
            }
        }
        return String.valueOf(result);
    }
}