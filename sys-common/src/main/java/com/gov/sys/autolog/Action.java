package com.gov.sys.autolog;

public enum Action {

    I("INSERT"), U("UPDATE"), D("DELETE");

    private final String type;

    Action(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
