package com.sesame.core.util

import java.util.*

class TextUtils private constructor() {

    companion object {

        private const val START_TAG_DOCLIB = "@doclib"
        private const val END_TAG_DOCLIB = "@enddoclib"

        fun extractDocLibTags(configDescription: String): Optional<String> {

            if (configDescription.isEmpty()) {
                return Optional.empty()
            }

            val start: Int = configDescription.indexOf(START_TAG_DOCLIB)
            val end: Int = configDescription.indexOf(END_TAG_DOCLIB)

            return if (start == -1 || end == -1) {
                Optional.empty()
            } else Optional.of(configDescription.substring(start, end).replace(START_TAG_DOCLIB, ""))

        }
    }
}