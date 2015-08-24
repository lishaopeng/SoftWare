/*     */ package com.javapms.basic.security.encoder;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;

/*     */ import org.apache.commons.codec.binary.Hex;
/*     */ 
/*     */ public class Md5PwdEncoder
/*     */   implements PwdEncoder
/*     */ {
/*     */   private String defaultSalt;
/*     */ 
/*     */   @Override
public String encodePassword(String rawPass)
/*     */   {
/*  17 */     return encodePassword(rawPass, this.defaultSalt);
/*     */   }
/*     */ 
/*     */   @Override
public String encodePassword(String rawPass, String salt) {
/*  21 */     String saltedPass = mergePasswordAndSalt(rawPass, salt, false);
/*  22 */     MessageDigest messageDigest = getMessageDigest();
		byte[] digest;
/*     */     try
/*     */     {
			/* 25 */ digest = messageDigest.digest(saltedPass
					.getBytes("UTF-8"));
/*     */     }
/*     */     catch (UnsupportedEncodingException e)
/*     */     {
/*  27 */       throw new IllegalStateException("UTF-8 not supported!");
/*     */     }
/*  29 */     return new String(Hex.encodeHex(digest));
/*     */   }
/*     */ 
/*     */   @Override
public boolean isPasswordValid(String encPass, String rawPass) {
/*  33 */     return isPasswordValid(encPass, rawPass, this.defaultSalt);
/*     */   }
/*     */ 
/*     */   @Override
public boolean isPasswordValid(String encPass, String rawPass, String salt) {
/*  37 */     if (encPass == null) {
/*  38 */       return false;
/*     */     }
/*  40 */     String pass2 = encodePassword(rawPass, salt);
/*  41 */     return encPass.equals(pass2);
/*     */   }
/*     */ 
/*     */   protected final MessageDigest getMessageDigest() {
/*  45 */     String algorithm = "MD5";
/*     */     try {
/*  47 */       return MessageDigest.getInstance(algorithm); } catch (NoSuchAlgorithmException e) {
/*     */     }
/*  49 */     throw new IllegalArgumentException("No such algorithm [" + 
/*  50 */       algorithm + "]");
/*     */   }
/*     */ 
/*     */   protected String mergePasswordAndSalt(String password, Object salt, boolean strict)
/*     */   {
/*  75 */     if (password == null) {
/*  76 */       password = "";
/*     */     }
/*  78 */     if ((strict) && (salt != null) && (
/*  79 */       (salt.toString().lastIndexOf("{") != -1) || 
/*  80 */       (salt.toString().lastIndexOf("}") != -1))) {
/*  81 */       throw new IllegalArgumentException(
/*  82 */         "Cannot use { or } in salt.toString()");
/*     */     }
/*     */ 
/*  85 */     if ((salt == null) || ("".equals(salt))) {
/*  86 */       return password;
/*     */     }
/*  88 */     return password + "{" + salt.toString() + "}";
/*     */   }
/*     */ 
/*     */   public String getDefaultSalt()
/*     */   {
/* 103 */     return this.defaultSalt;
/*     */   }
/*     */ 
/*     */   public void setDefaultSalt(String defaultSalt)
/*     */   {
/* 112 */     this.defaultSalt = defaultSalt;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.javapms.basic.security.encoder.Md5PwdEncoder
 * JD-Core Version:    0.6.1
 */