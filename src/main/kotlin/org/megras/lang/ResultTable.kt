package org.megras.lang

import com.jakewharton.picnic.table
import org.megras.data.graph.QuadValue

data class ResultTable(val rows: List<Map<String, QuadValue>>) {

    val headers: Set<String>

    init {
        this.headers = rows.flatMapTo(HashSet()) {
            it.keys
        }

    }

    override fun toString(): String {

        val headerList = headers.toList()  // 转换 headers 为 List 以便操作
        println(table {
            cellStyle {
                border = true
                paddingLeft = 1
                paddingRight = 1
            }
            header {
                row(*headerList.toTypedArray())  // 使用展开操作符将 List 转换为 Array
            }
            body {
                rows.forEach { r ->
                    row(*headerList.map { r[it]?.toString() ?: "null" }.toTypedArray())
                }
            }
        }.toString().javaClass)

        return table {
            cellStyle {
                border = true
                paddingLeft = 1
                paddingRight = 1
            }
            header {
                row(*headerList.toTypedArray())  // 使用展开操作符将 List 转换为 Array
            }
            body {
                rows.forEach { r ->
                    row(*headerList.map { r[it]?.toString() ?: "null" }.toTypedArray())
                }
            }
        }.toString()
    }

//    fun toTableString(): String {
//        val headerList = headers.toList()  // 转换 headers 为 List 以便操作
//        return table {
//            cellStyle {
//                border = true
//                paddingLeft = 1
//                paddingRight = 1
//            }
//            header {
//                row(*headerList.toTypedArray())  // 使用展开操作符将 List 转换为 Array
//            }
//            body {
//                rows.forEach { r ->
//                    row(*headerList.map { r[it]?.toString() ?: "null" }.toTypedArray())
//                }
//            }
//        }.toString()
//    }


//    override fun toString(): String {
//
//        val headerList = this.headers.toList()
////        return table {
////            cellStyle {
////                border = true
////                paddingLeft = 1
////                paddingRight = 1
////            }
////            header {
////                row(*headerList.toTypedArray())
////            }
////            body {
////                rows.forEach {r ->
////                    row(*headerList.map { r[it] ?: "null" }.toTypedArray())
////                }
////            }
////        }.toString()
//        val res = table {
//            cellStyle {
//                border = true
//                paddingLeft = 1
//                paddingRight = 1
//            }
//            header {
//                row(*headerList.toTypedArray())
//            }
//            body {
//                rows.forEach { r ->
//                    row(*headerList.map { r[it] ?: "null" }.toTypedArray())
//                }
//            }
//        }
//        println(res.toString())
//        try {
//            return table {
//                cellStyle {
//                    border = true
//                    paddingLeft = 1
//                    paddingRight = 1
//                }
//                header {
//                    row(*headerList.toTypedArray())
//                }
//                body {
//                    rows.forEach { r ->
//                        row(*headerList.map { r[it] ?: "null" }.toTypedArray())
//                    }
//                }
//            }.toString()
//        } catch (e: Exception) {
//            println("An error occurred while building the table: ${e.message}")
//            throw RuntimeException("Failed to build table", e)
//        }
//
//    }
}