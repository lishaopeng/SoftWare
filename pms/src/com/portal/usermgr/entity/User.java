/*     */ package com.portal.usermgr.entity;
/*     */ 
/*     */ import com.portal.extrafunc.entity.UserForum;
/*     */ import java.io.Serializable;
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
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tq_user")
/*     */ public class User
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final byte NORMAL = 0;
/*     */   public static final byte NO_CHECK = -2;
/*     */   public static final byte DISABLED = -1;
/*     */   private Integer id;
/*     */   private String username;
/*     */   private String password;
/*     */   private String email;
/*     */   private String realName;
/*     */   private String phone;
/*     */   private String mobile;
/*     */   private Byte status;
/*     */   private Integer failCount;
/*     */   private Date lastFailTime;
/*     */   private Member member;
/*     */   private Admin admin;
/*     */   private UserForum userForum;
/*     */ 
/*     */   public void init()
/*     */   {
/*  42 */     if (getStatus() == null) {
/*  43 */       setStatus(Byte.valueOf((byte)0));
/*     */     }
/*  45 */     if (getFailCount() == null)
/*  46 */       setFailCount(Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Boolean getTakeDepart(Integer siteId)
/*     */   {
/*  52 */     if (getAdmin() == null) {
/*  53 */       return Boolean.valueOf(true);
/*     */     }
/*  55 */     if (getAdmin().getAdminCheck(siteId) == null) {
/*  56 */       return Boolean.valueOf(true);
/*     */     }
/*  58 */     return getAdmin().getAdminCheck(siteId).getTakeDepart();
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Depart getDepart(Integer siteId) {
/*  63 */     if (getAdmin() == null) {
/*  64 */       return null;
/*     */     }
/*  66 */     if (getAdmin().getDepart(siteId) == null) {
/*  67 */       return null;
/*     */     }
/*  69 */     return getAdmin().getDepart(siteId);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Integer getDepartId(Integer siteId) {
/*  74 */     if (getDepart(siteId) == null) {
/*  75 */       return null;
/*     */     }
/*  77 */     return getDepart(siteId).getId();
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Role getRole(Integer siteId) {
/*  82 */     if (getAdmin() == null) {
/*  83 */       return null;
/*     */     }
/*  85 */     if (getAdmin().getRole(siteId) == null) {
/*  86 */       return null;
/*     */     }
/*  88 */     return getAdmin().getRole(siteId);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Integer getRoleId(Integer siteId) {
/*  93 */     if (getRole(siteId) == null) {
/*  94 */       return null;
/*     */     }
/*  96 */     return getRole(siteId).getId();
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public boolean getAllChannel(Integer siteId) {
/* 101 */     if (getAdmin() == null) {
/* 102 */       return false;
/*     */     }
/* 104 */     return getAdmin().getAllChannel(siteId).booleanValue();
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Byte getManageStatus(Integer siteId) {
/* 109 */     if (getAdmin() == null) {
/* 110 */       return null;
/*     */     }
/* 112 */     return getAdmin().getManageStatus(siteId);
/*     */   }
/*     */ 
/*     */   public static Integer[] fetchIds(Collection<User> users) {
/* 116 */     if (users == null) {
/* 117 */       return null;
/*     */     }
/* 119 */     Integer[] ids = new Integer[users.size()];
/* 120 */     int i = 0;
/* 121 */     for (User u : users) {
/* 122 */       ids[(i++)] = u.getId();
/*     */     }
/* 124 */     return ids;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public static boolean isToday(Date date) {
/* 129 */     long day = date.getTime() / 1000L / 60L / 60L / 24L;
/* 130 */     long currentDay = System.currentTimeMillis() / 1000L / 60L / 60L / 24L;
/* 131 */     return day == currentDay;
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="user_id", unique=true, nullable=false)
/*     */   @TableGenerator(name="tg_user", pkColumnValue="tq_user", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*     */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_user")
/*     */   public Integer getId()
/*     */   {
/* 158 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/* 162 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="username", nullable=false, length=50)
/*     */   public String getUsername() {
/* 167 */     return this.username;
/*     */   }
/*     */ 
/*     */   public void setUsername(String username) {
/* 171 */     this.username = username;
/*     */   }
/*     */ 
/*     */   @Column(name="t_password", nullable=false, length=50)
/*     */   public String getPassword() {
/* 176 */     return this.password;
/*     */   }
/*     */ 
/*     */   public void setPassword(String password) {
/* 180 */     this.password = password;
/*     */   }
/*     */ 
/*     */   @Column(name="email", nullable=true, length=50)
/*     */   public String getEmail() {
/* 185 */     return this.email;
/*     */   }
/*     */ 
/*     */   public void setEmail(String email) {
/* 189 */     this.email = email;
/*     */   }
/*     */ 
/*     */   @Column(name="real_name", nullable=true, length=50)
/*     */   public String getRealName() {
/* 194 */     return this.realName;
/*     */   }
/*     */ 
/*     */   public void setRealName(String realName) {
/* 198 */     this.realName = realName;
/*     */   }
/*     */ 
/*     */   @Column(name="phone", nullable=true, length=20)
/*     */   public String getPhone() {
/* 203 */     return this.phone;
/*     */   }
/*     */ 
/*     */   public void setPhone(String phone) {
/* 207 */     this.phone = phone;
/*     */   }
/*     */ 
/*     */   @Column(name="mobile", nullable=true, length=20)
/*     */   public String getMobile() {
/* 212 */     return this.mobile;
/*     */   }
/*     */ 
/*     */   public void setMobile(String mobile) {
/* 216 */     this.mobile = mobile;
/*     */   }
/*     */ 
/*     */   @Column(name="t_status", nullable=true, length=5)
/*     */   public Byte getStatus() {
/* 221 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Byte status) {
/* 225 */     this.status = status;
/*     */   }
/*     */ 
/*     */   @Column(name="fail_count", nullable=false, length=10)
/*     */   public Integer getFailCount() {
/* 230 */     return this.failCount;
/*     */   }
/*     */ 
/*     */   public void setFailCount(Integer failCount) {
/* 234 */     this.failCount = failCount;
/*     */   }
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   @Column(name="last_fail_time", nullable=true, length=19)
/*     */   public Date getLastFailTime() {
/* 240 */     return this.lastFailTime;
/*     */   }
/*     */ 
/*     */   public void setLastFailTime(Date lastFailTime) {
/* 244 */     this.lastFailTime = lastFailTime;
/*     */   }
/*     */   @OneToOne(cascade={javax.persistence.CascadeType.REMOVE}, fetch=FetchType.LAZY)
/*     */   @PrimaryKeyJoinColumn
/*     */   public Member getMember() {
/* 250 */     return this.member;
/*     */   }
/*     */ 
/*     */   public void setMember(Member member) {
/* 254 */     this.member = member;
/*     */   }
/*     */   @OneToOne(cascade={javax.persistence.CascadeType.REMOVE}, fetch=FetchType.LAZY)
/*     */   @PrimaryKeyJoinColumn
/*     */   public Admin getAdmin() {
/* 260 */     return this.admin;
/*     */   }
/*     */ 
/*     */   public void setAdmin(Admin admin) {
/* 264 */     this.admin = admin;
/*     */   }
/*     */   @OneToOne(cascade={javax.persistence.CascadeType.REMOVE}, fetch=FetchType.LAZY)
/*     */   @PrimaryKeyJoinColumn
/*     */   public UserForum getUserForum() {
/* 270 */     return this.userForum;
/*     */   }
/*     */ 
/*     */   public void setUserForum(UserForum userForum) {
/* 274 */     this.userForum = userForum;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.entity.User
 * JD-Core Version:    0.6.1
 */