package com.tubecentric.webapplication.register.service;

import com.tubecentric.webapplication.register.service.model.QuestionnairePersistRequest;
import com.tubecentric.webapplication.register.service.model.QuestionnairePersistResponse;
import com.tubecentric.webapplication.register.service.model.RegistrationPersistRequest;
import com.tubecentric.webapplication.register.service.model.RegistrationPersistResponse;

public interface IRegistrationService {

    RegistrationPersistResponse persistRegistration(RegistrationPersistRequest request);

    QuestionnairePersistResponse persistQuestionnaire(QuestionnairePersistRequest request);
}
