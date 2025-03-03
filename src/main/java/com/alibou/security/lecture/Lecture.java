package com.alibou.security.lecture;

import com.alibou.security.models.BaseEntity;
import com.alibou.security.resource.Resource;
import com.alibou.security.section.Section;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Lecture extends BaseEntity {

  private String name;
  private String description;

  @ManyToOne
  @JoinColumn(name = "section_id")
  private Section section;

  @OneToOne
  @JoinColumn(name = "resource_id", nullable = true)
  private Resource resource;
}
