/*    */ package com.portal.sysmgr.entity;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.OneToOne;
/*    */ import javax.persistence.PrimaryKeyJoinColumn;
/*    */ import javax.persistence.Table;
/*    */ import org.hibernate.annotations.GenericGenerator;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="tq_thirdparty_config")
/*    */ public class ThirdpartyConfig
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Integer id;
/*    */   private String qqKey;
/*    */   private String sinaKey;
/*    */   private Site site;
/*    */ 
/*    */   @Id
/*    */   @Column(name="config_id", unique=true, nullable=false)
/*    */   @GenericGenerator(name="copy", strategy="foreign", parameters={@org.hibernate.annotations.Parameter(name="property", value="site")})
/*    */   @GeneratedValue(generator="copy")
/*    */   public Integer getId()
/*    */   {
/* 36 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 40 */     this.id = id;
/*    */   }
/*    */ 
/*    */   @Column(name="qq_key", nullable=true, length=100)
/*    */   public String getQqKey() {
/* 45 */     return this.qqKey;
/*    */   }
/*    */ 
/*    */   public void setQqKey(String qqKey) {
/* 49 */     this.qqKey = qqKey;
/*    */   }
/*    */ 
/*    */   @Column(name="sina_key", nullable=true, length=100)
/*    */   public String getSinaKey() {
/* 54 */     return this.sinaKey;
/*    */   }
/*    */ 
/*    */   public void setSinaKey(String sinaKey) {
/* 58 */     this.sinaKey = sinaKey;
/*    */   }
/*    */   @OneToOne
/*    */   @PrimaryKeyJoinColumn
/*    */   public Site getSite() {
/* 64 */     return this.site;
/*    */   }
/*    */ 
/*    */   public void setSite(Site site) {
/* 68 */     this.site = site;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.entity.ThirdpartyConfig
 * JD-Core Version:    0.6.1
 */