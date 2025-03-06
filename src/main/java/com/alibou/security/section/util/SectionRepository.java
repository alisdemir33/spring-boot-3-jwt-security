package com.alibou.security.section.util;

import com.alibou.security.section.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SectionRepository extends JpaRepository<Section, Integer>, JpaSpecificationExecutor<Section> {
}