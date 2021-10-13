package com.igortullio.email.core.port;

import com.igortullio.email.core.domain.Email;

public interface EmailSendPort {

    void send(Email email);

}
