package io2018.ii.uj.edu.pl.jurpizza;

import java.text.DecimalFormat;

public class Util {
    private static final DecimalFormat MONEY_FORMAT = new DecimalFormat("0.00");

    public static String formatMoney(int mon) {
        return MONEY_FORMAT.format(mon / 100.0) + " zł";
    }

}
