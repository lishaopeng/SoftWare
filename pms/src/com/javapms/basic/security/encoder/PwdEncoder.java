package com.javapms.basic.security.encoder;

public abstract interface PwdEncoder
{
  public abstract String encodePassword(String paramString);

  public abstract String encodePassword(String paramString1, String paramString2);

  public abstract boolean isPasswordValid(String paramString1, String paramString2);

  public abstract boolean isPasswordValid(String paramString1, String paramString2, String paramString3);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.javapms.basic.security.encoder.PwdEncoder
 * JD-Core Version:    0.6.1
 */