package org.example.qwiz.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
public class ClassroomJoinedDTO {
    @JsonProperty("accountId")
    public int AccountId;
    @JsonProperty("classroomId")
    public int ClassroomId;
    public ClassroomJoinedDTO(int accountId, int classroomId){
        this.AccountId = accountId;
        this.ClassroomId = classroomId;
    }

}
