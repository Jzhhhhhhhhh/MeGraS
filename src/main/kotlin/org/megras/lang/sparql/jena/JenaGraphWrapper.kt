package org.megras.lang.sparql.jena

import org.apache.jena.graph.Node
import org.apache.jena.graph.Triple
import org.apache.jena.graph.impl.GraphBase
import org.apache.jena.util.iterator.ExtendedIterator
import org.megras.data.graph.QuadValue
import org.megras.data.graph.URIValue
import org.megras.data.schema.MeGraS
import org.megras.graphstore.QuadSet
import org.megras.lang.sparql.SparqlUtil
import org.megras.lang.sparql.SparqlUtil.toQuadValue
import org.megras.query.relation.aboveFunction
import org.megras.query.relation.belowFunction

class JenaGraphWrapper(private var quads: QuadSet) : GraphBase() {


    override fun graphBaseFind(triplePattern: Triple): ExtendedIterator<Triple> {

        val s = toQuadValue(triplePattern.subject)
        val p = toQuadValue(triplePattern.predicate)
        val o = toQuadValue(triplePattern.`object`)
        var quadset = quads
        //relations when give s and o

        when(p){
            QuadValue.of(MeGraS.ABOVE.uri)-> quadset = o?.let { aboveFunction(it, quads) }!!
            QuadValue.of(MeGraS.BELOW.uri)-> quadset = o?.let { belowFunction(it, quads) }!!
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