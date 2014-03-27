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

package com.cathive.fx.apps.contacts.model;

import javafx.beans.property.*;
import javafx.scene.image.Image;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author Benjamin P. Jung
 */
@Entity
@Table(name = "contact")
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.CHAR)
@DiscriminatorValue(value = "?")
@Inheritance(strategy=InheritanceType.JOINED)
public class Contact implements Serializable {

    /** @see java.io.Serializable */
    private static final long serialVersionUID = 1L;

    // <editor-fold desc="Property: ID">
    public static final String ID_PROPERTY = "id";
    private final ReadOnlyLongWrapper id = new ReadOnlyLongWrapper(this, ID_PROPERTY);
    public ReadOnlyLongProperty idProperty() {
        return this.id;
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id.get();
    }
    public void setId(final Long id) {
        this.id.set(id);
    }
    // </editor-fold>

    // <editor-fold desc="Pseudo-property: display name">
    public static final String DISPLAY_NAME_PROPERTY = "displayName";
    protected final ReadOnlyStringWrapper displayName = new ReadOnlyStringWrapper(this, DISPLAY_NAME_PROPERTY);
    public ReadOnlyStringProperty displayNameProperty() {
        return this.displayName.getReadOnlyProperty();
    }
    @Transient
    public String getDisplayName() {
        return this.displayName.get();
    }
    // </editor-fold>

    /*public static final String PHOTO_PROPERTY = "photo";
    private final ObjectProperty<Image> photo = new SimpleObjectProperty<>(this, PHOTO_PROPERTY);
    public ObjectProperty photoProperty() { return this.photo; }
    public Image getPhoto() { return this.photo.get(); }
    public void setPhoto(final Image photo) { this.photo.set(photo); }*/


    public Contact() {
        super();
    }

    public static <T extends Contact> T create(final Class<T> contactType) {
        final T contact;
        try {
            contact = contactType.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
        return contact;
    }

}