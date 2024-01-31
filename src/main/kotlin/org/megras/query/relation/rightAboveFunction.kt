package org.megras.query.relation

import org.megras.data.graph.QuadValue
import org.megras.data.schema.MeGraS
import org.megras.graphstore.QuadSet
import org.megras.query.QueryUtil
import org.megras.segmentation.Bounds

fun rightAboveFunction(o: QuadValue, quads: QuadSet): QuadSet {
    val result: QueryUtil.BoundsResult = QueryUtil.getBounds(o, quads)
    val originBounds = result.originBounds
    val boundsSet = result.boundsSet
    val resultBounds : MutableList<QuadValue> = mutableListOf()
    val originMaxY = Bounds(originBounds.firstOrNull()?.`object`.toString()).getMaxY()
    val originMaxX = Bounds(originBounds.firstOrNull()?.`object`.toString()).getMaxX()
    boundsSet.forEach{
        val thisMinY = Bounds(it.`object`.toString()).getMinY()
        val thisMinX = Bounds(it.`object`.toString()).getMinX()
        if (thisMinY > originMaxY && thisMinX > originMaxX){
            resultBounds.add(it.`object`)
        }
    }
    val resultSet = boundsSet.filter(null, listOf(MeGraS.SEGMENT_BOUNDS.uri), resultBounds)
    return resultSet
}