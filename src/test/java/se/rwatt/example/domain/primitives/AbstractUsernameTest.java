package se.rwatt.example.domain.primitives;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AbstractUsernameTest {

  @ParameterizedTest
  @ValueSource(strings = {"nam", "user-1_2", "Żółć", "Ὀδυσσεύς", "原田雅彦", "0123456789abcdef"})
  void validNamesTest(String s) {
    Username.of(s);
  }

  @ParameterizedTest
  @ValueSource(strings = {"na", "原田", "Jane Doe", "ME\uD83D\uDCA9", "0123456789abcdefg"})
  void invalidNamesTest(String s) {
    assertThrows(IllegalArgumentException.class, () -> Username.of(s));
  }

}
