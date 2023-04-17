package com.calc.api;

import com.calc.domain.Calc;
import com.calc.error.CalcException;
import com.calc.services.CalcService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.script.ScriptException;

@RestController
@RequestMapping("/calc")
public class CalcResources {

    @Autowired
    private CalcService calcService;

    @PostMapping("/operation")
    public ResponseEntity<Float> calc(@RequestBody Calc expressao) throws JSONException {

        return ResponseEntity.ok(calcService.doCalc(expressao).getResult());
    }

    @ExceptionHandler(CalcException.class)
    public ResponseEntity<String> exception(CalcException err){
        return ResponseEntity.status(422).body(err.getMessage());
    }
}
