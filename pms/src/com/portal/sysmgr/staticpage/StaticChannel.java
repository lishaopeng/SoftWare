/*     */ package com.portal.sysmgr.staticpage;
/*     */ 
/*     */ /*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.io.Writer;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;

/*     */ import javax.servlet.ServletContext;

/*     */ import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.portal.doccenter.entity.Channel;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.utils.ContextTools;
/*     */ import com.portal.sysmgr.utils.ViewTools;

/*     */ import freemarker.template.Configuration;
/*     */ import freemarker.template.Template;
/*     */ import freemarker.template.TemplateException;
/*     */ 
/*     */ 
/*     */ public class StaticChannel
/*     */ {
/*     */   public static Integer staticChannel(Channel channel, Configuration config, ServletContext ctx, Integer countpage)
/*     */   {
/*  38 */     if ((channel == null) || (!channel.getStaticChannel())) {
/*  39 */       return Integer.valueOf(0);
/*     */     }
		Template template;
/*     */     try
/*     */     {
/*  43 */       template = config.getTemplate(channel.getTplChannelOrDef());
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/*  45 */       return Integer.valueOf(-1);
/*     */     }
/*  47 */     Map data = new HashMap();
/*  48 */     data.put("channel", channel);
/*  49 */     Integer total = Integer.valueOf(1);
/*  50 */     for (int page = 1; page <= total.intValue(); page++) {
/*  51 */       String filename = ctx.getRealPath(channel.getChannelRealPath(Integer.valueOf(page)));
/*  52 */       File file = new File(filename);
/*  53 */       file.getParentFile().mkdirs();
/*  54 */       String url = channel.getUrlStatic(Integer.valueOf(page));
/*  55 */       ViewTools.frontData(url, data, channel.getSite());
/*  56 */       ViewTools.frontPageData(url, data, Integer.valueOf(page));
/*  57 */       FileOutputStream fos = null;
/*  58 */       Writer out = null;
/*     */       try {
/*  60 */         fos = new FileOutputStream(file);
/*  61 */         out = new OutputStreamWriter(fos, "UTF-8");
/*  62 */         ContextTools.resetTotalPages();
/*  63 */         template.process(data, new BufferedWriter(out));
/*  64 */         Integer cou = ContextTools.getTotalPages();
/*  65 */         if ((countpage != null) && (cou != null) && (countpage.intValue() <= cou.intValue())) {
/*  66 */           total = countpage;
/*     */         } else {
/*  68 */           total = cou;
/*  69 */           if ((total == null) || (total.intValue() < 1))
/*  70 */             total = Integer.valueOf(1);
/*     */         }
/*     */       }
/*     */       catch (FileNotFoundException e) {
/*  74 */         return Integer.valueOf(-3);
/*     */       } catch (UnsupportedEncodingException e) {
/*  76 */         return Integer.valueOf(-4);
/*     */       } catch (TemplateException e) {
/*  78 */         return Integer.valueOf(-1);
/*     */       }
/*     */       catch (IOException e)
/*     */       {
/*     */         Integer localInteger1;
/*  80 */         return Integer.valueOf(-2);
/*     */       } finally {
/*     */         try {
/*  83 */           if (out != null) {
/*  84 */             out.flush();
/*  85 */             out.close();
/*     */           }
/*  87 */           if (fos != null)
/*  88 */             fos.close();
/*     */         }
/*     */         catch (IOException localIOException5)
/*     */         {
/*     */         }
/*     */       }
/*     */       try
/*     */       {
/*  83 */         if (out != null) {
/*  84 */           out.flush();
/*  85 */           out.close();
/*     */         }
/*  87 */         if (fos != null)
/*  88 */           fos.close();
/*     */       }
/*     */       catch (IOException localIOException6)
/*     */       {
/*     */       }
/*     */     }
/*  94 */     return Integer.valueOf(1);
/*     */   }
/*     */ 
/*     */   public static void staticChannelpage(Channel channel, Configuration config, ServletContext ctx, Integer page)
/*     */   {
/*  99 */     if ((channel == null) || (!channel.getStaticChannel())) {
/* 100 */       return;
/*     */     }
		Template template;
/*     */     try
/*     */     {
/* 104 */       template = config.getTemplate(channel.getTplChannelOrDef());
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/* 106 */       System.out.println(channel.getName() + "的模板不存在，请检查!");
/*     */       return;
/*     */     }
/* 109 */     Map data = new HashMap();
/* 110 */     data.put("channel", channel);
/* 111 */     String filename = ctx.getRealPath(channel.getChannelRealPath(page));
/* 112 */     File file = new File(filename);
/* 113 */     file.getParentFile().mkdirs();
/* 114 */     String url = channel.getUrlStatic(page);
/* 115 */     ViewTools.frontData(url, data, channel.getSite());
/* 116 */     ViewTools.frontPageData(url, data, page);
/* 117 */     FileOutputStream fos = null;
/* 118 */     Writer out = null;
/*     */     try {
/* 120 */       fos = new FileOutputStream(file);
/* 121 */       out = new OutputStreamWriter(fos, "UTF-8");
/* 122 */       template.process(data, new BufferedWriter(out));
/*     */     }
/*     */     catch (FileNotFoundException localFileNotFoundException)
/*     */     {
/*     */       try
/*     */       {
/* 131 */         if (out != null) {
/* 132 */           out.flush();
/* 133 */           out.close();
/*     */         }
/* 135 */         if (fos != null)
/* 136 */           fos.close();
/*     */       }
/*     */       catch (IOException localIOException1)
/*     */       {
/*     */       }
/*     */     }
/*     */     catch (UnsupportedEncodingException localUnsupportedEncodingException)
/*     */     {
/*     */       try
/*     */       {
/* 131 */         if (out != null) {
/* 132 */           out.flush();
/* 133 */           out.close();
/*     */         }
/* 135 */         if (fos != null)
/* 136 */           fos.close();
/*     */       }
/*     */       catch (IOException localIOException2)
/*     */       {
/*     */       }
/*     */     }
/*     */     catch (TemplateException e)
/*     */     {
/* 126 */       System.out.println("模板中有错误，静态页生成失败!");
/*     */       try
/*     */       {
/* 131 */         if (out != null) {
/* 132 */           out.flush();
/* 133 */           out.close();
/*     */         }
/* 135 */         if (fos != null)
/* 136 */           fos.close();
/*     */       }
/*     */       catch (IOException localIOException3)
/*     */       {
/*     */       }
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/* 128 */       System.out.println("生成静态页异常!");
/*     */       try
/*     */       {
/* 131 */         if (out != null) {
/* 132 */           out.flush();
/* 133 */           out.close();
/*     */         }
/* 135 */         if (fos != null)
/* 136 */           fos.close();
/*     */       }
/*     */       catch (IOException localIOException4)
/*     */       {
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/*     */       try
/*     */       {
/* 131 */         if (out != null) {
/* 132 */           out.flush();
/* 133 */           out.close();
/*     */         }
/* 135 */         if (fos != null)
/* 136 */           fos.close();
/*     */       }
/*     */       catch (IOException localIOException5)
/*     */       {
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static int staticIndex(Site site, Configuration config, ServletContext ctx) {
/* 145 */     if ((site == null) || (StringUtils.isBlank(site.getTplIndexOrDef()))) {
/* 146 */       System.out.println("---------111");
/* 147 */       return 0;
/*     */     }
		Template template;
/*     */     try
/*     */     {
/* 151 */       template = config.getTemplate(site.getTplIndexOrDef());
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/* 153 */       System.out.println("---------222");
/* 154 */       return -1;
/*     */     }
/* 156 */     Map data = new HashMap();
/* 157 */     String filename = ctx.getRealPath(site.getStaticRealPath());
/* 158 */     File file = new File(filename);
/* 159 */     file.getParentFile().mkdirs();
/* 160 */     ViewTools.frontData(site.getUrl(), data, site);
/* 161 */     FileOutputStream fos = null;
/* 162 */     Writer out = null;
/*     */     try {
/* 164 */       fos = new FileOutputStream(file);
/* 165 */       out = new OutputStreamWriter(fos, "UTF-8");
/* 166 */       template.process(data, new BufferedWriter(out));
/*     */     } catch (FileNotFoundException e) {
/* 168 */       return -3;
/*     */     } catch (UnsupportedEncodingException e) {
/* 170 */       return -4;
/*     */     } catch (TemplateException e) {
/* 172 */       return -1;
/*     */     } catch (IOException e) {
/* 174 */       return -2;
/*     */     } finally {
/*     */       try {
/* 177 */         if (out != null) {
/* 178 */           out.flush();
/* 179 */           out.close();
/*     */         }
/* 181 */         if (fos != null)
/* 182 */           fos.close();
/*     */       }
/*     */       catch (IOException localIOException5)
/*     */       {
/*     */       }
/*     */     }
/*     */     try
/*     */     {
/* 177 */       if (out != null) {
/* 178 */         out.flush();
/* 179 */         out.close();
/*     */       }
/* 181 */       if (fos != null)
/* 182 */         fos.close();
/*     */     }
/*     */     catch (IOException localIOException6)
/*     */     {
/*     */     }
/* 187 */     return 1;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.staticpage.StaticChannel
 * JD-Core Version:    0.6.1
 */