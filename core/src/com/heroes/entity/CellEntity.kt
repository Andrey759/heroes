package com.heroes.entity

import com.badlogic.ashley.core.Entity
import com.heroes.component.RowColComponent

class CellEntity : Entity {

    constructor(x: Int, y: Int) {
        super.add(RowColComponent(x, y))
    }
}