import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*Ввести n чисел с консоли.
        1. Найти самое короткое и самое длинное число. Вывести найденные числа
        и их длину*/
        System.out.println("Enter a number \"n\" of digitals");
        Scanner scanner = new Scanner(System.in);
        String digitalLong;
        String digitalShort;


        int n = scanner.nextInt();
        String[] digitals = new String[n];

        for (int i = 0; i < digitals.length; i++) {
            System.out.println("Enter a digitals");
            digitals[i] = scanner.next();
        }

        int min = digitals[0].length();
        int max = digitals[0].length();
        int shortIndex = 0;
        int longIndex = 0;

        for (int i = 0; i < digitals.length; i++) {
            if (digitals[i].length() < min) {
                min = digitals[i].length();
                shortIndex = i;
            }
        }

        for (int i = 0; i < digitals.length; i++) {
            if (digitals[i].length() > max) {
                max = digitals[i].length();
                longIndex = i;
            }
        }

        digitalShort = digitals[shortIndex];
        digitalLong = digitals[longIndex];

        System.out.println("Short digital = " + digitalShort + " and length = " + min);
        System.out.println("Long digital = " + digitalLong  + " and length = " + max);

        /* 2. Упорядочить и вывести числа в порядке возрастания (убывания) значений
        их длины.*/
        for(int i = 1; i < digitals.length; i++) { // sort by desc
            String temp = digitals[i];
            int j;
            for(j = i - 1; j >= 0 && digitals[j].length() > temp.length(); j--) {
                digitals[j + 1] = digitals[j];
            }
            digitals[j + 1] = temp;
        }
        System.out.println("Array string sorted by Ascending order " + Arrays.toString(digitals));

        for(int i = 1; i < digitals.length; i++) { // sort by asc
            String temp = digitals[i];
            int j;
            for(j= i - 1; j >= 0 && digitals[j].length() < temp.length(); j--) {
                digitals[j + 1] = digitals[j];
            }
            digitals [j + 1] = temp;
        }
        System.out.println("Array string sorted by Descending order " + Arrays.toString(digitals));

        /*3. Вывести на консоль те числа, длина которых меньше (больше) средней,
        а также длину*/
        showLessThanLengthAverage(digitals);
        showMoreThanLengthAverage(digitals);

        /*4. Найти число, в котором число различных цифр минимально. Если таких
         чисел несколько, найти первое из них.*/
    }

    private static void showLessThanLengthAverage(String [] array){
        int average = averageArrayElementsLength(array);

        System.out.println("Average is: " + average);
        System.out.print("Element with lower than average length: ");
        for (int i = 0; i < array.length; i++) {
            if (array[i].length() < average) {
                System.out.println("element " + array[i] + " length " + array[i].length() + "");
            }
        }
    }

    private static void showMoreThanLengthAverage(String [] array){
        int average =  averageArrayElementsLength(array);

        System.out.println("Average is: " + average);
        System.out.print("Element with more than average length: ");
        for (int i = 0; i < array.length; i++) {
            if (array[i].length() > average) {
                System.out.println("element " + array[i] + " length " + array[i].length() + "");
            }
        }
    }

    private static int averageArrayElementsLength(String[] array) {
        int average = 0;
        for (String element: array) {
            average += element.length();
        }
        average /= array.length;
        return average;
    }

    private static boolean isUnique(String element){
        for(int i = 0; i < element.length(); i++) {
            for (int j = i + 1; j < element.length(); j++){
                if (element.charAt(i) == element.charAt(j))
                    return false;
            }
        }
        return true;
    }

    private static int uniqueNum(String element){
        int[] nums = new int[10]; // counter
        int count=0;
        for(int i = 0; i < element.length(); i++) {
            nums[Character.getNumericValue(element.charAt(i))]++;
        }
        for(int i = 0; i < 10; i++) {
            if(nums[i] == 1) count++;
        }
        return count;
    }
}
