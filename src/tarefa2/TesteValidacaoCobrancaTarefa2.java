package tarefa2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.esfinge.aom.api.model.IEntity;
import org.esfinge.aom.api.model.IEntityType;
import org.esfinge.aom.exceptions.EsfingeAOMException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TesteValidacaoCobrancaTarefa2 {
	
	private static IEntityType tipoCobranca;
	private IEntity cobranca; 
	private static ValidadorTarefa2 validador;
	
	@BeforeClass
	public static void criarTipoEntidade() throws EsfingeAOMException{
		tipoCobranca = FabricaTiposTarefa2.getTipoEntidadeCobranca();
	}
	
	@BeforeClass
	public static void criarValidador(){
		validador = new ValidadorTarefa2();
	}
	
	@Before
	public void criarCobrancaComInformacoesCorretas() throws EsfingeAOMException{
		cobranca = tipoCobranca.createNewEntity();
		cobranca.setProperty("empresa", "Servico Executa S.A.");
		cobranca.setProperty("cnpj", "15.043.602/0001-01");
		cobranca.setProperty("destinatario", "comprador@empresa.com.br");
		cobranca.setProperty("valor", 2000.00);
		long agora = System.currentTimeMillis();
		cobranca.setProperty("previsao", new Date(agora+100000));
		cobranca.setProperty("faturamento", new Date(agora-100000));
		cobranca.setProperty("quantidadeItens", 3);
	}

	@Test
	public void validaCobrancaBase() throws Exception{
		assertTrue("A cobrança definida como base deve possuir todas as informações corretas",
				validador.validarEntidadeCobranca(cobranca));
	}
	
	@Test
	public void empresaNula() throws Exception{
		cobranca.setProperty("empresa", null);
		assertFalse("A empresa não pode ser nula",
				validador.validarEntidadeCobranca(cobranca));
	}
	
	@Test
	public void empresaVazia() throws Exception{
		cobranca.setProperty("empresa", "");
		assertFalse("A empresa não pode ser uma String vazia",
				validador.validarEntidadeCobranca(cobranca));
	}
	
	@Test
	public void cnpjSemTraco() throws Exception{
		cobranca.setProperty("cnpj", "15.043.602/000101");
		assertFalse("O CNPJ não está no formato adequado",
				validador.validarEntidadeCobranca(cobranca));
	}
	
	@Test
	public void cnpjSemPonto() throws Exception{
		cobranca.setProperty("cnpj", "15.043602/0001-01");
		assertFalse("O CNPJ não está no formato adequado",
				validador.validarEntidadeCobranca(cobranca));
	}
	
	@Test
	public void cnpjSemBarra() throws Exception{
		cobranca.setProperty("cnpj", "15.043.6020001-01");
		assertFalse("O CNPJ não está no formato adequado",
				validador.validarEntidadeCobranca(cobranca));
	}
	
	@Test
	public void cnpjComMenosNumeros() throws Exception{
		cobranca.setProperty("cnpj", "15.04.60/001-01");
		assertFalse("O CNPJ não está no formato adequado",
				validador.validarEntidadeCobranca(cobranca));
	}
	
	@Test
	public void destinatarioSemArroba() throws Exception{
		cobranca.setProperty("destinatario", "comprador.empresa.com.br");
		assertFalse("O destinatario precisa possuir um arroba",
				validador.validarEntidadeCobranca(cobranca));
	}
	
	@Test
	public void valorMenorQueZero() throws Exception{
		cobranca.setProperty("valor", -1000.00);
		assertFalse("O valor precisa ser maior que zero",
				validador.validarEntidadeCobranca(cobranca));
	}
	
	@Test
	public void valorMaiorQueLimite() throws Exception{
		cobranca.setProperty("valor", 6000.00);
		assertFalse("O valor não pode ser maior que 5000",
				validador.validarEntidadeCobranca(cobranca));
	}
	
	@Test
	public void previsaoNoPassado() throws Exception{
		long agora = System.currentTimeMillis();
		cobranca.setProperty("previsao", new Date(agora-100000));
		assertFalse("A data da previsão deve ser no futuro",
				validador.validarEntidadeCobranca(cobranca));
	}
	
	@Test
	public void faturamentoNoFuturo() throws Exception{
		long agora = System.currentTimeMillis();
		cobranca.setProperty("faturamento", new Date(agora+100000));
		assertFalse("A data de faturamento deve ser no passado",
				validador.validarEntidadeCobranca(cobranca));
	}
	
	@Test
	public void quantidadeDeItensZero() throws Exception{
		cobranca.setProperty("quantidadeItens", 0);
		assertFalse("A quantidade de itens não pode ser zero",
				validador.validarEntidadeCobranca(cobranca));
	}
	
	
}
