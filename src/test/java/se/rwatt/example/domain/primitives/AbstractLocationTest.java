package se.rwatt.example.domain.primitives;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AbstractLocationTest {

  @Test
  void validLocationsTest() {
    Location.builder()
        .latitude(0)
        .longitude(0)
        .build();

    Location.of(-90, -180);

    Location.of(90, 180);
  }

  @Test
  void invalidLocationsTest() {

    assertThrows(
        IllegalArgumentException.class,

        () -> Location.builder()
            .latitude(-90.1)
            .longitude(0)
            .build()
    );

    assertThrows(
        IllegalArgumentException.class,

        () -> Location.of(90.1, 0)
    );

    assertThrows(
        IllegalArgumentException.class,

        () -> Location.of(0, -180.1)
    );

    assertThrows(
        IllegalArgumentException.class,

        () -> Location.of(0, 180.1)
    );
  }

}
