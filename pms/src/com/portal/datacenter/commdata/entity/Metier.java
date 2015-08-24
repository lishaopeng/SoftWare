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
/*    */ @Table(name="tq_metier")
/*    */ public class Metier
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Integer id;
/*    */   private String code;
/*    */   private String name;
/*    */   private Metier parent;
/*    */ 
/*    */   @Id
/*    */   @Column(name="metier_id", unique=true, nullable=false)
/*    */   @TableGenerator(name="tg_metier", pkColumnValue="tq_metier", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*    */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_metier")
/*    */   public Integer getId()
/*    */   {
/* 36 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 40 */     this.id = id;
/*    */   }
/*    */ 
/*    */   @Column(name="metier_code", nullable=false, length=10)
/*    */   public String getCode() {
/* 45 */     return this.code;
/*    */   }
/*    */ 
/*    */   public void setCode(String code) {
/* 49 */     this.code = code;
/*    */   }
/*    */ 
/*    */   @Column(name="metier_name", nullable=false, length=50)
/*    */   public String getName() {
/* 54 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 58 */     this.name = name;
/*    */   }
/*    */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="parent_id", nullable=true)
/*    */   public Metier getParent() {
/* 64 */     return this.parent;
/*    */   }
/*    */ 
/*    */   public void setParent(Metier parent) {
/* 68 */     this.parent = parent;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.commdata.entity.Metier
 * JD-Core Version:    0.6.1
 */