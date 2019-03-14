package com.example.cadastrocurriculo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cadastrocurriculo.exception.ResourceNotFoundException;
import com.example.cadastrocurriculo.model.Curriculo;
import com.example.cadastrocurriculo.repository.CurriculoRepository;
import com.example.cadastrocurriculo.service.CurriculoService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class CurriculoController {

	private CurriculoService curriculoService;

	public CurriculoController(CurriculoService curriculoService) {
		this.curriculoService = curriculoService;
	}
	
	@PostMapping("/curriculos")
	public ResponseEntity<Curriculo> criar(@RequestBody Curriculo curriculo){
		System.out.print("Criando um curriculo");
		curriculo = curriculoService.salvar(curriculo);
		return new ResponseEntity<Curriculo>(curriculo, HttpStatus.CREATED);
	}
	
	@PutMapping("/curriculos")
	public ResponseEntity<Void> alterar(@RequestBody Curriculo curriculo){
		System.out.println("Alterando curriculo");
		if(!curriculoService.buscarPorID(curriculo.getId()).isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		curriculoService.salvar(curriculo);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/curriculos/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id){
		System.out.println("Deletando curriculo");
		if(!curriculoService.buscarPorID(id).isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		curriculoService.deletar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/curriculos")
	public ResponseEntity<List<Curriculo>> buscarCurriculos(){
		System.out.println("Buscando curriculo");
		List<Curriculo> curriculos = curriculoService.buscarCurriculos();
		return new ResponseEntity<List<Curriculo>>(curriculos, HttpStatus.OK);		
	}
	
	@GetMapping("/curriculos/{id}")
	public ResponseEntity<Curriculo> buscarCurriculo(@PathVariable Long id){
		System.out.println("Buscando curriculo");
		Optional<Curriculo> curriculo = curriculoService.buscarPorID(id);
		if(curriculo.isPresent()) {
			return new ResponseEntity<Curriculo>(curriculo.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/curriculos/buscar")
	public ResponseEntity<Optional<Curriculo>> buscarCurriculoPorCPF(@PathParam(value = "cpf") String cpf){
		Optional<Curriculo> curriculo = curriculoService.buscarPorCpf(cpf);
		return new ResponseEntity<Optional<Curriculo>>(curriculo, HttpStatus.OK);
	}	
	
	
	
	
	
	
	
//	@Autowired
//	CurriculoRepository curriculoRepository;
//	
//	// Get All Curriculos
//	@GetMapping("/curriculos")
//	public List<Curriculo> getAll(){
//		return curriculoRepository.findAll();
//	}
//	
//	// Create a new Curriculo
//	@PostMapping("/curriculos")
//	public Curriculo createCurriculo(@Valid @RequestBody Curriculo curriculo) {
//		return curriculoRepository.save(curriculo);
//	}
//	
//	// Get a Single Curriculo
//	@GetMapping("/curriculos/{id}")
//	public Curriculo getCurriculoById(@PathVariable(value = "id") Long curriculoId) {
//		return curriculoRepository.findById(curriculoId).orElseThrow(() -> new ResourceNotFoundException("Curriculo", "id", curriculoId));
//	}
//	
//	// Update a Curriculo
//	@PutMapping("/curriculos/{id}")
//	public Curriculo updateCurriculo(@PathVariable(value = "id") Long curriculoId, @Valid @RequestBody Curriculo curriculoDetails) {
//
//	    Curriculo curriculo = curriculoRepository.findById(curriculoId).orElseThrow(() -> new ResourceNotFoundException("Curriculo", "id", curriculoId));
//
//	    curriculo.setNome(curriculoDetails.getNome());
//	    curriculo.setEmail(curriculoDetails.getEmail());
//	    curriculo.setCpf(curriculoDetails.getCpf());
//	    curriculo.setDataNascimento(curriculoDetails.getDataNascimento());
//	    curriculo.setCep(curriculoDetails.getCep());
//	    curriculo.setSexo(curriculoDetails.getSexo());
//	    curriculo.setEscolaridade(curriculoDetails.getEscolaridade());
//	    curriculo.setFormacao(curriculoDetails.getFormacao());
//	    curriculo.setExperienciaProfissional(curriculoDetails.getExperienciaProfissional());
//	    curriculo.setPassword(curriculoDetails.getPassword());
//	    
//	    Curriculo updatedCurriculo = curriculoRepository.save(curriculo);
//	    return updatedCurriculo;
//	}	
//	
//	// Deleta a Curriculo
//	@DeleteMapping("/curriculos/{id}")
//	public ResponseEntity<?> deleteCurriculo(@PathVariable(value = "id") Long curriculoId) {
//	    Curriculo curriculo = curriculoRepository.findById(curriculoId).orElseThrow(() -> new ResourceNotFoundException("Curriculo", "id", curriculoId));
//
//	    curriculoRepository.delete(curriculo);
//
//	    return ResponseEntity.ok().build();
//	}
	
}