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
/*    */ @Table(name="tq_economy_type")
/*    */ public class EconomyType
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Integer id;
/*    */   private String code;
/*    */   private String name;
/*    */ 
/*    */   @Id
/*    */   @Column(name="type_id", unique=true, nullable=false)
/*    */   @TableGenerator(name="tg_economy_type", pkColumnValue="tq_economy_type", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*    */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_economy_type")
/*    */   public Integer getId()
/*    */   {
/* 29 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 33 */     this.id = id;
/*    */   }
/*    */ 
/*    */   @Column(name="type_code", nullable=false, length=10)
/*    */   public String getCode() {
/* 38 */     return this.code;
/*    */   }
/*    */ 
/*    */   public void setCode(String code) {
/* 42 */     this.code = code;
/*    */   }
/*    */ 
/*    */   @Column(name="type_name", nullable=false, length=50)
/*    */   public String getName() {
/* 47 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 51 */     this.name = name;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.commdata.entity.EconomyType
 * JD-Core Version:    0.6.1
 */