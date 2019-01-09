package com.lind.basic.entity;

/**
 * 数据建立与更新.
 */
public interface Auditable {

  Audit getAudit();

  void setAudit(Audit audit);
}