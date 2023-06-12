package com.example.crud.domain.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record RequestUsers(
        String id,
        String gender,
        Name name,
        Location location,
        String email,
        Dob dob,
        Registered registered,
        @JsonProperty("phone")
        String telephone_numbers,
        @JsonProperty("cell")
        String mobile_numbers,
        Picture picture) {

        public record Name(
                String title,
                String first,
                String last) {
        }

        public record Location(
                String region,
                String street,
                String city,
                String state,
                Integer postcode,
                Coordinates coordinates,
                Timezone timezone) {
        }

        public record Coordinates(
                double latitude,
                double longitude) {
        }

        public record Timezone(
                String offset,
                String description) {
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public record Dob(
                @JsonProperty("date")
                String birthday) {
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public record Registered(
                @JsonProperty("date")
                String registered) {
        }

        public String getTelephoneNumbersE164() {
                String filteredNumber = telephone_numbers.replaceAll("[^0-9]", "");
                return "+55" + filteredNumber;
        }

        public String getMobileNumbersE164() {
                String filteredNumber = mobile_numbers.replaceAll("[^0-9]", "");
                return "+55" + filteredNumber;
        }

        public String getRegion() {
                String state = location.state().toLowerCase();

                if (isInRegionNorth(state)) {
                        return "norte";
                } else if (isInRegionNortheast(state)) {
                        return "nordeste";
                } else if (isInRegionCentralWest(state)) {
                        return "centro-oeste";
                } else if (isInRegionSoutheast(state)) {
                        return "sudeste";
                } else if (isInRegionSouth(state)) {
                        return "sul";
                } else {
                        return null;
                }
        }

        private boolean isInRegionNorth(String state) {
                List<String> northernStates = List.of("acre", "amapá", "amazonas", "pará", "rondônia", "roraima", "tocantins");
                return northernStates.contains(state);
        }

        private boolean isInRegionNortheast(String state) {
                List<String> northeasternStates = List.of(
                        "alagoas", "bahia", "ceará", "maranhão", "paraíba", "pernambuco", "piauí", "rio grande do norte", "sergipe");
                return northeasternStates.contains(state);
        }

        private boolean isInRegionCentralWest(String state) {
                List<String> centralWestStates = List.of("distrito federal", "goiás", "mato grosso", "mato grosso do sul");
                return centralWestStates.contains(state);
        }

        private boolean isInRegionSoutheast(String state) {
                List<String> southeasternStates = List.of("espírito santo", "minas gerais", "rio de janeiro", "são paulo");
                return southeasternStates.contains(state);
        }

        private boolean isInRegionSouth(String state) {
                List<String> southernStates = List.of("paraná", "rio grande do sul", "santa catarina");
                return southernStates.contains(state);
        }

        public String getType() {
                double latitude = location.coordinates().latitude();
                double longitude = location.coordinates().longitude();

                if (latitude >= -46.361899 && latitude <= -34.276938 && longitude >= -15.411580 && longitude <= -2.196998) {
                        return "special";
                } else if (latitude >= -52.997614 && latitude <= -44.428305 && longitude >= -23.966413 && longitude <= -19.766959) {
                        return "special";
                } else if (latitude >= -54.777426 && latitude <= -46.603598 && longitude >= -34.016466 && longitude <= -26.155681) {
                        return "normal";
                } else {
                        return "laborious";
                }
        }

        public record Picture(
                String large,
                String medium,
                String thumbnail) {
        }
}

