package com.example.mvvmcarrot.util

class PriceConverter {
    companion object {
        private const val SP = 3

        fun convert(s: String): String {
            val sb = StringBuilder()
            var n = s.length
            var cnt = 0

            //12,345,678
            for (i in n - 1 downTo 0) {
                sb.append(s[i])
                cnt++

                if (cnt == SP && i > 0) {
                    sb.append(',')
                    cnt = 0
                }

            }

            return sb.reverse().toString() + "원"
        }
    }
}