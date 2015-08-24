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
/*    */ @Table(name="tq_specialty")
/*    */ public class Specialty
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Integer id;
/*    */   private String code;
/*    */   private String name;
/*    */   private Specialty parent;
/*    */ 
/*    */   @Id
/*    */   @Column(name="special_id", unique=true, nullable=false)
/*    */   @TableGenerator(name="tg_specialty", pkColumnValue="tq_specialty", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*    */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_specialty")
/*    */   public Integer getId()
/*    */   {
/* 35 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 39 */     this.id = id;
/*    */   }
/*    */ 
/*    */   @Column(name="special_code", nullable=false, length=10)
/*    */   public String getCode() {
/* 44 */     return this.code;
/*    */   }
/*    */ 
/*    */   public void setCode(String code) {
/* 48 */     this.code = code;
/*    */   }
/*    */ 
/*    */   @Column(name="special_name", nullable=false, length=50)
/*    */   public String getName() {
/* 53 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 57 */     this.name = name;
/*    */   }
/*    */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="parent_id", nullable=true)
/*    */   public Specialty getParent() {
/* 63 */     return this.parent;
/*    */   }
/*    */ 
/*    */   public void setParent(Specialty parent) {
/* 67 */     this.parent = parent;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.commdata.entity.Specialty
 * JD-Core Version:    0.6.1
 */