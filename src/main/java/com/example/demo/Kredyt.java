package com.example.demo;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Kredyt {
    public static void main(String[] args) {
        // Wprowadź dane kredytu
        double kwotaKredytu = 10000000; // Kwota kredytu
        double oprocentowanie = 0.05; // Oprocentowanie w formie dziesiętnej (5%)
        int liczbaMiesiecy = 36000; // Liczba miesięcy

        // Oblicz stałą ratę
        double rata = obliczRate(kwotaKredytu, oprocentowanie, liczbaMiesiecy);

        // Wyświetl harmonogram kredytu
        System.out.println("Miesiąc\tSaldo Początkowe\tRata\tOdsetki\tSpłata Kapitału\tSaldo Końcowe");
        double saldo = kwotaKredytu;
        for (int miesiac = 1; miesiac <= liczbaMiesiecy; miesiac++) {
            double odsetki = saldo * oprocentowanie / 12;
            double splataKapitalu = rata - odsetki;
            saldo -= splataKapitalu;

            System.out.println(miesiac + "\t" + zaokragl(saldo + splataKapitalu) + "\t" + zaokragl(rata) + "\t" + zaokragl(odsetki) + "\t" + zaokragl(splataKapitalu) + "\t" + zaokragl(saldo));
        }
    }

    // Oblicz ratę kredytu
    public static double obliczRate(double kwotaKredytu, double oprocentowanie, int liczbaMiesiecy) {
        double miesieczneOprocentowanie = oprocentowanie / 12;
        double rata = kwotaKredytu * (miesieczneOprocentowanie) / (1 - Math.pow(1 + miesieczneOprocentowanie, -liczbaMiesiecy));
        return rata;
    }

    // Funkcja do zaokrąglania liczb
    public static double zaokragl(double liczba) {
        BigDecimal bd = new BigDecimal(liczba).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}