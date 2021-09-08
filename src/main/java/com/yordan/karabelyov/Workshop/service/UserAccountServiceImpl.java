package com.yordan.karabelyov.Workshop.service;

import com.yordan.karabelyov.Workshop.model.UserAccount;
import com.yordan.karabelyov.Workshop.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService{
    @Autowired
    UserAccountRepository userAccountRepository;

    @Override
    public void save(UserAccount userAccount) {
//        UserAccount user = userAccountRepository.

        userAccountRepository.save(userAccount);
    }
}
