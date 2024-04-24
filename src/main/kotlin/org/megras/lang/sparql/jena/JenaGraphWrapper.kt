package org.megras.lang.sparql.jena

import org.apache.jena.graph.Triple
import org.apache.jena.graph.impl.GraphBase
import org.apache.jena.util.iterator.ExtendedIterator
import org.megras.data.graph.QuadValue
import org.megras.data.schema.MeGraS
import org.megras.graphstore.QuadSet
import org.megras.lang.sparql.SparqlUtil.toQuadValue
import org.megras.query.relation.*

class JenaGraphWrapper(private var quads: QuadSet) : GraphBase() {

    var quadset: QuadSet = quads
    override fun graphBaseFind(triplePattern: Triple): ExtendedIterator<Triple> {
        val s = toQuadValue(triplePattern.subject)
        val p = toQuadValue(triplePattern.predicate)
        val o = toQuadValue(triplePattern.`object`)
        when(p){
            QuadValue.of(MeGraS.ABOVE.uri)-> quadset = o?.let { aboveFunction(it, quadset, quads) } ?: quads
            QuadValue.of(MeGraS.BELOW.uri)-> quadset = o?.let { belowFunction(it, quadset, quads) } ?: quads
            QuadValue.of(MeGraS.CONTAINS.uri)->quadset = o?.let { containsFunction(it,quadset, quads) } ?: quads
            QuadValue.of(MeGraS.BELONGS_TO.uri)->quadset = o?.let { belongsToFunction(it, quadset, quads) } ?: quads
            QuadValue.of(MeGraS.LEFT_ABOVE.uri)->quadset = o?.let { leftAboveFunction(it, quadset, quads) } ?: quads
            QuadValue.of(MeGraS.LEFT_BELOW.uri)->quadset = o?.let { leftBelowFunction(it, quadset, quads) } ?: quads
            QuadValue.of(MeGraS.LEFT_BESIDE.uri)->quadset = o?.let { leftBesideFunction(it, quadset, quads) } ?: quads
            QuadValue.of(MeGraS.OVERLAPS.uri)->quadset = o?.let { overlapFunction(it, quadset, quads) } ?: quads
            QuadValue.of(MeGraS.RIGHT_ABOVE.uri)->quadset = o?.let { rightAboveFunction(it, quadset, quads) } ?: quads
            QuadValue.of(MeGraS.RIGHT_BELOW.uri)->quadset = o?.let { rightBelowFunction(it, quadset, quads) } ?: quads
            QuadValue.of(MeGraS.RIGHT_BESIDE.uri)->quadset = o?.let { rightBesideFunction(it, quadset, quads) } ?: quads
            QuadValue.of(MeGraS.SIZE_EQUAL.uri)->quadset = o?.let { sizeEqualFunction(it, quadset, quads) } ?: quads
            QuadValue.of(MeGraS.SIZE_LARGER.uri)->quadset = o?.let { sizeLargerFunction(it, quadset, quads) } ?: quads
            QuadValue.of(MeGraS.SIZE_SMALLER.uri)->quadset = o?.let { sizeSmallerFunction(it, quadset, quads) } ?: quads
            QuadValue.of(MeGraS.RELATION.uri)->quadset = s?.takeIf { o != null }?.let { relationFunction(it, o!!, quads) } ?: quads
            QuadValue.of(MeGraS.EARLY.uri)->quadset = o?.let { earlyFunction(it, quadset, quads) } ?: quads
            QuadValue.of(MeGraS.LATE.uri)->quadset = o?.let { lateFunction(it, quadset, quads) } ?: quads
            QuadValue.of(MeGraS.TIME_BESIDE.uri)->quadset = o?.let { timeBesideFunction(it, quadset, quads) } ?: quads
            QuadValue.of(MeGraS.TIME_CONTAIN.uri)->quadset = o?.let { timeContainFunction(it, quadset, quads) } ?: quads
            QuadValue.of(MeGraS.TIME_OVERLAP.uri)->quadset = o?.let { timeOverlapFunction(it, quadset, quads) } ?: quads
            QuadValue.of(MeGraS.KNN.uri)->quadset = o?.let { knnFunction(o, p, quads) } ?: quads
            QuadValue.of(MeGraS.COLOR.uri)->quadset = o?.let { knnFunction(o, p, quads) } ?: quads
            else-> quadset = this.quadset.filter(
                if (s != null) {
                    listOf(s)
                } else null,
                if (p != null) {
                    listOf(p)
                } else null,
                if (o != null) {
                    listOf(o)
                } else null,
            )
        }



        return QuadSetIterator(quadset)

    }

}