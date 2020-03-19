package org.SDET.interviewtest.common.endpoint;

public enum StarwarsAPI implements Endpoint {

    ROOT_ENDPOINT("/api/", false),
    PEOPLE_ENDPOINT("/api/people/", false);

    String endpoint;
    boolean useRoot;


    public String getEndpoint() {
        if(useRoot){
            return  ROOT_ENDPOINT.getEndpoint() + endpoint;
        }
        return endpoint;
    }

    private StarwarsAPI(String endpoint, boolean useRoot) {
        this.endpoint = endpoint;
        this.useRoot = useRoot;
    }
}
