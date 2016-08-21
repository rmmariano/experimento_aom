package tarefa2;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.esfinge.aom.api.model.IEntity;

public class ValidadorTarefa2 {
	
	public boolean validarEntidadeCobranca(IEntity cobranca) throws Exception{		
		
		String empresa = (String) cobranca.getProperty("empresa").getValue();		
		if(empresa == null || empresa.equals("")) return false;		
		
		
		String cnpj = (String) cobranca.getProperty("cnpj").getValue();
				
		Pattern expressao = Pattern.compile("[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}\\/[0-9]{4}\\-[0-9]{2}");
        Matcher matcher = expressao.matcher(cnpj);
 
        if( ! matcher.find() ) return false;		
		
		
		String destinatario = (String) cobranca.getProperty("destinatario").getValue();		
		if(!destinatario.contains("@")) return false;		
		
		
		Double valor = (Double) cobranca.getProperty("valor").getValue();		
		if( !(valor > 0 && valor <= 5000) ) return false;
		
		
		Date dateToday = new Date();		
		
		Date previsao = (Date) cobranca.getProperty("previsao").getValue();		
		if(previsao.getTime() < dateToday.getTime()) return false;		
		
		
		Date faturamento = (Date) cobranca.getProperty("faturamento").getValue();		
		if(faturamento.getTime() > dateToday.getTime()) return false;		
		
		
		Integer quantidadeItens = (Integer) cobranca.getProperty("quantidadeItens").getValue();		
		if(quantidadeItens < 1) return false;		
		
		
		return true;
	}

}
