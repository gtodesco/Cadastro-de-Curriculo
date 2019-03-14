package com.example.cadastrocurriculo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.cadastrocurriculo.model.Curriculo;
import com.example.cadastrocurriculo.repository.CurriculoRepository;


@Service
public class CurriculoService {

	//@Autowired
	private CurriculoRepository curriculoRepository;
	
	public CurriculoService(CurriculoRepository curriculoRepository) {
		this.curriculoRepository = curriculoRepository;
	}

	public Curriculo salvar(Curriculo curriculo) {
		return curriculoRepository.save(curriculo);
	}

	public Optional<Curriculo> buscarPorID(Long id) {	
		return curriculoRepository.findById(id);
	}

	public void deletar(Long id) {
		curriculoRepository.deleteById(id);
	}

	public List<Curriculo> buscarCurriculos() {
		return curriculoRepository.findAll();		
	}

	public Optional<Curriculo> buscarPorCpf(String cpf) {
		return curriculoRepository.findByCpfIgnoreCase(cpf);
	}
}
