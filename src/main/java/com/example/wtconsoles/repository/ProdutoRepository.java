package com.example.wtconsoles.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.wtconsoles.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long>{
	public List<Produto> findAllByPrecoLessThan(BigDecimal price);
	public List<Produto> findAllByNomeProdutoContainingIgnoreCase(String nome);
}
