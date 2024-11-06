package org.example.qwiz.ClassroomFeatures.DTO;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomJoinedDTO {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("classname")
    private String classname;

    @JsonProperty("classroom_id")
    private String classroomId;

    @JsonProperty("course")
    private String course;

    @JsonProperty("created")
    private String created;

    @JsonProperty("creator")
    private String creator;

    @JsonProperty("grade")
    private String grade;

    // Constructor

}
