package com.profCom.service.Documentation;

import com.profCom.entity.Documentation;

import java.util.List;

/**
 * Created by VolgiNN on 08.12.2016.
 */
public interface DocumentationService {
    List<Documentation> getAll();
    Documentation getByID(String id);
    Documentation save(Documentation documentation);
    void remove(String id);
}