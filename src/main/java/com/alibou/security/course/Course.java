package com.alibou.security.course;

import com.alibou.security.author.Author;
import com.alibou.security.models.BaseEntity;
import com.alibou.security.section.Section;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Course extends BaseEntity {

  private String title;

  private String description;

  @JsonIgnore
  @ManyToMany
  @JoinTable(
      name = "authors_courses",
      joinColumns = {
          @JoinColumn(name = "course_id")
      },
      inverseJoinColumns = {
          @JoinColumn(name = "author_id")
      }
  )
  private List<Author> authors;

  @OneToMany(mappedBy = "course")
  private List<Section> sections;

}
