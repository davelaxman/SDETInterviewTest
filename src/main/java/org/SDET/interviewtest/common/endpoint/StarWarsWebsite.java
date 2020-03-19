package org.SDET.interviewtest.common.endpoint;

public enum StarWarsWebsite implements Endpoint  {
    HOME("/", false),
   SEARCH("/search", false);

    String endpoint;
    boolean useRoot;


    public String getEndpoint() {
        if(useRoot){
            return  HOME.getEndpoint() + endpoint;
        }
        return endpoint;
    }

    private StarWarsWebsite(String endpoint, boolean useRoot) {
        this.endpoint = endpoint;
        this.useRoot = useRoot;
    }
}
