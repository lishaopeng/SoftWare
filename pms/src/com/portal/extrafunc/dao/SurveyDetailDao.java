package com.portal.extrafunc.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.extrafunc.entity.SurveyDetail;
import org.springframework.data.domain.Page;

public abstract interface SurveyDetailDao extends QueryDao<SurveyDetail>
{
  public abstract Page<SurveyDetail> getPage(Integer paramInteger, int paramInt1, int paramInt2);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.SurveyDetailDao
 * JD-Core Version:    0.6.1
 */