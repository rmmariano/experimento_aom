package tarefa2;

import java.util.Date;

import org.esfinge.aom.api.model.IEntityType;
import org.esfinge.aom.exceptions.EsfingeAOMException;
import org.esfinge.aom.model.impl.GenericEntityType;
import org.esfinge.aom.model.impl.GenericPropertyType;

public class FabricaTiposTarefa2 {

	public static IEntityType getTipoEntidadeCobranca() throws EsfingeAOMException{
		IEntityType tipoCobranca = new GenericEntityType("Cobranca");
	
		//criando property types
		GenericPropertyType empresaPropertyType = new GenericPropertyType("empresa", String.class);
		GenericPropertyType cnpjPropertyType = new GenericPropertyType("cnpj", String.class);
		GenericPropertyType destinatarioPropertyType = new GenericPropertyType("destinatario", String.class);
		GenericPropertyType valorPropertyType = new GenericPropertyType("valor", Double.class);
		GenericPropertyType previsaoPropertyType = new GenericPropertyType("previsao", Date.class);
		GenericPropertyType faturamentoPropertyType = new GenericPropertyType("faturamento", Date.class);
		GenericPropertyType quantidadeItensPropertyType = new GenericPropertyType("quantidadeItens", Integer.class);

		//adicionando property types no tipo de entidade
		tipoCobranca.addPropertyType(empresaPropertyType);
		tipoCobranca.addPropertyType(cnpjPropertyType);
		tipoCobranca.addPropertyType(destinatarioPropertyType);
		tipoCobranca.addPropertyType(valorPropertyType);
		tipoCobranca.addPropertyType(previsaoPropertyType);
		tipoCobranca.addPropertyType(faturamentoPropertyType);
		tipoCobranca.addPropertyType(quantidadeItensPropertyType);
		
		return tipoCobranca;
	}
	
}
