package com.pontointeligente.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.pontointeligente.api.entities.Lancamento;
import com.pontointeligente.api.repositories.LancamentoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LancamentoServiceTest {
	
	@MockBean
	private LancamentoRepository lancRep;
	
	@Autowired
	private LancamentoService lancService;
	
	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.lancRep.findByFuncionarioId(Mockito.anyLong(), Mockito.any(PageRequest.class)))
		.willReturn(new PageImpl<Lancamento>(new ArrayList<Lancamento>()));
		BDDMockito.given(this.lancRep.findById(Mockito.anyLong())).willReturn(Optional.ofNullable(new Lancamento()));
		BDDMockito.given(this.lancRep.save(Mockito.any(Lancamento.class))).willReturn(new Lancamento());
	}
	
	@Test
	public void testaBuscarLancamentoPorFuncionarioId() {
		Page<Lancamento> lancamento = this.lancService.buscaPorFuncionarioId(1L, PageRequest.of(0, 10));
		
		assertNotNull(lancamento);
	}
	
	@Test
	public void tentaBuscaLancamentoPorId() {
		Optional<Lancamento> lancamento = lancService.buscaPorId(1L);
		
		assertTrue(lancamento.isPresent());
	}
	
	@Test
	public void persistirLancamento() {
		Lancamento lancamento = lancService.persistir(new Lancamento());
		
		assertNotNull(lancamento);
	}
	
}
