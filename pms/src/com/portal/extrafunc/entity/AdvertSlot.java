/*     */ package com.portal.extrafunc.entity;
/*     */ 
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import java.io.Serializable;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.GenerationType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.TableGenerator;
/*     */ import javax.persistence.Transient;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tq_advert_slot")
/*     */ public class AdvertSlot
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  22 */   public static final Short FIXED = Short.valueOf((short)1);
/*     */ 
/*  24 */   public static final Short FLOAT = Short.valueOf((short)2);
/*     */ 
/*  26 */   public static final Short POP = Short.valueOf((short)3);
/*     */ 
/*  28 */   public static final Short COUPLET = Short.valueOf((short)4);
/*     */   private Integer id;
/*     */   private String name;
/*     */   private Short slotType;
/*     */   private Integer height;
/*     */   private Integer width;
/*     */   private Integer remain;
/*     */   private Boolean scrollbar;
/*     */   private String sexplain;
/*     */   private Boolean idleholder;
/*     */   private Boolean rotation;
/*     */   private Site site;
/*     */ 
/*     */   public void init()
/*     */   {
/*  31 */     if (getRemain() == null) {
/*  32 */       setRemain(Integer.valueOf(0));
/*     */     }
/*  34 */     if (getScrollbar() == null) {
/*  35 */       setScrollbar(Boolean.valueOf(false));
/*     */     }
/*  37 */     if (getRotation() == null)
/*  38 */       setRotation(Boolean.valueOf(true));
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getSlotTypeString()
/*     */   {
/*  44 */     if (getSlotType().equals(FIXED))
/*  45 */       return "固定";
/*  46 */     if (getSlotType().equals(FLOAT))
/*  47 */       return "漂浮";
/*  48 */     if (getSlotType().equals(POP)) {
/*  49 */       return "弹窗";
/*     */     }
/*  51 */     return "对联";
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="slot_id", unique=true, nullable=false)
/*     */   @TableGenerator(name="tg_advert_slot", pkColumnValue="tq_advert_slot", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*     */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_advert_slot")
/*     */   public Integer getId()
/*     */   {
/*  77 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  81 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="name", nullable=false, length=30)
/*     */   public String getName() {
/*  86 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  90 */     this.name = name;
/*     */   }
/*     */ 
/*     */   @Column(name="slot_type", nullable=false, length=5)
/*     */   public Short getSlotType() {
/*  95 */     return this.slotType;
/*     */   }
/*     */ 
/*     */   public void setSlotType(Short slotType) {
/*  99 */     this.slotType = slotType;
/*     */   }
/*     */ 
/*     */   @Column(name="height", nullable=true, length=10)
/*     */   public Integer getHeight() {
/* 104 */     return this.height;
/*     */   }
/*     */ 
/*     */   public void setHeight(Integer height) {
/* 108 */     this.height = height;
/*     */   }
/*     */ 
/*     */   @Column(name="width", nullable=true, length=10)
/*     */   public Integer getWidth() {
/* 113 */     return this.width;
/*     */   }
/*     */ 
/*     */   public void setWidth(Integer width) {
/* 117 */     this.width = width;
/*     */   }
/*     */ 
/*     */   @Column(name="remain", nullable=true, length=10)
/*     */   public Integer getRemain() {
/* 122 */     return this.remain;
/*     */   }
/*     */ 
/*     */   public void setRemain(Integer remain) {
/* 126 */     this.remain = remain;
/*     */   }
/*     */ 
/*     */   @Column(name="scrollbar", nullable=true, length=1)
/*     */   public Boolean getScrollbar() {
/* 131 */     return this.scrollbar;
/*     */   }
/*     */ 
/*     */   public void setScrollbar(Boolean scrollbar) {
/* 135 */     this.scrollbar = scrollbar;
/*     */   }
/*     */ 
/*     */   @Column(name="sexplain", nullable=true, length=500)
/*     */   public String getSexplain() {
/* 140 */     return this.sexplain;
/*     */   }
/*     */ 
/*     */   public void setSexplain(String sexplain) {
/* 144 */     this.sexplain = sexplain;
/*     */   }
/*     */ 
/*     */   @Column(name="idleholder", nullable=true, length=1)
/*     */   public Boolean getIdleholder() {
/* 149 */     return this.idleholder;
/*     */   }
/*     */ 
/*     */   public void setIdleholder(Boolean idleholder) {
/* 153 */     this.idleholder = idleholder;
/*     */   }
/*     */ 
/*     */   @Column(name="rotation", nullable=false, length=1)
/*     */   public Boolean getRotation() {
/* 158 */     return this.rotation;
/*     */   }
/*     */ 
/*     */   public void setRotation(Boolean rotation) {
/* 162 */     this.rotation = rotation;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="site_id", nullable=false)
/*     */   public Site getSite() {
/* 168 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(Site site) {
/* 172 */     this.site = site;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.entity.AdvertSlot
 * JD-Core Version:    0.6.1
 */