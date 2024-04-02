//import io.javalin.Javalin
//import io.javalin.http.Context
//import io.javalin.testing.TestUtil
//import org.junit.Test
//import kotlin.test.assertEquals
//
//class KnnQueryHandlerTest {
//
//    @Test
//    fun testPostMethod() {
//        val ctx = mockk<Context>(relaxed = true)
//
//        val ctxQuery = """
//        {
//            "predicate": "yourPredicateValue",
//            "object": [1.0, 2.0, 3.0],
//            "count": 5,
//            "distance": "EUCLIDEAN"
//        }
//        """.trimIndent()
//
//        every { ctx.body() } returns ctxQuery
//
//        val quadSet = mockk<QuadSet>()
//        val handler = KnnQueryHandler(quadSet)
//
//        handler.post(ctx)
//
//    }
//}
