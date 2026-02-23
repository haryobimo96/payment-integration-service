package com.haryobimo.paymentservice.utils;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HashUtilsTest {
    @ParameterizedTest
    @NullAndEmptySource
    void nullOrEmptyKeyShouldThrowException(String key) {
        assertThrows(RuntimeException.class, () -> HashUtils.hashSha256(key, ""));
    }

    @ParameterizedTest
    @MethodSource("provideValidKeyAndDataPairs")
    void validKeyAndDataPairsShouldNotThrowException(String key, String data) {
        assertDoesNotThrow(() -> {
            String result = HashUtils.hashSha256(key, data);
            assertNotNull(result);
        });
    }

    static Stream<Arguments> provideValidKeyAndDataPairs() {
        return Stream.of(
                Arguments.of(" ", " "),
                Arguments.of("validKey", ""),
                Arguments.of("validKey", "someData"),
                Arguments.of("aaaaaaaaaaaaaaaaa", "someData"),
                Arguments.of("key!@#$%^&*()_+-=[]{}|;:'.<>?/someData", ""),
                Arguments.of("key_с_кириллицей_日本語", "someData")
        );
    }
}
