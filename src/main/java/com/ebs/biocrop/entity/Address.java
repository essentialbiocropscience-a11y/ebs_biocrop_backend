package com.ebs.biocrop.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {

    @Field("address_line_1")
    @JsonProperty("address_line_1")
    @JsonAlias({"addressLine1", "line1", "address line 1"})
    private String addressLine1;

    @Field("near_by_location")
    @JsonProperty("near_by_location")
    @JsonAlias({"nearby_location", "nearbyLocation", "street", "near by location"})
    private String nearbyLocation;

    private String city;
    private String state;

    @Field("pin_code")
    @JsonProperty("pin_code")
    @JsonAlias({"pinCode", "pincode"})
    private String pinCode;

    public Address() {
    }

    public Address(String addressLine1, String nearbyLocation, String city, String state, String pinCode) {
        this.addressLine1 = addressLine1;
        this.nearbyLocation = nearbyLocation;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
    }

    @JsonProperty("address_line_1")
    public String getAddressLine1() {
        return addressLine1;
    }

    @JsonProperty("address_line_1")
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1 != null ? addressLine1.trim() : null;
    }

    @JsonProperty("near_by_location")
    public String getNearbyLocation() {
        return nearbyLocation;
    }

    @JsonProperty("near_by_location")
    public void setNearbyLocation(String nearbyLocation) {
        this.nearbyLocation = nearbyLocation != null ? nearbyLocation.trim() : null;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city != null ? city.trim() : null;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state != null ? state.trim() : null;
    }

    @JsonProperty("pin_code")
    public String getPinCode() {
        return pinCode;
    }

    @JsonProperty("pin_code")
    public void setPinCode(String pinCode) {
        this.pinCode = pinCode != null ? pinCode.trim() : null;
    }
}
