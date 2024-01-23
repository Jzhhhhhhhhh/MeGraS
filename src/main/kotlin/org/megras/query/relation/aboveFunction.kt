package org.megras.query.relation

import org.megras.data.graph.Quad
import org.megras.data.schema.MeGraS
import org.megras.data.graph.QuadValue
import org.megras.graphstore.QuadSet
import org.megras.lang.sparql.SparqlUtil.toQuadValue

fun aboveFunction(o: QuadValue, quads: QuadSet):QuadSet {
    //检索目标对象的bounds
    //检索所有的bounds
    //比较得到结果判断所有bounds在上面的情况
    //得到结果列表，进行查询返回最终结果
    val originMedia = quads.filter(listOf(o), listOf(QuadValue.of(MeGraS.SEGMENT_OF.uri)) ,null).firstOrNull()?.`object`
    println(originMedia)
    val segmentSet = quads.filter(null, listOf(QuadValue.of(MeGraS.SEGMENT_OF.uri)), listOf(originMedia!!))
    var boundsSet: MutableList<QuadSet> = mutableListOf()

//    segmentSet.forEach{
//        boundsSet.add(quads.filter(listOf(QuadValue.of(it.subject))), listOf(QuadValue.of(MeGraS.SEGMENT_BOUNDS.uri)), null)
//    }
//    boundsSet.forEach{n->
//        println(n)
//    }


    return quads

}