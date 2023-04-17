package com.calc.service;

import com.calc.domain.Calc;
import com.calc.error.CalcException;
import com.calc.repository.CalcRepository;
import com.calc.services.CalcService;
import com.calc.stubs.CalcStub;
import com.calc.util.CalcHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CalcServiceTest {

    @InjectMocks
    private CalcService service;

    @Mock
    private CalcRepository repository;

    private Calc calc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void simpleSum(){
        calc = CalcStub.calcStub("2+2",0F);

        when(repository.findCalcByExpression(any())).thenReturn(Optional.empty());

        Calc response = service.doCalc(calc);

        assertEquals(response.getResult(), 4F);
    }

    @Test
    void sumWithDatabase(){
        CalcHandler handler = mock(CalcHandler.class);
        calc = CalcStub.calcStub("2.2+2.2", 4.4F);

        when(repository.findCalcByExpression(any())).thenReturn(Optional.ofNullable(calc));

        Calc response = service.doCalc(calc);

        assertEquals(response.getResult(), 4.4F);
        verify(handler, times(0)).makeCalc("2.2+2.2");

    }

    @Test
    void threeFactorsOperation(){
        calc = CalcStub.calcStub("2.3*2.3+5",0F);

        when(repository.findCalcByExpression(any())).thenReturn(Optional.empty());

        Calc response = service.doCalc(calc);

        assertEquals(response.getResult(), 10.29F);
    }

    @Test
    void division(){
        calc = CalcStub.calcStub("2.33/3",0F);

        when(repository.findCalcByExpression(any())).thenReturn(Optional.empty());

        Calc response = service.doCalc(calc);

        assertEquals(0.78F, response.getResult());
    }

    @Test
    void divisionError(){
        calc = CalcStub.calcStub("1/0",0F);

        when(repository.findCalcByExpression(any())).thenReturn(Optional.empty());

        assertThrows(CalcException.class, () -> service.doCalc(calc));

    }



}
