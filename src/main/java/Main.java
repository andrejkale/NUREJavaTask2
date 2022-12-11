import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*Ввести n чисел с консоли.
        1. Найти самое короткое и самое длинное число. Вывести найденные числа
        и их длину*/
        System.out.println("Enter a number \"n\" of digits");
        Scanner scanner = new Scanner(System.in);
        String digitalLong;
        String digitalShort;


        int n = scanner.nextInt();
        String[] digits = new String[n];

        for (int i = 0; i < digits.length; i++) {
            System.out.println("Enter a digits");
            digits[i] = scanner.next();
        }

        int min = digits[0].length();
        int max = digits[0].length();
        int shortIndex = 0;
        int longIndex = 0;

        for (int i = 0; i < digits.length; i++) {
            if (digits[i].length() < min) {
                min = digits[i].length();
                shortIndex = i;
            }
        }

        for (int i = 0; i < digits.length; i++) {
            if (digits[i].length() > max) {
                max = digits[i].length();
                longIndex = i;
            }
        }

        digitalShort = digits[shortIndex];
        digitalLong = digits[longIndex];

        System.out.println("Short digital = " + digitalShort + " and length = " + min);
        System.out.println("Long digital = " + digitalLong  + " and length = " + max);

        /* 2. Упорядочить и вывести числа в порядке возрастания (убывания) значений
        их длины.*/
        for(int i = 1; i < digits.length; i++) { // sort by desc
            String temp = digits[i];
            int j;
            for(j = i - 1; j >= 0 && digits[j].length() > temp.length(); j--) {
                digits[j + 1] = digits[j];
            }
            digits[j + 1] = temp;
        }
        System.out.println("Array string sorted by Ascending order " + Arrays.toString(digits));

        for(int i = 1; i < digits.length; i++) { // sort by asc
            String temp = digits[i];
            int j;
            for(j= i - 1; j >= 0 && digits[j].length() < temp.length(); j--) {
                digits[j + 1] = digits[j];
            }
            digits [j + 1] = temp;
        }
        System.out.println("Array string sorted by Descending order " + Arrays.toString(digits));

        /*3. Вывести на консоль те числа, длина которых меньше (больше) средней,
        а также длину*/
        showLessThanLengthAverage(digits);
        showMoreThanLengthAverage(digits);

        /*4. Найти число, в котором число различных цифр минимально. Если таких
         чисел несколько, найти первое из них.*/
        minDifDigit(digits);

        /*5. Найти количество чисел, содержащих только четные цифры, а среди них —
        количество чисел с равным числом четных и нечетных цифр.*/
        equalsEvenOddDigits(digits);

        /*6. Найти число, цифры в котором идут в строгом порядке возрастания. Если
        таких чисел несколько, найти первое из них.*/
        arithmeticProgression(digits);

        /*7. Найти число, состоящее только из различных цифр. Если таких чисел несколько
        , найти первое из них*/
        differentDigits(digits);

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

    private static void minDifDigit(String [] array){
        String numberWithMinDifDigit = "";

        int min = 11;
        for(String st: array){
            if(uniqueNum(st)<min){
                min=uniqueNum(st);
                numberWithMinDifDigit=st;
            }
        }
        System.out.println("Number with minimal count of unique digit is " + numberWithMinDifDigit);
    }

    private static boolean isEvenOdd(String element){
        int even = 0, odd = 0;
        for(int i = 0; i < element.length(); i++){
            if(Character.getNumericValue(element.charAt(i)) % 2 == 0)
                even++;
            else odd++;
        }
        if(even == odd)
            return true;
        else
            return false;
    }

    private static void equalsEvenOddDigits(String [] array){
        ArrayList<String> result = new ArrayList();
        for(String iterator:array){
            if(Integer.parseInt(iterator) % 2 == 0){
                result.add(iterator);
            }
        }
        int count=0;
        System.out.println("Numbers with equal number of even and odd digits:");
        for(String element: result){
            if(isEvenOdd(element)){
                System.out.print(element+" ");
                count++;
            }
        }
        System.out.println("\nCount of even numbers with equal number of even and odd digits is "+count);
    }

    private static boolean isProgression(String st){
        for(int i = 0; i<st.length();i++) {
            for (int j = i+1;j<st.length();j++){
                if (Character.getNumericValue(st.charAt(i))>Character.getNumericValue(st.charAt(j)))
                    return false;
            }
        }
        return true;
    }

    private static void arithmeticProgression(String [] array){
        ArrayList<String> result = new ArrayList();
        String arithmeticProgressionNumber;
        for(String iterator : array){
            if (Integer.parseInt(iterator) > 9)
                result.add(iterator);
        }
        for(String element: result){
            if(isProgression(element)){
                arithmeticProgressionNumber = element;
                System.out.println("Number with digit in arithmetic progression " + arithmeticProgressionNumber);
                break;
            }
        }
    }

    public static void differentDigits(String [] array){
        ArrayList<String> result = new ArrayList();
        String numberWithUniqueDigitals;
        for(String iterator : array){
            if(iterator.length() < 11 && iterator.length() > 1){
                result.add(iterator);
            }
        }
        for(String st: result)
            if (isUnique(st)) {
                numberWithUniqueDigitals = st;
                System.out.println("Number with unique digits is " + numberWithUniqueDigitals);
                break;
            }
    }
}
