package com.example.wtconsoles.controller;

import java.math.BigDecimal;
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

import com.example.wtconsoles.model.Produto;
import com.example.wtconsoles.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin("*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Produto> postProduto(@Valid @RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> putProduto(@Valid @RequestBody Produto produtoAtual,@PathVariable Long id){
		return repository.findById(id).map(produto->{
			produto.setNomeProduto(produtoAtual.getNomeProduto());
			produto.setDescricao(produtoAtual.getDescricao());
			produto.setPreco(produtoAtual.getPreco());
			produto.setMarca(produtoAtual.getMarca());
			return ResponseEntity.ok(produto);
		}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		 repository.deleteById(id);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id){
		return repository.findById(id).map(produto->ResponseEntity.ok(produto))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getAllByNome(@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByNomeProdutoContainingIgnoreCase(nome));
	}
	
	@GetMapping("/max-preco/{price}")
	public ResponseEntity<List<Produto>> filterByPrice(@PathVariable BigDecimal price){
		return ResponseEntity.ok(repository.findAllByPrecoLessThan(price));
	}
	
}
