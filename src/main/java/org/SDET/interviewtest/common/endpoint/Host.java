package org.SDET.interviewtest.common.endpoint;

public enum Host {
    STARWARS_API("swapi.co/"),
    STARWARS_COM("starwars.com");
    private String host;

    public String getValue() {
        try {
            return this.host;
        } catch (IllegalArgumentException iae) {
            return STARWARS_API.getValue();
        }
    }

    public String getURL() {
        try {
            return "https://" + this.host;
        } catch (IllegalArgumentException iae) {
            return STARWARS_API.getValue();
        }
    }

    Host(String hostIn) {
        host = hostIn;
    }
}
