/*    */ package com.portal.extrafunc.entity;
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
/*    */ @Table(name="tq_links_type")
/*    */ public class LinksType
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Integer id;
/*    */   private String name;
/*    */   private Integer priority;
/*    */   private Site site;
/*    */ 
/*    */   public void init()
/*    */   {
/* 22 */     if (getPriority() == null)
/* 23 */       setPriority(Integer.valueOf(10));
/*    */   }
/*    */ 
/*    */   @Id
/*    */   @Column(name="type_id", unique=true, nullable=false)
/*    */   @TableGenerator(name="tg_links_type", pkColumnValue="tq_links_type", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*    */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_links_type")
/*    */   public Integer getId()
/*    */   {
/* 42 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 46 */     this.id = id;
/*    */   }
/*    */ 
/*    */   @Column(name="name", nullable=false, length=50)
/*    */   public String getName() {
/* 51 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 55 */     this.name = name;
/*    */   }
/*    */ 
/*    */   @Column(name="priority", nullable=false, length=10)
/*    */   public Integer getPriority() {
/* 60 */     return this.priority;
/*    */   }
/*    */ 
/*    */   public void setPriority(Integer priority) {
/* 64 */     this.priority = priority;
/*    */   }
/*    */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="site_id", nullable=false)
/*    */   public Site getSite() {
/* 70 */     return this.site;
/*    */   }
/*    */ 
/*    */   public void setSite(Site site) {
/* 74 */     this.site = site;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.entity.LinksType
 * JD-Core Version:    0.6.1
 */