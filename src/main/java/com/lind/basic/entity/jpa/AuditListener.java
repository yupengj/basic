package com.lind.basic.entity.jpa;

import com.lind.basic.entity.jpa.Audit;
import com.lind.basic.entity.jpa.Auditable;
import java.time.LocalDateTime;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Hibernate 事件监听器.
 * 实体监听@EntityListeners(AuditListener.class).
 */
@Slf4j
@Component
@Transactional
public class AuditListener {
  /**
   * create.
   *
   * @param auditable .
   */
  @PrePersist
  public void setCreatedOn(Auditable auditable) {
    Audit audit = auditable.getAudit();

    if (audit == null) {
      audit = new Audit();
      auditable.setAudit(audit);
    }

    audit.setCreatedOn(LocalDateTime.now());
    audit.setUpdatedOn(LocalDateTime.now());
  }

  /**
   * update.
   *
   * @param auditable .
   */
  @PreUpdate
  public void setUpdatedOn(Auditable auditable) {
    Audit audit = auditable.getAudit();
    audit.setUpdatedOn(LocalDateTime.now());
  }
}
