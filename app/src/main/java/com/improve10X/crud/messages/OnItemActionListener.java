package com.improve10X.crud.messages;

import com.improve10X.crud.messages.Message;

public interface OnItemActionListener {

    void onItemClicked(Message message);
    void onItemDelete(Message message);
    void onItemEdit(Message message);
}
