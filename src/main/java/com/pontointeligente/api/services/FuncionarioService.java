package com.pontointeligente.api.services;

import java.util.Optional;

import com.pontointeligente.api.entities.Funcionario;

public interface FuncionarioService {
	
	Funcionario persistir(Funcionario funcionario);
	
	Optional<Funcionario> buscaPorCpf(String cpf);
	
	Optional<Funcionario> buscaPorEmail(String email);

	Optional<Funcionario> buscaPorId(Long id);
}
