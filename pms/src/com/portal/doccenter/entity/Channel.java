/*     */ package com.portal.doccenter.entity;
/*     */ 
/*     */ /*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;

/*     */ import javax.persistence.CollectionTable;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.ElementCollection;
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
/*     */ import javax.persistence.OneToOne;
/*     */ import javax.persistence.OrderBy;
/*     */ import javax.persistence.PrimaryKeyJoinColumn;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.TableGenerator;
/*     */ import javax.persistence.Transient;

/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.hibernate.annotations.Cache;
/*     */ import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.javapms.basic.comparator.BeanComparator;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.usermgr.entity.Admin;
/*     */ import com.portal.usermgr.entity.AdminCheck;
/*     */ import com.portal.usermgr.entity.Depart;
/*     */ import com.portal.usermgr.entity.Group;
/*     */ import com.portal.usermgr.entity.Role;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="tq_channel")
/*     */ @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
/*     */ public class Channel
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final String HTML = "html";
/*     */   public static final String NORMAL = "normalstatic";
/*     */   public static final String DEPART = "departstatic";
/*     */   private Integer id;
/*     */   private String name;
/*     */   private String path;
/*     */   private String number;
/*     */   private Integer priority;
/*     */   private Boolean alone;
/*     */   private Boolean show;
/*     */   private ChannelExt ext;
/*     */   private ChannelTxt txt;
/*     */   private Site site;
/*     */   private WorkFlow flow;
/*     */   private Depart inputDepart;
/*     */   private Channel parent;
/*     */   private Set<ChnlTplSelection> tpls;
/*     */   private Set<Channel> child;
/*     */   private Set<Group> viewGroups;
/*     */   private Set<Group> contriGroups;
/*     */   private Set<Depart> departs;
/*     */   private Set<AdminCheck> checks;
/*     */ 
/*     */   @Transient
/*     */   public String getUrl()
/*     */   {
/*  76 */     return getUrl(Integer.valueOf(1));
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getUrl(Integer page) {
/*  81 */     if (StringUtils.isNotBlank(getLink())) {
/*  82 */       return getLink();
/*     */     }
/*  84 */     if (page == null) {
/*  85 */       page = Integer.valueOf(1);
/*     */     }
/*     */ 
/*  88 */     return getUrlStatic(page);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getUrlDynamic(Integer page)
/*     */   {
/*  95 */     StringBuilder sb = new StringBuilder(getSite().getUrl());
/*  96 */     sb.append(getPath());
/*  97 */     sb.append("/");
/*  98 */     sb.append("index");
/*  99 */     if (page.intValue() > 1) {
/* 100 */       sb.append("_");
/* 101 */       sb.append(page);
/*     */     }
/* 103 */     sb.append(".jsp");
/* 104 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getUrlStatic(Integer page) {
/* 109 */     StringBuilder sb = new StringBuilder(getSite().getUrl());
/* 110 */     if (getSite().getStaticSuffix().booleanValue()) {
/* 111 */       sb.append(getUrlStaticHaveSuffix(page));
/* 112 */       return sb.toString();
/*     */     }
/* 114 */     sb.append(getUrlStaticNoSuffix(page));
/* 115 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getUrlStaticHaveSuffix(Integer page) {
/* 120 */     String path = getPath();
/* 121 */     path = path + "/";
/* 122 */     path = path + getChannelPageCorePath(page);
/* 123 */     return path;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getUrlStaticNoSuffix(Integer page) {
/* 128 */     StringBuilder sb = new StringBuilder();
/* 129 */     sb.append(getPath());
/* 130 */     if ((page != null) && (page.intValue() > 1)) {
/* 131 */       sb.append("_");
/* 132 */       sb.append(page);
/*     */     }
/* 134 */     sb.append("/");
/* 135 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getChannelCoreRealPath() {
/* 140 */     StringBuilder sb = new StringBuilder();
/* 141 */     sb.append("/");
/* 142 */     sb.append("html").append("/");
/* 143 */     sb.append(getSite().getPath()).append("/");
/* 144 */     sb.append("normalstatic");
/* 145 */     sb.append("/");
/* 146 */     sb.append(getPath());
/* 147 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getChannelPageCorePath(Integer page) {
/* 152 */     StringBuilder sb = new StringBuilder();
/* 153 */     sb.append("index");
/* 154 */     if ((page != null) && (page.intValue() > 1)) {
/* 155 */       sb.append("_");
/* 156 */       sb.append(page);
/*     */     }
/* 158 */     sb.append(".html");
/* 159 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getChannelRealPath(Integer page) {
/* 164 */     String path = getChannelCoreRealPath();
/* 165 */     path = path + "/";
/* 166 */     path = path + getChannelPageCorePath(page);
/* 167 */     return path;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getStaticDocPath() {
/* 172 */     String path = getChannelCoreRealPath();
/* 173 */     path = path + "/";
/* 174 */     path = path + "doc";
/* 175 */     return path;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public boolean getStaticChannel() {
/* 180 */     if (!StringUtils.isBlank(getLink())) {
/* 181 */       return false;
/*     */     }
/* 183 */     if (getSite().getStaticChannel().equals(Site.YES_STATIC)) {
/* 184 */       return true;
/*     */     }
/* 186 */     if (getSite().getStaticChannel().equals(Site.NO_STATIC)) {
/* 187 */       return false;
/*     */     }
/* 189 */     ChannelExt ext = getExt();
/* 190 */     if (ext != null) {
/* 191 */       return ext.getStaticChannel().booleanValue();
/*     */     }
/* 193 */     return false;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public boolean getStaticChannelSuper() {
/* 198 */     ChannelExt ext = getExt();
/* 199 */     if (ext != null) {
/* 200 */       return ext.getStaticChannel().booleanValue();
/*     */     }
/* 202 */     return false;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public boolean getStaticDoc() {
/* 207 */     ChannelExt ext = getExt();
/* 208 */     if (ext != null) {
/* 209 */       return ext.getStaticDoc().booleanValue();
/*     */     }
/* 211 */     return false;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public boolean isChanged(long time) {
/* 216 */     Date d = getSite().getUpdateTime();
/* 217 */     if (d == null) {
/* 218 */       return false;
/*     */     }
/* 220 */     if (d.getTime() < time) {
/* 221 */       return false;
/*     */     }
/* 223 */     return true;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public List<Channel> getNodeList() {
/* 228 */     LinkedList list = new LinkedList();
/* 229 */     Channel node = this;
/* 230 */     while (node != null) {
/* 231 */       list.addFirst(node);
/* 232 */       node = node.getParent();
/*     */     }
/* 234 */     return list;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Integer[] getNodeIds() {
		/* 239 */List<Channel> channels = getNodeList();
/* 240 */     Integer[] ids = new Integer[channels.size()];
/* 241 */     int i = 0;
/* 242 */     for (Channel c : channels) {
/* 243 */       ids[(i++)] = c.getId();
/*     */     }
/* 245 */     return ids;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public int getDeep() {
/* 250 */     int deep = 0;
/* 251 */     Channel parent = getParent();
/* 252 */     while (parent != null) {
/* 253 */       deep++;
/* 254 */       parent = parent.getParent();
/*     */     }
/* 256 */     return deep;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getTplChannelOrDef() {
/* 261 */     String tpl = getTplChannel();
/* 262 */     if (!StringUtils.isBlank(tpl)) {
/* 263 */       String root = getSite().getSolutionPath();
/* 264 */       return root + tpl;
/*     */     }
/* 266 */     return null;
/*     */   }
/*     */ 
/*     */   public void addToTpls(ChnlTplSelection chnltpl) {
/* 270 */     Set chnltpls = getTpls();
/* 271 */     if (chnltpls == null) {
/* 272 */       chnltpls = new HashSet();
/* 273 */       setTpls(chnltpls);
/*     */     }
/* 275 */     chnltpls.add(chnltpl);
/*     */   }
/*     */ 
/*     */   public void addToViewGroups(Group group) {
/* 279 */     Set groups = getViewGroups();
/* 280 */     if (groups == null) {
/* 281 */       groups = new TreeSet();
/* 282 */       setViewGroups(groups);
/*     */     }
/* 284 */     groups.add(group);
/* 285 */     group.getViewChannels().add(this);
/*     */   }
/*     */ 
/*     */   public void addToContriGroups(Group group) {
/* 289 */     Set groups = getContriGroups();
/* 290 */     if (groups == null) {
/* 291 */       groups = new TreeSet();
/* 292 */       setContriGroups(groups);
/*     */     }
/* 294 */     groups.add(group);
/* 295 */     group.getContriChannels().add(this);
/*     */   }
/*     */ 
/*     */   public void addToDeparts(Depart depart) {
/* 299 */     Set set = getDeparts();
/* 300 */     if (set == null) {
/* 301 */       set = new HashSet();
/* 302 */       setDeparts(set);
/*     */     }
/* 304 */     set.add(depart);
/* 305 */     depart.addToChannels(this);
/*     */   }
/*     */ 
/*     */   public void addToChilds(Channel child) {
/* 309 */     Set set = getChild();
/* 310 */     if (set == null) {
/* 311 */       set = new HashSet();
/* 312 */       setChild(set);
/*     */     }
/* 314 */     set.add(child);
/*     */   }
/*     */ 
/*     */   public void init() {
/* 318 */     if (getPriority() == null) {
/* 319 */       setPriority(Integer.valueOf(10));
/*     */     }
/* 321 */     if (getShow() == null) {
/* 322 */       setShow(Boolean.valueOf(true));
/*     */     }
/* 324 */     if (getAlone() == null)
/* 325 */       setAlone(Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   public void initTreeNumber()
/*     */   {
/* 330 */     String number = "-";
/* 331 */     if (getParent() != null) {
/* 332 */       number = getParent().getNumber();
/*     */     }
/* 334 */     setNumber(number + getId() + "-");
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getWholeName() {
/* 339 */     String name = getName();
/* 340 */     Channel parent = getParent();
/* 341 */     if (parent != null) {
/* 342 */       name = parent.getWholeName() + "-->" + name;
/*     */     }
/* 344 */     return name;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public FlowStep getFlowStep(Integer roleId) {
/* 349 */     if (getFlow() == null) {
/* 350 */       return null;
/*     */     }
/* 352 */     return getFlow().getStep(roleId);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public FlowStep getNextFlowStep(Integer roleId) {
/* 357 */     if (getFlow() == null) {
/* 358 */       return null;
/*     */     }
/* 360 */     return getFlow().getNextFlowStep(roleId);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Role getNextRole(Integer roleId) {
/* 365 */     if (getNextFlowStep(roleId) == null) {
/* 366 */       return null;
/*     */     }
/* 368 */     return getNextFlowStep(roleId).getRole();
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public boolean isLastStep(Role role) {
/* 373 */     if (getFlow().getLastFlowStep().getRole().equals(role)) {
/* 374 */       return true;
/*     */     }
/* 376 */     return false;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public boolean isDocChecked(Admin admin) {
/* 381 */     if (admin == null) {
/* 382 */       return false;
/*     */     }
/* 384 */     if ((admin.haveAllManage(getSite().getId())) || 
/* 385 */       (admin.getManageStatus(getSite().getId()).equals(
/* 386 */       Byte.valueOf((byte)3)))) {
/* 387 */       return true;
/*     */     }
/* 389 */     if (admin.getManageStatus(getSite().getId()).equals(
/* 390 */       Byte.valueOf((byte)2))) {
/* 391 */       if (isLastStep(admin.getRole(getSite().getId())))
/* 392 */         return true;
/*     */     }
/* 394 */     return false;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getLink() {
/* 399 */     ChannelExt ext = getExt();
/* 400 */     if (ext != null) {
/* 401 */       return ext.getLink();
/*     */     }
/* 403 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getImgSrc()
/*     */   {
/* 409 */     ChannelExt ext = getExt();
/* 410 */     if (ext != null) {
/* 411 */       return ext.getImgSrc();
/*     */     }
/* 413 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getTplChannel()
/*     */   {
/* 419 */     ChannelExt ext = getExt();
/* 420 */     if (ext != null) {
/* 421 */       return ext.getTplChannel();
/*     */     }
/* 423 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getTplDoc(Integer modelId)
/*     */   {
		/* 429 */Set<ChnlTplSelection> tpls = getTpls();
/* 430 */     if (tpls != null) {
/* 431 */       for (ChnlTplSelection tpl : tpls) {
/* 432 */         if (tpl.getModelId().equals(modelId)) {
/* 433 */           return tpl.getTplDoc();
/*     */         }
/*     */       }
/*     */     }
/* 437 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public List<Model> getModelList()
/*     */   {
		/* 443 */Set<ChnlTplSelection> tpls = getTpls();
/* 444 */     Comparator bc = new BeanComparator();
/* 445 */     if (tpls != null) {
/* 446 */       List modelList = new ArrayList();
/* 447 */       for (ChnlTplSelection tpl : tpls) {
/* 448 */         modelList.add(tpl.getModel());
/*     */       }
/* 450 */       Collections.sort(modelList, bc);
/* 451 */       return modelList;
/*     */     }
/* 453 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getTitle() {
/* 458 */     ChannelExt ext = getExt();
/* 459 */     if (ext != null) {
/* 460 */       return ext.getTitle();
/*     */     }
/* 462 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getKeywords()
/*     */   {
/* 468 */     ChannelExt ext = getExt();
/* 469 */     if (ext != null) {
/* 470 */       return ext.getKeywords();
/*     */     }
/* 472 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getDescription()
/*     */   {
/* 478 */     ChannelExt ext = getExt();
/* 479 */     if (ext != null) {
/* 480 */       return ext.getDescription();
/*     */     }
/* 482 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Boolean getCommentControl()
/*     */   {
/* 488 */     ChannelExt ext = getExt();
/* 489 */     if (ext != null) {
/* 490 */       return ext.getCommentControl();
/*     */     }
/* 492 */     return Boolean.valueOf(false);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Boolean getUpdownControl()
/*     */   {
/* 498 */     ChannelExt ext = getExt();
/* 499 */     if (ext != null) {
/* 500 */       return ext.getUpdownControl();
/*     */     }
/* 502 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Boolean getBlank()
/*     */   {
/* 508 */     ChannelExt ext = getExt();
/* 509 */     if (ext != null) {
/* 510 */       return ext.getBlank();
/*     */     }
/* 512 */     return null;
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public Boolean getSign()
/*     */   {
/* 518 */     ChannelExt ext = getExt();
/* 519 */     if (ext != null) {
/* 520 */       return ext.getSign();
/*     */     }
/* 522 */     return Boolean.valueOf(false);
/*     */   }
/*     */ 
/*     */   @Transient
/*     */   public String getTxtValue()
/*     */   {
/* 528 */     ChannelTxt txt = getTxt();
/* 529 */     if (txt != null) {
/* 530 */       return txt.getTxtval();
/*     */     }
/* 532 */     return null;
/*     */   }
/*     */ 
/*     */   public static Integer[] fetchIds(Collection<Channel> channels)
/*     */   {
/* 537 */     if (channels == null) {
/* 538 */       return null;
/*     */     }
/* 540 */     Integer[] ids = new Integer[channels.size()];
/* 541 */     int i = 0;
/* 542 */     for (Channel c : channels) {
/* 543 */       ids[(i++)] = c.getId();
/*     */     }
/* 545 */     return ids;
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @Column(name="channel_id", unique=true, nullable=false)
/*     */   @TableGenerator(name="tg_channel", pkColumnValue="tq_channel", table="tq_gen_table", pkColumnName="tg_gen_name", valueColumnName="tq_gen_value", initialValue=1, allocationSize=1)
/*     */   @GeneratedValue(strategy=GenerationType.TABLE, generator="tg_channel")
/*     */   public Integer getId()
/*     */   {
/* 582 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/* 586 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="channel_name", nullable=false, length=50)
/*     */   public String getName() {
/* 591 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/* 595 */     this.name = name;
/*     */   }
/*     */ 
/*     */   @Column(name="channel_path", nullable=false, length=30)
/*     */   public String getPath() {
/* 600 */     return this.path;
/*     */   }
/*     */ 
/*     */   public void setPath(String path) {
/* 604 */     this.path = path;
/*     */   }
/*     */ 
/*     */   @Column(name="chnl_number", nullable=true, length=100)
/*     */   public String getNumber() {
/* 609 */     return this.number;
/*     */   }
/*     */ 
/*     */   public void setNumber(String number) {
/* 613 */     this.number = number;
/*     */   }
/*     */ 
/*     */   @Column(name="priority", nullable=false, length=10)
/*     */   public Integer getPriority() {
/* 618 */     return this.priority;
/*     */   }
/*     */ 
/*     */   public void setPriority(Integer priority) {
/* 622 */     this.priority = priority;
/*     */   }
/*     */ 
/*     */   @Column(name="is_alone", nullable=false, length=1)
/*     */   public Boolean getAlone() {
/* 627 */     return this.alone;
/*     */   }
/*     */ 
/*     */   public void setAlone(Boolean alone) {
/* 631 */     this.alone = alone;
/*     */   }
/*     */ 
/*     */   @Column(name="is_show", nullable=false, length=1)
/*     */   public Boolean getShow() {
/* 636 */     return this.show;
/*     */   }
/*     */ 
/*     */   public void setShow(Boolean show) {
/* 640 */     this.show = show;
/*     */   }
/*     */   @OneToOne(cascade={javax.persistence.CascadeType.REMOVE}, fetch=FetchType.LAZY, mappedBy="channel")
/*     */   @PrimaryKeyJoinColumn
/*     */   public ChannelExt getExt() {
/* 646 */     return this.ext;
/*     */   }
/*     */ 
/*     */   public void setExt(ChannelExt ext) {
/* 650 */     this.ext = ext;
/*     */   }
/*     */   @OneToOne(cascade={javax.persistence.CascadeType.REMOVE}, fetch=FetchType.LAZY, mappedBy="channel")
/*     */   @PrimaryKeyJoinColumn
/*     */   public ChannelTxt getTxt() {
/* 656 */     return this.txt;
/*     */   }
/*     */ 
/*     */   public void setTxt(ChannelTxt txt) {
/* 660 */     this.txt = txt;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="site_id", nullable=false)
/*     */   public Site getSite() {
/* 666 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(Site site) {
/* 670 */     this.site = site;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="flow_id", nullable=true)
/*     */   public WorkFlow getFlow() {
/* 676 */     return this.flow;
/*     */   }
/*     */ 
/*     */   public void setFlow(WorkFlow flow) {
/* 680 */     this.flow = flow;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="depart_id", nullable=false)
/*     */   public Depart getInputDepart() {
/* 686 */     return this.inputDepart;
/*     */   }
/*     */ 
/*     */   public void setInputDepart(Depart inputDepart) {
/* 690 */     this.inputDepart = inputDepart;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="parent_id", nullable=true)
/*     */   public Channel getParent() {
/* 696 */     return this.parent;
/*     */   }
/*     */ 
/*     */   public void setParent(Channel parent) {
/* 700 */     this.parent = parent;
/*     */   }
/*     */   @ElementCollection(fetch=FetchType.LAZY)
/*     */   @CollectionTable(name="tq_chnl_tpl_selection", joinColumns={@JoinColumn(name="chnl_id")})
/*     */   public Set<ChnlTplSelection> getTpls() {
/* 706 */     return this.tpls;
/*     */   }
/*     */ 
/*     */   public void setTpls(Set<ChnlTplSelection> tpls) {
/* 710 */     this.tpls = tpls;
/*     */   }
/*     */   @OneToMany(fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.REMOVE}, mappedBy="parent")
/*     */   @OrderBy("priority asc,id asc")
/*     */   public Set<Channel> getChild() {
/* 716 */     return this.child;
/*     */   }
/*     */ 
/*     */   public void setChild(Set<Channel> child)
/*     */   {
/* 721 */     this.child = child;
/*     */   }
/*     */   @ManyToMany(fetch=FetchType.LAZY)
/*     */   @JoinTable(name="tq_chnl_group_view", joinColumns={@JoinColumn(name="channel_id")}, inverseJoinColumns={@JoinColumn(name="group_id")})
/*     */   public Set<Group> getViewGroups() {
/* 727 */     return this.viewGroups;
/*     */   }
/*     */ 
/*     */   public void setViewGroups(Set<Group> viewGroups)
/*     */   {
/* 732 */     this.viewGroups = viewGroups;
/*     */   }
/*     */   @ManyToMany(fetch=FetchType.LAZY)
/*     */   @JoinTable(name="tq_chnl_group_contri", joinColumns={@JoinColumn(name="channel_id")}, inverseJoinColumns={@JoinColumn(name="group_id")})
/*     */   public Set<Group> getContriGroups() {
/* 738 */     return this.contriGroups;
/*     */   }
/*     */ 
/*     */   public void setContriGroups(Set<Group> contriGroups)
/*     */   {
/* 743 */     this.contriGroups = contriGroups;
/*     */   }
/*     */ 
/*     */   @ManyToMany(mappedBy="channels", fetch=FetchType.LAZY)
/*     */   public Set<Depart> getDeparts() {
/* 748 */     return this.departs;
/*     */   }
/*     */ 
/*     */   public void setDeparts(Set<Depart> departs)
/*     */   {
/* 753 */     this.departs = departs;
/*     */   }
/*     */ 
/*     */   @ManyToMany(mappedBy="channels", fetch=FetchType.LAZY)
/*     */   public Set<AdminCheck> getChecks() {
/* 758 */     return this.checks;
/*     */   }
/*     */ 
/*     */   public void setChecks(Set<AdminCheck> checks)
/*     */   {
/* 763 */     this.checks = checks;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.entity.Channel
 * JD-Core Version:    0.6.1
 */