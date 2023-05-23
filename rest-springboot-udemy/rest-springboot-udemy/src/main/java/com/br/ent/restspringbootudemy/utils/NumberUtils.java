package com.br.ent.restspringbootudemy.utils;

import org.springframework.context.annotation.EnableMBeanExport;

import com.br.ent.restspringbootudemy.exception.UnsuportedMathOperationException;

@EnableMBeanExport
public class NumberUtils {
	public static Double convertToDouble(String number) throws NumberFormatException {
		if(number == null) return 0D;
		String n = number.replaceAll(",", ".");
		if (isNumeric(number)) return Double.parseDouble(n);
		return 0D;
	}
	
	public static boolean isNumeric(String number) {
		if(number == null) throw new UnsuportedMathOperationException("Please set a numeric value!");
		String n = number.replaceAll(",", ".");
		return n.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
}
