package com.pontointeligente.api.repositories;

import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.pontointeligente.api.entities.Empresa;
import com.pontointeligente.api.entities.Funcionario;
import com.pontointeligente.api.entities.Lancamento;
import com.pontointeligente.api.enums.PerfilEnum;
import com.pontointeligente.api.enums.TipoEnum;
import com.pontointeligente.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LancamentoRepositoryTest {

	@Autowired
	private EmpresaRepository empresaRep;
	
	@Autowired
	private FuncionarioRepository funcRep;
	
	@Autowired
	private LancamentoRepository lancRep;
	
	private Long funcionarioId;
	
	@Before
	public void setUp() throws Exception {
		Empresa empresa = this.empresaRep.save(obterDadosEmpresa());
		Funcionario func = this.funcRep.save(obterDadosFuncionario(empresa));
		this.funcionarioId = func.getId();
		
		this.lancRep.save(obterDadosLancamento(func));
		this.lancRep.save(obterDadosLancamento(func));
	}

	@After
	public void tearDown() {
		this.empresaRep.deleteAll();
	}
	
	@Test
	public void testBuscarLancamentosPorFuncionarioId( ) {
		List<Lancamento> lancamentos = this.lancRep.findByFuncionarioId(funcionarioId);

		assertEquals(2, lancamentos.size());
	}
	
	@Test
	public void testBuscarLancamentosPorFuncionarioIdPaginado( ) {
		PageRequest page = PageRequest.of(0, 10);
		Page<Lancamento> lancamentos = this.lancRep.findByFuncionarioId(funcionarioId, page);

		assertEquals(2, lancamentos.getTotalElements());
	}
	
	private Funcionario obterDadosFuncionario(Empresa empresa) throws NoSuchAlgorithmException {
		Funcionario func = new Funcionario();;
		func.setNome("fulano test");
		func.setPerfil(PerfilEnum.ROLE_USUARIO);
		func.setSenha(PasswordUtils.gerarBCrypt("12345"));
		func.setCpf("321432122331");
		func.setEmail("teste@gmail.com");
		func.setEmpresa(empresa);
		return func;
	}
	
	private Empresa obterDadosEmpresa() {
		Empresa empresa = new Empresa();
		empresa.setRazaoSocial("Empresa teste");
		empresa.setCnpj("334324324234");
		return empresa;
	}
	
	private Lancamento obterDadosLancamento(Funcionario func) {
		Lancamento lancamento = new Lancamento();
		lancamento.setData(new Date());
		lancamento.setTipo(TipoEnum.INICIO_ALMOCO);
		lancamento.setFuncionario(func);
		
		return lancamento;
	}
}
