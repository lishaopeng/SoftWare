package com.portal.extrafunc.service;

import com.portal.extrafunc.entity.Questionnaire;
import com.portal.sysmgr.entity.Site;
import org.springframework.data.domain.Page;

public abstract interface QuestionnaireService
{
  public abstract Page<Questionnaire> getPage(Integer paramInteger, boolean paramBoolean, int paramInt1, int paramInt2);

  public abstract Questionnaire findById(Integer paramInteger);

  public abstract Questionnaire save(Questionnaire paramQuestionnaire, Site paramSite);

  public abstract Questionnaire update(Questionnaire paramQuestionnaire);

  public abstract Questionnaire deleteById(Integer paramInteger);

  public abstract Questionnaire[] deleteByIds(Integer[] paramArrayOfInteger);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.service.QuestionnaireService
 * JD-Core Version:    0.6.1
 */