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

import com.cathive.fx.cdi.CdiApplication;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.util.ResourceBundle;

/**
 * Contacts app
 * @author Benjamin P. Jung
 */
@Named("app")
@ApplicationScoped
public class ContactsApp extends CdiApplication {

    @Inject
    private EntityManager entityManager;

    @Inject
    private RootPane rootPane;

    @Inject
    private ResourceBundle messages;

    @Override
    public void start(final Stage primaryStage) throws Exception {

        primaryStage.setTitle(messages.getString(Messages.APP_TITLE));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/cathive/fx/apps/contacts/icons/x-office-address-book.png")));

        final Scene scene = new Scene(this.rootPane);
        scene.getStylesheets().add(getClass().getResource("ContactsApp.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String... args) {
        Application.launch(ContactsApp.class, args);
    }

}
