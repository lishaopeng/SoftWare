/*    */ package com.portal.usermgr.entity;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.FetchType;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.GenerationType;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.JoinColumn;
/*    */ import javax.persistence.ManyToOne;
/*    */ import javax.persistence.Table;
/*    */ import javax.persistence.TableGenerator;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="tq_site_message_status")
/*    */ public class SiteMessageStatus
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 21 */   public static final Integer DEL = Integer.valueOf(-1);
/*    */ 
/* 23 */   public static final Integer NO_READ = Integer.valueOf(0);
/*    */ 
/* 25 */   public static final Integer HAVE_READ = Integer.valueOf(1);
/*    */   private Integer id;
/*    */   private Integer status;
/*    */   private User receive;
/*    */   private SiteMessage message;
/*    */ 
/*    */   public void init()
/*    */   {
/* 28 */     if (getStatus() == null)
/* 29 */       setStatus(NO_READ);
/*    */   }
/*    */ 
/*    */   @Id
/*    */   @Column(name="status_id", unique=true, nullable=false)
/*    */   @TableGenerator(name="tg_site_message_status", pkColumnValue="tq_site_message_status", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*    */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_site_message_status")
/*    */   public Integer getId()
/*    */   {
/* 48 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 52 */     this.id = id;
/*    */   }
/*    */ 
/*    */   @Column(name="status", nullable=false, length=10)
/*    */   public Integer getStatus() {
/* 57 */     return this.status;
/*    */   }
/*    */ 
/*    */   public void setStatus(Integer status) {
/* 61 */     this.status = status;
/*    */   }
/*    */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="receive_id", nullable=false)
/*    */   public User getReceive() {
/* 67 */     return this.receive;
/*    */   }
/*    */ 
/*    */   public void setReceive(User receive) {
/* 71 */     this.receive = receive;
/*    */   }
/*    */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="message_id", nullable=false)
/*    */   public SiteMessage getMessage() {
/* 77 */     return this.message;
/*    */   }
/*    */ 
/*    */   public void setMessage(SiteMessage message) {
/* 81 */     this.message = message;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.entity.SiteMessageStatus
 * JD-Core Version:    0.6.1
 */