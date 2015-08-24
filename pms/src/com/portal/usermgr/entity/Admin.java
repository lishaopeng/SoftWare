/*     */ package com.portal.usermgr.entity;
/*     */ 
/*     */ /*     */ import java.io.Serializable;
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

import com.portal.doccenter.entity.Channel;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tq_admin")
/*     */ public class Admin
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Integer id;
/*     */   private Date registerTime;
/*     */   private String registerIp;
/*     */   private Date lastLoginTime;
/*     */   private String lastLoginIp;
/*     */   private Integer loginCount;
/*     */   private Byte status;
/*     */   private User user;
/*     */   private Set<AdminCheck> adminChecks;
/*     */   private Set<Role> roles;
/*     */   private Set<Depart> departs;
/*     */ 
/*     */   public void init()
/*     */   {
/*  36 */     if (getRegisterTime() == null) {
/*  37 */       setRegisterTime(new Timestamp(System.currentTimeMillis()));
/*     */     }
/*  39 */     if (getLoginCount() == null) {
/*  40 */       setLoginCount(Integer.valueOf(0));
/*     */     }
/*  42 */     if (getStatus() == null)
/*  43 */       setStatus(Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Byte getManageStatus(Integer siteId)
/*     */   {
/*  49 */     AdminCheck uc = getAdminCheck(siteId);
/*  50 */     if (uc != null) {
/*  51 */       return uc.getManageStatus();
/*     */     }
/*  53 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public boolean haveAllManage(Integer siteId)
/*     */   {
/*  59 */     if (getManageStatus(siteId) == null) {
/*  60 */       return false;
/*     */     }
/*  62 */     if (getManageStatus(siteId).equals(Byte.valueOf((byte)4))) {
/*  63 */       return true;
/*     */     }
/*  65 */     return false;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public AdminCheck getAdminCheck(Integer siteId) {
		/* 70 */Set<AdminCheck> set = getAdminChecks();
/*  71 */     for (AdminCheck uc : set) {
/*  72 */       if (uc.getSite().getId().equals(siteId)) {
/*  73 */         return uc;
/*     */       }
/*     */     }
/*  76 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Boolean getTakeDepart(Integer siteId) {
/*  81 */     if (getAdminCheck(siteId) == null) {
/*  82 */       return null;
/*     */     }
/*  84 */     return getAdminCheck(siteId).getTakeDepart();
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Role getRole(Integer siteId) {
/*  89 */     for (Role role : getRoles()) {
/*  90 */       if (role.getSite().getId().equals(siteId)) {
/*  91 */         return role;
/*     */       }
/*     */     }
/*  94 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Depart getDepart(Integer siteId) {
/*  99 */     for (Depart depart : getDeparts()) {
/* 100 */       if (depart.getSite().getId().equals(siteId)) {
/* 101 */         return depart;
/*     */       }
/*     */     }
/* 104 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Set<Channel> getChannels(Integer siteId) {
/* 109 */     if ((getAdminCheck(siteId) != null) && 
/* 110 */       (!getAdminCheck(siteId).getTakeDepart().booleanValue())) {
/* 111 */       return getAdminCheck(siteId).getChannels();
/*     */     }
/*     */ 
/* 114 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getStatusString() {
/* 119 */     if (getStatus().equals(Byte.valueOf((byte)0)))
/* 120 */       return "<span style='color:blue'>正常</span>";
/* 121 */     if (getStatus().equals(Byte.valueOf((byte)-2))) {
/* 122 */       return "<span style='color:red'>未审核</span>";
/*     */     }
/* 124 */     return "<span style='color:red'>禁用</span>";
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Boolean getAllChannel(Integer siteId)
/*     */   {
/* 130 */     if ((getAdminCheck(siteId) != null) && 
/* 131 */       (!getAdminCheck(siteId).getTakeDepart().booleanValue())) {
/* 132 */       return Boolean.valueOf(false);
/*     */     }
/*     */ 
/* 135 */     Depart depart = getDepart(siteId);
/* 136 */     if (depart == null) {
/* 137 */       return Boolean.valueOf(true);
/*     */     }
/* 139 */     if (depart.getAllChannel().booleanValue()) {
/* 140 */       return Boolean.valueOf(true);
/*     */     }
/* 142 */     return Boolean.valueOf(false);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getUsername() {
/* 147 */     return getUser().getUsername();
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Role getRole() {
/* 152 */     return getRole(Integer.valueOf(1));
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Depart getDepart() {
/* 157 */     return getDepart(Integer.valueOf(1));
/*     */   }
/*     */ 
/*     */   public void addToRoles(Role role) {
/* 161 */     if (role == null) {
/* 162 */       return;
/*     */     }
/* 164 */     Set set = getRoles();
/* 165 */     if (set == null) {
/* 166 */       set = new HashSet();
/* 167 */       setRoles(set);
/*     */     }
/* 169 */     set.add(role);
/*     */   }
/*     */ 
/*     */   public void addToDeparts(Depart depart) {
/* 173 */     if (depart == null) {
/* 174 */       return;
/*     */     }
/* 176 */     Set set = getDeparts();
/* 177 */     if (set == null) {
/* 178 */       set = new HashSet();
/* 179 */       setDeparts(set);
/*     */     }
/* 181 */     set.add(depart);
/*     */   }
/*     */ 
/*     */   public void addToAdminChecks(AdminCheck adminCheck) {
/* 185 */     if (adminCheck == null) {
/* 186 */       return;
/*     */     }
/* 188 */     Set set = getAdminChecks();
/* 189 */     if (set == null) {
/* 190 */       set = new HashSet();
/* 191 */       setAdminChecks(set);
/*     */     }
/* 193 */     set.add(adminCheck);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Set<Site> getSites() {
		/* 198 */Set<AdminCheck> adminSites = getAdminChecks();
/* 199 */     Set sites = new HashSet(adminSites.size());
/* 200 */     for (AdminCheck us : adminSites) {
/* 201 */       sites.add(us.getSite());
/*     */     }
/* 203 */     return sites;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Boolean getAllPerm(Integer siteId) {
/* 208 */     Role role = getRole(siteId);
/* 209 */     if (role == null) {
/* 210 */       return Boolean.valueOf(false);
/*     */     }
/* 212 */     if (role.getAllPerm() != null) {
/* 213 */       return role.getAllPerm();
/*     */     }
/* 215 */     return Boolean.valueOf(false);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Set<String> getPerms(Integer siteId) {
/* 220 */     Role role = getRole(siteId);
/* 221 */     if (role == null) {
/* 222 */       return null;
/*     */     }
/* 224 */     Set perms = new HashSet();
/* 225 */     if (role.getAllPerm().booleanValue()) {
/* 226 */       perms.add("*");
/*     */     }
/* 228 */     else if (role.getPerms() != null) {
/* 229 */       for (String perm : role.getPerms().split(",")) {
/* 230 */         perms.add(perm);
/*     */       }
/*     */     }
/*     */ 
/* 234 */     return perms;
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="admin_id", unique=true, nullable=false)
/*     */   @GenericGenerator(name="copy", strategy="foreign", parameters={@org.hibernate.annotations.Parameter(name="property", value="user")})
/*     */   @GeneratedValue(generator="copy")
/*     */   public Integer getId()
/*     */   {
/* 261 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/* 265 */     this.id = id;
/*     */   }
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   @Column(name="registe_time", nullable=false, length=19)
/*     */   public Date getRegisterTime() {
/* 271 */     return this.registerTime;
/*     */   }
/*     */ 
/*     */   public void setRegisterTime(Date registerTime) {
/* 275 */     this.registerTime = registerTime;
/*     */   }
/*     */ 
/*     */   @Column(name="registe_ip", nullable=false, length=20)
/*     */   public String getRegisterIp() {
/* 280 */     return this.registerIp;
/*     */   }
/*     */ 
/*     */   public void setRegisterIp(String registerIp) {
/* 284 */     this.registerIp = registerIp;
/*     */   }
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   @Column(name="last_login_time", nullable=true, length=19)
/*     */   public Date getLastLoginTime() {
/* 290 */     return this.lastLoginTime;
/*     */   }
/*     */ 
/*     */   public void setLastLoginTime(Date lastLoginTime) {
/* 294 */     this.lastLoginTime = lastLoginTime;
/*     */   }
/*     */ 
/*     */   @Column(name="last_login_ip", nullable=true, length=20)
/*     */   public String getLastLoginIp() {
/* 299 */     return this.lastLoginIp;
/*     */   }
/*     */ 
/*     */   public void setLastLoginIp(String lastLoginIp) {
/* 303 */     this.lastLoginIp = lastLoginIp;
/*     */   }
/*     */ 
/*     */   @Column(name="login_count", nullable=false, length=10)
/*     */   public Integer getLoginCount() {
/* 308 */     return this.loginCount;
/*     */   }
/*     */ 
/*     */   public void setLoginCount(Integer loginCount) {
/* 312 */     this.loginCount = loginCount;
/*     */   }
/*     */ 
/*     */   @Column(name="t_status", nullable=false, length=5)
/*     */   public Byte getStatus() {
/* 317 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Byte status) {
/* 321 */     this.status = status;
/*     */   }
/*     */   @OneToOne
/*     */   @PrimaryKeyJoinColumn
/*     */   public User getUser() {
/* 327 */     return this.user;
/*     */   }
/*     */ 
/*     */   public void setUser(User user) {
/* 331 */     this.user = user;
/*     */   }
/*     */ 
/*     */   @OneToMany(fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.REMOVE}, mappedBy="admin")
/*     */   public Set<AdminCheck> getAdminChecks() {
/* 336 */     return this.adminChecks;
/*     */   }
/*     */ 
/*     */   public void setAdminChecks(Set<AdminCheck> adminChecks)
/*     */   {
/* 341 */     this.adminChecks = adminChecks;
/*     */   }
/*     */   @OneToMany(fetch=FetchType.LAZY)
/*     */   @JoinTable(name="tq_admin_role", joinColumns={@javax.persistence.JoinColumn(name="admin_id")}, inverseJoinColumns={@javax.persistence.JoinColumn(name="role_id")})
/*     */   public Set<Role> getRoles() {
/* 347 */     return this.roles;
/*     */   }
/*     */ 
/*     */   public void setRoles(Set<Role> roles) {
/* 351 */     this.roles = roles;
/*     */   }
/*     */   @OneToMany(fetch=FetchType.LAZY)
/*     */   @JoinTable(name="tq_admin_depart", joinColumns={@javax.persistence.JoinColumn(name="admin_id")}, inverseJoinColumns={@javax.persistence.JoinColumn(name="depart_id")})
/*     */   public Set<Depart> getDeparts() {
/* 357 */     return this.departs;
/*     */   }
/*     */ 
/*     */   public void setDeparts(Set<Depart> departs)
/*     */   {
/* 362 */     this.departs = departs;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.entity.Admin
 * JD-Core Version:    0.6.1
 */