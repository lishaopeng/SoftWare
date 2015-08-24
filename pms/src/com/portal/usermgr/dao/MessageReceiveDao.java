package com.portal.usermgr.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.usermgr.entity.MessageReceive;
import org.springframework.data.domain.Page;

public abstract interface MessageReceiveDao extends QueryDao<MessageReceive>
{
  public abstract Page<MessageReceive> getPage(int paramInt1, int paramInt2);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.dao.MessageReceiveDao
 * JD-Core Version:    0.6.1
 */