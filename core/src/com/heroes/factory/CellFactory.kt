package com.heroes.factory

import com.badlogic.ashley.core.Entity
import com.heroes.component.RowColComponent
import com.heroes.component.link.*
import com.heroes.component.point.CBotLeftPoint
import com.heroes.component.point.CBotPoint
import com.heroes.component.point.CBotRightPoint
import com.heroes.component.point.CTopLeftPoint
import com.heroes.component.point.CTopPoint
import com.heroes.component.point.CTopRightPoint

class CellFactory {
    companion object {

        fun getCellList(rowNum: Int, colNum: Int): List<Entity> {
            val array = Array(rowNum) { row ->
                Array(colNum) { col ->
                    Entity()
                        .add(RowColComponent(row, col))
                        .add(CBotLeftPoint(0F, 0F))
                        .add(CBotPoint(0F, 0F))
                        .add(CBotRightPoint(0F, 0F))
                        .add(CTopLeftPoint(0F, 0F))
                        .add(CTopPoint(0F, 0F))
                        .add(CTopRightPoint(0F, 0F))
                }
            }
            for (row in 0 until rowNum) {
                for (col in 1 until colNum - 1) {
                    array[row][col].add(CLeftLink(array[row][col - 1]))
                    array[row][col - 1].add(CRightLink(array[row][col]))
                }
            }
            for (row in 1 until rowNum) {
                for (col in 0 until colNum) {
                    val startsFromLeft = row % 2 == 0
                    if (startsFromLeft) {
                        if (col > 0) {
                            array[row][col].add(CBotLeftLink(array[row - 1][col - 1]))
                            array[row - 1][col - 1].add(CTopRightLink(array[row][col]))
                        }
                        array[row][col].add(CBotRightLink(array[row - 1][col]))
                        array[row - 1][col].add(CTopLeftLink(array[row][col]))
                    } else {
                        array[row][col].add(CBotLeftLink(array[row - 1][col]))
                        array[row - 1][col].add(CTopRightLink(array[row][col]))
                        if (col < colNum - 1) {
                            array[row][col].add(CBotRightLink(array[row - 1][col]))
                            array[row - 1][col].add(CTopLeftLink(array[row][col]))
                        }
                    }
                }
            }
            return array.flatten()
        }
    }
}