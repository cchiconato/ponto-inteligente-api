package com.pontointeligente.api.repositories;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.pontointeligente.api.entities.Empresa;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmpresaRepositoryTest {

	@Autowired
	private EmpresaRepository empresaRep;
	
	private static final String CNPJ = "54545435355";
	
	@Before
	public void setUp() throws Exception {
		Empresa empresa = new Empresa();
		empresa.setRazaoSocial("Empresa teste");
		empresa.setCnpj(CNPJ);
		this.empresaRep.save(empresa);
	}
	
	@After
	public void tearDown() {
		this.empresaRep.deleteAll();
	}
	
	@Test
	public void testBuscaPorCnpj( ) {
		Empresa empresa = this.empresaRep.findByCnpj(CNPJ);
		
		assertEquals(CNPJ, empresa.getCnpj());
	}
}
