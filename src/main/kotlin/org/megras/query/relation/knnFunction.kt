package org.megras.query.relation

import org.megras.data.graph.DoubleVectorValue
import org.megras.data.graph.QuadValue
import org.megras.data.schema.MeGraS
import org.megras.graphstore.Distance
import org.megras.graphstore.QuadSet

fun knnFunction(o: QuadValue, p: QuadValue, quads: QuadSet): QuadSet {
    println("yes")
    val originMedia = quads.filter(listOf(o), listOf(QuadValue.of(MeGraS.COLOR.uri)), null).firstOrNull()?.`object`
    println(originMedia)
    val originVector = DoubleVectorValue.parse(originMedia.toString())
    println(originVector)
    val result = quads.nearestNeighbor(p, originVector, 3, Distance.COSINE)
    val resultSet = quads.filter(result.map { it.subject }, listOf(QuadValue.of(MeGraS.COLOR.uri)), null)
    resultSet.forEach{println(it)}
    return resultSet
}