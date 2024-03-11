package com.systemdesign.notificationservice.enums;

public enum EventType {
    LIKE("like"),COMMENT("comment"),TEST("test");
    private String stringValue;
    private EventType(String type){
        this.stringValue = type;
    }

    public String getStringValue(){
        return stringValue;
    }
}
