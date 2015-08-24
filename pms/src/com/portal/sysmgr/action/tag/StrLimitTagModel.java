/*    */ package com.portal.sysmgr.action.tag;
/*    */ 
/*    */ import com.javapms.basic.utils.StringBeanUtils;
/*    */ import com.portal.sysmgr.utils.TagModelTools;
/*    */ import freemarker.core.Environment;
/*    */ import freemarker.template.TemplateDirectiveBody;
/*    */ import freemarker.template.TemplateDirectiveModel;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ import java.io.IOException;
/*    */ import java.io.Writer;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class StrLimitTagModel
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public static final String PARAM_S = "s";
/*    */   public static final String PARAM_LEN = "l";
/*    */   public static final String PARAM_REP = "r";
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 32 */     String s = TagModelTools.getString("s", params);
/* 33 */     Integer len = TagModelTools.getInt("l", params);
/* 34 */     String r = TagModelTools.getString("r", params);
/* 35 */     if (!StringUtils.isBlank(r)) {
/* 36 */       s = s.replaceAll(r, "");
/*    */     }
/* 38 */     String append = "&hellip;";
/* 39 */     if (s != null) {
/* 40 */       Writer out = env.getOut();
/* 41 */       if (len != null)
/* 42 */         out.append(StringBeanUtils.textCut(s, len.intValue(), append));
/*    */       else
/* 44 */         out.append(s);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.action.tag.StrLimitTagModel
 * JD-Core Version:    0.6.1
 */