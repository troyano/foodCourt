package com.pragma.foodcourt.domain.api;

import java.util.List;

import com.pragma.foodcourt.domain.model.ObjectModel;

public interface IObjectServicePort {

    void saveObject(ObjectModel objectModel);

    List<ObjectModel> getAllObjects();
}