package com.portal.doccenter.service;

import com.portal.doccenter.entity.Article;
import com.portal.doccenter.entity.ArticleExt;

public abstract interface ArticleExtService
{
  public abstract ArticleExt save(ArticleExt paramArticleExt, Article paramArticle);

  public abstract ArticleExt update(ArticleExt paramArticleExt);

  public abstract int delDocByInputUser(Integer paramInteger);

  public abstract int delDocBySite(Integer paramInteger);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.service.ArticleExtService
 * JD-Core Version:    0.6.1
 */