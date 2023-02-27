package com.br.ent.restspringbootudemy.calculadora;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.br.ent.restspringbootudemy.exception.UnsuportedMathOperationException;

@RestController
public class CalculatorController {

	@RequestMapping(value = "/calculadora/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double calculadora(@PathVariable("numberOne") String n1, @PathVariable("numberTwo") String n2) throws Exception {
		if(!isNumeric(n1) || !isNumeric(n2)) {
			throw new UnsuportedMathOperationException("Please set a numeric value!");
		}
		Double sum = convertToDouble(n1) + convertToDouble(n2);
		return sum;
	}

	private Double convertToDouble(String number) {
		if(number == null) return 0D;
		String n = number.replaceAll(",", ".");
		if (isNumeric(number)) return Double.parseDouble(n);
		return 0D;
	}

	private boolean isNumeric(String number) {
		if(number == null) return false;
		String n = number.replaceAll(",", ".");
		return n.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
}
