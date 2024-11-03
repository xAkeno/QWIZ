package org.example.qwiz.ClassroomFeatures.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public ClassroomJoinedDTO(int accountId) {
        AccountId = accountId;
    }
}
