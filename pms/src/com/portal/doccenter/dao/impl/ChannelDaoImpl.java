/*     */ package com.portal.doccenter.dao.impl;
/*     */ 
/*     */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*     */ import com.portal.doccenter.dao.ChannelDao;
/*     */ import com.portal.doccenter.entity.Channel;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.hibernate.Criteria;
/*     */ import org.hibernate.criterion.Order;
/*     */ import org.hibernate.criterion.Projections;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.hibernate.type.IntegerType;
/*     */ import org.springframework.data.domain.Page;
/*     */ import org.springframework.stereotype.Repository;
/*     */ 
/*     */ @Repository
/*     */ public class ChannelDaoImpl extends QueryDaoImpl<Channel, Integer>
/*     */   implements ChannelDao
/*     */ {
/*     */   public List<Channel> getChannelListByTag(Integer siteId, Integer parentId, Boolean alone, boolean show, int count)
/*     */   {
/*  24 */     Criteria crit = getChannelByTag(siteId, parentId, alone, show);
/*  25 */     crit.setMaxResults(count);
/*  26 */     crit.setCacheable(true);
/*  27 */     return findByCriteria(crit);
/*     */   }
/*     */ 
/*     */   public Page<Channel> getChannelPageByTag(Integer siteId, Integer parentId, Boolean alone, boolean show, int pageNo, int pageSize)
/*     */   {
/*  32 */     Criteria crit = getChannelByTag(siteId, parentId, alone, show);
/*  33 */     crit.setCacheable(true);
/*  34 */     return findByCriteria(crit, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   public List<Channel> getChannelBySite(Integer siteId, Integer parentId, String key, String sortname, String sortorder, Boolean alone)
/*     */   {
/*  39 */     Criteria crit = createCriteria();
/*  40 */     crit.add(Restrictions.eq("site.id", siteId));
/*  41 */     if (parentId != null)
/*  42 */       crit.add(Restrictions.eq("parent.id", parentId));
/*     */     else {
/*  44 */       crit.add(Restrictions.isNull("parent.id"));
/*     */     }
/*  46 */     if (!StringUtils.isBlank(key)) {
/*  47 */       crit.add(Restrictions.or(
/*  48 */         Restrictions.like("name", "%" + key + "%"), 
/*  49 */         Restrictions.like("path", "%" + key + "%")));
/*     */     }
/*  51 */     if (alone != null) {
/*  52 */       crit.add(Restrictions.eq("alone", alone));
/*     */     }
/*  54 */     if (!StringUtils.isBlank(sortname)) {
/*  55 */       if ("asc".equals(sortorder))
/*  56 */         crit.addOrder(Order.asc(sortname));
/*     */       else
/*  58 */         crit.addOrder(Order.desc(sortname));
/*     */     }
/*     */     else {
/*  61 */       crit.addOrder(Order.asc("priority"));
/*  62 */       crit.addOrder(Order.asc("id"));
/*     */     }
/*  64 */     return findByCriteria(crit);
/*     */   }
/*     */ 
/*     */   public List<Channel> getChannelByAdmin(Integer siteId, Integer departId, Integer parentId, String key, String sortname, String sortorder, Boolean alone)
/*     */   {
/*  70 */     Criteria crit = createCriteria();
/*  71 */     if (departId != null) {
/*  72 */       crit.createAlias("departs", "depart");
/*  73 */       crit.add(Restrictions.eq("depart.id", departId));
/*     */     } else {
/*  75 */       crit.add(Restrictions.eq("site.id", siteId));
/*     */     }
/*  77 */     if (parentId != null)
/*  78 */       crit.add(Restrictions.eq("parent.id", parentId));
/*     */     else {
/*  80 */       crit.add(Restrictions.isNull("parent.id"));
/*     */     }
/*  82 */     if (!StringUtils.isBlank(key)) {
/*  83 */       crit.add(Restrictions.or(
/*  84 */         Restrictions.like("name", "%" + key + "%"), 
/*  85 */         Restrictions.like("path", "%" + key + "%")));
/*     */     }
/*  87 */     if (alone != null) {
/*  88 */       crit.add(Restrictions.eq("alone", alone));
/*     */     }
/*  90 */     if (!StringUtils.isBlank(sortname)) {
/*  91 */       if ("asc".equals(sortorder))
/*  92 */         crit.addOrder(Order.asc(sortname));
/*     */       else
/*  94 */         crit.addOrder(Order.desc(sortname));
/*     */     }
/*     */     else {
/*  97 */       crit.addOrder(Order.asc("priority"));
/*  98 */       crit.addOrder(Order.asc("id"));
/*     */     }
/* 100 */     return findByCriteria(crit);
/*     */   }
/*     */ 
/*     */   public List<Channel> getChannelByAdminAndTake(Integer siteId, Integer adminId, Integer parentId, String key, String sortname, String sortorder, Boolean alone)
/*     */   {
/* 106 */     Criteria crit = createCriteria();
/* 107 */     if (adminId != null) {
/* 108 */       crit.createAlias("checks", "check");
/* 109 */       crit.add(Restrictions.eq("check.admin.id", adminId));
/*     */     } else {
/* 111 */       crit.add(Restrictions.eq("site.id", siteId));
/*     */     }
/* 113 */     if (parentId != null)
/* 114 */       crit.add(Restrictions.eq("parent.id", parentId));
/*     */     else {
/* 116 */       crit.add(Restrictions.isNull("parent.id"));
/*     */     }
/* 118 */     if (!StringUtils.isBlank(key)) {
/* 119 */       crit.add(Restrictions.or(
/* 120 */         Restrictions.like("name", "%" + key + "%"), 
/* 121 */         Restrictions.like("path", "%" + key + "%")));
/*     */     }
/* 123 */     if (alone != null) {
/* 124 */       crit.add(Restrictions.eq("alone", alone));
/*     */     }
/* 126 */     if (!StringUtils.isBlank(sortname)) {
/* 127 */       if ("asc".equals(sortorder))
/* 128 */         crit.addOrder(Order.asc(sortname));
/*     */       else
/* 130 */         crit.addOrder(Order.desc(sortname));
/*     */     }
/*     */     else {
/* 133 */       crit.addOrder(Order.asc("priority"));
/* 134 */       crit.addOrder(Order.asc("id"));
/*     */     }
/* 136 */     return findByCriteria(crit);
/*     */   }
/*     */ 
/*     */   public List<Channel> getChannelByModel(Integer parentId, Integer modelId, Integer departId, Integer siteId)
/*     */   {
/* 141 */     StringBuffer sb = new StringBuffer("{alias}.channel_id in ");
/* 142 */     sb.append("(select e.chnl_id from tq_chnl_tpl_selection e where e.model_id=?)");
/* 143 */     Criteria crit = createCriteria();
/* 144 */     crit.add(Restrictions.sqlRestriction(sb.toString(), modelId, 
/* 145 */       IntegerType.INSTANCE));
/* 146 */     if (departId != null) {
/* 147 */       crit.createAlias("departs", "depart");
/* 148 */       crit.add(Restrictions.eq("depart.id", departId));
/*     */     }
/* 150 */     crit.add(Restrictions.eq("site.id", siteId));
/* 151 */     if (parentId != null)
/* 152 */       crit.add(Restrictions.eq("parent.id", parentId));
/*     */     else {
/* 154 */       crit.add(Restrictions.isNull("parent.id"));
/*     */     }
/* 156 */     crit.add(Restrictions.eq("alone", Boolean.valueOf(false)));
/* 157 */     crit.addOrder(Order.asc("priority"));
/* 158 */     crit.addOrder(Order.asc("id"));
/* 159 */     return findByCriteria(crit);
/*     */   }
/*     */ 
/*     */   public List<Channel> getChannelByModelAndTake(Integer parentId, Integer modelId, Integer adminId, Integer siteId)
/*     */   {
/* 164 */     StringBuffer sb = new StringBuffer("{alias}.channel_id in ");
/* 165 */     sb.append("(select e.chnl_id from tq_chnl_tpl_selection e where e.model_id=?)");
/* 166 */     Criteria crit = createCriteria();
/* 167 */     crit.add(Restrictions.sqlRestriction(sb.toString(), modelId, 
/* 168 */       IntegerType.INSTANCE));
/* 169 */     if (adminId != null) {
/* 170 */       crit.createAlias("checks", "check");
/* 171 */       crit.add(Restrictions.eq("check.admin.id", adminId));
/*     */     }
/* 173 */     crit.add(Restrictions.eq("site.id", siteId));
/* 174 */     if (parentId != null)
/* 175 */       crit.add(Restrictions.eq("parent.id", parentId));
/*     */     else {
/* 177 */       crit.add(Restrictions.isNull("parent.id"));
/*     */     }
/* 179 */     crit.add(Restrictions.eq("alone", Boolean.valueOf(false)));
/* 180 */     crit.addOrder(Order.asc("priority"));
/* 181 */     crit.addOrder(Order.asc("id"));
/* 182 */     return findByCriteria(crit);
/*     */   }
/*     */ 
/*     */   public List<Channel> getChannelByModelAndMember(Integer parentId, Integer modelId, Integer groupId, Integer siteId)
/*     */   {
/* 187 */     StringBuffer sb = new StringBuffer("{alias}.channel_id in ");
/* 188 */     sb.append("(select e.chnl_id from tq_chnl_tpl_selection e where e.model_id=?)");
/* 189 */     Criteria crit = createCriteria();
/* 190 */     crit.add(Restrictions.sqlRestriction(sb.toString(), modelId, 
/* 191 */       IntegerType.INSTANCE));
/* 192 */     if (groupId != null) {
/* 193 */       crit.createAlias("contriGroups", "g");
/* 194 */       crit.add(Restrictions.eq("g.id", groupId));
/*     */     }
/* 196 */     crit.add(Restrictions.eq("site.id", siteId));
/* 197 */     if (parentId != null)
/* 198 */       crit.add(Restrictions.eq("parent.id", parentId));
/*     */     else {
/* 200 */       crit.add(Restrictions.isNull("parent.id"));
/*     */     }
/* 202 */     crit.add(Restrictions.eq("alone", Boolean.valueOf(false)));
/* 203 */     crit.addOrder(Order.asc("priority"));
/* 204 */     crit.addOrder(Order.asc("id"));
/* 205 */     return findByCriteria(crit);
/*     */   }
/*     */ 
/*     */   public Channel findByNumber(String number) {
/* 209 */     Criteria crit = createCriteria();
/* 210 */     crit.add(Restrictions.eq("chnlNumber", number));
/* 211 */     return (Channel)findUnique(crit);
/*     */   }
/*     */ 
/*     */   public Channel findByPath(String path, Integer siteId, boolean cache) {
/* 215 */     Criteria crit = createCriteria();
/* 216 */     crit.add(Restrictions.eq("path", path));
/* 217 */     crit.add(Restrictions.eq("site.id", siteId));
/* 218 */     crit.setCacheable(cache);
/* 219 */     return (Channel)findUnique(crit);
/*     */   }
/*     */ 
/*     */   public Channel findByName(String name) {
/* 223 */     Criteria crit = createCriteria();
/* 224 */     crit.add(Restrictions.eq("name", name));
/* 225 */     return (Channel)findUnique(crit);
/*     */   }
/*     */ 
/*     */   public int getAllChannelCount(Integer siteId) {
/* 229 */     Criteria crit = createCriteria();
/* 230 */     crit.add(Restrictions.eq("site.id", siteId));
/* 231 */     crit.setProjection(Projections.count("id"));
/* 232 */     return ((Number)crit.uniqueResult()).intValue();
/*     */   }
/*     */ 
/*     */   public int updateChannelByInputDepart(Integer departId) {
/* 236 */     String hql = "update Channel bean set bean.inputDepart=null where bean.inputDepart.id=?";
/* 237 */     return executeQuery(hql, new Object[] { departId });
/*     */   }
/*     */ 
/*     */   public int delChannelByInputDepart(Integer departId) {
/* 241 */     String hql = "delete from Channel bean where bean.inputDepart.id=?";
/* 242 */     return executeQuery(hql, new Object[] { departId });
/*     */   }
/*     */ 
/*     */   public int updateChannelByWorkFlow(Integer flowId) {
/* 246 */     String hql = "update Channel bean set bean.flow=null where bean.flow.id=?";
/* 247 */     return executeQuery(hql, new Object[] { flowId });
/*     */   }
/*     */ 
/*     */   private Criteria getChannelByTag(Integer siteId, Integer parentId, Boolean alone, boolean show)
/*     */   {
/* 252 */     Criteria crit = createCriteria();
/* 253 */     if (parentId != null) {
/* 254 */       crit.add(Restrictions.eq("parent.id", parentId));
/*     */     } else {
/* 256 */       crit.add(Restrictions.eq("site.id", siteId));
/* 257 */       crit.add(Restrictions.isNull("parent.id"));
/*     */     }
/* 259 */     if (show) {
/* 260 */       crit.add(Restrictions.eq("show", Boolean.valueOf(true)));
/*     */     }
/* 262 */     if (alone != null) {
/* 263 */       crit.add(Restrictions.eq("alone", alone));
/*     */     }
/* 265 */     crit.addOrder(Order.asc("priority"));
/* 266 */     crit.addOrder(Order.asc("id"));
/* 267 */     return crit;
/*     */   }
/*     */ 
/*     */   protected Class<Channel> getEntityClass()
/*     */   {
/* 272 */     return Channel.class;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.dao.impl.ChannelDaoImpl
 * JD-Core Version:    0.6.1
 */