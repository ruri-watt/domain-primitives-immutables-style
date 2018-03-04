package se.rwatt.example.domain.primitives;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;
import org.immutables.value.Value;

@Value.Immutable
@DomainPrimitive
@JsonDeserialize(as = TelephoneNumber.class)
public abstract class AbstractTelephoneNumber {

    public static final int MINIMUM_LENGTH = 8;
    public static final int MAXIMUM_LENGTH = 15;

    public static final String REGEX = String.format(
            "[+]?[0-9]{%d,%d}",
            MINIMUM_LENGTH,
            MAXIMUM_LENGTH
    );

    @Value.Parameter
    @JsonProperty("telephoneNumber")
    public abstract String value();

    @Value.Check
    void check() {
        Preconditions.checkArgument(
                value().matches(REGEX),
                "Bad Telephone Number: %s\n"
                        + "\tA telephone number must be %s-%s digits with an optional initial + character",
                value(),
                MINIMUM_LENGTH,
                MAXIMUM_LENGTH
        );
    }
}
