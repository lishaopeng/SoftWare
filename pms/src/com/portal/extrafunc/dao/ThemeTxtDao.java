package com.portal.extrafunc.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.extrafunc.entity.ThemeTxt;
import org.springframework.data.domain.Page;

public abstract interface ThemeTxtDao extends QueryDao<ThemeTxt>
{
  public abstract Page<ThemeTxt> getPage(int paramInt1, int paramInt2);

  public abstract int deleteByForumId(Integer paramInteger);

  public abstract int deleteByUserId(Integer paramInteger);

  public abstract int deleteBySiteId(Integer paramInteger);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.ThemeTxtDao
 * JD-Core Version:    0.6.1
 */