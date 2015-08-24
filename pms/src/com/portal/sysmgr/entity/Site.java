/*     */ package com.portal.sysmgr.entity;
/*     */ 
/*     */ import com.portal.extrafunc.entity.ForumStatis;
/*     */ import java.io.Serializable;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.GenerationType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.OneToOne;
/*     */ import javax.persistence.PrimaryKeyJoinColumn;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.TableGenerator;
/*     */ import javax.persistence.Temporal;
/*     */ import javax.persistence.TemporalType;
/*     */ import javax.persistence.Transient;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tq_site")
/*     */ public class Site
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  32 */   public static final Byte NO_STATIC = Byte.valueOf((byte)0);
/*     */ 
/*  34 */   public static final Byte YES_STATIC = Byte.valueOf((byte)1);
/*     */ 
/*  36 */   public static final Byte CHANNEL_STATIC = Byte.valueOf((byte)2);
/*     */   public static final String PROTOCOL = "http://";
/*     */   public static final String HTML = "html";
/*     */   private Integer id;
/*     */   private String domain;
/*     */   private String path;
/*     */   private String name;
/*     */   private String shortName;
/*     */   private String contextPath;
/*     */   private Integer port;
/*     */   private String tplStyle;
/*     */   private String title;
/*     */   private String keywords;
/*     */   private String description;
/*     */   private Byte staticChannel;
/*     */   private Byte staticDoc;
/*     */   private Boolean staticSuffix;
/*     */   private Date updateTime;
/*     */   private String tplIndex;
/*     */   private Boolean terminus;
/*     */   private ForumStatis forumStatis;
/*     */   private SiteConfig config;
/*     */ 
/*     */   @Transient
/*     */   public String getUrl()
/*     */   {
/*  49 */     StringBuilder sb = new StringBuilder();
/*  50 */     sb.append("http://");
/*  51 */     sb.append(getDomain());
/*  52 */     if ((getPort() != null) && (!getPort().equals(Integer.valueOf(80)))) {
/*  53 */       sb.append(":");
/*  54 */       sb.append(getPort());
/*     */     }
/*  56 */     if (getContextPath() != null) {
/*  57 */       sb.append(getContextPath());
/*     */     }
/*  59 */     sb.append("/");
/*  60 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getStaticRealPath() {
/*  65 */     StringBuilder sb = new StringBuilder();
/*  66 */     sb.append("/");
/*  67 */     sb.append("html").append("/");
/*  68 */     sb.append(getPath()).append("/");
/*  69 */     sb.append("index").append(".html");
/*  70 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getTplIndexOrDef() {
/*  75 */     if (!StringUtils.isBlank(getTplIndex())) {
/*  76 */       return getSolutionPath() + getTplIndex();
/*     */     }
/*  78 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getTplPath() {
/*  83 */     return "/WEB-INF/tpl/" + getPath();
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getSolutionPath() {
/*  88 */     return getTplPath() + "/" + getTplStyle();
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getResPath() {
/*  93 */     return "/skin/" + getPath();
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getResSolutionPath() {
/*  98 */     return "/skin/" + getPath() + "/" + getTplStyle();
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getUploadPath() {
/* 103 */     return "/member/upload/" + getPath();
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getDefImg() {
/* 108 */     String skin = "/skin/" + getPath() + "/" + 
/* 109 */       getTplStyle();
/* 110 */     return skin + "/img/nophoto.gif";
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Boolean getCommentCheck() {
/* 115 */     SiteConfig config = getConfig();
/* 116 */     if (config != null) {
/* 117 */       return config.getCommentCheck();
/*     */     }
/* 119 */     return Boolean.valueOf(true);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Boolean getCommentLogin() {
/* 124 */     SiteConfig config = getConfig();
/* 125 */     if (config != null) {
/* 126 */       return config.getCommentLogin();
/*     */     }
/* 128 */     return Boolean.valueOf(false);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Boolean getMessageCheck() {
/* 133 */     SiteConfig config = getConfig();
/* 134 */     if (config != null) {
/* 135 */       return config.getMessageCheck();
/*     */     }
/* 137 */     return Boolean.valueOf(true);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Boolean getMessageLogin() {
/* 142 */     SiteConfig config = getConfig();
/* 143 */     if (config != null) {
/* 144 */       return config.getMessageLogin();
/*     */     }
/* 146 */     return Boolean.valueOf(false);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Boolean getMessageName() {
/* 151 */     SiteConfig config = getConfig();
/* 152 */     if (config != null) {
/* 153 */       return config.getMessageName();
/*     */     }
/* 155 */     return Boolean.valueOf(false);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Boolean getMessageMobile() {
/* 160 */     SiteConfig config = getConfig();
/* 161 */     if (config != null) {
/* 162 */       return config.getMessageMobile();
/*     */     }
/* 164 */     return Boolean.valueOf(false);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Boolean getMessageEmail() {
/* 169 */     SiteConfig config = getConfig();
/* 170 */     if (config != null) {
/* 171 */       return config.getMessageEmail();
/*     */     }
/* 173 */     return Boolean.valueOf(false);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Boolean getMessageAddress() {
/* 178 */     SiteConfig config = getConfig();
/* 179 */     if (config != null) {
/* 180 */       return config.getMessageAddress();
/*     */     }
/* 182 */     return Boolean.valueOf(false);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Boolean getMessageZipcode() {
/* 187 */     SiteConfig config = getConfig();
/* 188 */     if (config != null) {
/* 189 */       return config.getMessageZipcode();
/*     */     }
/* 191 */     return Boolean.valueOf(false);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Boolean getRegOpen() {
/* 196 */     SiteConfig config = getConfig();
/* 197 */     if (config != null) {
/* 198 */       return config.getRegOpen();
/*     */     }
/* 200 */     return Boolean.valueOf(true);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Integer getRegMin() {
/* 205 */     SiteConfig config = getConfig();
/* 206 */     if (config != null) {
/* 207 */       return config.getRegMin();
/*     */     }
/* 209 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Integer getRegMax() {
/* 214 */     SiteConfig config = getConfig();
/* 215 */     if (config != null) {
/* 216 */       return config.getRegMax();
/*     */     }
/* 218 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Boolean getRegCheck() {
/* 223 */     SiteConfig config = getConfig();
/* 224 */     if (config != null) {
/* 225 */       return config.getRegCheck();
/*     */     }
/* 227 */     return Boolean.valueOf(false);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Integer getLoginCount() {
/* 232 */     SiteConfig config = getConfig();
/* 233 */     if (config != null) {
/* 234 */       return config.getLoginCount();
/*     */     }
/* 236 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public boolean getNeedCheck() {
/* 241 */     if ((getLoginCount() != null) && (getLoginCount().intValue() > 0)) {
/* 242 */       return true;
/*     */     }
/* 244 */     return false;
/*     */   }
/*     */ 
/*     */   public static Integer[] fetchIds(Collection<Site> sites) {
/* 248 */     if (sites == null) {
/* 249 */       return null;
/*     */     }
/* 251 */     Integer[] ids = new Integer[sites.size()];
/* 252 */     int i = 0;
/* 253 */     for (Site s : sites) {
/* 254 */       ids[(i++)] = s.getId();
/*     */     }
/* 256 */     return ids;
/*     */   }
/*     */ 
/*     */   public void init() {
/* 260 */     if (StringUtils.isBlank(getTplStyle())) {
/* 261 */       setTplStyle("default");
/*     */     }
/* 263 */     if (getTerminus() == null)
/* 264 */       setTerminus(Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   public void initTime()
/*     */   {
/* 269 */     setUpdateTime(new Timestamp(System.currentTimeMillis()));
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="site_id", unique=true, nullable=false)
/*     */   @TableGenerator(name="tg_site", pkColumnValue="tq_site", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*     */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_site")
/*     */   public Integer getId()
/*     */   {
/* 302 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/* 306 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="domain", unique=true, nullable=false, length=50)
/*     */   public String getDomain() {
/* 311 */     return this.domain;
/*     */   }
/*     */ 
/*     */   public void setDomain(String domain) {
/* 315 */     this.domain = domain;
/*     */   }
/*     */ 
/*     */   @Column(name="site_path", nullable=false, length=20)
/*     */   public String getPath() {
/* 320 */     return this.path;
/*     */   }
/*     */ 
/*     */   public void setPath(String path) {
/* 324 */     this.path = path;
/*     */   }
/*     */ 
/*     */   @Column(name="site_name", nullable=false, length=100)
/*     */   public String getName() {
/* 329 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/* 333 */     this.name = name;
/*     */   }
/*     */ 
/*     */   @Column(name="short_name", nullable=true, length=100)
/*     */   public String getShortName() {
/* 338 */     return this.shortName;
/*     */   }
/*     */ 
/*     */   public void setShortName(String shortName) {
/* 342 */     this.shortName = shortName;
/*     */   }
/*     */ 
/*     */   @Column(name="context_path", nullable=true, length=20)
/*     */   public String getContextPath() {
/* 347 */     return this.contextPath;
/*     */   }
/*     */ 
/*     */   public void setContextPath(String contextPath) {
/* 351 */     this.contextPath = contextPath;
/*     */   }
/*     */ 
/*     */   @Column(name="port", nullable=true, length=10)
/*     */   public Integer getPort() {
/* 356 */     return this.port;
/*     */   }
/*     */ 
/*     */   public void setPort(Integer port) {
/* 360 */     this.port = port;
/*     */   }
/*     */ 
/*     */   @Column(name="tpl_style", nullable=false, length=50)
/*     */   public String getTplStyle() {
/* 365 */     return this.tplStyle;
/*     */   }
/*     */ 
/*     */   public void setTplStyle(String tplStyle) {
/* 369 */     this.tplStyle = tplStyle;
/*     */   }
/*     */ 
/*     */   @Column(name="title", nullable=true, length=80)
/*     */   public String getTitle() {
/* 374 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title) {
/* 378 */     this.title = title;
/*     */   }
/*     */ 
/*     */   @Column(name="keywords", nullable=true, length=100)
/*     */   public String getKeywords() {
/* 383 */     return this.keywords;
/*     */   }
/*     */ 
/*     */   public void setKeywords(String keywords) {
/* 387 */     this.keywords = keywords;
/*     */   }
/*     */ 
/*     */   @Column(name="description", nullable=true, length=255)
/*     */   public String getDescription() {
/* 392 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description) {
/* 396 */     this.description = description;
/*     */   }
/*     */ 
/*     */   @Column(name="is_static_channel", nullable=false, length=1)
/*     */   public Byte getStaticChannel() {
/* 401 */     return this.staticChannel;
/*     */   }
/*     */ 
/*     */   public void setStaticChannel(Byte staticChannel) {
/* 405 */     this.staticChannel = staticChannel;
/*     */   }
/*     */ 
/*     */   @Column(name="is_static_doc", nullable=false, length=1)
/*     */   public Byte getStaticDoc() {
/* 410 */     return this.staticDoc;
/*     */   }
/*     */ 
/*     */   public void setStaticDoc(Byte staticDoc) {
/* 414 */     this.staticDoc = staticDoc;
/*     */   }
/*     */ 
/*     */   @Column(name="is_static_suffix", nullable=false, length=1)
/*     */   public Boolean getStaticSuffix() {
/* 419 */     return this.staticSuffix;
/*     */   }
/*     */ 
/*     */   public void setStaticSuffix(Boolean staticSuffix) {
/* 423 */     this.staticSuffix = staticSuffix;
/*     */   }
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   @Column(name="update_time", nullable=true, length=19)
/*     */   public Date getUpdateTime() {
/* 429 */     return this.updateTime;
/*     */   }
/*     */ 
/*     */   public void setUpdateTime(Date updateTime) {
/* 433 */     this.updateTime = updateTime;
/*     */   }
/*     */ 
/*     */   @Column(name="tpl_index", nullable=true, length=50)
/*     */   public String getTplIndex() {
/* 438 */     return this.tplIndex;
/*     */   }
/*     */ 
/*     */   public void setTplIndex(String tplIndex) {
/* 442 */     this.tplIndex = tplIndex;
/*     */   }
/*     */ 
/*     */   @Column(name="is_terminus", nullable=false, length=1)
/*     */   public Boolean getTerminus() {
/* 447 */     return this.terminus;
/*     */   }
/*     */ 
/*     */   public void setTerminus(Boolean terminus) {
/* 451 */     this.terminus = terminus;
/*     */   }
/*     */   @OneToOne(cascade={javax.persistence.CascadeType.REMOVE}, fetch=FetchType.LAZY)
/*     */   @PrimaryKeyJoinColumn
/*     */   public ForumStatis getForumStatis() {
/* 457 */     return this.forumStatis;
/*     */   }
/*     */ 
/*     */   public void setForumStatis(ForumStatis forumStatis)
/*     */   {
/* 462 */     this.forumStatis = forumStatis;
/*     */   }
/*     */   @OneToOne(cascade={javax.persistence.CascadeType.REMOVE}, fetch=FetchType.LAZY)
/*     */   @PrimaryKeyJoinColumn
/*     */   public SiteConfig getConfig() {
/* 468 */     return this.config;
/*     */   }
/*     */ 
/*     */   public void setConfig(SiteConfig config) {
/* 472 */     this.config = config;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.entity.Site
 * JD-Core Version:    0.6.1
 */