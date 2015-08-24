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
/*    */ @Table(name="tq_group_perm")
/*    */ public class GroupPerm
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Integer id;
/*    */   private String perms;
/*    */   private Group group;
/*    */ 
/*    */   @Id
/*    */   @Column(name="group_id", unique=true, nullable=false)
/*    */   @GenericGenerator(name="copy", strategy="foreign", parameters={@org.hibernate.annotations.Parameter(name="property", value="group")})
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
/*    */   public Group getGroup() {
/* 56 */     return this.group;
/*    */   }
/*    */ 
/*    */   public void setGroup(Group group) {
/* 60 */     this.group = group;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.entity.GroupPerm
 * JD-Core Version:    0.6.1
 */