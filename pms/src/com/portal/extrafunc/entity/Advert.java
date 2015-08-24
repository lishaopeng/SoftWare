/*     */ package com.portal.extrafunc.entity;
/*     */ 
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import java.io.Serializable;
/*     */ import java.sql.Time;
/*     */ import java.util.Date;
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
/*     */ import javax.persistence.Temporal;
/*     */ import javax.persistence.TemporalType;
/*     */ import javax.persistence.Transient;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tq_advert")
/*     */ public class Advert
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final String IMG = "img";
/*     */   public static final String FLASH = "flash";
/*     */   public static final String JS = "js";
/*     */   private Integer id;
/*     */   private String name;
/*     */   private String advType;
/*     */   private String attrUrl;
/*     */   private String jsstring;
/*     */   private String url;
/*     */   private Date startTime;
/*     */   private Date endTime;
/*     */   private Integer priority;
/*     */   private Integer weights;
/*     */   private Integer clicks;
/*     */   private Integer showTimes;
/*     */   private String aexplain;
/*     */   private Boolean enable;
/*     */   private AdvertSlot slot;
/*     */   private Site site;
/*     */ 
/*     */   public void init()
/*     */   {
/*  32 */     if (getClicks() == null) {
/*  33 */       setClicks(Integer.valueOf(0));
/*     */     }
/*  35 */     if (getStartTime() == null) {
/*  36 */       setStartTime(new Time(System.currentTimeMillis()));
/*     */     }
/*  38 */     if (getPriority() == null)
/*  39 */       setPriority(Integer.valueOf(10));
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getAdvTypeString()
/*     */   {
/*  45 */     if (getAdvType().equals("img"))
/*  46 */       return "图片";
/*  47 */     if (getAdvType().equals("flash")) {
/*  48 */       return "FLASH";
/*     */     }
/*  50 */     return "代码";
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="advert_id", unique=true, nullable=false)
/*     */   @TableGenerator(name="tg_advert", pkColumnValue="tq_advert", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*     */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_advert")
/*     */   public Integer getId()
/*     */   {
/*  81 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  85 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="name", nullable=false, length=50)
/*     */   public String getName() {
/*  90 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  94 */     this.name = name;
/*     */   }
/*     */ 
/*     */   @Column(name="adv_type", nullable=false, length=20)
/*     */   public String getAdvType() {
/*  99 */     return this.advType;
/*     */   }
/*     */ 
/*     */   public void setAdvType(String advType) {
/* 103 */     this.advType = advType;
/*     */   }
/*     */ 
/*     */   @Column(name="attr_url", nullable=true, length=80)
/*     */   public String getAttrUrl() {
/* 108 */     return this.attrUrl;
/*     */   }
/*     */ 
/*     */   public void setAttrUrl(String attrUrl) {
/* 112 */     this.attrUrl = attrUrl;
/*     */   }
/*     */ 
/*     */   @Column(name="jsstring", nullable=true, length=500)
/*     */   public String getJsstring() {
/* 117 */     return this.jsstring;
/*     */   }
/*     */ 
/*     */   public void setJsstring(String jsstring) {
/* 121 */     this.jsstring = jsstring;
/*     */   }
/*     */ 
/*     */   @Column(name="url", nullable=false, length=100)
/*     */   public String getUrl() {
/* 126 */     return this.url;
/*     */   }
/*     */ 
/*     */   public void setUrl(String url) {
/* 130 */     this.url = url;
/*     */   }
/*     */   @Temporal(TemporalType.DATE)
/*     */   @Column(name="start_time", nullable=false, length=10)
/*     */   public Date getStartTime() {
/* 136 */     return this.startTime;
/*     */   }
/*     */ 
/*     */   public void setStartTime(Date startTime) {
/* 140 */     this.startTime = startTime;
/*     */   }
/*     */   @Temporal(TemporalType.DATE)
/*     */   @Column(name="end_time", nullable=true, length=10)
/*     */   public Date getEndTime() {
/* 146 */     return this.endTime;
/*     */   }
/*     */ 
/*     */   public void setEndTime(Date endTime) {
/* 150 */     this.endTime = endTime;
/*     */   }
/*     */ 
/*     */   @Column(name="priority", nullable=false, length=10)
/*     */   public Integer getPriority() {
/* 155 */     return this.priority;
/*     */   }
/*     */ 
/*     */   public void setPriority(Integer priority) {
/* 159 */     this.priority = priority;
/*     */   }
/*     */ 
/*     */   @Column(name="weights", nullable=true, length=10)
/*     */   public Integer getWeights() {
/* 164 */     return this.weights;
/*     */   }
/*     */ 
/*     */   public void setWeights(Integer weights) {
/* 168 */     this.weights = weights;
/*     */   }
/*     */ 
/*     */   @Column(name="clicks", nullable=false, length=10)
/*     */   public Integer getClicks() {
/* 173 */     return this.clicks;
/*     */   }
/*     */ 
/*     */   public void setClicks(Integer clicks) {
/* 177 */     this.clicks = clicks;
/*     */   }
/*     */ 
/*     */   @Column(name="show_times", nullable=true, length=10)
/*     */   public Integer getShowTimes() {
/* 182 */     return this.showTimes;
/*     */   }
/*     */ 
/*     */   public void setShowTimes(Integer showTimes) {
/* 186 */     this.showTimes = showTimes;
/*     */   }
/*     */ 
/*     */   @Column(name="aexplain", nullable=true, length=500)
/*     */   public String getAexplain() {
/* 191 */     return this.aexplain;
/*     */   }
/*     */ 
/*     */   public void setAexplain(String aexplain) {
/* 195 */     this.aexplain = aexplain;
/*     */   }
/*     */ 
/*     */   @Column(name="enable", nullable=false, length=1)
/*     */   public Boolean getEnable() {
/* 200 */     return this.enable;
/*     */   }
/*     */ 
/*     */   public void setEnable(Boolean enable) {
/* 204 */     this.enable = enable;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="slot_id", nullable=false)
/*     */   public AdvertSlot getSlot() {
/* 210 */     return this.slot;
/*     */   }
/*     */ 
/*     */   public void setSlot(AdvertSlot slot) {
/* 214 */     this.slot = slot;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="site_id", nullable=false)
/*     */   public Site getSite() {
/* 220 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(Site site) {
/* 224 */     this.site = site;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.entity.Advert
 * JD-Core Version:    0.6.1
 */