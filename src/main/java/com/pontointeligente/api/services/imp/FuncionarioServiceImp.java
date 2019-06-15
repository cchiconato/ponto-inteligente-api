package com.pontointeligente.api.services.imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pontointeligente.api.entities.Funcionario;
import com.pontointeligente.api.repositories.FuncionarioRepository;
import com.pontointeligente.api.services.FuncionarioService;

@Service
public class FuncionarioServiceImp implements FuncionarioService{

	@Autowired
	private FuncionarioRepository funcRep;

	@Override
	public Funcionario persistir(Funcionario funcionario) {
		return this.funcRep.save(funcionario);
	}

	@Override
	public Optional<Funcionario> buscaPorCpf(String cpf) {
		return Optional.ofNullable(this.funcRep.findByCpf(cpf));
	}

	@Override
	public Optional<Funcionario> buscaPorEmail(String email) {
		return Optional.ofNullable(this.funcRep.findByEmail(email));
	}

	@Override
	public Optional<Funcionario> buscaPorId(Long id) {
		return this.funcRep.findById(id);
	}

}
