package com.ivarrace.gringotts.infrastructure;

import com.ivarrace.gringotts.infrastructure.persistence.AccountingPersistencePort;
import com.ivarrace.gringotts.infrastructure.persistence.GroupPersistencePort;
import com.ivarrace.gringotts.infrastructure.persistence.UserPersistencePort;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.AccountingMongoPersistenceAdapter;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.GroupMongoPersistenceAdapter;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.UserMongoPersistenceAdapter;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.AccountingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class InfrastructureBeansConfig {

    @Autowired
    AccountingRepository accountingRepository;

    @Bean
    public AccountingPersistencePort accountingPersistencePort(){
        return new AccountingMongoPersistenceAdapter(accountingRepository);
    }

    @Bean
    public GroupPersistencePort groupPersistencePort(){
        return new GroupMongoPersistenceAdapter();
    }

    @Bean
    public UserPersistencePort userPersistencePort(){
        return new UserMongoPersistenceAdapter();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
