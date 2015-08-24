/*     */ package com.portal.usermgr.entity;
/*     */ 
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
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
/*     */ @Table(name="tq_role")
/*     */ public class Role
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private Integer id;
/*     */   private String name;
/*     */   private Integer priority;
/*     */   private Boolean allPerm;
/*     */   private RolePerm rolePerm;
/*     */   private Site site;
/*     */ 
/*     */   public void init()
/*     */   {
/*  30 */     if (getPriority() == null)
/*  31 */       setPriority(Integer.valueOf(10));
/*     */   }
/*     */ 
/*     */   public static Integer[] fetchIds(Collection<Role> roles)
/*     */   {
/*  36 */     if (roles == null) {
/*  37 */       return null;
/*     */     }
/*  39 */     Integer[] ids = new Integer[roles.size()];
/*  40 */     int i = 0;
/*  41 */     for (Role r : roles) {
/*  42 */       ids[(i++)] = r.getId();
/*     */     }
/*  44 */     return ids;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getPerms() {
/*  49 */     RolePerm rolePerm = getRolePerm();
/*  50 */     if (rolePerm != null) {
/*  51 */       return rolePerm.getPerms();
/*     */     }
/*  53 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Set<String> getPermsSet() {
/*  58 */     Set perms = new HashSet();
/*  59 */     RolePerm rolePerm = getRolePerm();
/*  60 */     if ((rolePerm != null) && 
/*  61 */       (rolePerm.getPerms() != null)) {
/*  62 */       for (String perm : rolePerm.getPerms().split(",")) {
/*  63 */         perms.add(perm);
/*     */       }
/*     */     }
/*     */ 
/*  67 */     return perms;
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="role_id", unique=true, nullable=false)
/*     */   @TableGenerator(name="tg_role", pkColumnValue="tq_role", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*     */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_role")
/*     */   public Integer getId()
/*     */   {
/*  88 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  92 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="name", nullable=false, length=50)
/*     */   public String getName() {
/*  97 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/* 101 */     this.name = name;
/*     */   }
/*     */ 
/*     */   @Column(name="priority", nullable=false, length=11)
/*     */   public Integer getPriority() {
/* 106 */     return this.priority;
/*     */   }
/*     */ 
/*     */   public void setPriority(Integer priority) {
/* 110 */     this.priority = priority;
/*     */   }
/*     */ 
/*     */   @Column(name="is_all_perm", nullable=false, length=1)
/*     */   public Boolean getAllPerm() {
/* 115 */     return this.allPerm;
/*     */   }
/*     */ 
/*     */   public void setAllPerm(Boolean allPerm) {
/* 119 */     this.allPerm = allPerm;
/*     */   }
/*     */   @OneToOne(cascade={javax.persistence.CascadeType.REMOVE}, fetch=FetchType.LAZY)
/*     */   @PrimaryKeyJoinColumn
/*     */   public RolePerm getRolePerm() {
/* 125 */     return this.rolePerm;
/*     */   }
/*     */ 
/*     */   public void setRolePerm(RolePerm rolePerm) {
/* 129 */     this.rolePerm = rolePerm;
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
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.entity.Role
 * JD-Core Version:    0.6.1
 */