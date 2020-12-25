package com.example.demo.service;

import java.util.List;
import java.util.Optional;


import com.example.demo.entity.CauHoi;
import com.example.demo.service.dto.BoCauHoiDTO;
import com.example.demo.service.dto.CauHoiDTO;


public interface CauHoiService {
	List<CauHoi> getAll();
	CauHoi getByID(long id);
	CauHoi setData(CauHoiDTO bch);
	Optional<Object> delete(Long id);
	Optional<Object> update(CauHoiDTO cauHoi);
}
