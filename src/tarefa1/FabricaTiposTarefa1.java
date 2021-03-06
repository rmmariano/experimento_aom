package tarefa1;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.esfinge.aom.api.model.IEntityType;
import org.esfinge.aom.exceptions.EsfingeAOMException;
import org.esfinge.aom.model.impl.GenericEntityType;
import org.esfinge.aom.model.impl.GenericPropertyType;


public class FabricaTiposTarefa1 {
	
	public static IEntityType getTipoEntidadePessoa() throws EsfingeAOMException{
		IEntityType tipoPessoa = new GenericEntityType("Pessoa");
		
		//criando property types
		GenericPropertyType nomePropertyType = new GenericPropertyType("nome", String.class);
		GenericPropertyType alturaPropertyType = new GenericPropertyType("altura", Integer.class);
		GenericPropertyType cpfPropertyType = new GenericPropertyType("cpf", String.class);
		GenericPropertyType emailPropertyType = new GenericPropertyType("email", String.class);
		GenericPropertyType profissaoPropertyType = new GenericPropertyType("profissao", String.class);
		GenericPropertyType nascimentoPropertyType = new GenericPropertyType("nascimento", Date.class);
		GenericPropertyType loginPropertyType = new GenericPropertyType("login", String.class);
		
		nomePropertyType.setProperty("size.min", 6);
		nomePropertyType.setProperty("size.max", 49);		
		alturaPropertyType.setProperty("min", 1);
		alturaPropertyType.setProperty("max", 215);	
//		cpfPropertyType.setProperty("cpf", "");
		cpfPropertyType.setProperty("pattern", "[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}");
		emailPropertyType.setProperty("email", "");
		profissaoPropertyType.setProperty("notempty", "");		
		nascimentoPropertyType.setProperty("past", 0);
		loginPropertyType.setProperty("size.min", 7);
		
		//adicionando property types no tipo de entidade
		tipoPessoa.addPropertyType(nomePropertyType);
		tipoPessoa.addPropertyType(alturaPropertyType);
		tipoPessoa.addPropertyType(cpfPropertyType);
		tipoPessoa.addPropertyType(emailPropertyType);
		tipoPessoa.addPropertyType(profissaoPropertyType);
		tipoPessoa.addPropertyType(nascimentoPropertyType);
		tipoPessoa.addPropertyType(loginPropertyType);
		
		return tipoPessoa;
	}

}
