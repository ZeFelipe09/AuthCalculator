package com.calc.services;

import com.calc.domain.Calc;
import com.calc.repository.CalcRepository;
import com.calc.util.CalcHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.calc.util.RoundNumbers.round;

@Service
public class CalcService {

    @Autowired
    private CalcRepository repository;
    private CalcHandler calcHandler;


    public Calc doCalc(Calc expression){
        Optional<Calc> calc = repository.findCalcByExpression(expression.getExpression());

        if(calc.isEmpty()){
            calcHandler = new CalcHandler();
            Float result = calcHandler.makeCalc(expression.getExpression());
            expression.setResult(round(result));
            saveCalc(expression);
            return expression;
        }else{
            return calc.get();
        }
    }

    private void saveCalc(Calc calc){
        repository.save(calc);
    }

}
