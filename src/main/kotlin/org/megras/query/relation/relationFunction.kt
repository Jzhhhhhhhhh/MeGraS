package org.megras.query.relation

import org.megras.data.graph.QuadValue
import org.megras.data.schema.MeGraS
import org.megras.graphstore.QuadSet


//all other functions and not in the same media

fun relationFunction(s: QuadValue, o: QuadValue, quads: QuadSet): QuadSet {
    val sMedia = quads.filter(listOf(s), listOf(QuadValue.of(MeGraS.SEGMENT_BOUNDS.uri)), null)
    val oMedia = quads.filter(listOf(o), listOf(QuadValue.of(MeGraS.SEGMENT_BOUNDS.uri)), null)
    

    return quads
}