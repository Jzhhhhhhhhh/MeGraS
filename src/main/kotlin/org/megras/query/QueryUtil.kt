package org.megras.query

import org.megras.data.graph.QuadValue
import org.megras.data.schema.MeGraS
import org.megras.graphstore.QuadSet
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
//import org.bytedeco.opencv.opencv_core.*
//import org.bytedeco.opencv.global.opencv_imgproc.*

object QueryUtil {

    data class BoundsResult(val originBounds: QuadSet, val boundsSet: QuadSet)

    fun getBounds(o: QuadValue, quads: QuadSet): BoundsResult {
        val originMedia = quads.filter(listOf(o), listOf(QuadValue.of(MeGraS.SEGMENT_OF.uri)), null).firstOrNull()?.`object`
        val segmentSet = quads.filter(null, listOf(QuadValue.of(MeGraS.SEGMENT_OF.uri)), listOf(originMedia!!))
        val boundsSet = quads.filter(segmentSet.map { it.subject }, listOf(QuadValue.of(MeGraS.SEGMENT_BOUNDS.uri)), null)
        val originBounds = quads.filter(listOf(o), listOf(QuadValue.of(MeGraS.SEGMENT_BOUNDS.uri)), null)
        return BoundsResult(originBounds, boundsSet)
    }

    fun extractColorFeatures(imagePath: String, bins: Int = 256): List<Int> {
        val image: BufferedImage = ImageIO.read(File(imagePath))
        val width = image.width
        val height = image.height

        val histogram = MutableList(bins) { 0 }

        for (x in 0 until width) {
            for (y in 0 until height) {
                val pixel = Color(image.getRGB(x, y))
                val red = pixel.red
                histogram[red]++
            }
        }
        return histogram
    }
//    fun extractContours(imagePath: String) {
//        val image = imread(imagePath)
//        val grayImage = Mat()
//        cvtColor(image, grayImage, COLOR_BGR2GRAY)
//        val edges = Mat()
//        Canny(grayImage, edges, 100.0, 200.0)
//
//        val contours = MatVector()
//        findContours(edges, contours, RETR_LIST, CHAIN_APPROX_SIMPLE)
//    }
}
