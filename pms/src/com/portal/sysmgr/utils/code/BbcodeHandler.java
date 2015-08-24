/*     */ package com.portal.sysmgr.utils.code;
/*     */ 
/*     */ import com.javapms.basic.utils.StringBeanUtils;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import javax.xml.parsers.SAXParser;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.beans.factory.InitializingBean;
/*     */ import org.springframework.core.io.Resource;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.SAXParseException;
/*     */ import org.xml.sax.helpers.DefaultHandler;
/*     */ 
/*     */ public class BbcodeHandler extends DefaultHandler
/*     */   implements InitializingBean
/*     */ {
/*  24 */   private Map<String, Bbcode> bbMap = new LinkedHashMap();
/*  25 */   private Map<String, Bbcode> alwaysProcessMap = new LinkedHashMap();
/*  26 */   private String tagName = "";
/*     */   private StringBuffer sb;
/*     */   private Bbcode bb;
/*     */   private static BbcodeHandler handler;
/*     */   private Resource configLocation;
/*     */ 
/*     */   public String bbcode2html(String s)
/*     */   {
/*  34 */     if (StringUtils.isBlank(s)) {
/*  35 */       return s;
/*     */     }
/*  37 */     s = StringBeanUtils.txt2htm(s);
/*  38 */     return processText(s);
/*     */   }
/*     */ 
/*     */   public String processText(String s) {
/*  42 */     int codeIndex = s.indexOf("[code");
/*  43 */     int codeEndIndex = codeIndex > -1 ? s.indexOf("[/code]") : -1;
/*     */ 
/*  45 */     if ((codeIndex == -1) || (codeEndIndex == -1) || (codeEndIndex < codeIndex)) {
/*  46 */       return bbcode2htmlExceptCodeTag(s);
/*     */     }
/*  48 */     int nextStartPos = 0;
/*  49 */     StringBuilder result = new StringBuilder(s.length());
/*     */ 
/*  51 */     while ((codeIndex > -1) && (codeEndIndex > -1) && 
/*  52 */       (codeEndIndex > codeIndex)) {
/*  53 */       codeEndIndex += "[/code]".length();
/*  54 */       String nonCodeResult = bbcode2htmlExceptCodeTag(s.substring(
/*  55 */         nextStartPos, codeIndex));
/*  56 */       String codeResult = parseCode(s.substring(codeIndex, 
/*  57 */         codeEndIndex));
/*  58 */       result.append(nonCodeResult).append(codeResult);
/*  59 */       nextStartPos = codeEndIndex;
/*  60 */       codeIndex = s.indexOf("[code", codeEndIndex);
/*  61 */       codeEndIndex = codeIndex > -1 ? s.indexOf("[/code]", codeIndex) : 
/*  62 */         -1;
/*     */     }
/*     */ 
/*  65 */     if (nextStartPos > -1) {
/*  66 */       String nonCodeResult = bbcode2htmlExceptCodeTag(s
/*  67 */         .substring(nextStartPos));
/*  68 */       result.append(nonCodeResult);
/*     */     }
/*  70 */     return result.toString();
/*     */   }
/*     */ 
/*     */   private String parseCode(String text)
/*     */   {
/*  75 */     for (Iterator iter = getBbList().iterator(); iter.hasNext(); ) {
/*  76 */       Bbcode bb = (Bbcode)iter.next();
/*  77 */       if (bb.getTagName().startsWith("code")) {
/*  78 */         Matcher matcher = Pattern.compile(bb.getRegex()).matcher(text);
/*  79 */         StringBuffer sb = new StringBuffer(text);
/*  80 */         while (matcher.find()) {
/*  81 */           StringBuilder lang = null;
/*  82 */           StringBuilder contents = null;
/*  83 */           if ("code".equals(bb.getTagName())) {
/*  84 */             contents = new StringBuilder(matcher.group(1));
/*     */           } else {
/*  86 */             lang = new StringBuilder(matcher.group(1));
/*  87 */             contents = new StringBuilder(matcher.group(2));
/*     */           }
/*  89 */           StringBeanUtils.replace(contents, "<br /> ", "\n");
/*     */ 
/*  91 */           StringBeanUtils.replace(contents, "<", "&lt;");
/*  92 */           StringBeanUtils.replace(contents, ">", "&gt;");
/*     */ 
/*  97 */           StringBuffer replace = new StringBuffer(bb.getReplace());
/*  98 */           int index = replace.indexOf("$1");
/*  99 */           if ("code".equals(bb.getTagName())) {
/* 100 */             if (index > -1) {
/* 101 */               replace.replace(index, index + 2, 
/* 102 */                 contents.toString());
/*     */             }
/* 104 */             index = sb.indexOf("[code]");
/*     */           } else {
/* 106 */             if (index > -1) {
/* 107 */               replace.replace(index, index + 2, lang.toString());
/*     */             }
/* 109 */             index = replace.indexOf("$2");
/* 110 */             if (index > -1) {
/* 111 */               replace.replace(index, index + 2, 
/* 112 */                 contents.toString());
/*     */             }
/* 114 */             index = sb.indexOf("[code=");
/*     */           }
/* 116 */           int lastIndex = sb.indexOf("[/code]", index) + 
/* 117 */             "[/code]".length();
/*     */ 
/* 119 */           if (lastIndex > index) {
/* 120 */             sb.replace(index, lastIndex, replace.toString());
/*     */           }
/*     */         }
/* 123 */         text = sb.toString();
/*     */       }
/*     */     }
/* 126 */     return text;
/*     */   }
/*     */ 
/*     */   public String bbcode2htmlExceptCodeTag(String text) {
/* 130 */     if (text == null) {
/* 131 */       return text;
/*     */     }
/*     */ 
/* 134 */     if ((text.indexOf('[') > -1) && (text.indexOf(']') > -1)) {
/* 135 */       for (Iterator iter = getBbList().iterator(); iter.hasNext(); ) {
/* 136 */         Bbcode bb = (Bbcode)iter.next();
/* 137 */         if (!bb.getTagName().startsWith("code")) {
/* 138 */           text = text.replaceAll(bb.getRegex(), bb.getReplace());
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 143 */     text = parseDefaultRequiredBBCode(text);
/*     */ 
/* 145 */     return text;
/*     */   }
/*     */ 
/*     */   public String parseDefaultRequiredBBCode(String text) {
/* 149 */     Collection list = getAlwaysProcessList();
/*     */ 
/* 151 */     for (Iterator iter = list.iterator(); iter.hasNext(); ) {
/* 152 */       Bbcode bb = (Bbcode)iter.next();
/* 153 */       text = text.replaceAll(bb.getRegex(), bb.getReplace());
/*     */     }
/*     */ 
/* 156 */     return text;
/*     */   }
/*     */ 
/*     */   public void addBb(Bbcode bb) {
/* 160 */     if (bb.alwaysProcess())
/* 161 */       this.alwaysProcessMap.put(bb.getTagName(), bb);
/*     */     else
/* 163 */       this.bbMap.put(bb.getTagName(), bb);
/*     */   }
/*     */ 
/*     */   public Collection<Bbcode> getBbList()
/*     */   {
/* 168 */     return this.bbMap.values();
/*     */   }
/*     */ 
/*     */   public Collection<Bbcode> getAlwaysProcessList() {
/* 172 */     return this.alwaysProcessMap.values();
/*     */   }
/*     */ 
/*     */   public Bbcode findByName(String tagName) {
/* 176 */     return (Bbcode)this.bbMap.get(tagName);
/*     */   }
/*     */ 
/*     */   public void startElement(String uri, String localName, String tag, Attributes attrs)
/*     */   {
/* 181 */     if (tag.equals("match")) {
/* 182 */       this.sb = new StringBuffer();
/* 183 */       this.bb = new Bbcode();
/*     */ 
/* 185 */       String tagName = attrs.getValue("name");
/* 186 */       if (tagName != null) {
/* 187 */         this.bb.setTagName(tagName);
/*     */       }
/*     */ 
/* 191 */       String removeQuotes = attrs.getValue("removeQuotes");
/* 192 */       if ((removeQuotes != null) && (removeQuotes.equals("true"))) {
/* 193 */         this.bb.enableRemoveQuotes();
/*     */       }
/*     */ 
/* 196 */       String alwaysProcess = attrs.getValue("alwaysProcess");
/* 197 */       if ((alwaysProcess != null) && ("true".equals(alwaysProcess))) {
/* 198 */         this.bb.enableAlwaysProcess();
/*     */       }
/*     */     }
/*     */ 
/* 202 */     this.tagName = tag;
/*     */   }
/*     */ 
/*     */   public void endElement(String uri, String localName, String tag) {
/* 206 */     if (tag.equals("match")) {
/* 207 */       addBb(this.bb);
/* 208 */     } else if (this.tagName.equals("replace")) {
/* 209 */       this.bb.setReplace(this.sb.toString().trim());
/* 210 */       this.sb.delete(0, this.sb.length());
/* 211 */     } else if (this.tagName.equals("regex")) {
/* 212 */       this.bb.setRegex(this.sb.toString().trim());
/* 213 */       this.sb.delete(0, this.sb.length());
/*     */     }
/*     */ 
/* 216 */     this.tagName = "";
/*     */   }
/*     */ 
/*     */   public void characters(char[] ch, int start, int length) {
/* 220 */     if ((this.tagName.equals("replace")) || (this.tagName.equals("regex")))
/* 221 */       this.sb.append(ch, start, length);
/*     */   }
/*     */ 
/*     */   public void error(SAXParseException exception) throws SAXException {
/* 225 */     throw exception;
/*     */   }
/*     */ 
/*     */   public void afterPropertiesSet() throws Exception {
/* 229 */     SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
/* 230 */     parser.parse(this.configLocation.getInputStream(), this);
/* 231 */     handler = this;
/*     */   }
/*     */ 
/*     */   public static String toHtml(String s)
/*     */   {
/* 237 */     if (handler == null) {
/* 238 */       throw new RuntimeException("BbcodeHandler not prepared!");
/*     */     }
/* 240 */     return handler.bbcode2html(s);
/*     */   }
/*     */ 
/*     */   public Resource getConfigLocation()
/*     */   {
/* 246 */     return this.configLocation;
/*     */   }
/*     */ 
/*     */   public void setConfigLocation(Resource configLocation) {
/* 250 */     this.configLocation = configLocation;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.utils.code.BbcodeHandler
 * JD-Core Version:    0.6.1
 */