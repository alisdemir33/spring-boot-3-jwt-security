package com.alibou.security.resource;

import com.alibou.security.lecture.Lecture;
import com.alibou.security.models.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// @DiscriminatorColumn(name = "resource_type") --> only with SINGLE_TABLE
public class Resource extends BaseEntity {

  @Id
  @GeneratedValue
  private Integer id;
  private String name;
  private int size;
  private String url;
  private String description;

  @OneToOne
  @JoinColumn(name = "lecture_id")
  private Lecture lecture;
}
