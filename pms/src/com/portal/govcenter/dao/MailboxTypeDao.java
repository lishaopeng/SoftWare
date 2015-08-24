package com.portal.govcenter.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.govcenter.entity.MailboxType;
import java.util.List;

public abstract interface MailboxTypeDao extends QueryDao<MailboxType>
{
  public abstract List<MailboxType> getList(Integer paramInteger);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.govcenter.dao.MailboxTypeDao
 * JD-Core Version:    0.6.1
 */