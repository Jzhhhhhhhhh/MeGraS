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


    override fun graphBaseFind(triplePattern: Triple): ExtendedIterator<Triple> {

        val s = toQuadValue(triplePattern.subject)
        val p = toQuadValue(triplePattern.predicate)
        val o = toQuadValue(triplePattern.`object`)
        val quadset: QuadSet
        //relations when give s and o

        when(p){
            QuadValue.of(MeGraS.ABOVE.uri)-> quadset = o?.let { aboveFunction(it, quads) }!!
            QuadValue.of(MeGraS.BELOW.uri)-> quadset = o?.let { belowFunction(it, quads) }!!
            QuadValue.of(MeGraS.CONTAINS.uri)->quadset = o?.let { containsFunction(it, quads) }!!
            QuadValue.of(MeGraS.BELONGS_TO.uri)->quadset = o?.let { belongsToFunction(it, quads) }!!
            QuadValue.of(MeGraS.DURING.uri)->quadset = o?.let { duringFunction(it, quads) }!!
            QuadValue.of(MeGraS.LEFT_ABOVE.uri)->quadset = o?.let { leftAboveFunction(it, quads) }!!
            QuadValue.of(MeGraS.LEFT_BELOW.uri)->quadset = o?.let { leftBelowFunction(it, quads) }!!
            QuadValue.of(MeGraS.LEFT_BESIDE.uri)->quadset = o?.let { leftBesideFunction(it, quads) }!!
            QuadValue.of(MeGraS.OVERLAPPED_BY.uri)->quadset = o?.let { overlapedByFunction(it, quads) }!!
            QuadValue.of(MeGraS.OVERLAPS.uri)->quadset = o?.let { overlapsFunction(it, quads) }!!
            QuadValue.of(MeGraS.RELATION.uri)->quadset = o?.let { relationFunction(it, quads) }!!
            QuadValue.of(MeGraS.RIGHT_ABOVE.uri)->quadset = o?.let { rightAboveFunction(it, quads) }!!
            QuadValue.of(MeGraS.RIGHT_BELOW.uri)->quadset = o?.let { rightBelowFunction(it, quads) }!!
            QuadValue.of(MeGraS.RIGHT_BESIDE.uri)->quadset = o?.let { rightBesideFunction(it, quads) }!!
            QuadValue.of(MeGraS.SIZE_EQUAL.uri)->quadset = o?.let { sizeEqualFunction(it, quads) }!!
            QuadValue.of(MeGraS.SIZE_LARGER.uri)->quadset = o?.let { sizeLargerFunction(it, quads) }!!
            QuadValue.of(MeGraS.SIZE_SMALLER.uri)->quadset = o?.let { sizeSmallerFunction(it, quads) }!!
            else-> quadset = this.quads.filter(
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