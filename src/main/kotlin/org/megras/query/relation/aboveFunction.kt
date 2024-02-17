package org.megras.query.relation

import org.megras.data.schema.MeGraS
import org.megras.data.graph.QuadValue
import org.megras.graphstore.QuadSet
import org.megras.query.QueryUtil
import org.megras.query.QueryUtil.BoundsResult
import org.megras.segmentation.Bounds


fun aboveFunction(s: QuadValue?, o: QuadValue?, quads: QuadSet):QuadSet {
    val result: BoundsResult = QueryUtil.getBounds(o!!, quads)
    val originBounds = result.originBounds
    val boundsSet = result.boundsSet
    val resultBounds : MutableList<QuadValue> = mutableListOf()
    val originMaxY = Bounds(originBounds.firstOrNull()?.`object`.toString()).getMaxY()
    boundsSet.forEach{
        val thisMinY = Bounds(it.`object`.toString()).getMinY()
        if (thisMinY > originMaxY){
            resultBounds.add(it.`object`)
        }
    }
    val resultSet = boundsSet.filter(null, listOf(MeGraS.SEGMENT_BOUNDS.uri), resultBounds)
    return resultSet
}