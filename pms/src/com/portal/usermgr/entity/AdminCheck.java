/*     */ package com.portal.usermgr.entity;
/*     */ 
/*     */ import com.portal.doccenter.entity.Channel;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import java.io.Serializable;
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
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.TableGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tq_admin_check")
/*     */ public class AdminCheck
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final byte READ_ONLY = 0;
/*     */   public static final byte MANAGE_SELF = 1;
/*     */   public static final byte MANAGE_CHECK = 2;
/*     */   public static final byte MANAGE_DEPART = 3;
/*     */   public static final byte MANAGE_ALL = 4;
/*     */   private Integer id;
/*     */   private Byte manageStatus;
/*     */   private Boolean takeDepart;
/*     */   private Admin admin;
/*     */   private Site site;
/*     */   private Set<Channel> channels;
/*     */ 
/*     */   public void addToChannels(Channel channel)
/*     */   {
/*  49 */     if (channel == null) {
/*  50 */       return;
/*     */     }
/*  52 */     Set set = getChannels();
/*  53 */     if (set == null) {
/*  54 */       set = new HashSet();
/*  55 */       setChannels(set);
/*     */     }
/*  57 */     set.add(channel);
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="check_id", unique=true, nullable=false)
/*     */   @TableGenerator(name="tg_admin_check", pkColumnValue="tq_admin_check", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*     */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_admin_check")
/*     */   public Integer getId()
/*     */   {
/*  78 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  82 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="mng_status", nullable=false, length=3)
/*     */   public Byte getManageStatus() {
/*  87 */     return this.manageStatus;
/*     */   }
/*     */ 
/*     */   public void setManageStatus(Byte manageStatus) {
/*  91 */     this.manageStatus = manageStatus;
/*     */   }
/*     */ 
/*     */   @Column(name="is_take_depart", nullable=false, length=1)
/*     */   public Boolean getTakeDepart() {
/*  96 */     return this.takeDepart;
/*     */   }
/*     */ 
/*     */   public void setTakeDepart(Boolean takeDepart) {
/* 100 */     this.takeDepart = takeDepart;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="admin_id", nullable=false)
/*     */   public Admin getAdmin() {
/* 106 */     return this.admin;
/*     */   }
/*     */ 
/*     */   public void setAdmin(Admin admin) {
/* 110 */     this.admin = admin;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="site_id", nullable=false)
/*     */   public Site getSite() {
/* 116 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(Site site) {
/* 120 */     this.site = site;
/*     */   }
/*     */   @ManyToMany(fetch=FetchType.LAZY)
/*     */   @JoinTable(name="tq_admin_channel", joinColumns={@JoinColumn(name="admin_id")}, inverseJoinColumns={@JoinColumn(name="channel_id")})
/*     */   public Set<Channel> getChannels() {
/* 126 */     return this.channels;
/*     */   }
/*     */ 
/*     */   public void setChannels(Set<Channel> channels)
/*     */   {
/* 131 */     this.channels = channels;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.entity.AdminCheck
 * JD-Core Version:    0.6.1
 */