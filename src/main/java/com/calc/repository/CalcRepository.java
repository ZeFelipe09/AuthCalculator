package com.calc.repository;

import com.calc.domain.Calc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CalcRepository extends JpaRepository<Calc, Integer> {

    Optional<Calc> findCalcByExpression(String expression);
}
