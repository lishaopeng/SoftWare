/*    */ package com.portal.usermgr.entity;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.Lob;
/*    */ import javax.persistence.OneToOne;
/*    */ import javax.persistence.PrimaryKeyJoinColumn;
/*    */ import javax.persistence.Table;
/*    */ import org.hibernate.annotations.GenericGenerator;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="tq_role_perm")
/*    */ public class RolePerm
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Integer id;
/*    */   private String perms;
/*    */   private Role role;
/*    */ 
/*    */   @Id
/*    */   @Column(name="role_id", unique=true, nullable=false)
/*    */   @GenericGenerator(name="copy", strategy="foreign", parameters={@org.hibernate.annotations.Parameter(name="property", value="role")})
/*    */   @GeneratedValue(generator="copy")
/*    */   public Integer getId()
/*    */   {
/* 36 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 40 */     this.id = id;
/*    */   }
/*    */   @Lob
/*    */   @Column(name="perms", nullable=true)
/*    */   public String getPerms() {
/* 46 */     return this.perms;
/*    */   }
/*    */ 
/*    */   public void setPerms(String perms) {
/* 50 */     this.perms = perms;
/*    */   }
/*    */   @OneToOne
/*    */   @PrimaryKeyJoinColumn
/*    */   public Role getRole() {
/* 56 */     return this.role;
/*    */   }
/*    */ 
/*    */   public void setRole(Role role) {
/* 60 */     this.role = role;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.entity.RolePerm
 * JD-Core Version:    0.6.1
 */