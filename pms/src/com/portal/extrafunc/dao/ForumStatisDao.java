package com.portal.extrafunc.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.extrafunc.entity.ForumStatis;
import org.springframework.data.domain.Page;

public abstract interface ForumStatisDao extends QueryDao<ForumStatis>
{
  public abstract Page<ForumStatis> getPage(int paramInt1, int paramInt2);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.ForumStatisDao
 * JD-Core Version:    0.6.1
 */