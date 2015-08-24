/*     */ package com.portal.doccenter.entity;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.OneToOne;
/*     */ import javax.persistence.PrimaryKeyJoinColumn;
/*     */ import javax.persistence.Table;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tq_channel_ext")
/*     */ public class ChannelExt
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Integer id;
/*     */   private String link;
/*     */   private String tplChannel;
/*     */   private Boolean staticChannel;
/*     */   private Boolean staticDoc;
/*     */   private Boolean commentControl;
/*     */   private Boolean updownControl;
/*     */   private Boolean blank;
/*     */   private Boolean sign;
/*     */   private String title;
/*     */   private String keywords;
/*     */   private String description;
/*     */   private String imgSrc;
/*     */   private Channel channel;
/*     */ 
/*     */   public void init()
/*     */   {
/*  23 */     if (getBlank() == null) {
/*  24 */       setBlank(Boolean.valueOf(false));
/*     */     }
/*  26 */     if (getSign() == null) {
/*  27 */       setSign(Boolean.valueOf(false));
/*     */     }
/*  29 */     if (getCommentControl() == null) {
/*  30 */       setCommentControl(Boolean.valueOf(true));
/*     */     }
/*  32 */     if (getUpdownControl() == null) {
/*  33 */       setUpdownControl(Boolean.valueOf(true));
/*     */     }
/*  35 */     if (getStaticChannel() == null) {
/*  36 */       setStaticChannel(Boolean.valueOf(true));
/*     */     }
/*  38 */     if (getStaticDoc() == null) {
/*  39 */       setStaticDoc(Boolean.valueOf(true));
/*     */     }
/*  41 */     blankToNull();
/*     */   }
/*     */ 
/*     */   public void blankToNull() {
/*  45 */     if (StringUtils.isBlank(getLink())) {
/*  46 */       setLink(null);
/*     */     }
/*  48 */     if (StringUtils.isBlank(getTplChannel())) {
/*  49 */       setTplChannel(null);
/*     */     }
/*  51 */     if (StringUtils.isBlank(getTitle())) {
/*  52 */       setTitle(null);
/*     */     }
/*  54 */     if (StringUtils.isBlank(getKeywords())) {
/*  55 */       setKeywords(null);
/*     */     }
/*  57 */     if (StringUtils.isBlank(getDescription()))
/*  58 */       setDescription(null);
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="channel_id", unique=true, nullable=false)
/*     */   @GenericGenerator(name="copy", strategy="foreign", parameters={@org.hibernate.annotations.Parameter(name="property", value="channel")})
/*     */   @GeneratedValue(generator="copy")
/*     */   public Integer getId()
/*     */   {
/*  87 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  91 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="link", nullable=true, length=150)
/*     */   public String getLink() {
/*  96 */     return this.link;
/*     */   }
/*     */ 
/*     */   public void setLink(String link) {
/* 100 */     this.link = link;
/*     */   }
/*     */ 
/*     */   @Column(name="tpl_channel", nullable=true, length=100)
/*     */   public String getTplChannel() {
/* 105 */     return this.tplChannel;
/*     */   }
/*     */ 
/*     */   public void setTplChannel(String tplChannel) {
/* 109 */     this.tplChannel = tplChannel;
/*     */   }
/*     */ 
/*     */   @Column(name="is_static_channel", nullable=false, length=1)
/*     */   public Boolean getStaticChannel() {
/* 114 */     return this.staticChannel;
/*     */   }
/*     */ 
/*     */   public void setStaticChannel(Boolean staticChannel) {
/* 118 */     this.staticChannel = staticChannel;
/*     */   }
/*     */ 
/*     */   @Column(name="is_static_doc", nullable=false, length=1)
/*     */   public Boolean getStaticDoc() {
/* 123 */     return this.staticDoc;
/*     */   }
/*     */ 
/*     */   public void setStaticDoc(Boolean staticDoc) {
/* 127 */     this.staticDoc = staticDoc;
/*     */   }
/*     */ 
/*     */   @Column(name="comment_control", nullable=false, length=1)
/*     */   public Boolean getCommentControl() {
/* 132 */     return this.commentControl;
/*     */   }
/*     */ 
/*     */   public void setCommentControl(Boolean commentControl) {
/* 136 */     this.commentControl = commentControl;
/*     */   }
/*     */ 
/*     */   @Column(name="updown_control", nullable=true, length=1)
/*     */   public Boolean getUpdownControl() {
/* 141 */     return this.updownControl;
/*     */   }
/*     */ 
/*     */   public void setUpdownControl(Boolean updownControl) {
/* 145 */     this.updownControl = updownControl;
/*     */   }
/*     */ 
/*     */   @Column(name="is_blank", nullable=false, length=1)
/*     */   public Boolean getBlank() {
/* 150 */     return this.blank;
/*     */   }
/*     */ 
/*     */   public void setBlank(Boolean blank) {
/* 154 */     this.blank = blank;
/*     */   }
/*     */ 
/*     */   @Column(name="is_sign", nullable=false, length=1)
/*     */   public Boolean getSign() {
/* 159 */     return this.sign;
/*     */   }
/*     */ 
/*     */   public void setSign(Boolean sign) {
/* 163 */     this.sign = sign;
/*     */   }
/*     */ 
/*     */   @Column(name="title", nullable=true, length=100)
/*     */   public String getTitle() {
/* 168 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title) {
/* 172 */     this.title = title;
/*     */   }
/*     */ 
/*     */   @Column(name="keywords", nullable=true, length=100)
/*     */   public String getKeywords() {
/* 177 */     return this.keywords;
/*     */   }
/*     */ 
/*     */   public void setKeywords(String keywords) {
/* 181 */     this.keywords = keywords;
/*     */   }
/*     */ 
/*     */   @Column(name="description", nullable=true, length=255)
/*     */   public String getDescription() {
/* 186 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description) {
/* 190 */     this.description = description;
/*     */   }
/*     */ 
/*     */   @Column(name="img_src", nullable=true, length=100)
/*     */   public String getImgSrc() {
/* 195 */     return this.imgSrc;
/*     */   }
/*     */ 
/*     */   public void setImgSrc(String imgSrc) {
/* 199 */     this.imgSrc = imgSrc;
/*     */   }
/*     */   @OneToOne
/*     */   @PrimaryKeyJoinColumn
/*     */   public Channel getChannel() {
/* 205 */     return this.channel;
/*     */   }
/*     */ 
/*     */   public void setChannel(Channel channel) {
/* 209 */     this.channel = channel;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.entity.ChannelExt
 * JD-Core Version:    0.6.1
 */