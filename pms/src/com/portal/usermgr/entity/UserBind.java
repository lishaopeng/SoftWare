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
/*    */ @Table(name="tq_user_bind")
/*    */ public class UserBind
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 24 */   public static final Integer OA = Integer.valueOf(1);
/*    */   private Integer id;
/*    */   private String username;
/*    */   private String password;
/*    */   private Integer status;
/*    */   private User user;
/*    */ 
/*    */   public void init()
/*    */   {
/* 27 */     if (getStatus() == null)
/* 28 */       setStatus(OA);
/*    */   }
/*    */ 
/*    */   @Id
/*    */   @Column(name="bind_id", unique=true, nullable=false)
/*    */   @TableGenerator(name="tg_user_bind", pkColumnValue="tq_user_bind", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*    */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_user_bind")
/*    */   public Integer getId()
/*    */   {
/* 48 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 52 */     this.id = id;
/*    */   }
/*    */ 
/*    */   @Column(name="username", nullable=false, length=100)
/*    */   public String getUsername() {
/* 57 */     return this.username;
/*    */   }
/*    */ 
/*    */   public void setUsername(String username) {
/* 61 */     this.username = username;
/*    */   }
/*    */ 
/*    */   @Column(name="pass", nullable=false, length=100)
/*    */   public String getPassword() {
/* 66 */     return this.password;
/*    */   }
/*    */ 
/*    */   public void setPassword(String password) {
/* 70 */     this.password = password;
/*    */   }
/*    */ 
/*    */   @Column(name="status", nullable=true, length=5)
/*    */   public Integer getStatus() {
/* 75 */     return this.status;
/*    */   }
/*    */ 
/*    */   public void setStatus(Integer status) {
/* 79 */     this.status = status;
/*    */   }
/*    */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="user_id", nullable=false)
/*    */   public User getUser() {
/* 85 */     return this.user;
/*    */   }
/*    */ 
/*    */   public void setUser(User user) {
/* 89 */     this.user = user;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.entity.UserBind
 * JD-Core Version:    0.6.1
 */