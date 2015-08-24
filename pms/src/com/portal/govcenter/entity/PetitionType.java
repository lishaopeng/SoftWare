/*    */ package com.portal.govcenter.entity;
/*    */ 
/*    */ import com.portal.sysmgr.entity.Site;
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
/*    */ @Table(name="tq_petition_type")
/*    */ public class PetitionType
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Integer id;
/*    */   private String name;
/*    */   private Integer priority;
/*    */   private Site site;
/*    */ 
/*    */   @Id
/*    */   @Column(name="type_id", unique=true, nullable=false)
/*    */   @TableGenerator(name="tg_petition_type", pkColumnValue="tq_petition_type", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*    */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_petition_type")
/*    */   public Integer getId()
/*    */   {
/* 36 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 40 */     this.id = id;
/*    */   }
/*    */ 
/*    */   @Column(name="name", nullable=false, length=20)
/*    */   public String getName() {
/* 45 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 49 */     this.name = name;
/*    */   }
/*    */ 
/*    */   @Column(name="priority", nullable=false, length=10)
/*    */   public Integer getPriority() {
/* 54 */     return this.priority;
/*    */   }
/*    */ 
/*    */   public void setPriority(Integer priority) {
/* 58 */     this.priority = priority;
/*    */   }
/*    */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="site_id", nullable=false)
/*    */   public Site getSite() {
/* 64 */     return this.site;
/*    */   }
/*    */ 
/*    */   public void setSite(Site site) {
/* 68 */     this.site = site;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.govcenter.entity.PetitionType
 * JD-Core Version:    0.6.1
 */