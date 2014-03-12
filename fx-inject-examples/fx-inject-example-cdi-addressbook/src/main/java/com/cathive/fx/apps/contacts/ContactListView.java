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

package com.cathive.fx.apps.contacts;

import com.cathive.fx.apps.contacts.model.Person;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import javax.enterprise.inject.spi.CDI;

/**
 * List view for contacts.
 * @author Benjamin P. Jung
 */
public class ContactListView extends ListView<Person> {

    public ContactListView() {
        super();
        this.setCellFactory(listView -> {
            final ListCell<Person> listCell = new ListCell<>();
            final ContactListCell graphics = CDI.current().select(ContactListCell.class).get();
            graphics.personProperty().bind(listCell.itemProperty());
            listCell.setGraphic(graphics);
            return listCell;
        });
    }

}
