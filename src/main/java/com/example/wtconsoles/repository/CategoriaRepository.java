package com.example.wtconsoles.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.wtconsoles.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long>{
	public Optional<Categoria> findByPlataformaContainingIgnoreCase(String plataforma);
}
