package org.megras.api.rest.handlers

import io.javalin.http.Context
import org.megras.api.rest.GetRequestHandler
import org.megras.api.rest.RestErrorStatus
import org.megras.data.fs.FileSystemObjectStore
import org.megras.data.fs.StoredObjectId
import org.megras.data.schema.MeGraS
import org.megras.graphstore.QuadSet

class CanonicalObjectRequestHandler(private val quads: QuadSet, private val objectStore: FileSystemObjectStore) : GetRequestHandler {

    override fun get(ctx: Context) {

        val rawId = quads.filter(
            setOf(ctx.pathParam("objectId")),
            setOf(MeGraS.CANONICAL_ID.string),
            null
        ).firstOrNull()?.`object` ?: throw RestErrorStatus.notFound

        val osId = StoredObjectId.of(rawId) ?: throw RestErrorStatus.notFound

        RawObjectRequestHandler.streamObject(osId, objectStore, ctx)

    }


}