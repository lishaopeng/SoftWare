package com.portal.extrafunc.action.cache;

import java.util.Date;

public abstract interface PostsCheckCache
{
  public abstract void updateCheck(String paramString);

  public abstract Date postsTime(String paramString);

  public abstract void refreshCheck();

  public abstract long getInterval();
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.cache.PostsCheckCache
 * JD-Core Version:    0.6.1
 */