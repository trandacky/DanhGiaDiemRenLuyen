package com.example.demo.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.BoCauHoi;
import com.example.demo.entity.CauHoi;
import com.example.demo.entity.ChiTietPhieuRenLuyen;
import com.example.demo.entity.Lop;
import com.example.demo.entity.PhieuRenLuyen;
import com.example.demo.entity.TaiKhoan;
import com.example.demo.service.BoCauHoiService;
import com.example.demo.service.ChiTietPhieuRenLuyenService;
import com.example.demo.service.LopService;
import com.example.demo.service.PhieuRenLuyenService;

@Controller
@RequestMapping(value = "/quanly/phatphieu")
public class PhatPhieuController {
	@Autowired
	private BoCauHoiService boCauHoiService;
	@Autowired
	private LopService lopService;
	@Autowired
	private ChiTietPhieuRenLuyenService chiTietPhieuRenLuyenService;
	@Autowired
	private PhieuRenLuyenService phieuRenLuyenService;

	@RequestMapping(value = { "", "/" })
	public String index(Model model) {
		String page = "/WEB-INF/jsp/admin/phatphieu.jsp";
		List<BoCauHoi> listBoCauHoi = boCauHoiService.getBoCauHoiTrue();
		List<Lop> listLop = lopService.getLopTrue();

		model.addAttribute("listLop", listLop);
		model.addAttribute("ListBoCauHoi", listBoCauHoi);
		model.addAttribute("page", page);
		model.addAttribute("activephatphieu", "active");

		return "adminMaster";
	}

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public String index2(Model model, HttpServletRequest request) {
		String back = request.getHeader("Referer");
		if (Boolean.parseBoolean(request.getParameter("phattoanbo")) != true) {
			int hocKy = Integer.parseInt(request.getParameter("hocky"));
			int namHoc = Integer.parseInt(request.getParameter("namhoc"));
			long idLop = 0;
			if (request.getParameter("idlop") != "") {
				idLop = Long.parseLong(request.getParameter("idlop"));
			} else {
				return "redirect:" + back;
			}
			List<BoCauHoi> listBoCauHoi = boCauHoiService.getBoCauHoiTrue();
			Lop lop = lopService.getByID(idLop).get();
			List<CauHoi> listCauHoi;
			List<TaiKhoan> listTaiKhoan = lop.getTaiKhoans();
			// trong lớp sẽ có nhiều tài khoản, mỗi tài khoản sẽ có nhiều bộ câu hỏi, mỗi bộ
			// câu hỏi có nhiều câu hỏi
			for (int i = 0; i < listTaiKhoan.size(); i++) {
				if (listTaiKhoan.get(i).getQuyen() == 0)
					continue;
				else if (listTaiKhoan.get(i).getQuyen() == 4 || (listTaiKhoan.get(i).getQuyen() == 3)) {
					PhieuRenLuyen phieuRenLuyen = new PhieuRenLuyen();
					phieuRenLuyen.setHocKy(hocKy);
					phieuRenLuyen.setNamHoc(namHoc);
					phieuRenLuyen.setMaSinhVien(listTaiKhoan.get(i));
					PhieuRenLuyen phieuRenLuyenForCauHoi = new PhieuRenLuyen();
					phieuRenLuyenForCauHoi = phieuRenLuyenService.setData(phieuRenLuyen);

					for (int j = 0; j < listBoCauHoi.size(); j++) {
						listCauHoi = listBoCauHoi.get(j).getCauHois();
						for (int k = 0; k < listCauHoi.size(); k++) {
							ChiTietPhieuRenLuyen chiTietPhieuRenLuyen = new ChiTietPhieuRenLuyen();
							chiTietPhieuRenLuyen.setIdCauHoi(listCauHoi.get(k));
							chiTietPhieuRenLuyen.setIdPhieuRenLuyen(phieuRenLuyenForCauHoi);
							chiTietPhieuRenLuyenService.setData(chiTietPhieuRenLuyen);
						}
					}
				}
			}
		} else {
			int hocKy = Integer.parseInt(request.getParameter("hocky").trim());
			int namHoc = Integer.parseInt(request.getParameter("namhoc").trim());

			List<BoCauHoi> listBoCauHoi = boCauHoiService.getBoCauHoiTrue();
			List<Lop> listLop = lopService.getAll();
			List<CauHoi> listCauHoi;

			// ở đây có nhiều lớp trong lớp sẽ có nhiều tài khoản, mỗi tài khoản sẽ có nhiều
			// bộ câu hỏi, mỗi bộ câu hỏi có nhiều câu hỏi
			for (int temp = 0; temp < listLop.size(); temp++) {
				List<TaiKhoan> listTaiKhoan = listLop.get(temp).getTaiKhoans();
				for (int i = 0; i < listTaiKhoan.size(); i++) {
					if (listTaiKhoan.get(i).getQuyen() == 0)
						continue;
					else if (listTaiKhoan.get(i).getQuyen() == 4 || (listTaiKhoan.get(i).getQuyen() == 3)) {
						PhieuRenLuyen phieuRenLuyen = new PhieuRenLuyen();
						phieuRenLuyen.setHocKy(hocKy);
						phieuRenLuyen.setNamHoc(namHoc);
						phieuRenLuyen.setMaSinhVien(listTaiKhoan.get(i));
						PhieuRenLuyen phieuRenLuyenForCauHoi = new PhieuRenLuyen();
						phieuRenLuyenForCauHoi = phieuRenLuyenService.setData(phieuRenLuyen);

						for (int j = 0; j < listBoCauHoi.size(); j++) {
							listCauHoi = listBoCauHoi.get(j).getCauHois();
							for (int k = 0; k < listCauHoi.size(); k++) {
								ChiTietPhieuRenLuyen chiTietPhieuRenLuyen = new ChiTietPhieuRenLuyen();
								chiTietPhieuRenLuyen.setIdCauHoi(listCauHoi.get(k));
								chiTietPhieuRenLuyen.setIdPhieuRenLuyen(phieuRenLuyenForCauHoi);
								chiTietPhieuRenLuyenService.setData(chiTietPhieuRenLuyen);
							}

						}
					}
				}
			}
		}
		return "redirect:" + back;
	}
}
