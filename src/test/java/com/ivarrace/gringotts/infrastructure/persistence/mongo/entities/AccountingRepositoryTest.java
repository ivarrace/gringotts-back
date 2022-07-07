package com.ivarrace.gringotts.infrastructure.persistence.mongo.entities;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataMongoTest
class AccountingRepositoryTest {

    @Autowired
    AccountingRepository accountingRepository;

    @Before
    public void setUp() throws Exception {
        accountingRepository.save(new AccountingEntity());
    }

    @Test
    public void shouldBeNotEmpty() {
        assertFalse(accountingRepository.findAll().isEmpty());
    }
}