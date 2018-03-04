package se.rwatt.example.domain.primitives;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.pcollections.PCollectionsModule;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pcollections.TreePVector;

class AbstractUserTest {

    ObjectMapper om;

    @BeforeEach
    void setup() {
        om = new ObjectMapper();
        om.registerModule(new Jdk8Module());
        om.registerModule(new PCollectionsModule());
    }

    @Test
    void shouldNotAllowNullAttributes() {
        assertThrows(
                NullPointerException.class,
                () -> User.of(Username.of("user1"), null)
        );
    }

    @Test
    void shouldNotAllowMissingBuilderAttributes() {
        assertThrows(
                IllegalStateException.class,
                () -> User.builder()
                        .location(Location.of(0, 0))
                        .build()
        );
    }

    @Test
    void shouldUnwrapUsernameWhenSerialized() throws Exception {

        String userJson = om.writeValueAsString(
                User.builder()
                        .username(Username.of("Ruaridh_Watt"))
                        .location(Location.of(20, 20))
                        .telephoneNumber(TelephoneNumber.of("070432456345"))
                        .roles(TreePVector.from(List.of(Role.ROUTE_ADMIN, Role.USER_ADMIN)))
                        .build()
        );

        assertFalse(userJson.contains("\"username\":{"));
    }

    @Test
    void shouldDeserializeUserJson() throws Exception {

        String userJson = "{" +
                "\"username\":\"Ruaridh_Watt\"," +
                "\"location\":{\"latitude\":20.0,\"longitude\":20.0}," +
                "\"telephoneNumber\":\"0705040506\"," +
                "\"roles\":[\"ROUTE_ADMIN\"]" +
                "}";
        User user = om.readValue(userJson, User.class);
        assertEquals(Username.of("Ruaridh_Watt"), user.getUsername());
        assertTrue(user.getTelephoneNumber().isPresent());
        assertEquals(Role.ROUTE_ADMIN, user.getRoles().get(0));
    }

    @Test
    void shouldNotDeserializeBadUserJson() throws Exception {

        String userJson = "{" +
                "\"username\":\"Ruaridh_Watt\"," +
                "\"location\":{\"latitude\":20.0,\"longitude\":20.0}," +
                "\"roles\":[\"INVALID_ROLE\"]" +
                "}";

        assertThrows(
                InvalidFormatException.class,
                () -> om.readValue(userJson, User.class)
        );

    }


}
