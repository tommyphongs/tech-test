package com.example.quantile.Repository;

import com.example.quantile.math.Quantiles;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.DisplayName.class)
class NonPreserveRepositoryTest {

    static NonPreserveRepository<Long, Double> repository;

    @BeforeAll
    static void init() {
        repository = new NonPreserveRepository<>();
    }

    @DisplayName("1")
    @Test
    void testInsertTest() throws Exception {
        boolean inserted = repository.append(1L, List.of(1D, 2.5));
        Assertions.assertTrue(inserted);
    }

    @DisplayName("2")
    @Test
    void testAppendTest() throws Exception {
        boolean append = repository.append(1L, List.of(3D, 4D));
        Assertions.assertFalse(append);
    }

    @DisplayName("3")
    @Test
    void testGetTest() throws Exception {
        List<Double> values = repository.get(1L);
        Assertions.assertEquals(values.size(), 4);
    }

    @DisplayName("4")
    @Test
    void testAppendNull() {
        String expectedMessage = "is null";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.append(null, List.of());
        });
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.append(100L, null);
        });
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }


}