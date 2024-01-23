package org.megras.data.schema

import org.megras.data.graph.URIValue

enum class MeGraS(suffix: String) {

    RAW_ID("rawId"),
    MEDIA_TYPE("mediaType"),
    RAW_MIME_TYPE("rawMimeType"),
    CANONICAL_ID("canonicalId"), //raw id of canonical representation
    CANONICAL_MIME_TYPE("canonicalMimeType"),
    FILE_NAME("fileName"),
    BOUNDS("bounds"),
    SEGMENT_OF("segmentOf"),
    SEGMENT_TYPE("segmentType"),
    SEGMENT_DEFINITION("segmentDefinition"),
    SEGMENT_BOUNDS("segmentBounds"),
    QUERY_DISTANCE("queryDistance"),
    PREVIEW_ID("previewId"),
    ABOVE("above"),
    BELOW("below"),
    CONTAINS("contains"),
    BELONGS_TO("belongsTo"),
    LEFT_ABOVE("leftAbove"),
    RIGHT_ABOVE("rightAbove"),
    LEFT_BELOW("leftBelow"),
    RIGHT_BELOW("rightBelow"),
    LEFT_BESIDE("leftBeside"),
    RIGHT_BESIDE("rightBeside"),
    OVERLAPS("overlaps"),
    OVERLAPPED_BY("overlappedBy"),
    SIZE_LARGER("sizeLarger"),
    SIZE_SMALLER("sizeSmaller"),

    ;

    companion object {
        private const val prefix = "http://megras.org/schema#"
    }

    val uri = URIValue(MeGraS.prefix, suffix)


}