package com.example.demo.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.TaiKhoan;
import com.example.demo.service.TaiKhoanService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private  TaiKhoanService taiKhoanService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		TaiKhoan nguoiDung = new TaiKhoan();
		if (taiKhoanService.getByID(username).isEmpty())
			throw new UsernameNotFoundException("User " + username + " was not found in the database");

		try {
			nguoiDung = taiKhoanService.getByID(username).get();
		} catch (Exception e) {

			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}
		// [ROLE_USER, ROLE_ADMIN,..]
		int roleNames = nguoiDung.getQuyen();

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (roleNames != 0) {
			String role;
			if(roleNames==1)
			 role = "ROLE_ADMIN";
			else
				if(roleNames==2)
			role = "ROLE_GIANGVIEN";
				else
					if(roleNames==3)
				role = "ROLE_CANBO";
					else
						if(roleNames==4)
					role = "ROLE_USER";
				else role=null;
			// ROLE_USER, ROLE_ADMIN,..
			GrantedAuthority authority = new SimpleGrantedAuthority(role);
			grantList.add(authority);

		}

		UserDetails userDetails = (UserDetails) new User(username, nguoiDung.getMatKhau(), grantList);

		return userDetails;
	}

}
