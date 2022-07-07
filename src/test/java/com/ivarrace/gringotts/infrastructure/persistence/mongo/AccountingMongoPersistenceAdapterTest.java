package com.ivarrace.gringotts.infrastructure.persistence.mongo;

import com.ivarrace.gringotts.domain.model.Accounting;
import com.ivarrace.gringotts.infrastructure.persistence.AccountingPersistencePort;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.AccountingRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
//@DataMongoTest
class AccountingMongoPersistenceAdapterTest {

    @Mock
    AccountingRepository accountingRepository;

    private AccountingPersistencePort accountingPersistencePort = new AccountingMongoPersistenceAdapter(accountingRepository);
/*
    @Test
    public void findByUserAndKey(){
        Optional<Accounting> result =
                accountingPersistencePort.findByUserAndKey("w", "w");
        assertTrue(result.isPresent());
    }*/
}