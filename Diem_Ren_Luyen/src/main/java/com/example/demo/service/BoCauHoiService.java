package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.BoCauHoi;
import com.example.demo.service.dto.BoCauHoiDTO;

public interface BoCauHoiService {
	List<BoCauHoi> getAll();
	Optional<BoCauHoi> getByID(long id);
	BoCauHoi setData(BoCauHoi boCauHoi);
	Optional<BoCauHoi> update(BoCauHoi boCauhoi);
	Optional<Object> delete(Long id);
	List<BoCauHoi> seach(String x);
}
