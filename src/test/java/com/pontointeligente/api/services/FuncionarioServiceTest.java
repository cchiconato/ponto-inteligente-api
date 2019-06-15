package com.pontointeligente.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.pontointeligente.api.entities.Funcionario;
import com.pontointeligente.api.repositories.FuncionarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class FuncionarioServiceTest {
	
	@MockBean
	private FuncionarioRepository funcRep;
	
	@Autowired
	private FuncionarioService funcService;
	
	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.funcRep.save(Mockito.any(Funcionario.class))).willReturn(new Funcionario());
		BDDMockito.given(this.funcRep.findById(Mockito.anyLong())).willReturn(Optional.ofNullable(new Funcionario()));
		BDDMockito.given(this.funcRep.findByEmail(Mockito.anyString())).willReturn(new Funcionario());
		BDDMockito.given(this.funcRep.findByCpf(Mockito.anyString())).willReturn(new Funcionario());
	}
	
	@Test
	public void tentaPersistirFuncionario() {
		Funcionario funcionario = this.funcService.persistir(new Funcionario());
		
		assertNotNull(funcionario);
	}
	
	@Test
	public void tentaBuscarPorId() {
		Optional<Funcionario> func = funcService.buscaPorId(1L);
		
		assertTrue(func.isPresent());
	}
	
	@Test
	public void tentaBuscarPorEmail() {
		Optional<Funcionario> func = funcService.buscaPorEmail("teste@gmail.com");
		
		assertTrue(func.isPresent());
	}
	
	@Test
	public void tentaBuscarPorCpf() {
		Optional<Funcionario> func = funcService.buscaPorCpf("421442");
		
		assertTrue(func.isPresent());
	}
}
