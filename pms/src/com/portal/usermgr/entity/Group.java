/*     */ package com.portal.usermgr.entity;
/*     */ 
/*     */ import com.portal.doccenter.entity.Channel;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Set;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.GenerationType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToMany;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.OneToOne;
/*     */ import javax.persistence.PrimaryKeyJoinColumn;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.TableGenerator;
/*     */ import javax.persistence.Transient;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tq_group")
/*     */ public class Group
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Integer id;
/*     */   private String name;
/*     */   private Integer priority;
/*     */   private Boolean allPerm;
/*     */   private Boolean registShow;
/*     */   private GroupPerm groupPerm;
/*     */   private Site site;
/*     */   private Set<Channel> viewChannels;
/*     */   private Set<Channel> contriChannels;
/*     */ 
/*     */   public void init()
/*     */   {
/*  29 */     if (getPriority() == null) {
/*  30 */       setPriority(Integer.valueOf(10));
/*     */     }
/*  32 */     if (getRegistShow() == null)
/*  33 */       setRegistShow(Boolean.valueOf(true));
/*     */   }
/*     */ 
/*     */   public static Integer[] fetchIds(Collection<Group> groups)
/*     */   {
/*  38 */     Integer[] ids = new Integer[groups.size()];
/*  39 */     int i = 0;
/*  40 */     for (Group g : groups) {
/*  41 */       ids[(i++)] = g.getId();
/*     */     }
/*  43 */     return ids;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getPerms() {
/*  48 */     GroupPerm groupPerm = getGroupPerm();
/*  49 */     if (groupPerm != null) {
/*  50 */       return groupPerm.getPerms();
/*     */     }
/*  52 */     return null;
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="group_id", unique=true, nullable=false)
/*     */   @TableGenerator(name="tg_group", pkColumnValue="tq_group", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*     */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_group")
/*     */   public Integer getId()
/*     */   {
/*  79 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  83 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="name", nullable=false, length=30)
/*     */   public String getName() {
/*  88 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  92 */     this.name = name;
/*     */   }
/*     */ 
/*     */   @Column(name="priority", nullable=false, length=10)
/*     */   public Integer getPriority() {
/*  97 */     return this.priority;
/*     */   }
/*     */ 
/*     */   public void setPriority(Integer priority) {
/* 101 */     this.priority = priority;
/*     */   }
/*     */ 
/*     */   @Column(name="is_all_perm", nullable=false, length=1)
/*     */   public Boolean getAllPerm() {
/* 106 */     return this.allPerm;
/*     */   }
/*     */ 
/*     */   public void setAllPerm(Boolean allPerm) {
/* 110 */     this.allPerm = allPerm;
/*     */   }
/*     */ 
/*     */   @Column(name="is_regist_show", nullable=false, length=1)
/*     */   public Boolean getRegistShow() {
/* 115 */     return this.registShow;
/*     */   }
/*     */ 
/*     */   public void setRegistShow(Boolean registShow) {
/* 119 */     this.registShow = registShow;
/*     */   }
/*     */   @OneToOne(cascade={javax.persistence.CascadeType.REMOVE}, fetch=FetchType.LAZY)
/*     */   @PrimaryKeyJoinColumn
/*     */   public GroupPerm getGroupPerm() {
/* 125 */     return this.groupPerm;
/*     */   }
/*     */ 
/*     */   public void setGroupPerm(GroupPerm groupPerm) {
/* 129 */     this.groupPerm = groupPerm;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="site_id", nullable=false)
/*     */   public Site getSite() {
/* 135 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(Site site) {
/* 139 */     this.site = site;
/*     */   }
/*     */ 
/*     */   @ManyToMany(mappedBy="viewGroups", fetch=FetchType.LAZY)
/*     */   public Set<Channel> getViewChannels() {
/* 144 */     return this.viewChannels;
/*     */   }
/*     */ 
/*     */   public void setViewChannels(Set<Channel> viewChannels)
/*     */   {
/* 149 */     this.viewChannels = viewChannels;
/*     */   }
/*     */ 
/*     */   @ManyToMany(mappedBy="contriGroups", fetch=FetchType.LAZY)
/*     */   public Set<Channel> getContriChannels() {
/* 154 */     return this.contriChannels;
/*     */   }
/*     */ 
/*     */   public void setContriChannels(Set<Channel> contriChannels)
/*     */   {
/* 159 */     this.contriChannels = contriChannels;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.entity.Group
 * JD-Core Version:    0.6.1
 */