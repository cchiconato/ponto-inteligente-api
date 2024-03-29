package com.pontointeligente.api.services.imp;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pontointeligente.api.entities.Empresa;
import com.pontointeligente.api.repositories.EmpresaRepository;
import com.pontointeligente.api.services.EmpresaService;

@Service
public class EmpresaServiceImp implements EmpresaService{

	private static final Logger log = LoggerFactory.getLogger(EmpresaServiceImp.class);
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Override
	public Optional<Empresa> buscaPorCnpj(String cnpj) {
		log.info("Buscando uma empresa para o CNPJ {}", cnpj);
		return Optional.ofNullable(empresaRepository.findByCnpj(cnpj));
	}

	@Override
	public Empresa persistir(Empresa empresa) {
		log.info("Buscando uma empresa para o empresa {}", empresa);
		return this.empresaRepository.save(empresa);
	}

}
