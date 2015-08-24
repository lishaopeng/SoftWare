package com.portal.usermgr.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.usermgr.entity.User;

public abstract interface UserDao extends QueryDao<User>
{
  public abstract int getAllUserCount();

  public abstract User findByUsername(String paramString);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.dao.UserDao
 * JD-Core Version:    0.6.1
 */