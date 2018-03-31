package com.sistema.easyservice.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class OrdemServicoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return OrdemServicoValidator.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmpty(errors, "cliente.id", "Cliente é obrigatorio");
		ValidationUtils.rejectIfEmpty(errors, "responsavel.id", "Cliente é obrigatorio");
		
	}
	
}
