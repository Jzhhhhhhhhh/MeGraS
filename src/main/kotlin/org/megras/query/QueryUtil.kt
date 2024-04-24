package org.megras.query

import org.megras.data.graph.QuadValue
import org.megras.data.schema.MeGraS
import org.megras.graphstore.QuadSet

object QueryUtil{

    data class BoundsResult(val originBounds: QuadSet, val boundsSet: QuadSet)

    fun getBounds(o: QuadValue, quadset: QuadSet, quads: QuadSet): BoundsResult {
        val originMedia = quads.filter(listOf(o), listOf(QuadValue.of(MeGraS.SEGMENT_OF.uri)) ,null).firstOrNull()?.`object`
        val segmentSet = quads.filter(null, listOf(QuadValue.of(MeGraS.SEGMENT_OF.uri)), listOf(originMedia!!))
        val moreBoundsSet = quads.filter(segmentSet.map { it.subject }, listOf(QuadValue.of(MeGraS.SEGMENT_BOUNDS.uri)), null)
        val lessBoundsSet = quads.filter(quadset.map { it.subject }, listOf(QuadValue.of(MeGraS.SEGMENT_BOUNDS.uri)), null)
        val boundsSet = moreBoundsSet.filter(lessBoundsSet.map { it.subject }, listOf(QuadValue.of(MeGraS.SEGMENT_BOUNDS.uri)), null)
        val originBounds = quads.filter(listOf(o), listOf(QuadValue.of(MeGraS.SEGMENT_BOUNDS.uri)), null)
        return BoundsResult(originBounds, boundsSet)
    }
}