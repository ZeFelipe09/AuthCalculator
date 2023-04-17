package com.calc.util;

import com.calc.error.CalcException;

public class CalcHandler {

    private String numberRegex = "^\\d+\\.?\\d*$";

    public Float makeCalc(String expression) {

        String regex = "(?<=op)|(?=op)".replace("op", "[-+*/()]");
        String[] factors = expression.split(regex);

        Float result = 0F;

        for (int i = 0 ; i < factors.length - 1 ; i++){

            if (!factors[i].matches(numberRegex)){
                Float firstNumber = result == 0 ? Float.valueOf(factors[i-1]) : result;
                Float secondNumber = Float.valueOf(factors[i+1]);
                String operator = factors[i];

                result = doOperation(firstNumber,secondNumber , operator);
            }
        }

        return result;
    }

    private Float doOperation(Float firstNumber, Float secondNumber, String operator){
        switch (operator){
            case "+":
                return firstNumber + secondNumber;
            case "-":
                return firstNumber - secondNumber;
            case "*":
                return firstNumber * secondNumber;
            case "/":
                if(secondNumber == 0){
                    throw new CalcException("Divison by 0 is not possible");
                }
                return firstNumber/secondNumber;
            default:
                return 0F;

        }
    }
}
