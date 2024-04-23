package org.megras

import org.megras.data.fs.FileSystemObjectStore
import org.megras.data.model.Config
import org.megras.graphstore.TSVMutableQuadSet
import org.megras.lang.sparql.SparqlUtil
import java.io.File

object MeGraS {

    @JvmStatic
    fun main(args: Array<String>) {

        val config = if (args.isNotEmpty()) {
            Config.read(File(args[0]))
        } else {
            null
        } ?: Config()

        val objectStore = FileSystemObjectStore(config.objectStoreBase)

//        val postgresStore = PostgresStore()
//        val cottontailStore = CottontailStore()

//        val quadSet = HybridMutableQuadSet(postgresStore, cottontailStore)

        val quadSet = TSVMutableQuadSet("test.tsv")

//        postgresStore.setup()
//        cottontailStore.setup()
//        RestApi.init(config, objectStore, quadSet)
//
//        Cli.init(quadSet, objectStore)
//
//        Cli.loop()
//
//        RestApi.stop()

//        val query = "SELECT ?s " +
//                    "WHERE { " +
//                "?s <http://megras.org/schema#color> <http://localhost:8080/6IO0J3UDL6VQ> ." +
//                "}"


        val query = "SELECT ?s " +
                "WHERE {?s <http://megras.org/schema#segmentOf> <http://localhost:8080/iARcbuvP-fw-_46DzlR294jid7Mx2oTyu2I9fNALjMvRqevcAdHZzUQ/c/cAeuyOBOKXKbsJqnm7hckA> .}"


        val result = SparqlUtil.select(query, quadSet)
        println(result)




    }

}