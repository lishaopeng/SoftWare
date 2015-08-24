/*     */ package com.portal.extrafunc.entity;
/*     */ 
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.usermgr.entity.User;
/*     */ import java.io.Serializable;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.GenerationType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.OneToOne;
/*     */ import javax.persistence.PrimaryKeyJoinColumn;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.TableGenerator;
/*     */ import javax.persistence.Transient;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tq_forum")
/*     */ public class Forum
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final String HTEME_LIST = "themeList";
/*     */   private Integer id;
/*     */   private String name;
/*     */   private Integer priority;
/*     */   private Integer themeTotal;
/*     */   private Integer replyTotal;
/*     */   private Integer themeToday;
/*     */   private Integer replyToday;
/*     */   private String moderators;
/*     */   private ForumExt ext;
/*     */   private Theme lastTheme;
/*     */   private Site site;
/*     */   private Category category;
/*     */   private User lastReplyer;
/*     */ 
/*     */   public void init()
/*     */   {
/*  31 */     if (getPriority() == null) {
/*  32 */       setPriority(Integer.valueOf(10));
/*     */     }
/*  34 */     if (getThemeTotal() == null) {
/*  35 */       setThemeTotal(Integer.valueOf(0));
/*     */     }
/*  37 */     if (getThemeToday() == null) {
/*  38 */       setThemeToday(Integer.valueOf(0));
/*     */     }
/*  40 */     if (getReplyTotal() == null) {
/*  41 */       setReplyTotal(Integer.valueOf(0));
/*     */     }
/*  43 */     if (getReplyToday() == null)
/*  44 */       setReplyToday(Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getUrl()
/*     */   {
/*  50 */     StringBuilder sb = new StringBuilder(getSite().getUrl());
/*  51 */     sb.append("themeList");
/*  52 */     sb.append("-");
/*  53 */     sb.append(getId());
/*  54 */     sb.append(".jsp");
/*  55 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getUrl(Integer pn) {
/*  60 */     StringBuilder sb = new StringBuilder(getSite().getUrl());
/*  61 */     sb.append("themeList");
/*  62 */     sb.append("-");
/*  63 */     sb.append(getId());
/*  64 */     sb.append("_");
/*  65 */     sb.append(pn);
/*  66 */     sb.append(".jsp");
/*  67 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getKeywords() {
/*  72 */     ForumExt ext = getExt();
/*  73 */     if (ext != null) {
/*  74 */       return ext.getKeywords();
/*     */     }
/*  76 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getDescription() {
/*  81 */     ForumExt ext = getExt();
/*  82 */     if (ext != null) {
/*  83 */       return ext.getDescription();
/*     */     }
/*  85 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getRule() {
/*  90 */     ForumExt ext = getExt();
/*  91 */     if (ext != null) {
/*  92 */       return ext.getRule();
/*     */     }
/*  94 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getTplContent() {
/*  99 */     ForumExt ext = getExt();
/* 100 */     if (ext != null) {
/* 101 */       return ext.getTplContent();
/*     */     }
/* 103 */     return null;
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="forum_id", unique=true, nullable=false)
/*     */   @TableGenerator(name="tg_forum", pkColumnValue="tq_forum", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*     */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_forum")
/*     */   public Integer getId()
/*     */   {
/* 132 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/* 136 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="name", nullable=false, length=50)
/*     */   public String getName() {
/* 141 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/* 145 */     this.name = name;
/*     */   }
/*     */ 
/*     */   @Column(name="priority", nullable=false, length=10)
/*     */   public Integer getPriority() {
/* 150 */     return this.priority;
/*     */   }
/*     */ 
/*     */   public void setPriority(Integer priority) {
/* 154 */     this.priority = priority;
/*     */   }
/*     */ 
/*     */   @Column(name="theme_total", nullable=false, length=10)
/*     */   public Integer getThemeTotal() {
/* 159 */     return this.themeTotal;
/*     */   }
/*     */ 
/*     */   public void setThemeTotal(Integer themeTotal) {
/* 163 */     this.themeTotal = themeTotal;
/*     */   }
/*     */ 
/*     */   @Column(name="reply_total", nullable=false, length=10)
/*     */   public Integer getReplyTotal() {
/* 168 */     return this.replyTotal;
/*     */   }
/*     */ 
/*     */   public void setReplyTotal(Integer replyTotal) {
/* 172 */     this.replyTotal = replyTotal;
/*     */   }
/*     */ 
/*     */   @Column(name="theme_today", nullable=false, length=10)
/*     */   public Integer getThemeToday() {
/* 177 */     return this.themeToday;
/*     */   }
/*     */ 
/*     */   public void setThemeToday(Integer themeToday) {
/* 181 */     this.themeToday = themeToday;
/*     */   }
/*     */ 
/*     */   @Column(name="reply_today", nullable=false, length=10)
/*     */   public Integer getReplyToday() {
/* 186 */     return this.replyToday;
/*     */   }
/*     */ 
/*     */   public void setReplyToday(Integer replyToday) {
/* 190 */     this.replyToday = replyToday;
/*     */   }
/*     */ 
/*     */   @Column(name="moderators", nullable=true, length=50)
/*     */   public String getModerators() {
/* 195 */     return this.moderators;
/*     */   }
/*     */ 
/*     */   public void setModerators(String moderators) {
/* 199 */     this.moderators = moderators;
/*     */   }
/*     */   @OneToOne(cascade={javax.persistence.CascadeType.REMOVE}, fetch=FetchType.LAZY)
/*     */   @PrimaryKeyJoinColumn
/*     */   public ForumExt getExt() {
/* 205 */     return this.ext;
/*     */   }
/*     */ 
/*     */   public void setExt(ForumExt ext) {
/* 209 */     this.ext = ext;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="last_theme_id", nullable=true)
/*     */   public Theme getLastTheme() {
/* 215 */     return this.lastTheme;
/*     */   }
/*     */ 
/*     */   public void setLastTheme(Theme lastTheme) {
/* 219 */     this.lastTheme = lastTheme;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="site_id", nullable=false)
/*     */   public Site getSite() {
/* 225 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(Site site) {
/* 229 */     this.site = site;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="category_id", nullable=false)
/*     */   public Category getCategory() {
/* 235 */     return this.category;
/*     */   }
/*     */ 
/*     */   public void setCategory(Category category) {
/* 239 */     this.category = category;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="last_replyer_id", nullable=true)
/*     */   public User getLastReplyer() {
/* 245 */     return this.lastReplyer;
/*     */   }
/*     */ 
/*     */   public void setLastReplyer(User lastReplyer) {
/* 249 */     this.lastReplyer = lastReplyer;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.entity.Forum
 * JD-Core Version:    0.6.1
 */