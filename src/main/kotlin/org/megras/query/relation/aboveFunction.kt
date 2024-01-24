package org.megras.query.relation

import org.megras.data.schema.MeGraS
import org.megras.data.graph.QuadValue
import org.megras.graphstore.QuadSet
import org.megras.segmentation.Bounds


fun aboveFunction(o: QuadValue, quads: QuadSet):QuadSet {
    val originMedia = quads.filter(listOf(o), listOf(QuadValue.of(MeGraS.SEGMENT_OF.uri)) ,null).firstOrNull()?.`object`
    val segmentSet = quads.filter(null, listOf(QuadValue.of(MeGraS.SEGMENT_OF.uri)), listOf(originMedia!!))
    val boundsSet = quads.filter(segmentSet.map { it.subject }, listOf(QuadValue.of(MeGraS.SEGMENT_BOUNDS.uri)), null)
    val originBounds = quads.filter(listOf(o), listOf(QuadValue.of(MeGraS.SEGMENT_BOUNDS.uri)), null)
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