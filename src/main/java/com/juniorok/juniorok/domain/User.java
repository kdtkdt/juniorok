package com.juniorok.juniorok.domain;


import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class User {

    private long id;
    private String email;
    private String nickname;
    private LocalDateTime createdAt;
    private boolean isAdmin;
    private boolean isWriter;

}
