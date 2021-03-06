/*
 * Copyright (C) 2013,2014 The Cat Hive Developers.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cathive.fx.cdi;

import javax.enterprise.inject.spi.CDI;

import com.cathive.fx.inject.core.FXMLComponentBuilder;

/**
 * Component builder for CDI-managed components.
 *
 * @author Benjamin P. Jung
 */
public class CdiFXMLComponentBuilder<T> extends FXMLComponentBuilder<T> {

    /**
     * Default constructor.
     * @param componentClass
     *         Class to use when constructing new component instances.
     */
    CdiFXMLComponentBuilder(Class<T> componentClass) {
        super(componentClass);
    }

    @Override
    protected T getInstance(Class<T> clazz) {
        return CDI.current().select(clazz).get();
    }

    @Override
    public String toString() {
        return String.format("[CDI-aware] %s", super.toString());
    }

}
