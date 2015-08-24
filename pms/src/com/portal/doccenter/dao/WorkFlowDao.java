package com.portal.doccenter.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.doccenter.entity.WorkFlow;
import java.util.List;
import org.springframework.data.domain.Page;

public abstract interface WorkFlowDao extends QueryDao<WorkFlow>
{
  public abstract Page<WorkFlow> getPage(Integer paramInteger, String paramString1, String paramString2, int paramInt1, int paramInt2);

  public abstract List<WorkFlow> findByList(Integer paramInteger);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.dao.WorkFlowDao
 * JD-Core Version:    0.6.1
 */