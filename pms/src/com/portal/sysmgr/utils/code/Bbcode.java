/*     */ package com.portal.sysmgr.utils.code;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class Bbcode
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*   7 */   private String tagName = "";
/*     */   private String regex;
/*     */   private String replace;
/*     */   private boolean removQuotes;
/*     */   private boolean alwaysProcess;
/*     */ 
/*     */   public Bbcode()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Bbcode(String tagName, String regex, String replace)
/*     */   {
/*  27 */     this.tagName = tagName;
/*  28 */     this.regex = regex;
/*  29 */     this.replace = replace;
/*     */   }
/*     */ 
/*     */   public String getRegex()
/*     */   {
/*  38 */     return this.regex;
/*     */   }
/*     */ 
/*     */   public String getReplace()
/*     */   {
/*  47 */     return this.replace;
/*     */   }
/*     */ 
/*     */   public String getTagName()
/*     */   {
/*  56 */     return this.tagName;
/*     */   }
/*     */ 
/*     */   public boolean removeQuotes() {
/*  60 */     return this.removQuotes;
/*     */   }
/*     */ 
/*     */   public void setRegex(String regex)
/*     */   {
/*  70 */     this.regex = regex;
/*     */   }
/*     */ 
/*     */   public void setReplace(String replace)
/*     */   {
/*  80 */     this.replace = replace;
/*     */   }
/*     */ 
/*     */   public void setTagName(String tagName)
/*     */   {
/*  90 */     this.tagName = tagName;
/*     */   }
/*     */ 
/*     */   public void enableAlwaysProcess() {
/*  94 */     this.alwaysProcess = true;
/*     */   }
/*     */ 
/*     */   public boolean alwaysProcess() {
/*  98 */     return this.alwaysProcess;
/*     */   }
/*     */ 
/*     */   public void enableRemoveQuotes() {
/* 102 */     this.removQuotes = true;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.utils.code.Bbcode
 * JD-Core Version:    0.6.1
 */