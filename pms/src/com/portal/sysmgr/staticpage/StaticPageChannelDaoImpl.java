/*    */ package com.portal.sysmgr.staticpage;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.doccenter.entity.Channel;
/*    */ import freemarker.template.Configuration;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.servlet.ServletContext;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.CacheMode;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.ScrollMode;
/*    */ import org.hibernate.ScrollableResults;
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class StaticPageChannelDaoImpl extends QueryDaoImpl<Channel, Integer>
/*    */   implements StaticPageChannelDao
/*    */ {
/*    */   public List<String> staticChannelPage(String treeNumber, Configuration config, ServletContext ctx)
/*    */   {
/* 35 */     Criteria crit = createCriteria();
/* 36 */     if (!StringUtils.isBlank(treeNumber)) {
/* 37 */       crit.add(Restrictions.like("number", treeNumber + "%"));
/*    */     }
/* 39 */     Session session = getSession();
/* 40 */     ScrollableResults channels = crit.setCacheMode(CacheMode.IGNORE)
/* 41 */       .scroll(ScrollMode.FORWARD_ONLY);
/*    */ 
/* 43 */     List list = new ArrayList();
/* 44 */     List listtemp = new ArrayList();
/* 45 */     int count = 0; int counts = 0; int countf = 0; int countn = 0;
/* 46 */     while (channels.next()) {
/* 47 */       Channel channel = (Channel)channels.get(0);
/* 48 */       Integer i = StaticChannel.staticChannel(channel, config, ctx, null);
/* 49 */       switch (i.intValue()) {
/*    */       case 0:
/* 51 */         countn++;
/* 52 */         break;
/*    */       case -1:
/* 54 */         countf++;
/* 55 */         listtemp.add("栏目：" + channel.getName() + "的模板不存在或者存在错误，生成静态页失败！");
/* 56 */         break;
/*    */       case -2:
/* 58 */         countf++;
/* 59 */         listtemp.add("栏目：" + channel.getName() + "生成静态页时发生IO异常！");
/* 60 */         break;
/*    */       case -3:
/* 62 */         countf++;
/* 63 */         listtemp.add("栏目：" + channel.getName() + "目标静态页文件或者文件夹不存在!");
/* 64 */         break;
/*    */       case -4:
/* 66 */         countf++;
/* 67 */         listtemp.add("栏目：" + channel.getName() + "生成静态页时发生异常！");
/* 68 */         break;
/*    */       default:
/* 70 */         counts++;
/*    */       }
/*    */ 
/* 73 */       count++; if (count % 5 == 0) {
/* 74 */         session.clear();
/*    */       }
/*    */     }
/* 77 */     list.add("成功生成栏目静态页" + counts + "个栏目，失败" + countf + "个栏目,未生成" + countn + 
/* 78 */       "个栏目。");
/* 79 */     list.addAll(listtemp);
/* 80 */     return list;
/*    */   }
/*    */ 
/*    */   protected Class<Channel> getEntityClass() {
/* 84 */     return Channel.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.staticpage.StaticPageChannelDaoImpl
 * JD-Core Version:    0.6.1
 */