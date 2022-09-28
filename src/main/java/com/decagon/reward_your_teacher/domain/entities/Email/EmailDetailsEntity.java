package com.decagon.reward_your_teacher.domain.entities.Email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Annotations
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

// Class
public class EmailDetailsEntity {

    // Class data members
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}