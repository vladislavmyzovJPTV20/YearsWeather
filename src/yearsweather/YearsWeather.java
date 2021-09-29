/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yearsweather;

import com.oracle.xmlns.internal.webservices.jaxws_databinding.SoapBindingParameterStyle;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author pupil
 */
public class YearsWeather {
    static enum Month{
        Январь, Февраль, Март, Апрель, Май, Июнь, Июль, Август, Сентябрь, Октябрь, Ноябрь, Декабрь
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Создаем min и max
        int min=-30, max=30;
        //Создаем зубчатый массив из 12 строк
        int[][] dayTempInYear = new int[12][];
        dayTempInYear[0] = new int[31]; // январь
        dayTempInYear[1] = new int[28]; // февраль
        dayTempInYear[2] = new int[31]; // март
        dayTempInYear[3] = new int[30]; // апрель
        dayTempInYear[4] = new int[31]; // май
        dayTempInYear[5] = new int[30]; // июнь
        dayTempInYear[6] = new int[31]; // июль
        dayTempInYear[7] = new int[31]; // август
        dayTempInYear[8] = new int[30]; // сентябрь
        dayTempInYear[9] = new int[31]; // октябрь
        dayTempInYear[10] = new int[30]; // ноябрь
        dayTempInYear[11] = new int[31]; // декабрь
        Random random = new Random();
        for(int i = 0; i < dayTempInYear.length; i++) {
            int n = 0;
            switch(i) {
                case 2: min = -5; max = 15;n = 31;break; // Весна
                case 3: min = -5; max = 15;n = 30;break; // Весна
                case 4: min = -5; max = 15;n = 31;break; // Весна
                case 5: min = 5;  max = 30;n = 30;break; // Лето
                case 6: min = 5;  max = 30;n = 31;break; // Лето
                case 7: min = 5;  max = 30;n = 31;break; // Лето
                case 8: min = -10;max = 10;n = 30;break; // Осень
                case 9: min = -10;max = 10;n = 31;break; // Осень
                case 10:min = -10;max = 10;n = 30;break; // Осень
                case 11:min = -30;max = 0; n = 31;break; // Зима
                case 0: min = -30;max = 0; n = 31;break; // Зима
                case 1: min = -30;max = 0; n = 28;break; // Зима
            }
            dayTempInYear[i] = new int[n];
            for(int j = 0;j < dayTempInYear[i].length; j++) {
                dayTempInYear[i][j] = random.nextInt(max - min + 1)+min;
            }
        }
        
        for(int i = 0; i < dayTempInYear.length; i++) {
            System.out.print(Month.values()[i]+". ");
            for(int j = 0; j < dayTempInYear[i].length; j++) {
                System.out.printf("%4d", dayTempInYear[i][j]);
            }
            System.out.println();
        }
        //Средняя арифметическая каждого месяца
        
        double[] averageTemperatureInMonth = new double[12];
        for(int i = 0; i < dayTempInYear.length; i++) {
            int daysInMonth=0;
            for(int j = 0; j < dayTempInYear[i].length; j++) {
                averageTemperatureInMonth[i] += (double)dayTempInYear[i][j];
                daysInMonth=j+1;
            }
            averageTemperatureInMonth[i] = averageTemperatureInMonth[i]/daysInMonth;
        }
        System.out.println("Средняя температура по месяцам: ");
        for(int i = 0; i < averageTemperatureInMonth.length; i++) {
            System.out.printf("%s: %-4.2f%n",Month.values()[i], averageTemperatureInMonth[i]);
        }
        // Погода на указанную дату
        String[] MonthsStr = {"января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря"};
        System.out.print("Введите день: ");
        Scanner scanner = new Scanner(System.in);
        int day = scanner.nextInt();
        System.out.print("Введите число месяца: ");
        int month = scanner.nextInt();
        System.out.println(day + " " + MonthsStr[month-1] + " погода составляла " + dayTempInYear[month-1][day-1] + " °C");
        
        // Самая теплая и холодная температура
        
        int maximum_temperature = 0;
        int minimum_temperature = 0;
        int daymaxtemp = 0;
        int monthmaxtemp = 0;
        int daymintemp = 0;
        int monthmintemp = 0;
        for(int i = 0; i < dayTempInYear.length; i++) {
            for(int j = 0; j < dayTempInYear[i].length; j++) {
                if(dayTempInYear[i][j] > maximum_temperature) {
                    maximum_temperature = dayTempInYear[i][j];
                    daymaxtemp = j;
                    monthmaxtemp = i;
                }
                if(dayTempInYear[i][j] < minimum_temperature) {
                    minimum_temperature = dayTempInYear[i][j];
                    daymintemp = j;
                    monthmintemp = i;
                }
            }
        }
        System.out.println("Максимальная температура составляет: " + maximum_temperature + " °C" + " и была она " + daymaxtemp + " " + MonthsStr[monthmaxtemp]);
        System.out.println("Минимальная температура составляет: " + minimum_temperature + " °C" + " и была она " + daymintemp + " " + MonthsStr[monthmintemp]);
    }
}
