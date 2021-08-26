package com.yordan.karabelyov.Workshop.repository;

import com.yordan.karabelyov.Workshop.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount,Long> {

}
