/*    */ package com.portal.datacenter.commdata.entity;
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
/*    */ @Table(name="tq_industry")
/*    */ public class Industry
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Integer id;
/*    */   private String code;
/*    */   private String name;
/*    */   private Industry parent;
/*    */ 
/*    */   @Id
/*    */   @Column(name="industry_id", unique=true, nullable=false)
/*    */   @TableGenerator(name="tg_industry", pkColumnValue="tq_industry", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*    */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_industry")
/*    */   public Integer getId()
/*    */   {
/* 33 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 37 */     this.id = id;
/*    */   }
/*    */ 
/*    */   @Column(name="industry_code", nullable=false, length=10)
/*    */   public String getCode() {
/* 42 */     return this.code;
/*    */   }
/*    */ 
/*    */   public void setCode(String code) {
/* 46 */     this.code = code;
/*    */   }
/*    */ 
/*    */   @Column(name="industry_name", nullable=false, length=50)
/*    */   public String getName() {
/* 51 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 55 */     this.name = name;
/*    */   }
/*    */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="parent_id", nullable=true)
/*    */   public Industry getParent() {
/* 61 */     return this.parent;
/*    */   }
/*    */ 
/*    */   public void setParent(Industry parent) {
/* 65 */     this.parent = parent;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.commdata.entity.Industry
 * JD-Core Version:    0.6.1
 */