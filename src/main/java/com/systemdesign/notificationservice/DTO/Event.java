package com.systemdesign.notificationservice.DTO;

import com.systemdesign.notificationservice.enums.EventType;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder
public class Event {
    private String userId;
    private String message;
    private EventType eventType;

}
