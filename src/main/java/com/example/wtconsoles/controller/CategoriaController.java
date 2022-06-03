package com.example.wtconsoles.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.example.wtconsoles.model.Categoria;
import com.example.wtconsoles.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
@CrossOrigin("*")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Categoria> postCategoria(@Valid @RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> putCategoria(@Valid @RequestBody Categoria categoriaA,@PathVariable Long id){
		return repository.findById(id).map(categoria->{
			categoria.setPlatforma(categoriaA.getPlataforma());
			return ResponseEntity.ok(categoria);
		}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public void deleteCategoria(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getById(@PathVariable Long id){
		return repository.findById(id).map(categoria->ResponseEntity.ok(categoria))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/plataforma/{nome}")
	public ResponseEntity<Categoria> getByPlataforma(@PathVariable String nome){
		return repository.findByPlataformaContainingIgnoreCase(nome)
				.map(categoria->ResponseEntity.ok(categoria)).orElse(ResponseEntity.notFound().build());
	}
}
