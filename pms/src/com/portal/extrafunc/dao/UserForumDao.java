package com.portal.extrafunc.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.extrafunc.entity.UserForum;
import org.springframework.data.domain.Page;

public abstract interface UserForumDao extends QueryDao<UserForum>
{
  public abstract Page<UserForum> getPage(int paramInt1, int paramInt2);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.UserForumDao
 * JD-Core Version:    0.6.1
 */