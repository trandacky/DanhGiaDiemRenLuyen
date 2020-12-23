package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.BoCauHoi;
import com.example.demo.service.dto.BoCauHoiDTO;

public interface BoCauHoiService {
	List<BoCauHoi> getAll();
	Optional<BoCauHoi> getByID(long id);
	Optional<Object> delete(Long id);
	List<BoCauHoi> seach(String x);
	BoCauHoi setData(BoCauHoiDTO boCauHoiDTO);
	Optional<BoCauHoi> update(BoCauHoiDTO boCauHoiDTO);
	void updateTen(long id, String ten);
}
