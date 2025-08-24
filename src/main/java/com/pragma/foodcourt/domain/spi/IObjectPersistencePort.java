package com.pragma.foodcourt.domain.spi;

import java.util.List;

import com.pragma.foodcourt.domain.model.ObjectModel;

public interface IObjectPersistencePort {
    ObjectModel saveObject(ObjectModel objectModel);

    List<ObjectModel> getAllObjects();
}