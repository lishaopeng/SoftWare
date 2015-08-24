/*     */ package com.javapms.basic.plugin.springmvc;
/*     */ 
/*     */ import freemarker.core.ParseException;
/*     */ import freemarker.template.Configuration;
/*     */ import freemarker.template.Template;
/*     */ import java.io.IOException;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.springframework.beans.BeansException;
/*     */ import org.springframework.beans.factory.BeanFactoryUtils;
/*     */ import org.springframework.beans.factory.NoSuchBeanDefinitionException;
/*     */ import org.springframework.context.ApplicationContextException;
/*     */ import org.springframework.web.servlet.view.AbstractTemplateView;
/*     */ import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
/*     */ 
/*     */ public class SimpleFreeMarkerView extends AbstractTemplateView
/*     */ {
/*     */   public static final String CONTEXT_PATH = "base";
/*     */   private Configuration configuration;
/*     */ 
/*     */   public void setConfiguration(Configuration configuration)
/*     */   {
/*  36 */     this.configuration = configuration;
/*     */   }
/*     */ 
/*     */   protected Configuration getConfiguration() {
/*  40 */     return this.configuration;
/*     */   }
/*     */ 
/*     */   protected FreeMarkerConfig autodetectConfiguration()
/*     */     throws BeansException
/*     */   {
/*     */     try
/*     */     {
/*  51 */       return 
/*  52 */         (FreeMarkerConfig)BeanFactoryUtils.beanOfTypeIncludingAncestors(getApplicationContext(), 
/*  53 */         FreeMarkerConfig.class, true, false);
/*     */     } catch (NoSuchBeanDefinitionException ex) {
/*  55 */       throw new ApplicationContextException(
/*  56 */         "Must define a single FreeMarkerConfig bean in this web application context (may be inherited): FreeMarkerConfigurer is the usual implementation. This bean may be given any name.", 
/*  58 */         ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void initApplicationContext()
/*     */     throws BeansException
/*     */   {
/*  73 */     super.initApplicationContext();
/*     */ 
/*  75 */     if (getConfiguration() == null) {
/*  76 */       FreeMarkerConfig config = autodetectConfiguration();
/*  77 */       setConfiguration(config.getConfiguration());
/*     */     }
/*  79 */     checkTemplate();
/*     */   }
/*     */ 
/*     */   protected void checkTemplate()
/*     */     throws ApplicationContextException
/*     */   {
/*     */     try
/*     */     {
/*  96 */       getConfiguration().getTemplate(getUrl());
/*     */     } catch (ParseException ex) {
/*  98 */       throw new ApplicationContextException(
/*  99 */         "Failed to parse FreeMarker template for URL [" + getUrl() + 
/* 100 */         "]", ex);
/*     */     } catch (IOException ex) {
/* 102 */       throw new ApplicationContextException(
/* 103 */         "Could not load FreeMarker template for URL [" + getUrl() + 
/* 104 */         "]", ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void renderMergedTemplateModel(Map model, HttpServletRequest request, HttpServletResponse response)
/*     */     throws Exception
/*     */   {
/* 112 */     model.put("base", request.getContextPath());
/* 113 */     getConfiguration().getTemplate(getUrl()).process(model, 
/* 114 */       response.getWriter());
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.javapms.basic.plugin.springmvc.SimpleFreeMarkerView
 * JD-Core Version:    0.6.1
 */