/*     */ package com.portal.usermgr.entity;
/*     */ 
/*     */ import com.portal.doccenter.entity.Channel;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import java.io.Serializable;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.GenerationType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.JoinTable;
/*     */ import javax.persistence.ManyToMany;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.OrderBy;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.TableGenerator;
/*     */ import javax.persistence.Temporal;
/*     */ import javax.persistence.TemporalType;
/*     */ import javax.persistence.Transient;
/*     */ import org.hibernate.annotations.Formula;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tq_depart")
/*     */ public class Depart
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Integer id;
/*     */   private String name;
/*     */   private String shortName;
/*     */   private String number;
/*     */   private String visitPath;
/*     */   private Integer priority;
/*     */   private Integer signCount;
/*     */   private Boolean show;
/*     */   private Boolean allChannel;
/*     */   private Date createTime;
/*     */   private Integer reportCount;
/*     */   private Integer useCount;
/*     */   private Depart parent;
/*     */   private Site site;
/*     */   private Set<Channel> channels;
/*     */   private Set<Depart> child;
/*     */ 
/*     */   public void init()
/*     */   {
/*  39 */     if (getCreateTime() == null) {
/*  40 */       setCreateTime(new Timestamp(System.currentTimeMillis()));
/*     */     }
/*  42 */     if (getPriority() == null) {
/*  43 */       setPriority(Integer.valueOf(10));
/*     */     }
/*  45 */     if (getSignCount() == null) {
/*  46 */       setSignCount(Integer.valueOf(0));
/*     */     }
/*  48 */     if (getShow() == null) {
/*  49 */       setShow(Boolean.valueOf(false));
/*     */     }
/*  51 */     if (getAllChannel() == null)
/*  52 */       setAllChannel(Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   public void initTreeNumber()
/*     */   {
/*  57 */     String number = "-";
/*  58 */     if (getParent() != null) {
/*  59 */       number = getParent().getNumber();
/*     */     }
/*  61 */     setNumber(number + getId() + "-");
/*     */   }
/*     */ 
/*     */   public void addToChannels(Channel channel) {
/*  65 */     if (channel == null) {
/*  66 */       return;
/*     */     }
/*  68 */     Set set = getChannels();
/*  69 */     if (set == null) {
/*  70 */       set = new HashSet();
/*  71 */       setChannels(set);
/*     */     }
/*  73 */     set.add(channel);
/*     */   }
/*     */ 
/*     */   public void addToChilds(Depart child) {
/*  77 */     Set set = getChild();
/*  78 */     if (set == null) {
/*  79 */       set = new HashSet();
/*  80 */       setChild(set);
/*     */     }
/*  82 */     set.add(child);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getUrl() {
/*  87 */     Site site = getSite();
/*  88 */     StringBuilder url = new StringBuilder(site.getUrl());
/*  89 */     url.append("/").append(getVisitPath());
/*  90 */     url.append("/").append("dp~")
/*  91 */       .append("index");
/*  92 */     url.append(".jsp");
/*  93 */     return url.toString();
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public int getDeep() {
/*  98 */     int deep = 0;
/*  99 */     Depart parent = getParent();
/* 100 */     while (parent != null) {
/* 101 */       deep++;
/* 102 */       parent = parent.getParent();
/*     */     }
/* 104 */     return deep;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Integer[] getChannelIds() {
/* 109 */     Set channels = getChannels();
/* 110 */     return Channel.fetchIds(channels);
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="depart_id", unique=true, nullable=false)
/*     */   @TableGenerator(name="tg_depart", pkColumnValue="tq_depart", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*     */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_depart")
/*     */   public Integer getId()
/*     */   {
/* 142 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/* 146 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="name", nullable=false, length=50)
/*     */   public String getName() {
/* 151 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/* 155 */     this.name = name;
/*     */   }
/*     */ 
/*     */   @Column(name="short_name", nullable=false, length=50)
/*     */   public String getShortName() {
/* 160 */     return this.shortName;
/*     */   }
/*     */ 
/*     */   public void setShortName(String shortName) {
/* 164 */     this.shortName = shortName;
/*     */   }
/*     */ 
/*     */   @Column(name="tree_number", nullable=true, length=150)
/*     */   public String getNumber() {
/* 169 */     return this.number;
/*     */   }
/*     */ 
/*     */   public void setNumber(String number) {
/* 173 */     this.number = number;
/*     */   }
/*     */ 
/*     */   @Column(name="visit_path", nullable=true, length=30)
/*     */   public String getVisitPath() {
/* 178 */     return this.visitPath;
/*     */   }
/*     */ 
/*     */   public void setVisitPath(String visitPath) {
/* 182 */     this.visitPath = visitPath;
/*     */   }
/*     */ 
/*     */   @Column(name="priority", nullable=false, length=10)
/*     */   public Integer getPriority() {
/* 187 */     return this.priority;
/*     */   }
/*     */ 
/*     */   public void setPriority(Integer priority) {
/* 191 */     this.priority = priority;
/*     */   }
/*     */ 
/*     */   @Formula("(select count(s.sign_id) from tq_article_sign s where s.depart_id=depart_id)")
/*     */   public Integer getSignCount() {
/* 196 */     return this.signCount;
/*     */   }
/*     */ 
/*     */   public void setSignCount(Integer signCount) {
/* 200 */     this.signCount = signCount;
/*     */   }
/*     */ 
/*     */   @Column(name="is_show", nullable=false, length=1)
/*     */   public Boolean getShow() {
/* 205 */     return this.show;
/*     */   }
/*     */ 
/*     */   public void setShow(Boolean show) {
/* 209 */     this.show = show;
/*     */   }
/*     */ 
/*     */   @Column(name="is_all_channel", nullable=false, length=1)
/*     */   public Boolean getAllChannel() {
/* 214 */     return this.allChannel;
/*     */   }
/*     */ 
/*     */   public void setAllChannel(Boolean allChannel) {
/* 218 */     this.allChannel = allChannel;
/*     */   }
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   @Column(name="create_time", nullable=false, length=19)
/*     */   public Date getCreateTime() {
/* 224 */     return this.createTime;
/*     */   }
/*     */ 
/*     */   public void setCreateTime(Date createTime) {
/* 228 */     this.createTime = createTime;
/*     */   }
/*     */ 
/*     */   @Formula("(select count(a.article_id) from tq_article a,tq_channel c where a.depart_id=depart_id and a.channel_id=c.channel_id and c.flow_id is not null and a.status!=3)")
/*     */   public Integer getReportCount() {
/* 233 */     return this.reportCount;
/*     */   }
/*     */ 
/*     */   public void setReportCount(Integer reportCount) {
/* 237 */     this.reportCount = reportCount;
/*     */   }
/*     */ 
/*     */   @Formula("(select count(a.article_id) from tq_article a,tq_channel c where a.status=2 and a.depart_id=depart_id and a.channel_id=c.channel_id and c.flow_id is not null)")
/*     */   public Integer getUseCount() {
/* 242 */     return this.useCount;
/*     */   }
/*     */ 
/*     */   public void setUseCount(Integer useCount) {
/* 246 */     this.useCount = useCount;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="parent_id", nullable=true)
/*     */   public Depart getParent() {
/* 252 */     return this.parent;
/*     */   }
/*     */ 
/*     */   public void setParent(Depart parent) {
/* 256 */     this.parent = parent;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="site_id", nullable=false)
/*     */   public Site getSite() {
/* 262 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(Site site) {
/* 266 */     this.site = site;
/*     */   }
/*     */   @ManyToMany(fetch=FetchType.LAZY)
/*     */   @JoinTable(name="tq_depart_channel", joinColumns={@JoinColumn(name="depart_id")}, inverseJoinColumns={@JoinColumn(name="channel_id")})
/*     */   public Set<Channel> getChannels() {
/* 272 */     return this.channels;
/*     */   }
/*     */ 
/*     */   public void setChannels(Set<Channel> channels)
/*     */   {
/* 277 */     this.channels = channels;
/*     */   }
/*     */   @OneToMany(fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.REMOVE}, mappedBy="parent")
/*     */   @OrderBy("priority asc")
/*     */   public Set<Depart> getChild() {
/* 283 */     return this.child;
/*     */   }
/*     */ 
/*     */   public void setChild(Set<Depart> child) {
/* 287 */     this.child = child;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.entity.Depart
 * JD-Core Version:    0.6.1
 */