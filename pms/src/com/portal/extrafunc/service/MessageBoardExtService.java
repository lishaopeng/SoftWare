package com.portal.extrafunc.service;

import com.portal.extrafunc.entity.MessageBoard;
import com.portal.extrafunc.entity.MessageBoardExt;
import org.springframework.data.domain.Page;

public abstract interface MessageBoardExtService
{
  public abstract Page<MessageBoardExt> getPage(int paramInt1, int paramInt2);

  public abstract MessageBoardExt findById(Integer paramInteger);

  public abstract MessageBoardExt save(MessageBoard paramMessageBoard, MessageBoardExt paramMessageBoardExt);

  public abstract MessageBoardExt update(MessageBoardExt paramMessageBoardExt);

  public abstract MessageBoardExt deleteById(Integer paramInteger);

  public abstract MessageBoardExt[] deleteByIds(Integer[] paramArrayOfInteger);
}

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.service.MessageBoardExtService
 * JD-Core Version:    0.6.1
 */