package se.rwatt.example.domain.primitives;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class AbstractTelephoneNumberTest {

    @ParameterizedTest
    @ValueSource(strings = {"0701323402", "+46723485596", "0903452345", "+46902348747", "12345678", "012345678901234", "+012345678901234"})
    void validTelephoneNumnerTest(String s) {
        TelephoneNumber.of(s);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1234567", "0123456789012345", "+0123456789012345", "1234567890a", "a1234567890"})
    void invalidTelephoneNumnerTest(String s) {
        assertThrows(
                IllegalArgumentException.class,
                () -> TelephoneNumber.of(s)
        );
    }

}