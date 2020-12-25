package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.BoCauHoi;
import com.example.demo.entity.Lop;
import com.example.demo.service.dto.BoCauHoiDTO;
import com.example.demo.service.dto.LopDTO;

public interface LopService {
	List<Lop> getAll();
	Optional<Lop> getByID(long id);
	Lop setData(LopDTO lopDTO);
	Optional<Object> delete(Long id);
	List<Lop> seach(String x);
	Optional<Lop> update(LopDTO lopDTO);

}
