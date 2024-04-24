package org.megras.query.relation

import org.megras.data.graph.QuadValue
import org.megras.data.schema.MeGraS
import org.megras.graphstore.QuadSet
import org.megras.query.QueryUtil
import org.megras.segmentation.Bounds

fun timeContainFunction(o: QuadValue, quadset: QuadSet, quads: QuadSet): QuadSet {
    val result: QueryUtil.BoundsResult = QueryUtil.getBounds(o, quadset, quads)
    val originBounds = result.originBounds
    val boundsSet = result.boundsSet
    val resultBounds : MutableList<QuadValue> = mutableListOf()
    println((originBounds.firstOrNull()?.`object`))
    val originMinT = Bounds(originBounds.firstOrNull()?.`object`.toString()).getMinT()
    val originMaxT = Bounds(originBounds.firstOrNull()?.`object`.toString()).getMaxT()
    boundsSet.forEach{
        val thisMaxT = Bounds(it.`object`.toString()).getMaxT()
        val thisMinT = Bounds(it.`object`.toString()).getMinT()
        if ((thisMaxT >= originMaxT && thisMinT <= originMinT)||(thisMaxT <= originMaxT && thisMinT >= originMinT)){
            resultBounds.add(it.`object`)
        }
    }
    val resultSet = boundsSet.filter(null, listOf(MeGraS.SEGMENT_BOUNDS.uri), resultBounds)
    return resultSet
}