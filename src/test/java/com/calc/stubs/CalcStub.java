package com.calc.stubs;

import com.calc.domain.Calc;

public class CalcStub {

    public static Calc calcStub(String expression, Float result){
        return new Calc(10, expression, result);
    }

}
