package tarefa1;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.esfinge.aom.api.model.IEntity;
import org.esfinge.aom.api.model.IEntityType;
import org.esfinge.aom.exceptions.EsfingeAOMException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class TesteValidacaoPessoaTarefa1 {
	
	private static IEntityType tipoPessoa;
	private IEntity pessoa; 
	private static ValidadorTarefa1 validador;
	
	@BeforeClass
	public static void criarTipoEntidade() throws EsfingeAOMException{
		tipoPessoa = FabricaTiposTarefa1.getTipoEntidadePessoa();
	}
	
	@BeforeClass
	public static void criarValidador(){
		validador = new ValidadorTarefa1();
	}
	
	@Before
	public void criarPessoaComInformacoesCorretas() throws EsfingeAOMException, ParseException{
		pessoa = tipoPessoa.createNewEntity();
		pessoa.setProperty("nome", "Pedro Paulo da Silva Pereira");
		pessoa.setProperty("altura", 180);
		pessoa.setProperty("cpf", "012.717.436-20");
		pessoa.setProperty("email", "pedro@dominio.com.br");
		pessoa.setProperty("profissao", "programador");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		pessoa.setProperty("nascimento", sdf.parse("09/06/1980"));
		pessoa.setProperty("login", "pedro.pereira");
	}

	@Test
	public void validaPessoaBase() throws Exception{
		assertTrue("A pessoa definida como base deve possuir todas as informações corretas",
				validador.validarEntidadePessoa(pessoa));
	}
	
	@Test
	public void nomePequenoDemais() throws Exception{
		pessoa.setProperty("nome", "Pepê");
		assertFalse("O nome precisa possuir mais de 5 caracteres",
				validador.validarEntidadePessoa(pessoa));
	}
	
	@Test
	public void nomeGrandeDemais() throws Exception{
		pessoa.setProperty("nome", "Pedro Paulo Oliveira Fonceca Santos Martins "+
							"Ferraz Marinez de Orleans e Brangança da Silva Pinto");
		assertFalse("O nome precisa possuir menos de 50 caracteres",
				validador.validarEntidadePessoa(pessoa));
	}
	
	@Test
	public void alturaNegativa() throws Exception{
		pessoa.setProperty("altura", -10);
		assertFalse("A altura deve ser maior que zero",
				validador.validarEntidadePessoa(pessoa));
	}
	
	@Test
	public void alturaMuitoGrande() throws Exception{
		pessoa.setProperty("altura", 300);
		assertFalse("A altura deve ser menor ou igual que 215",
				validador.validarEntidadePessoa(pessoa));
	}
	
	@Test
	public void cpfSemTraco() throws Exception{
		pessoa.setProperty("cpf", "012.717.43620");
		assertFalse("O CPF não está no formato adequado",
				validador.validarEntidadePessoa(pessoa));
	}
	
	@Test
	public void cpfSemPonto() throws Exception{
		pessoa.setProperty("cpf", "012717436-20");
		assertFalse("O CPF não está no formato adequado",
				validador.validarEntidadePessoa(pessoa));
	}
	
	@Test
	public void cpfIncompleto() throws Exception{
		pessoa.setProperty("cpf", "01.71.43-20");
		assertFalse("O CPF não está no formato adequado",
				validador.validarEntidadePessoa(pessoa));
	}
	
	@Test
	public void emailSemArroba() throws Exception{
		pessoa.setProperty("email", "pedro.dominio.com.br");
		assertFalse("O email precisa ter arroba",
				validador.validarEntidadePessoa(pessoa));
	}
	
	@Test
	public void profissaoNula() throws Exception{
		pessoa.setProperty("profissao", null);
		assertFalse("A profissão não pode ser nula",
				validador.validarEntidadePessoa(pessoa));
	}
	
	@Test
	public void profissaoVazia() throws Exception{
		pessoa.setProperty("profissao", "");
		assertFalse("A profissão não pode ser uma String vazia",
				validador.validarEntidadePessoa(pessoa));
	}
	
	@Test
	public void nascimentoNoFuturo() throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		pessoa.setProperty("nascimento", sdf.parse("09/06/2020"));
		assertFalse("O nascimento precisa ser no passado",
				validador.validarEntidadePessoa(pessoa));
	}
	
	@Test
	public void loginPequenoDemais() throws Exception{
		pessoa.setProperty("login", "pepe");
		assertFalse("O login precisa possuir mais que 7 caracteres",
				validador.validarEntidadePessoa(pessoa));
	}

}
