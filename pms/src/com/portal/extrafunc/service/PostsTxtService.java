package com.portal.extrafunc.service;

import com.portal.extrafunc.entity.Posts;
import com.portal.extrafunc.entity.PostsTxt;
import org.springframework.data.domain.Page;

public abstract interface PostsTxtService
{
  public abstract Page<PostsTxt> getPage(int paramInt1, int paramInt2);

  public abstract PostsTxt findById(Integer paramInteger);

  public abstract PostsTxt save(String paramString, Posts paramPosts);

  public abstract PostsTxt update(Integer paramInteger, String paramString);

  public abstract PostsTxt deleteById(Integer paramInteger);

  public abstract PostsTxt[] deleteByIds(Integer[] paramArrayOfInteger);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.service.PostsTxtService
 * JD-Core Version:    0.6.1
 */