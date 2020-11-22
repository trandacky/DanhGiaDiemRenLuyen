package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.TaiKhoan;
import java.util.List;

@Repository
public interface TaiKhoanService {

	TaiKhoan setData(TaiKhoan TaiKhoan);

	Optional<Object> delete(Long id);

	TaiKhoan getByID(long id);

	List<TaiKhoan> getAll();

	Optional<Object> update(TaiKhoan TaiKhoan);

}
