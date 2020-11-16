package com.example.demo.service;

import java.util.List;
import java.util.Optional;


import com.example.demo.entity.CauHoi;


public interface CauHoiService {
	List<CauHoi> getAll();
	CauHoi getByID(long id);
	CauHoi setData(CauHoi cauHoi);
	Optional<Object> update(CauHoi cauhoi);
	Optional<Object> delete(Long id);
}
