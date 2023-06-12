package com.example.crud.domain.users;

import jakarta.persistence.*;
import lombok.*;

@Table(name="users")
@Entity(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Users {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String type;

    private String gender;

    public void setGender(String gender) {
        if (gender.equals("male")) {
            this.gender = "m";
        } else if (gender.equals("female")) {
            this.gender = "f";
        } else {
            this.gender = gender;
        }
    }

    private String name_title;

    private String name_first;

    private String name_last;

    private String location_region;

    private String location_street;

    private String location_city;

    private String location_state;

    private Integer location_postcode;

    private double location_coordinates_latitude;

    private double location_coordinates_longitude;

    private String location_timezone_offset;

    private String location_timezone_description;

    private String birthday;

    private String registered;

    private String email;

    private String telephone_numbers;

    private String mobile_numbers;

    private String picture_large;

    private String picture_medium;

    private String picture_thumbnail;

    private String nationality;

    private Boolean active;

    public Users(RequestUsers requestUsers){
        this.gender = requestUsers.gender();
        this.type = requestUsers.getType();
        this.name_title = requestUsers.name().title();
        this.name_first = requestUsers.name().first();
        this.name_last = requestUsers.name().last();
        this.location_region = requestUsers.location().region();
        this.location_street = requestUsers.location().street();
        this.location_city = requestUsers.location().city();
        this.location_state = requestUsers.location().state();
        this.location_postcode = requestUsers.location().postcode();
        this.location_coordinates_latitude = requestUsers.location().coordinates().latitude();
        this.location_coordinates_longitude = requestUsers.location().coordinates().longitude();
        this.location_timezone_offset = requestUsers.location().timezone().offset();
        this.location_timezone_description = requestUsers.location().timezone().description();
        this.email = requestUsers.email();
        this.birthday = requestUsers.dob().birthday();
        this.registered = requestUsers.registered().registered();
        this.telephone_numbers = requestUsers.telephone_numbers();
        this.mobile_numbers = requestUsers.mobile_numbers();
        this.picture_large = requestUsers.picture().large();
        this.picture_medium = requestUsers.picture().medium();
        this.picture_thumbnail = requestUsers.picture().thumbnail();
        this.nationality = "BR";
        this.active = true;
    }
}
