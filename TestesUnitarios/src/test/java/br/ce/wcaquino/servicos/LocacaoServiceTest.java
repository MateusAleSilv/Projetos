package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmesSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {

	private LocacaoService service;

	//private static int contador = 0;

	@Rule
	public ErrorCollector error = new ErrorCollector();

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void setup(){
	//	System.out.println("Before");
		service = new LocacaoService();
	//	contador++;
	//	System.out.println(contador);
	}

	@After
	public void tearDown(){
		System.out.println("After");
	}

//	@BeforeClass
//	public static void setupClass(){
//		System.out.println("Before");
//	}

//	@AfterClass
//	public static void tearDownClass(){
//		System.out.println("After");
//	}

	@Test
	public void deveAlugarFilme() throws Exception {
		Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

		// Cenario
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filme = Arrays.asList(new Filme("Filme 1", 2, 5.0));

		// Ação
		Locacao locacao = service.alugarFilme(usuario, filme);

		// Verificação
		assertThat(locacao.getValor(), is(5.0));
		assertThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		assertThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));

		// Outro tipo de verificação
		error.checkThat(locacao.getValor(), is(5.0));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),
				is(true));

	}

	@Test(expected = FilmesSemEstoqueException.class)
	public void naoDeveAlugarFilmeSemEstoque() throws Exception {

		// Cenario
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filme = Arrays.asList(new Filme("Filme 1", 0, 5.0));

		// Ação
		service.alugarFilme(usuario, (List<Filme>) filme);

	}

	@Test
	public void naoDeveAlugarFilmeSemUsuario() throws FilmesSemEstoqueException {

		// Cenario
		List<Filme> filme = Arrays.asList(new Filme("Filme 1", 1, 5.0));
		//Usuario usuario = new Usuario("Usuário 1");

		// Ação
		try {
			service.alugarFilme(null, filme);
			Assert.fail();
		} catch (LocadoraException e) {
			assertThat(e.getMessage(), is("Usuário vazio"));
		}
	}
	
	
	@Test
	public void naoDeveAlugarFilmeSemFilme() throws FilmesSemEstoqueException, LocadoraException {
		//Cenario
		Usuario usuario = new Usuario("Usuário 1");
		
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		
		//Ação
		service.alugarFilme(usuario, null);
	}

	@Test
	public void devePagar75PcNoFilme3() throws LocadoraException, FilmesSemEstoqueException {
		// Cenario
		Usuario usuario =new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(
				new Filme("Filme 1", 2, 4.0),
				new Filme("Filme 2", 2, 4.0),
				new Filme("Filme 3", 2, 4.0));

		// Ação
		Locacao resultado = service.alugarFilme(usuario, filmes);

		// Verificação
		assertThat(resultado.getValor(), is(11.0));
	}

	@Test
	public void devePagar50PcNoFilme4() throws LocadoraException, FilmesSemEstoqueException {
		// Cenario
		Usuario usuario =new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(
				new Filme("Filme 1", 2, 4.0),
				new Filme("Filme 2", 2, 4.0),
				new Filme("Filme 3", 2, 4.0),
				new Filme("Filme 4", 2, 4.0));

		// Ação
		Locacao resultado = service.alugarFilme(usuario, filmes);

		// Verificação
		assertThat(resultado.getValor(), is(13.0));
	}

	@Test
	public void devePagar25PcNoFilme5() throws LocadoraException, FilmesSemEstoqueException {
		// Cenario
		Usuario usuario =new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(
				new Filme("Filme 1", 2, 4.0),
				new Filme("Filme 2", 2, 4.0),
				new Filme("Filme 3", 2, 4.0),
				new Filme("Filme 4", 2, 4.0),
				new Filme("Filme 4", 2, 4.0));

		// Ação
		Locacao resultado = service.alugarFilme(usuario, filmes);

		// Verificação
		assertThat(resultado.getValor(), is(14.0));
	}
	@Test
	public void devePagar0PcNoFilme6() throws LocadoraException, FilmesSemEstoqueException {
		// Cenario
		Usuario usuario =new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(
				new Filme("Filme 1", 2, 4.0),
				new Filme("Filme 2", 2, 4.0),
				new Filme("Filme 3", 2, 4.0),
				new Filme("Filme 4", 2, 4.0),
				new Filme("Filme 5", 2, 4.0),
				new Filme("Filme 6", 2, 4.0));

		// Ação
		Locacao resultado = service.alugarFilme(usuario, filmes);

		// Verificação
		assertThat(resultado.getValor(), is(14.0));
	}

	@Test
	public void deveDevolverNaSegundaQuandoAlugaNoSabado() throws LocadoraException, FilmesSemEstoqueException {
		Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

		// Cenario
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 1, 5.0));

		// Ação
		Locacao retorno = service.alugarFilme(usuario, filmes);

		// Verificação
		boolean ehSegunda = DataUtils.verificarDiaSemana(retorno.getDataRetorno(), Calendar.MONDAY);
		Assert.assertTrue(ehSegunda);
	}
}
