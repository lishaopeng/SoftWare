/*     */ package com.portal.sysmgr.utils;
/*     */ 
/*     */ import com.javapms.basic.plugin.springmvc.DateTypeEditor;
/*     */ import freemarker.template.TemplateBooleanModel;
/*     */ import freemarker.template.TemplateDateModel;
/*     */ import freemarker.template.TemplateException;
/*     */ import freemarker.template.TemplateModel;
/*     */ import freemarker.template.TemplateModelException;
/*     */ import freemarker.template.TemplateNumberModel;
/*     */ import freemarker.template.TemplateScalarModel;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Date;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.util.NumberUtils;
/*     */ 
/*     */ public abstract class TagModelTools
/*     */ {
/*     */   public static final String BEAN = "bean";
/*     */   public static final String LIST = "list";
/*     */   public static final String PAGE = "page";
/*     */ 
/*     */   public static String getString(String name, Map<String, TemplateModel> params)
/*     */     throws TemplateModelException
/*     */   {
/*  35 */     TemplateModel model = (TemplateModel)params.get(name);
/*  36 */     return getString(name, model);
/*     */   }
/*     */ 
/*     */   public static String getString(String name, TemplateModel model) throws TemplateModelException
/*     */   {
/*  41 */     if (model == null) {
/*  42 */       return null;
/*     */     }
/*  44 */     if ((model instanceof TemplateScalarModel))
/*  45 */       return ((TemplateScalarModel)model).getAsString();
/*  46 */     if ((model instanceof TemplateNumberModel)) {
/*  47 */       return ((TemplateNumberModel)model).getAsNumber().toString();
/*     */     }
/*  49 */     System.out.println("参数必须是字符！");
/*  50 */     return null;
/*     */   }
/*     */ 
/*     */   public static String[] getStringArray(String name, Map<String, TemplateModel> params)
/*     */     throws TemplateModelException
/*     */   {
/*  56 */     String str = getString(name, params);
/*  57 */     if (StringUtils.isBlank(str)) {
/*  58 */       return null;
/*     */     }
/*  60 */     String[] arr = StringUtils.split(str, ',');
/*  61 */     return arr;
/*     */   }
/*     */ 
/*     */   public static Long getLong(String name, TemplateModel model) throws TemplateModelException
/*     */   {
/*  66 */     return (Long)getNumber(model, name, Long.class);
/*     */   }
/*     */ 
/*     */   public static Long getLong(String name, Map<String, TemplateModel> params) throws TemplateModelException
/*     */   {
/*  71 */     return (Long)getNumber(params, name, Long.class);
/*     */   }
/*     */ 
/*     */   public static Integer getInt(String name, TemplateModel model) throws TemplateModelException
/*     */   {
/*  76 */     return (Integer)getNumber(model, name, Integer.class);
/*     */   }
/*     */ 
/*     */   public static Integer getInt(String name, Map<String, TemplateModel> params) throws TemplateModelException
/*     */   {
/*  81 */     return (Integer)getNumber(params, name, Integer.class);
/*     */   }
/*     */ 
/*     */   public static Byte getByte(String name, TemplateModel model) throws TemplateModelException
/*     */   {
/*  86 */     return (Byte)getNumber(model, name, Byte.class);
/*     */   }
/*     */ 
/*     */   public static Byte getByte(String name, Map<String, TemplateModel> params) throws TemplateModelException
/*     */   {
/*  91 */     return (Byte)getNumber(params, name, Byte.class);
/*     */   }
/*     */ 
/*     */   public static <T extends Number> T getNumber(TemplateModel model, String name, Class<T> targetClass) throws TemplateModelException
/*     */   {
/*  96 */     if (model == null) {
/*  97 */       return null;
/*     */     }
/*  99 */     if ((model instanceof TemplateScalarModel)) {
/* 100 */       TemplateScalarModel scalarModel = (TemplateScalarModel)model;
/* 101 */       String text = scalarModel.getAsString();
/* 102 */       if (StringUtils.isNotBlank(text)) {
/*     */         try {
/* 104 */           return NumberUtils.parseNumber(text, targetClass);
/*     */         } catch (NumberFormatException e) {
/* 106 */           System.out.println("参数必须是数字");
/* 107 */           return null;
/*     */         }
/*     */       }
/* 110 */       return null;
/*     */     }
/* 112 */     if ((model instanceof TemplateNumberModel)) {
/* 113 */       TemplateNumberModel numberModel = (TemplateNumberModel)model;
/* 114 */       Number number = numberModel.getAsNumber();
/* 115 */       return NumberUtils.convertNumberToTargetClass(number, targetClass);
/*     */     }
/* 117 */     System.out.println("参数必须是数字");
/* 118 */     return null;
/*     */   }
/*     */ 
/*     */   public static <T extends Number> T getNumber(Map<String, TemplateModel> params, String name, Class<T> targetClass)
/*     */     throws TemplateModelException
/*     */   {
/* 125 */     TemplateModel model = (TemplateModel)params.get(name);
/* 126 */     return getNumber(model, name, targetClass);
/*     */   }
/*     */ 
/*     */   public static Integer[] getIntArray(String name, Map<String, TemplateModel> params) throws TemplateException
/*     */   {
/* 131 */     String str = getString(name, params);
/* 132 */     if (StringUtils.isBlank(str)) {
/* 133 */       return null;
/*     */     }
/* 135 */     String[] arr = StringUtils.split(str, ',');
/* 136 */     Integer[] ids = new Integer[arr.length];
/* 137 */     int i = 0;
/*     */     try {
/* 139 */       for (String s : arr) {
/* 140 */         ids[(i++)] = Integer.valueOf(s);
/*     */       }
/* 142 */       return ids;
/*     */     } catch (NumberFormatException e) {
/* 144 */       System.out.println("参数必须是数字,而且以,隔开!");
/* 145 */     }return null;
/*     */   }
/*     */ 
/*     */   public static Boolean getBool(String name, Map<String, TemplateModel> params)
/*     */     throws TemplateException
/*     */   {
/* 151 */     TemplateModel model = (TemplateModel)params.get(name);
/* 152 */     if (model == null) {
/* 153 */       return null;
/*     */     }
/* 155 */     if ((model instanceof TemplateBooleanModel))
/* 156 */       return Boolean.valueOf(((TemplateBooleanModel)model).getAsBoolean());
/* 157 */     if ((model instanceof TemplateNumberModel))
/* 158 */       return Boolean.valueOf(((TemplateNumberModel)model).getAsNumber().intValue() != 0);
/* 159 */     if ((model instanceof TemplateScalarModel)) {
/* 160 */       String s = ((TemplateScalarModel)model).getAsString();
/* 161 */       if (!StringUtils.isBlank(s)) {
/* 162 */         return Boolean.valueOf((!s.equals("0")) && (!s.equalsIgnoreCase("false")) && (!s
/* 163 */           .equalsIgnoreCase("f")));
/*     */       }
/* 165 */       return null;
/*     */     }
/*     */ 
/* 168 */     System.out.println("参数必须是boolean型");
/* 169 */     return null;
/*     */   }
/*     */ 
/*     */   public static Date getDate(String name, Map<String, TemplateModel> params)
/*     */     throws TemplateException
/*     */   {
/* 175 */     TemplateModel model = (TemplateModel)params.get(name);
/* 176 */     if (model == null) {
/* 177 */       return null;
/*     */     }
/* 179 */     if ((model instanceof TemplateDateModel))
/* 180 */       return ((TemplateDateModel)model).getAsDate();
/* 181 */     if ((model instanceof TemplateScalarModel)) {
/* 182 */       DateTypeEditor editor = new DateTypeEditor();
/* 183 */       editor.setAsText(((TemplateScalarModel)model).getAsString());
/* 184 */       return (Date)editor.getValue();
/*     */     }
/* 186 */     System.out.println("参数必须是日期型");
/* 187 */     return null;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.utils.TagModelTools
 * JD-Core Version:    0.6.1
 */