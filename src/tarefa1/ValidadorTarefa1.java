package tarefa1;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.esfinge.aom.api.model.IEntity;
import org.esfinge.aom.model.dynamic.factory.AdapterFactory;

public class ValidadorTarefa1 {
	
	public boolean validarEntidadePessoa(IEntity pessoa) throws Exception{		
		
		AdapterFactory af = AdapterFactory.getInstance("AnnotationMapping.json");
		Object javaBean = af.generate(pessoa);		
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Object>> violations = validator.validate(javaBean);
		
		if(violations.isEmpty()) return true;
		
		return false;
	}

}
