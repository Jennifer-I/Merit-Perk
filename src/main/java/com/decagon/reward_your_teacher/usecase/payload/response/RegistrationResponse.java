package com.decagon.reward_your_teacher.usecase.payload.response;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RegistrationResponse<T> {
    private String name;
    private String email;
    private long id;
}
