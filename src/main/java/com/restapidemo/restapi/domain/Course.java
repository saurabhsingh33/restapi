package com.restapidemo.restapi.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
public class Course {

    @Id
    private Long id;

    @Column
    @NotBlank(message = "Title Can't be empty")
    @Size(min = 4, max = 20, message = "Title must be between 4-20 characters")
    private String title;

    @Column
    private String description;


    public Course() {

    }

}
