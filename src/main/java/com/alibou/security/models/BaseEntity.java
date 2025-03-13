package com.alibou.security.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
public class BaseEntity {

  @Id
  @GeneratedValue
  private Integer id;

  private LocalDateTime createdAt;
  private LocalDateTime lastModifiedAt;

  private String createdBy;
  private String lastModifiedBy;

  @PrePersist
  protected void onCreate() {
    createdAt = LocalDateTime.now();
     }

  @PreUpdate
  protected void onUpdate() {
    lastModifiedAt = LocalDateTime.now();
  }

}
