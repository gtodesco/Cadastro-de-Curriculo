package com.example.cadastrocurriculo.repository;

import com.example.cadastrocurriculo.model.Curriculo;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.parser.Entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurriculoRepository extends JpaRepository<Curriculo, Long>{

	public Optional<Curriculo> findByCpfIgnoreCase(String cpf);
	
}
