package fpa.validation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Anotação para valicação de uma data posterior a outra
 * @author arielrai (arielrairodrigues@gmail.com)
 *
 */
@Constraint(validatedBy = AfterDateConstraintValidator.class)
@Target({TYPE})
@Retention(RUNTIME)
public @interface AfterDateValidator {

	/**
	 * Campo da data anterior
	 * @return uma {@link String} com o nome do campo
	 */
	String getPreviousDateField();
	
	/**
	 * Campo da data posterior
	 * @return uma {@link String} com o nome do campo
	 */
	String getAfterDateField();
	
	Class<? extends Payload>[] payload() default { };
	
	Class<?>[] groups() default {};
	
	String message() default "A data final deve ser posterior à data inicial";
}
