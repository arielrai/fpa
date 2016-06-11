package fpa.validation;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validates a date after another 
 * @author arielrai (arielrairodrigues@gmail.com)
 *
 */
public class AfterDateConstraintValidator implements ConstraintValidator<AfterDateValidator, Serializable> {

	//Campo da data inicial
	private String previousDateField;
	//Campo da data inicial
	private String afterDateField;

	@Override
	public void initialize(AfterDateValidator constraintAnnotation) {
		previousDateField = constraintAnnotation.getPreviousDateField();
		afterDateField = constraintAnnotation.getAfterDateField();
	}

	@Override
	public boolean isValid(Serializable value, ConstraintValidatorContext context) {
		try {
			//Busca os campos para validação
			Field previousfield = value.getClass().getDeclaredField(previousDateField);
			previousfield.setAccessible(true);
			Field afterField = value.getClass().getDeclaredField(afterDateField);
			afterField.setAccessible(true);
			
			//Busca os valores dos campos
			LocalDate previousDate = (LocalDate)previousfield.get(value);
			LocalDate afterDate = (LocalDate)afterField.get(value);
			
			//Se ambos forem nulos não valida
			if (afterDate == null && previousDate == null) {
				return true;
			}
			
			//Verifica se o valor do campo da data anterior é realemente anterior
			return previousDate.isBefore(afterDate);
			
		} catch (SecurityException | NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return false;
	}

}
