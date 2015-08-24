package com.portal.extrafunc.dao;

import com.javapms.basic.hibernate4.QueryDao;
import com.portal.extrafunc.entity.Questionnaire;
import org.springframework.data.domain.Page;

public abstract interface QuestionnaireDao extends QueryDao<Questionnaire>
{
  public abstract Page<Questionnaire> getPage(Integer paramInteger, boolean paramBoolean, int paramInt1, int paramInt2);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.QuestionnaireDao
 * JD-Core Version:    0.6.1
 */