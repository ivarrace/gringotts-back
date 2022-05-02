package com.ivarrace.gringotts.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DtoUtilsTest {

    @Test
    void generateKey() {
        String result = DtoUtils.generateKey("te ST á ü");
        assertEquals("te_st_a_u", result);
    }

    @Test
    void generateKey_null() {
        String result = DtoUtils.generateKey(null);
        assertEquals("empty", result);
    }

    @Test
    void generateKey_empty() {
        String result = DtoUtils.generateKey("");
        assertEquals("empty", result);
    }

    @Test
    void round_bottom() {
        double result = DtoUtils.round(1.1011111);
        assertEquals(1.10, result);
    }

    @Test
    void round_top() {
        double result = DtoUtils.round(1.9999999);
        assertEquals(2.0, result);
    }
}