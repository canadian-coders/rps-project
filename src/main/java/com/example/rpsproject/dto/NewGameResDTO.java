package com.example.rpsproject.dto;

import com.example.rpsproject.model.Status;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewGameResDTO {
    private int id;

    private Status status;
}
