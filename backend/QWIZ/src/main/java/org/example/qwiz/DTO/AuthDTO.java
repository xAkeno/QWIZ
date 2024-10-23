package org.example.qwiz.DTO;

import lombok.Data;

import java.util.Optional;

@Data
public class AuthDTO {
    private String Access_token;
    private String Token_Type = "Bearer ";
    public AuthDTO(Optional<String> token){
        this.Access_token = token.get();
    }

}
