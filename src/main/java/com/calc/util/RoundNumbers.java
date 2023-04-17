package com.calc.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundNumbers {

    public static Float round(Float number){
        BigDecimal resultFormat = new BigDecimal(number).setScale(2, RoundingMode.HALF_EVEN);
        return Float.valueOf(String.valueOf(resultFormat));
    }
}
