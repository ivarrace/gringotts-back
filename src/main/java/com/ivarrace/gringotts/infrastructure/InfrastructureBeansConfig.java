package com.ivarrace.gringotts.infrastructure;

import com.ivarrace.gringotts.infrastructure.persistence.AccountingPersistencePort;
import com.ivarrace.gringotts.infrastructure.persistence.UserPersistencePort;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.AccountingMongoPersistenceAdapter;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.UserMongoPersistenceAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class InfrastructureBeansConfig {

    @Bean
    public AccountingPersistencePort accountingPersistencePort(){
        return new AccountingMongoPersistenceAdapter();
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
