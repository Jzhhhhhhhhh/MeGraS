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
}