package com.portal.doccenter.service;

import com.portal.doccenter.entity.Article;
import com.portal.doccenter.entity.ArticleTxt;

public abstract interface ArticleTxtService
{
  public abstract ArticleTxt save(ArticleTxt paramArticleTxt, Article paramArticle);

  public abstract ArticleTxt update(ArticleTxt paramArticleTxt, Article paramArticle);

  public abstract int delDocByInputUser(Integer paramInteger);

  public abstract int delDocBySite(Integer paramInteger);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.service.ArticleTxtService
 * JD-Core Version:    0.6.1
 */