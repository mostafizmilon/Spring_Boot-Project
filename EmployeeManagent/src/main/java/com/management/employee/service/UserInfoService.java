package com.management.employee.service;

import com.management.employee.enums.Role;
import com.management.employee.model.UserInfo;
import com.management.employee.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserInfoService {

	@Autowired
	private UserDetailsRepository userDatailsRepository;

	public UserInfo getUserInfoByEmail(String userName) {
		return userDatailsRepository.findByEmailAndEnabled(userName, true);
	}

	public List<UserInfo> getAllUserInfo() {
		return userDatailsRepository.findAllByRoleOrderByEnabled(Role.USER);
	}

	public UserInfo getUserInfoById(Integer id) {
		return userDatailsRepository.findById(id).get();
	}

	public UserInfo addUser(UserInfo userInfo) {
		userInfo.setPassword(new BCryptPasswordEncoder().encode(userInfo.getPassword()));
		return userDatailsRepository.save(userInfo);
	}

	public UserInfo updateUser(int id, UserInfo userInfo) {
		return userDatailsRepository.save(userInfo);
	}

	/*public UserInfo updateUser(Integer id, UserInfo userRecord) {
		UserInfo userInfo = userDatailsRepository.findById(id);
		userInfo.setUserName(userRecord.getUserName());
		userInfo.setPassword(userRecord.getPassword());
		userInfo.setRole(userRecord.getRole());
		userInfo.setEnabled(userRecord.getEnabled());
		return userDatailsRepository.save(userInfo);
	}*/

	public void deleteUser(Integer id) {
		userDatailsRepository.deleteById(id);
	}

	public UserInfo updatePassword(Integer id, UserInfo userRecord) {
		UserInfo userInfo = userDatailsRepository.findById(id).get();
		userInfo.setPassword(userRecord.getPassword());
		return userDatailsRepository.save(userInfo);
	}

	public UserInfo updateRole(Integer id, UserInfo userRecord) {
		UserInfo userInfo = userDatailsRepository.findById(id).get();
		userInfo.setRole(userRecord.getRole());
		return userDatailsRepository.save(userInfo);
	}
}