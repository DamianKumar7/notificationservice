package com.systemdesign.notificationservice.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.systemdesign.notificationservice.enums.EventType;
import lombok.*;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("message")
    private String message;
    @JsonProperty("eventType")
    private EventType eventType;

}
