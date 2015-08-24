package com.portal.usermgr.service;

import com.portal.usermgr.entity.Group;
import com.portal.usermgr.entity.GroupPerm;
import org.springframework.data.domain.Page;

public abstract interface GroupPermService
{
  public abstract Page<GroupPerm> getPage(int paramInt1, int paramInt2);

  public abstract GroupPerm findById(Integer paramInteger);

  public abstract GroupPerm save(Group paramGroup, GroupPerm paramGroupPerm);

  public abstract GroupPerm save(GroupPerm paramGroupPerm);

  public abstract GroupPerm update(GroupPerm paramGroupPerm);

  public abstract GroupPerm deleteById(Integer paramInteger);

  public abstract GroupPerm[] deleteByIds(Integer[] paramArrayOfInteger);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.service.GroupPermService
 * JD-Core Version:    0.6.1
 */