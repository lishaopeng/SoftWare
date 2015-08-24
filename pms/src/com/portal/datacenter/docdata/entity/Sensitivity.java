/*    */ package com.portal.datacenter.docdata.entity;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.GenerationType;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.Table;
/*    */ import javax.persistence.TableGenerator;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="tq_sensitivity")
/*    */ public class Sensitivity
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Integer id;
/*    */   private String search;
/*    */   private String replacement;
/*    */ 
/*    */   @Id
/*    */   @Column(name="sensitivity_id", unique=true, nullable=false)
/*    */   @TableGenerator(name="tg_sensitivity", pkColumnValue="tq_sensitivity", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*    */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_sensitivity")
/*    */   public Integer getId()
/*    */   {
/* 29 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 33 */     this.id = id;
/*    */   }
/*    */ 
/*    */   @Column(name="search", nullable=false, length=255)
/*    */   public String getSearch() {
/* 38 */     return this.search;
/*    */   }
/*    */ 
/*    */   public void setSearch(String search) {
/* 42 */     this.search = search;
/*    */   }
/*    */ 
/*    */   @Column(name="replacement", nullable=false, length=255)
/*    */   public String getReplacement() {
/* 47 */     return this.replacement;
/*    */   }
/*    */ 
/*    */   public void setReplacement(String replacement) {
/* 51 */     this.replacement = replacement;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.docdata.entity.Sensitivity
 * JD-Core Version:    0.6.1
 */