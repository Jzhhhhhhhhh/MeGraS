package org.megras.query.relation

import org.megras.data.graph.QuadValue
import org.megras.data.schema.MeGraS
import org.megras.graphstore.QuadSet
import org.megras.query.QueryUtil
import org.megras.segmentation.Bounds

fun rightBelowFunction(o: QuadValue, quads: QuadSet): QuadSet {
    val result: QueryUtil.BoundsResult = QueryUtil.getBounds(o, quads)
    val originBounds = result.originBounds
    val boundsSet = result.boundsSet
    val resultBounds : MutableList<QuadValue> = mutableListOf()
    val originMinY = Bounds(originBounds.firstOrNull()?.`object`.toString()).getMinY()
    val originMaxX = Bounds(originBounds.firstOrNull()?.`object`.toString()).getMaxX()
    boundsSet.forEach{
        val thisMaxY = Bounds(it.`object`.toString()).getMaxY()
        val thisMinX = Bounds(it.`object`.toString()).getMinX()
        if (thisMaxY < originMinY && thisMinX > originMaxX){
            resultBounds.add(it.`object`)
        }
    }
    val resultSet = boundsSet.filter(null, listOf(MeGraS.SEGMENT_BOUNDS.uri), resultBounds)
    return resultSet
}