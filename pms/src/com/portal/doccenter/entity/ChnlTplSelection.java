/*    */ package com.portal.doccenter.entity;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Collection;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Embeddable;
/*    */ import javax.persistence.FetchType;
/*    */ import javax.persistence.JoinColumn;
/*    */ import javax.persistence.ManyToOne;
/*    */ import javax.persistence.Transient;
/*    */ 
/*    */ @Embeddable
/*    */ public class ChnlTplSelection
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String tplDoc;
/*    */   private Model model;
/*    */ 
/*    */   public static Integer[] fetchIds(Collection<ChnlTplSelection> tpls)
/*    */   {
/* 24 */     Integer[] ids = new Integer[tpls.size()];
/* 25 */     int i = 0;
/* 26 */     for (ChnlTplSelection g : tpls) {
/* 27 */       ids[(i++)] = g.getModelId();
/*    */     }
/* 29 */     return ids;
/*    */   }
/*    */ 
/*    */   @Transient
/*    */   public Integer getModelId() {
/* 34 */     return getModel().getId();
/*    */   }
/*    */ 
/*    */   @Transient
/*    */   public Integer getPriority() {
/* 39 */     return getModel().getPriority();
/*    */   }
/*    */ 
/*    */   @Column(name="tpl_doc", nullable=true, length=100)
/*    */   public String getTplDoc()
/*    */   {
/* 49 */     return this.tplDoc;
/*    */   }
/*    */ 
/*    */   public void setTplDoc(String tplDoc) {
/* 53 */     this.tplDoc = tplDoc;
/*    */   }
/*    */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="model_id", nullable=false)
/*    */   public Model getModel() {
/* 59 */     return this.model;
/*    */   }
/*    */ 
/*    */   public void setModel(Model model) {
/* 63 */     this.model = model;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.entity.ChnlTplSelection
 * JD-Core Version:    0.6.1
 */