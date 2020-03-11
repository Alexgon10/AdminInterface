package ru.goncharov.application.entities.cadElements;

import ru.goncharov.application.entities.Entity;

/**
 * Created on 28.02.2020.
 */
public interface CadElement extends Entity {

    boolean isSelected1() ;

    void setSelected1(boolean selected1) ;

    boolean isSelected2();

    void setSelected2(boolean selected2);

    String getName() ;

    void setName(String ptkName);
}
