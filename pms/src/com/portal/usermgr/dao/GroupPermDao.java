package com.portal.usermgr.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.usermgr.entity.GroupPerm;
import org.springframework.data.domain.Page;

public abstract interface GroupPermDao extends QueryDao<GroupPerm>
{
  public abstract Page<GroupPerm> getPage(int paramInt1, int paramInt2);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.dao.GroupPermDao
 * JD-Core Version:    0.6.1
 */