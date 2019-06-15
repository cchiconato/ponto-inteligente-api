package com.pontointeligente.api.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.NoSuchAlgorithmException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.pontointeligente.api.entities.Empresa;
import com.pontointeligente.api.entities.Funcionario;
import com.pontointeligente.api.enums.PerfilEnum;
import com.pontointeligente.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class FuncionarioRepositoryTest {

	@Autowired
	private EmpresaRepository empresaRep;
	
	@Autowired
	private FuncionarioRepository funcRep;
	
	private static final String email = "email@email.com";
	private static final String cpf = "443432435";
	
	@Before
	public void setUp() throws Exception {
		Empresa empresa = this.empresaRep.save(obterDadosEmpresa());
		this.funcRep.save(obterDadosFuncionario(empresa));
	}
	
	@After
	public void tearDown() {
		this.empresaRep.deleteAll();
	}
	
	@Test
	public void testBuscarFuncionarioPorEmail( ) {
		Funcionario funcionario = this.funcRep.findByEmail(email);
		
		assertEquals(email, funcionario.getEmail());
	}
	
	@Test
	public void tentaBuscarFuncionarioPorCpfOuEmail() {
		Funcionario func = this.funcRep.findByCpfOrEmail(cpf, email);
		
		assertNotNull(func);
	}
	
	private Funcionario obterDadosFuncionario(Empresa empresa) throws NoSuchAlgorithmException {
		Funcionario func = new Funcionario();;
		func.setNome("fulano test");
		func.setPerfil(PerfilEnum.ROLE_USUARIO);
		func.setSenha(PasswordUtils.gerarBCrypt("12345"));
		func.setCpf(cpf);
		func.setEmail(email);
		func.setEmpresa(empresa);
		return func;
	}
	
	private Empresa obterDadosEmpresa() {
		Empresa empresa = new Empresa();
		empresa.setRazaoSocial("Empresa teste");
		empresa.setCnpj("334324324234");
		return empresa;
	}
}
