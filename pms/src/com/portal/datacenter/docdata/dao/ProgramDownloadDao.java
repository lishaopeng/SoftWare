package com.portal.datacenter.docdata.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.datacenter.docdata.entity.ProgramDownload;
import org.springframework.data.domain.Page;

public abstract interface ProgramDownloadDao extends QueryDao<ProgramDownload>
{
  public abstract Page<ProgramDownload> getPage(int paramInt1, int paramInt2);

  public abstract ProgramDownload findUnique();
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.docdata.dao.ProgramDownloadDao
 * JD-Core Version:    0.6.1
 */