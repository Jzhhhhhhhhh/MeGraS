package org.megras.query.relation

import org.megras.data.graph.QuadValue
import org.megras.data.schema.MeGraS
import org.megras.graphstore.QuadSet
import org.megras.query.QueryUtil
import org.megras.segmentation.Bounds

fun sizeLargerFunction(o: QuadValue, quads: QuadSet): QuadSet {
    val result: QueryUtil.BoundsResult = QueryUtil.getBounds(o, quads)
    val originBounds = result.originBounds
    val boundsSet = result.boundsSet
    val resultBounds : MutableList<QuadValue> = mutableListOf()
    val originXDimension = Bounds(originBounds.firstOrNull()?.`object`.toString()).getXDimension()
    val originYDimension = Bounds(originBounds.firstOrNull()?.`object`.toString()).getYDimension()
    boundsSet.forEach{
        val thisXDimension = Bounds(it.`object`.toString()).getXDimension()
        val thisYDimension = Bounds(it.`object`.toString()).getYDimension()
        //t dimension
        if (thisYDimension * thisXDimension > originYDimension * originXDimension){
            resultBounds.add(it.`object`)
        }
    }
    val resultSet = boundsSet.filter(null, listOf(MeGraS.SEGMENT_BOUNDS.uri), resultBounds)
    return resultSet
}