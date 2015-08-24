package com.portal.doccenter.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.doccenter.entity.FlowDetail;
import org.springframework.data.domain.Page;

public abstract interface FlowDetailDao extends QueryDao<FlowDetail>
{
  public abstract Page<FlowDetail> getPage(int paramInt1, int paramInt2);

  public abstract FlowDetail getLastFlowDetail(Integer paramInteger);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.dao.FlowDetailDao
 * JD-Core Version:    0.6.1
 */