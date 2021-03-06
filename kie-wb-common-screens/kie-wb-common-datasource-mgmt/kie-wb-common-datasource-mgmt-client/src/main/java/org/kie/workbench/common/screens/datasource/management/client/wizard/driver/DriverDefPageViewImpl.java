/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.workbench.common.screens.datasource.management.client.wizard.driver;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.Composite;
import org.gwtbootstrap3.client.ui.gwt.FlowPanel;
import org.jboss.errai.ui.client.local.spi.TranslationService;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.kie.workbench.common.screens.datasource.management.client.editor.driver.DriverDefMainPanel;
import org.kie.workbench.common.screens.datasource.management.client.resources.i18n.DataSourceManagementConstants;

@Dependent
@Templated
public class DriverDefPageViewImpl
        extends Composite
        implements DriverDefPageView {

    @Inject
    @DataField( "main-panel-container" )
    private FlowPanel mainPanelContainer;

    private DriverDefPageView.Presenter presenter;

    @Inject
    private TranslationService translationService;

    public DriverDefPageViewImpl() {
    }

    @Override
    public void init( Presenter presenter ) {
        this.presenter = presenter;
    }

    @Override
    public void setMainPanel( DriverDefMainPanel mainPanel ) {
        mainPanelContainer.add( mainPanel );
    }

    @Override
    public String getPageTitle() {
        return translationService.getTranslation( DataSourceManagementConstants.DriverDefDefPageViewImpl_pageTitle );
    }
}
