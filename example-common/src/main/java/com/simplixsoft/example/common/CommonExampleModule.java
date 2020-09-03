package com.simplixsoft.example.common;

import com.google.inject.Provides;
import dev.simplix.core.common.aop.AbstractSimplixModule;
import dev.simplix.core.common.aop.ApplicationModule;
import dev.simplix.core.common.aop.Private;
import dev.simplix.core.common.i18n.LocalizationManager;
import dev.simplix.core.common.i18n.LocalizationManagerFactory;

@ApplicationModule("SimplixExample")
public class CommonExampleModule extends AbstractSimplixModule {

  @Provides
  @Private
  public LocalizationManager localizationManager(LocalizationManagerFactory factory) {
    return factory.create("/lang/", ExampleApplication.class);
  }

}
