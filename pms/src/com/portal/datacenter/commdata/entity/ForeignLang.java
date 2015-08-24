/*    */ package com.portal.datacenter.commdata.entity;
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
/*    */ @Table(name="tq_foreign_lang")
/*    */ public class ForeignLang
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Integer id;
/*    */   private String code;
/*    */   private String name;
/*    */ 
/*    */   @Id
/*    */   @Column(name="lang_id", unique=true, nullable=false)
/*    */   @TableGenerator(name="tg_foreign_lang", pkColumnValue="tq_foreign_lang", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*    */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_foreign_lang")
/*    */   public Integer getId()
/*    */   {
/* 30 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 34 */     this.id = id;
/*    */   }
/*    */ 
/*    */   @Column(name="lang_code", nullable=false, length=10)
/*    */   public String getCode() {
/* 39 */     return this.code;
/*    */   }
/*    */ 
/*    */   public void setCode(String code) {
/* 43 */     this.code = code;
/*    */   }
/*    */ 
/*    */   @Column(name="lang_name", nullable=false, length=50)
/*    */   public String getName() {
/* 48 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 52 */     this.name = name;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.commdata.entity.ForeignLang
 * JD-Core Version:    0.6.1
 */