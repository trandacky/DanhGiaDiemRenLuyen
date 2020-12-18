package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Lop;

public interface LopService {
	List<Lop> getAll();
	Optional<Lop> getByID(long id);
	Lop setData(Lop lop);
	Optional<Lop> update(Lop lop);
	Optional<Object> delete(Long id);
}
