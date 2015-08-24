package com.portal.doccenter.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.doccenter.entity.Article;

public abstract interface ArticleQueryDao extends QueryDao<Article>
{
  public abstract int emptyArticlePage(String paramString);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.dao.ArticleQueryDao
 * JD-Core Version:    0.6.1
 */