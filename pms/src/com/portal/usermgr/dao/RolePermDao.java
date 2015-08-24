package com.portal.usermgr.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.usermgr.entity.RolePerm;
import org.springframework.data.domain.Page;

public abstract interface RolePermDao extends QueryDao<RolePerm>
{
  public abstract Page<RolePerm> getPage(int paramInt1, int paramInt2);

  public abstract int deleteBySiteId(Integer paramInteger);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.dao.RolePermDao
 * JD-Core Version:    0.6.1
 */