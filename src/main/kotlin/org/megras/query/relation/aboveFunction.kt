package org.megras.query.relation

import org.megras.data.graph.Quad
import org.megras.data.schema.MeGraS
import org.megras.data.graph.QuadValue
import org.megras.graphstore.QuadSet
import org.megras.lang.sparql.SparqlUtil.toQuadValue

fun aboveFunction(o: QuadValue, quads: QuadSet):QuadSet {
    val originMedia = quads.filter(listOf(o), listOf(QuadValue.of(MeGraS.SEGMENT_OF.uri)) ,null).firstOrNull()?.`object`
    println(originMedia)
    val segmentSet = quads.filter(null, listOf(QuadValue.of(MeGraS.SEGMENT_OF.uri)), listOf(originMedia!!))
    var boundsSet: MutableList<Quad> = mutableListOf()
    segmentSet.forEach{
        boundsSet.add(quads.filter(listOf(QuadValue.of(it.subject)), listOf(QuadValue.of(MeGraS.SEGMENT_BOUNDS.uri)), null).firstOrNull()!!)
    }
    boundsSet.forEach{n->
        println(n)
    }


    return quads

}