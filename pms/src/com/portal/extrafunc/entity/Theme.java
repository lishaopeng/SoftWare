/*     */ package com.portal.extrafunc.entity;
/*     */ 
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.usermgr.entity.User;
/*     */ import java.io.Serializable;
/*     */ import java.sql.Timestamp;
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
/*     */ import javax.persistence.Temporal;
/*     */ import javax.persistence.TemporalType;
/*     */ import javax.persistence.Transient;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tq_theme")
/*     */ public class Theme
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final int TOP_THREE = 3;
/*     */   public static final int TOP_TWO = 2;
/*     */   public static final int TOP_ONE = 1;
/*     */   public static final int NORMAL = 0;
/*     */   public static final int SHIELD = -1;
/*     */   public static final int DELETE = -2;
/*     */   public static final String HTEME_DETAIL = "themeDetail";
/*     */   private Integer id;
/*     */   private String title;
/*     */   private Integer viewsCount;
/*     */   private Integer replyCount;
/*     */   private Boolean lock;
/*     */   private Boolean essena;
/*     */   private Boolean bold;
/*     */   private Boolean italic;
/*     */   private String color;
/*     */   private java.util.Date topTime;
/*     */   private java.util.Date essenaTime;
/*     */   private java.util.Date lockTime;
/*     */   private java.util.Date lightTime;
/*     */   private Integer status;
/*     */   private Boolean affix;
/*     */   private Boolean img;
/*     */   private Boolean moderReply;
/*     */   private java.util.Date lastReplyTime;
/*     */   private java.util.Date createTime;
/*     */   private ThemeTxt txt;
/*     */   private User creater;
/*     */   private Forum forum;
/*     */   private Site site;
/*     */   private User lastReplyer;
/*     */ 
/*     */   public void init()
/*     */   {
/*  47 */     if (getViewsCount() == null) {
/*  48 */       setViewsCount(Integer.valueOf(0));
/*     */     }
/*  50 */     if (getReplyCount() == null) {
/*  51 */       setReplyCount(Integer.valueOf(0));
/*     */     }
/*  53 */     if (getLock() == null) {
/*  54 */       setLock(Boolean.valueOf(false));
/*     */     }
/*  56 */     if (getBold() == null) {
/*  57 */       setBold(Boolean.valueOf(false));
/*     */     }
/*  59 */     if (getEssena() == null) {
/*  60 */       setEssena(Boolean.valueOf(false));
/*     */     }
/*  62 */     if (getItalic() == null) {
/*  63 */       setItalic(Boolean.valueOf(false));
/*     */     }
/*  65 */     if (getModerReply() == null) {
/*  66 */       setModerReply(Boolean.valueOf(false));
/*     */     }
/*  68 */     if (getStatus() == null) {
/*  69 */       setStatus(Integer.valueOf(0));
/*     */     }
/*  71 */     if (getCreateTime() == null) {
/*  72 */       setCreateTime(new Timestamp(System.currentTimeMillis()));
/*     */     }
/*  74 */     if (getLastReplyTime() == null)
/*  75 */       setLastReplyTime(new Timestamp(System.currentTimeMillis()));
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getUrl()
/*     */   {
/*  81 */     StringBuilder sb = new StringBuilder(getSite().getUrl());
/*  82 */     sb.append("themeDetail");
/*  83 */     sb.append("-");
/*  84 */     sb.append(getId());
/*  85 */     sb.append(".jsp");
/*  86 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getUrl(Integer pn) {
/*  91 */     StringBuilder sb = new StringBuilder(getSite().getUrl());
/*  92 */     sb.append("themeDetail");
/*  93 */     sb.append("-");
/*  94 */     sb.append(getId());
/*  95 */     sb.append("_");
/*  96 */     sb.append(pn);
/*  97 */     sb.append(".jsp");
/*  98 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public boolean getTop() {
/* 103 */     if (getStatus().intValue() > 0) {
/* 104 */       return true;
/*     */     }
/* 106 */     return false;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public boolean getCheckTopTime() {
/* 111 */     if (getTopTime() == null) {
/* 112 */       return false;
/*     */     }
/* 114 */     java.sql.Date d = new java.sql.Date(System.currentTimeMillis());
/* 115 */     java.sql.Date d1 = new java.sql.Date(getTopTime().getTime());
/* 116 */     if (d.toString().equals(d1.toString())) {
/* 117 */       return true;
/*     */     }
/* 119 */     return false;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public boolean getCheckLightTime() {
/* 124 */     if (getLightTime() == null) {
/* 125 */       return false;
/*     */     }
/* 127 */     java.sql.Date d = new java.sql.Date(System.currentTimeMillis());
/* 128 */     java.sql.Date d1 = new java.sql.Date(getLightTime().getTime());
/* 129 */     if (d.toString().equals(d1.toString())) {
/* 130 */       return true;
/*     */     }
/* 132 */     return false;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public boolean getCheckLockTime() {
/* 137 */     if (getLockTime() == null) {
/* 138 */       return false;
/*     */     }
/* 140 */     java.sql.Date d = new java.sql.Date(System.currentTimeMillis());
/* 141 */     java.sql.Date d1 = new java.sql.Date(getLockTime().getTime());
/* 142 */     if (d.toString().equals(d1.toString())) {
/* 143 */       return true;
/*     */     }
/* 145 */     return false;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public boolean getNewTheme() {
/* 150 */     java.sql.Date d = new java.sql.Date(System.currentTimeMillis());
/* 151 */     java.sql.Date d1 = new java.sql.Date(getCreateTime().getTime());
/* 152 */     if (d.toString().equals(d1.toString())) {
/* 153 */       return true;
/*     */     }
/* 155 */     return false;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getReplyTimeString() {
/* 160 */     java.util.Date d = new java.util.Date();
/* 161 */     long s = d.getTime() - getLastReplyTime().getTime();
/* 162 */     s /= 1000L;
/* 163 */     if (s < 60L)
/* 164 */       return s + "秒前";
/* 165 */     if ((s > 60L) && (s < 3600L)) {
/* 166 */       s /= 60L;
/* 167 */       return s + "分钟前";
/* 168 */     }if ((s > 3600L) && (s < 86400L)) {
/* 169 */       s /= 3600L;
/* 170 */       return s + "小时前";
/*     */     }
/* 172 */     return getLastReplyTime().toString().substring(0, 
/* 173 */       getLastReplyTime().toString().length() - 2);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getReplyUser()
/*     */   {
/* 179 */     ThemeTxt txt = getTxt();
/* 180 */     if (txt != null) {
/* 181 */       return txt.getContent();
/*     */     }
/* 183 */     return ",";
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="theme_id", unique=true, nullable=false)
/*     */   @TableGenerator(name="tg_theme", pkColumnValue="tq_theme", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*     */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_theme")
/*     */   public Integer getId()
/*     */   {
/* 223 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/* 227 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="title", nullable=false, length=150)
/*     */   public String getTitle() {
/* 232 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title) {
/* 236 */     this.title = title;
/*     */   }
/*     */ 
/*     */   @Column(name="views_count", nullable=false, length=10)
/*     */   public Integer getViewsCount() {
/* 241 */     return this.viewsCount;
/*     */   }
/*     */ 
/*     */   public void setViewsCount(Integer viewsCount) {
/* 245 */     this.viewsCount = viewsCount;
/*     */   }
/*     */ 
/*     */   @Column(name="reply_count", nullable=false, length=10)
/*     */   public Integer getReplyCount() {
/* 250 */     return this.replyCount;
/*     */   }
/*     */ 
/*     */   public void setReplyCount(Integer replyCount) {
/* 254 */     this.replyCount = replyCount;
/*     */   }
/*     */ 
/*     */   @Column(name="is_lock", nullable=false, length=1)
/*     */   public Boolean getLock() {
/* 259 */     return this.lock;
/*     */   }
/*     */ 
/*     */   public void setLock(Boolean lock) {
/* 263 */     this.lock = lock;
/*     */   }
/*     */ 
/*     */   @Column(name="is_essena", nullable=true, length=1)
/*     */   public Boolean getEssena() {
/* 268 */     return this.essena;
/*     */   }
/*     */ 
/*     */   public void setEssena(Boolean essena) {
/* 272 */     this.essena = essena;
/*     */   }
/*     */ 
/*     */   @Column(name="is_bold", nullable=true, length=1)
/*     */   public Boolean getBold() {
/* 277 */     return this.bold;
/*     */   }
/*     */ 
/*     */   public void setBold(Boolean bold) {
/* 281 */     this.bold = bold;
/*     */   }
/*     */ 
/*     */   @Column(name="is_italic", nullable=true, length=1)
/*     */   public Boolean getItalic() {
/* 286 */     return this.italic;
/*     */   }
/*     */ 
/*     */   public void setItalic(Boolean italic) {
/* 290 */     this.italic = italic;
/*     */   }
/*     */ 
/*     */   @Column(name="color", nullable=true, length=50)
/*     */   public String getColor() {
/* 295 */     return this.color;
/*     */   }
/*     */ 
/*     */   public void setColor(String color) {
/* 299 */     this.color = color;
/*     */   }
/*     */   @Temporal(TemporalType.DATE)
/*     */   @Column(name="top_time", nullable=true, length=19)
/*     */   public java.util.Date getTopTime() {
/* 305 */     return this.topTime;
/*     */   }
/*     */ 
/*     */   public void setTopTime(java.util.Date topTime) {
/* 309 */     this.topTime = topTime;
/*     */   }
/*     */   @Temporal(TemporalType.DATE)
/*     */   @Column(name="essena_time", nullable=true, length=19)
/*     */   public java.util.Date getEssenaTime() {
/* 315 */     return this.essenaTime;
/*     */   }
/*     */ 
/*     */   public void setEssenaTime(java.util.Date essenaTime) {
/* 319 */     this.essenaTime = essenaTime;
/*     */   }
/*     */   @Temporal(TemporalType.DATE)
/*     */   @Column(name="lock_time", nullable=true, length=19)
/*     */   public java.util.Date getLockTime() {
/* 325 */     return this.lockTime;
/*     */   }
/*     */ 
/*     */   public void setLockTime(java.util.Date lockTime) {
/* 329 */     this.lockTime = lockTime;
/*     */   }
/*     */   @Temporal(TemporalType.DATE)
/*     */   @Column(name="light_time", nullable=true, length=19)
/*     */   public java.util.Date getLightTime() {
/* 335 */     return this.lightTime;
/*     */   }
/*     */ 
/*     */   public void setLightTime(java.util.Date lightTime) {
/* 339 */     this.lightTime = lightTime;
/*     */   }
/*     */ 
/*     */   @Column(name="status", nullable=false, length=10)
/*     */   public Integer getStatus() {
/* 344 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Integer status) {
/* 348 */     this.status = status;
/*     */   }
/*     */ 
/*     */   @Column(name="is_affix", nullable=false, length=1)
/*     */   public Boolean getAffix() {
/* 353 */     return this.affix;
/*     */   }
/*     */ 
/*     */   public void setAffix(Boolean affix) {
/* 357 */     this.affix = affix;
/*     */   }
/*     */ 
/*     */   @Column(name="is_img", nullable=false, length=1)
/*     */   public Boolean getImg() {
/* 362 */     return this.img;
/*     */   }
/*     */ 
/*     */   public void setImg(Boolean img) {
/* 366 */     this.img = img;
/*     */   }
/*     */ 
/*     */   @Column(name="is_moder_reply", nullable=false, length=1)
/*     */   public Boolean getModerReply() {
/* 371 */     return this.moderReply;
/*     */   }
/*     */ 
/*     */   public void setModerReply(Boolean moderReply) {
/* 375 */     this.moderReply = moderReply;
/*     */   }
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   @Column(name="last_reply_time", nullable=true, length=19)
/*     */   public java.util.Date getLastReplyTime() {
/* 381 */     return this.lastReplyTime;
/*     */   }
/*     */ 
/*     */   public void setLastReplyTime(java.util.Date lastReplyTime) {
/* 385 */     this.lastReplyTime = lastReplyTime;
/*     */   }
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   @Column(name="create_time", nullable=false, length=19)
/*     */   public java.util.Date getCreateTime() {
/* 391 */     return this.createTime;
/*     */   }
/*     */ 
/*     */   public void setCreateTime(java.util.Date createTime) {
/* 395 */     this.createTime = createTime;
/*     */   }
/*     */   @OneToOne(cascade={javax.persistence.CascadeType.REMOVE}, fetch=FetchType.LAZY)
/*     */   @PrimaryKeyJoinColumn
/*     */   public ThemeTxt getTxt() {
/* 401 */     return this.txt;
/*     */   }
/*     */ 
/*     */   public void setTxt(ThemeTxt txt) {
/* 405 */     this.txt = txt;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="creater_id", nullable=true)
/*     */   public User getCreater() {
/* 411 */     return this.creater;
/*     */   }
/*     */ 
/*     */   public void setCreater(User creater) {
/* 415 */     this.creater = creater;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="forum_id", nullable=false)
/*     */   public Forum getForum() {
/* 421 */     return this.forum;
/*     */   }
/*     */ 
/*     */   public void setForum(Forum forum) {
/* 425 */     this.forum = forum;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="site_id", nullable=false)
/*     */   public Site getSite() {
/* 431 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(Site site) {
/* 435 */     this.site = site;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="last_replyer_id", nullable=true)
/*     */   public User getLastReplyer() {
/* 441 */     return this.lastReplyer;
/*     */   }
/*     */ 
/*     */   public void setLastReplyer(User lastReplyer) {
/* 445 */     this.lastReplyer = lastReplyer;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.entity.Theme
 * JD-Core Version:    0.6.1
 */