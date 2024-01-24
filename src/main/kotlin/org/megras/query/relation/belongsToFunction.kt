package org.megras.query.relation

import org.megras.data.graph.QuadValue
import org.megras.data.schema.MeGraS
import org.megras.graphstore.QuadSet
import org.megras.query.QueryUtil
import org.megras.segmentation.Bounds

fun belongsToFunction (o: QuadValue, quads: QuadSet): QuadSet {
    val result: QueryUtil.BoundsResult = QueryUtil.getBounds(o, quads)
    val originBounds = result.originBounds
    val boundsSet = result.boundsSet
    val resultBounds : MutableList<QuadValue> = mutableListOf()
    val originBound = Bounds(originBounds.firstOrNull()?.`object`.toString())
    boundsSet.forEach{
        val thisBound = Bounds(it.`object`.toString())
        if (originBound.contains(thisBound)){
            resultBounds.add(it.`object`)
        }
    }
    val resultSet = boundsSet.filter(null, listOf(MeGraS.SEGMENT_BOUNDS.uri), resultBounds)
    return resultSet

}