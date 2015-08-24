package com.portal.govcenter.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.govcenter.entity.Mailbox;
import org.springframework.data.domain.Page;

public abstract interface MailboxDao extends QueryDao<Mailbox>
{
  public abstract Page<Mailbox> getPage(String paramString, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, int paramInt1, int paramInt2);

  public abstract Page<Mailbox> getPageByTag(String paramString, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, int paramInt1, int paramInt2);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.govcenter.dao.MailboxDao
 * JD-Core Version:    0.6.1
 */