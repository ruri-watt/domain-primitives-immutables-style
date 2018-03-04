package se.rwatt.example.domain.primitives;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.pcollections.PVector;
import org.pcollections.TreePVector;

import java.util.Optional;

@Value.Immutable
@DomainPrimitive
@JsonDeserialize(as = User.class)
interface AbstractUser {
    @Value.Parameter // Included as an argument of the generated User.of method
    @JsonUnwrapped   // Serialized as "username":"theUsername" instead of "username":{"username":"theUsername"}
    Username getUsername();

    @Value.Parameter
    Location getLocation();

    @JsonUnwrapped
    @JsonInclude(JsonInclude.Include.NON_ABSENT) // Don't serialize Optional.empty()
    Optional<TelephoneNumber> getTelephoneNumber();

    @Value.Default                              // Defaults to the empty vector
    @JsonInclude(JsonInclude.Include.NON_EMPTY) // Only serialize if not empty
    default PVector<Role> getRoles() {
        return TreePVector.empty();
    }
}
