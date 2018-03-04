package se.rwatt.example.domain.primitives;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;
import org.immutables.value.Value;

@Value.Immutable
@DomainPrimitive
@JsonDeserialize(as = Location.class)
public abstract class AbstractLocation {

    public static final double MIN_LATITUDE = -90.0;
    public static final double MAX_LATITUDE = 90.0;

    public static final double MIN_LONGITUDE = -180.0;
    public static final double MAX_LONGITUDE = 180.0;

    @Value.Parameter
    public abstract double getLatitude();

    @Value.Parameter
    public abstract double getLongitude();

    @Value.Check
    void check() {

        Preconditions.checkArgument(
                getLatitude() >= MIN_LATITUDE,
                "Bad Latitude: %s\n"
                        + "\tA latitude must be greater than %s",
                getLatitude(),
                MIN_LATITUDE
        );

        Preconditions.checkArgument(
                getLatitude() <= MAX_LATITUDE,
                "Bad Latitude: %s\n"
                        + "\tA latitude must be less than %s",
                getLatitude(),
                MAX_LATITUDE
        );

        Preconditions.checkArgument(
                getLongitude() >= MIN_LONGITUDE,
                "Bad Longitude: %s\n"
                        + "\tA longitude must be greater than %s",
                getLongitude(),
                MIN_LONGITUDE
        );

        Preconditions.checkArgument(
                getLongitude() <= MAX_LONGITUDE,
                "Bad Longitude: %s\n"
                        + "\tA longitude must be less than %s",
                getLongitude(),
                MAX_LONGITUDE
        );
    }
}
