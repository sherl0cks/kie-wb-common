/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.workbench.common.stunner.core.client.definition.adapter.binding;

import org.kie.workbench.common.stunner.core.definition.adapter.binding.BindableAdapterUtils;
import org.kie.workbench.common.stunner.core.definition.adapter.binding.BindableDefinitionSetAdapter;
import org.kie.workbench.common.stunner.core.factory.graph.ElementFactory;

import java.util.Map;
import java.util.Set;

class ClientBindableDefinitionSetAdapter extends AbstractClientBindableAdapter<Object> implements BindableDefinitionSetAdapter<Object> {

    private Map<Class, String> propertyDescriptionFieldNames;
    private Map<Class, Class> graphFactoryTypes;
    private Set<String> definitionIds;

    @Override
    public void setBindings( final Map<Class, String> propertyDescriptionFieldNames,
                             final Map<Class, Class> graphFactoryTypes,
                             final Set<String> definitionIds ) {
        this.propertyDescriptionFieldNames = propertyDescriptionFieldNames;
        this.graphFactoryTypes = graphFactoryTypes;
        this.definitionIds = definitionIds;
    }

    @Override
    public String getId( final Object pojo ) {
        String _id = BindableAdapterUtils.getDefinitionSetId( pojo.getClass() );
        // Avoid weld def class names issues.
        if ( _id.contains( "$" ) ) {
            _id = _id.substring( 0, _id.indexOf( "$" ) );
        }
        return _id;
    }

    @Override
    public String getDomain( final Object pojo ) {
        String n = pojo.getClass().getName();
        return n.substring( n.lastIndexOf( "." ) + 1, n.length() );
    }

    @Override
    public String getDescription( final Object pojo ) {
        return getProxiedValue( pojo, getPropertyDescriptionFieldNames().get( pojo.getClass() ) );
    }

    @Override
    public Set<String> getDefinitions( final Object pojo ) {
        return getDefinitionIds();
    }

    @Override
    @SuppressWarnings( "unchecked" )
    public Class<? extends ElementFactory> getGraphFactoryType( final Object pojo ) {
        return getGraphFactoryTypes().get( pojo.getClass() );
    }

    @Override
    public boolean accepts( final Class<?> pojoClass ) {
        if ( null != propertyDescriptionFieldNames ) {
            return getPropertyDescriptionFieldNames().containsKey( pojoClass );
        }
        return false;
    }

    private Map<Class, String> getPropertyDescriptionFieldNames() {
        return propertyDescriptionFieldNames;
    }

    private Map<Class, Class> getGraphFactoryTypes() {
        return graphFactoryTypes;
    }

    private Set<String> getDefinitionIds() {
        return definitionIds;
    }

}
