package org.megras.query.relation

import org.megras.api.rest.data.ApiQuad
import org.megras.data.graph.Quad
import org.megras.data.graph.QuadValue
import org.megras.data.schema.MeGraS
import org.megras.graphstore.IndexedMutableQuadSet
import org.megras.graphstore.QuadSet
import org.megras.lang.sparql.SparqlUtil
import org.megras.segmentation.Bounds


//all other functions and not in the same media

fun relationFunction(s: QuadValue, o: QuadValue, quads: QuadSet): QuadSet {
    val sBound = Bounds(quads.filter(listOf(s), listOf(QuadValue.of(MeGraS.SEGMENT_BOUNDS.uri)), null).firstOrNull()?.`object`.toString())
    val oBound = Bounds(quads.filter(listOf(o), listOf(QuadValue.of(MeGraS.SEGMENT_BOUNDS.uri)), null).firstOrNull()?.`object`.toString())
    val result = when{
        sBound.getMinX() > oBound.getMaxX() -> MeGraS.RIGHT_BESIDE.uri
        sBound.getMaxX() < oBound.getMinX() -> MeGraS.LEFT_BESIDE.uri
        sBound.getMinY() > oBound.getMaxY() -> MeGraS.ABOVE.uri
        sBound.getMaxY() < oBound.getMinY() -> MeGraS.BELOW.uri
        else -> MeGraS.ERROR.uri
    }
    val resultQuad =  Quad(null, s, result, o)
    val tempQuads = ArrayList<Quad>(10000)
    tempQuads.add(resultQuad)
    val resultQuads = IndexedMutableQuadSet()
    resultQuads.addAll(tempQuads)
    return resultQuads
}