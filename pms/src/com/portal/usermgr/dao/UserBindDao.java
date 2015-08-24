package com.portal.usermgr.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.usermgr.entity.UserBind;

public abstract interface UserBindDao extends QueryDao<UserBind>
{
  public abstract UserBind getBindByUser(Integer paramInteger1, Integer paramInteger2);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.dao.UserBindDao
 * JD-Core Version:    0.6.1
 */