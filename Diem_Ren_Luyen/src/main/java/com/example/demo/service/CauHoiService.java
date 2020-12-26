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
	Optional<Object> updatebocauhoi(Long idCauHoi, Long idBoCauHoi);
	List<CauHoi> seach(String seachString);
	Optional<CauHoi> updatenoidung(long id, String noidung, int diemtoida);
	List<CauHoi> getCauHoiByIDBoCauHoi(long idBoCauHoi);
	List<CauHoi> getCauHoiTrue();
}
