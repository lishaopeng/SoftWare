package com.portal.usermgr.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.usermgr.entity.Group;
import java.util.List;

public abstract interface GroupDao extends QueryDao<Group>
{
  public abstract List<Group> getList(Integer paramInteger, String paramString1, String paramString2);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.dao.GroupDao
 * JD-Core Version:    0.6.1
 */