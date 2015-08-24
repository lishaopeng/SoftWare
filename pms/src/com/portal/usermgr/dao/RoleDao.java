package com.portal.usermgr.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.usermgr.entity.Role;
import java.util.List;
import org.springframework.data.domain.Page;

public abstract interface RoleDao extends QueryDao<Role>
{
  public abstract Page<Role> getPage(String paramString1, Integer paramInteger, String paramString2, String paramString3, int paramInt1, int paramInt2);

  public abstract List<Role> getListBySite(Integer paramInteger);

  public abstract int deleteBySiteId(Integer paramInteger);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.dao.RoleDao
 * JD-Core Version:    0.6.1
 */