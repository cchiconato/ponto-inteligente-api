package com.pontointeligente.api.services.imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.pontointeligente.api.entities.Lancamento;
import com.pontointeligente.api.repositories.LancamentoRepository;
import com.pontointeligente.api.services.LancamentoService;

@Service
public class LancamentoServiceImp implements LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Override
	public Page<Lancamento> buscaPorFuncionarioId(Long funcionarioId, PageRequest pageRequest) {
		return this.lancamentoRepository.findByFuncionarioId(funcionarioId, pageRequest);
	}

	@Override
	public Optional<Lancamento> buscaPorId(Long id) {
		return lancamentoRepository.findById(id);
	}

	@Override
	public Lancamento persistir(Lancamento lancamento) {
		return lancamentoRepository.save(lancamento);
	}

	@Override
	public void remover(Long id) {
		lancamentoRepository.deleteById(id);
	}
}
