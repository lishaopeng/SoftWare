/*     */ package com.portal.doccenter.entity;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.OneToOne;
/*     */ import javax.persistence.PrimaryKeyJoinColumn;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.Temporal;
/*     */ import javax.persistence.TemporalType;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tq_article_ext")
/*     */ public class ArticleExt
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Integer id;
/*     */   private String subTitle;
/*     */   private String author;
/*     */   private String origin;
/*     */   private String originUrl;
/*     */   private String tagStr;
/*     */   private Boolean showIndex;
/*     */   private Boolean redTape;
/*     */   private String redtapeOrigin;
/*     */   private String description;
/*     */   private String tplContent;
/*     */   private String link;
/*     */   private Boolean commentControl;
/*     */   private Boolean updownControl;
/*     */   private Date timeDay;
/*     */   private Date timeHour;
/*     */   private Article article;
/*     */ 
/*     */   public void init()
/*     */   {
/*  25 */     if (getShowIndex() == null) {
/*  26 */       setShowIndex(Boolean.valueOf(true));
/*     */     }
/*  28 */     if (getRedTape() == null) {
/*  29 */       setRedTape(Boolean.valueOf(false));
/*     */     }
/*  31 */     if (getCommentControl() == null) {
/*  32 */       setCommentControl(Boolean.valueOf(true));
/*     */     }
/*  34 */     if (getUpdownControl() == null) {
/*  35 */       setUpdownControl(Boolean.valueOf(true));
/*     */     }
/*  37 */     if (StringUtils.isBlank(getAuthor())) {
/*  38 */       setAuthor(null);
/*     */     }
/*  40 */     if (StringUtils.isBlank(getOrigin())) {
/*  41 */       setOrigin(null);
/*     */     }
/*  43 */     if (StringUtils.isBlank(getOriginUrl())) {
/*  44 */       setOriginUrl(null);
/*     */     }
/*  46 */     if (StringUtils.isBlank(getDescription())) {
/*  47 */       setDescription(null);
/*     */     }
/*  49 */     if (StringUtils.isBlank(getLink())) {
/*  50 */       setLink(null);
/*     */     }
/*  52 */     if (StringUtils.isBlank(getTplContent()))
/*  53 */       setTplContent(null);
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="article_id", unique=true, nullable=false)
/*     */   @GenericGenerator(name="copy", strategy="foreign", parameters={@org.hibernate.annotations.Parameter(name="property", value="article")})
/*     */   @GeneratedValue(generator="copy")
/*     */   public Integer getId()
/*     */   {
/*  85 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  89 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="sub_title", nullable=true, length=100)
/*     */   public String getSubTitle() {
/*  94 */     return this.subTitle;
/*     */   }
/*     */ 
/*     */   public void setSubTitle(String subTitle) {
/*  98 */     this.subTitle = subTitle;
/*     */   }
/*     */ 
/*     */   @Column(name="author", nullable=true, length=30)
/*     */   public String getAuthor() {
/* 103 */     return this.author;
/*     */   }
/*     */ 
/*     */   public void setAuthor(String author) {
/* 107 */     this.author = author;
/*     */   }
/*     */ 
/*     */   @Column(name="origin", nullable=true, length=50)
/*     */   public String getOrigin() {
/* 112 */     return this.origin;
/*     */   }
/*     */ 
/*     */   public void setOrigin(String origin) {
/* 116 */     this.origin = origin;
/*     */   }
/*     */ 
/*     */   @Column(name="origin_url", nullable=true, length=50)
/*     */   public String getOriginUrl() {
/* 121 */     return this.originUrl;
/*     */   }
/*     */ 
/*     */   public void setOriginUrl(String originUrl) {
/* 125 */     this.originUrl = originUrl;
/*     */   }
/*     */ 
/*     */   @Column(name="tag_str", nullable=true, length=50)
/*     */   public String getTagStr() {
/* 130 */     return this.tagStr;
/*     */   }
/*     */ 
/*     */   public void setTagStr(String tagStr) {
/* 134 */     this.tagStr = tagStr;
/*     */   }
/*     */ 
/*     */   @Column(name="show_index", nullable=true, length=1)
/*     */   public Boolean getShowIndex() {
/* 139 */     return this.showIndex;
/*     */   }
/*     */ 
/*     */   public void setShowIndex(Boolean showIndex) {
/* 143 */     this.showIndex = showIndex;
/*     */   }
/*     */ 
/*     */   @Column(name="is_red_tape", nullable=false, length=1)
/*     */   public Boolean getRedTape() {
/* 148 */     return this.redTape;
/*     */   }
/*     */ 
/*     */   public void setRedTape(Boolean redTape) {
/* 152 */     this.redTape = redTape;
/*     */   }
/*     */ 
/*     */   @Column(name="red_tape_origin", nullable=true, length=100)
/*     */   public String getRedtapeOrigin() {
/* 157 */     return this.redtapeOrigin;
/*     */   }
/*     */ 
/*     */   public void setRedtapeOrigin(String redtapeOrigin) {
/* 161 */     this.redtapeOrigin = redtapeOrigin;
/*     */   }
/*     */ 
/*     */   @Column(name="description", nullable=true, length=255)
/*     */   public String getDescription() {
/* 166 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description) {
/* 170 */     this.description = description;
/*     */   }
/*     */ 
/*     */   @Column(name="tpl_content", nullable=true, length=100)
/*     */   public String getTplContent() {
/* 175 */     return this.tplContent;
/*     */   }
/*     */ 
/*     */   public void setTplContent(String tplContent) {
/* 179 */     this.tplContent = tplContent;
/*     */   }
/*     */ 
/*     */   @Column(name="link", nullable=true, length=100)
/*     */   public String getLink() {
/* 184 */     return this.link;
/*     */   }
/*     */ 
/*     */   public void setLink(String link) {
/* 188 */     this.link = link;
/*     */   }
/*     */ 
/*     */   @Column(name="comment_control", nullable=true, length=1)
/*     */   public Boolean getCommentControl() {
/* 193 */     return this.commentControl;
/*     */   }
/*     */ 
/*     */   public void setCommentControl(Boolean commentControl) {
/* 197 */     this.commentControl = commentControl;
/*     */   }
/*     */ 
/*     */   @Column(name="updown_control", nullable=true, length=1)
/*     */   public Boolean getUpdownControl() {
/* 202 */     return this.updownControl;
/*     */   }
/*     */ 
/*     */   public void setUpdownControl(Boolean updownControl) {
/* 206 */     this.updownControl = updownControl;
/*     */   }
/*     */   @Temporal(TemporalType.DATE)
/*     */   @Column(name="time_day", nullable=true, length=19)
/*     */   public Date getTimeDay() {
/* 212 */     return this.timeDay;
/*     */   }
/*     */ 
/*     */   public void setTimeDay(Date timeDay) {
/* 216 */     this.timeDay = timeDay;
/*     */   }
/*     */   @Temporal(TemporalType.TIME)
/*     */   @Column(name="time_hour", nullable=true, length=19)
/*     */   public Date getTimeHour() {
/* 222 */     return this.timeHour;
/*     */   }
/*     */ 
/*     */   public void setTimeHour(Date timeHour) {
/* 226 */     this.timeHour = timeHour;
/*     */   }
/*     */   @OneToOne
/*     */   @PrimaryKeyJoinColumn
/*     */   public Article getArticle() {
/* 232 */     return this.article;
/*     */   }
/*     */ 
/*     */   public void setArticle(Article article) {
/* 236 */     this.article = article;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.entity.ArticleExt
 * JD-Core Version:    0.6.1
 */