package com.management.employee.repository;


import com.management.employee.enums.Role;
import com.management.employee.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserDetailsRepository extends JpaRepository<UserInfo, Integer> {
	UserInfo findByEmailAndEnabled(String userName, boolean enabled);

	public List<UserInfo> findAllByRoleOrderByEnabled(Role role);

	void deleteById(Integer id);
}
