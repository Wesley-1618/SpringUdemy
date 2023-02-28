package com.br.ent.restspringbootudemy.calculadora;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.br.ent.restspringbootudemy.utils.NumberUtils;

@RestController
@RequestMapping("/calculadora")
public class CalculatorController {

	NumberUtils nUtil = new NumberUtils();

	MathOperations math = new MathOperations();

	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double calculadoraSoma(@PathVariable("numberOne") String n1, @PathVariable("numberTwo") String n2)
			throws Exception {
		NumberUtils.isNumeric(n1);
		NumberUtils.isNumeric(n2);
		return math.soma(NumberUtils.convertToDouble(n1), NumberUtils.convertToDouble(n2));
	}

	@RequestMapping(value = "/sub/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double calculadoraSub(@PathVariable("numberOne") String n1, @PathVariable("numberTwo") String n2) {
		NumberUtils.isNumeric(n1);
		NumberUtils.isNumeric(n2);
		return math.subtracao(NumberUtils.convertToDouble(n1), NumberUtils.convertToDouble(n2));
	}

	@RequestMapping(value = "/mult/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double calculadoraMult(@PathVariable("numberOne") String n1, @PathVariable("numberTwo") String n2) {
		NumberUtils.isNumeric(n1);
		NumberUtils.isNumeric(n2);
		return math.multiplicacao(NumberUtils.convertToDouble(n1), NumberUtils.convertToDouble(n2));
	}

	@RequestMapping(value = "/media/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double calculadoraMedia(@PathVariable("numberOne") String n1, @PathVariable("numberTwo") String n2) {
		NumberUtils.isNumeric(n1);
		NumberUtils.isNumeric(n2);
		return math.media(NumberUtils.convertToDouble(n1), NumberUtils.convertToDouble(n2));
	}

	@RequestMapping(value = "/div/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double calculadoraDiv(@PathVariable("numberOne") String n1, @PathVariable("numberTwo") String n2) {
		NumberUtils.isNumeric(n1);
		NumberUtils.isNumeric(n2);
		return math.divisao(NumberUtils.convertToDouble(n1), NumberUtils.convertToDouble(n2));
	}

	@RequestMapping(value = "/raiz2/{numberOne}", method = RequestMethod.GET)
	public Double calculadoraRaiz2(@PathVariable("numberOne") String n1) {
		NumberUtils.isNumeric(n1);
		return math.raizQuadrada(NumberUtils.convertToDouble(n1));
	}

}
