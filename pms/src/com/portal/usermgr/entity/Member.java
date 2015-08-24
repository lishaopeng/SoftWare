/*     */ package com.portal.usermgr.entity;
/*     */ 
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
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinTable;
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.OneToOne;
/*     */ import javax.persistence.PrimaryKeyJoinColumn;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.Temporal;
/*     */ import javax.persistence.TemporalType;
/*     */ import javax.persistence.Transient;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tq_member")
/*     */ public class Member
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Integer id;
/*     */   private String avatar;
/*     */   private Byte gender;
/*     */   private Date birthday;
/*     */   private String address;
/*     */   private String signature;
/*     */   private Date registerTime;
/*     */   private String registerIp;
/*     */   private Date lastLoginTime;
/*     */   private String lastLoginIp;
/*     */   private Integer loginCount;
/*     */   private Byte status;
/*     */   private User user;
/*     */   private Set<Group> groups;
/*     */ 
/*     */   public void init()
/*     */   {
/*  32 */     if (getRegisterTime() == null) {
/*  33 */       setRegisterTime(new Timestamp(System.currentTimeMillis()));
/*     */     }
/*  35 */     if (getLastLoginTime() == null) {
/*  36 */       setLastLoginTime(new Timestamp(System.currentTimeMillis()));
/*     */     }
/*  38 */     if (getLoginCount() == null) {
/*  39 */       setLoginCount(Integer.valueOf(1));
/*     */     }
/*  41 */     if (getStatus() == null)
/*  42 */       setStatus(Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Group getGroup(Integer siteId)
/*     */   {
/*  48 */     for (Group group : getGroups()) {
/*  49 */       if (group.getSite().getId().equals(siteId)) {
/*  50 */         return group;
/*     */       }
/*     */     }
/*  53 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Integer getGroupId(Integer siteId) {
/*  58 */     if (getGroup(siteId) != null) {
/*  59 */       return getGroup(siteId).getId();
/*     */     }
/*  61 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Group getGroup() {
/*  66 */     return getGroup(Integer.valueOf(1));
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Set<String> getPerms(Integer siteId) {
/*  71 */     Group group = getGroup(siteId);
/*  72 */     if (group == null) {
/*  73 */       return null;
/*     */     }
/*  75 */     Set perms = new HashSet();
/*  76 */     if (group.getPerms() != null) {
/*  77 */       for (String perm : group.getPerms().split(",")) {
/*  78 */         perms.add(perm);
/*     */       }
/*     */     }
/*  81 */     return perms;
/*     */   }
/*     */ 
/*     */   public void addToGroups(Group group) {
/*  85 */     if (group == null) {
/*  86 */       return;
/*     */     }
/*  88 */     Set set = getGroups();
/*  89 */     if (set == null) {
/*  90 */       set = new HashSet();
/*  91 */       setGroups(set);
/*     */     }
/*  93 */     set.add(group);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getStatusString() {
/*  98 */     if (getStatus().equals(Byte.valueOf((byte)0)))
/*  99 */       return "<span style='color:blue'>正常</span>";
/* 100 */     if (getStatus().equals(Byte.valueOf((byte)-2))) {
/* 101 */       return "<span style='color:red'>未审核</span>";
/*     */     }
/* 103 */     return "<span style='color:red'>禁用</span>";
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="member_id", unique=true, nullable=false)
/*     */   @GenericGenerator(name="copy", strategy="foreign", parameters={@org.hibernate.annotations.Parameter(name="property", value="user")})
/*     */   @GeneratedValue(generator="copy")
/*     */   public Integer getId()
/*     */   {
/* 134 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/* 138 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="avatar", nullable=true, length=100)
/*     */   public String getAvatar() {
/* 143 */     return this.avatar;
/*     */   }
/*     */ 
/*     */   public void setAvatar(String avatar) {
/* 147 */     this.avatar = avatar;
/*     */   }
/*     */ 
/*     */   @Column(name="gender", nullable=true, length=5)
/*     */   public Byte getGender() {
/* 152 */     return this.gender;
/*     */   }
/*     */ 
/*     */   public void setGender(Byte gender) {
/* 156 */     this.gender = gender;
/*     */   }
/*     */   @Temporal(TemporalType.DATE)
/*     */   @Column(name="birthday", nullable=true, length=19)
/*     */   public Date getBirthday() {
/* 162 */     return this.birthday;
/*     */   }
/*     */ 
/*     */   public void setBirthday(Date birthday) {
/* 166 */     this.birthday = birthday;
/*     */   }
/*     */ 
/*     */   @Column(name="address", nullable=true, length=100)
/*     */   public String getAddress() {
/* 171 */     return this.address;
/*     */   }
/*     */ 
/*     */   public void setAddress(String address) {
/* 175 */     this.address = address;
/*     */   }
/*     */ 
/*     */   @Column(name="signature", nullable=true, length=255)
/*     */   public String getSignature() {
/* 180 */     return this.signature;
/*     */   }
/*     */ 
/*     */   public void setSignature(String signature) {
/* 184 */     this.signature = signature;
/*     */   }
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   @Column(name="registe_time", nullable=false, length=19)
/*     */   public Date getRegisterTime() {
/* 190 */     return this.registerTime;
/*     */   }
/*     */ 
/*     */   public void setRegisterTime(Date registerTime) {
/* 194 */     this.registerTime = registerTime;
/*     */   }
/*     */ 
/*     */   @Column(name="registe_ip", nullable=true, length=20)
/*     */   public String getRegisterIp() {
/* 199 */     return this.registerIp;
/*     */   }
/*     */ 
/*     */   public void setRegisterIp(String registerIp) {
/* 203 */     this.registerIp = registerIp;
/*     */   }
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   @Column(name="last_login_time", nullable=true, length=19)
/*     */   public Date getLastLoginTime() {
/* 209 */     return this.lastLoginTime;
/*     */   }
/*     */ 
/*     */   public void setLastLoginTime(Date lastLoginTime) {
/* 213 */     this.lastLoginTime = lastLoginTime;
/*     */   }
/*     */ 
/*     */   @Column(name="last_login_ip", nullable=true, length=20)
/*     */   public String getLastLoginIp() {
/* 218 */     return this.lastLoginIp;
/*     */   }
/*     */ 
/*     */   public void setLastLoginIp(String lastLoginIp) {
/* 222 */     this.lastLoginIp = lastLoginIp;
/*     */   }
/*     */ 
/*     */   @Column(name="login_count", nullable=false, length=10)
/*     */   public Integer getLoginCount() {
/* 227 */     return this.loginCount;
/*     */   }
/*     */ 
/*     */   public void setLoginCount(Integer loginCount) {
/* 231 */     this.loginCount = loginCount;
/*     */   }
/*     */ 
/*     */   @Column(name="t_status", nullable=false, length=5)
/*     */   public Byte getStatus() {
/* 236 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Byte status) {
/* 240 */     this.status = status;
/*     */   }
/*     */   @OneToOne
/*     */   @PrimaryKeyJoinColumn
/*     */   public User getUser() {
/* 246 */     return this.user;
/*     */   }
/*     */ 
/*     */   public void setUser(User user) {
/* 250 */     this.user = user;
/*     */   }
/*     */   @OneToMany(fetch=FetchType.LAZY)
/*     */   @JoinTable(name="tq_member_group", joinColumns={@javax.persistence.JoinColumn(name="member_id")}, inverseJoinColumns={@javax.persistence.JoinColumn(name="group_id")})
/*     */   public Set<Group> getGroups() {
/* 256 */     return this.groups;
/*     */   }
/*     */ 
/*     */   public void setGroups(Set<Group> groups) {
/* 260 */     this.groups = groups;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.entity.Member
 * JD-Core Version:    0.6.1
 */