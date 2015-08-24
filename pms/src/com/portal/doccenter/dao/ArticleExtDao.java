package com.portal.doccenter.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.doccenter.entity.ArticleExt;

public abstract interface ArticleExtDao extends QueryDao<ArticleExt>
{
  public abstract int delDocByInputUser(Integer paramInteger);

  public abstract int delDocBySite(Integer paramInteger);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.dao.ArticleExtDao
 * JD-Core Version:    0.6.1
 */