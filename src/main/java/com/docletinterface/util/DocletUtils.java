package com.docletinterface.util;

import java.util.Optional;

public class DocletUtils {

    private static final String START_TAG_DOCLIB = "@doclib";
    private static final String END_TAG_DOCLIB = "@enddoclib";

    private DocletUtils() {
    }

    public static Optional<String> extractDoclibTags(String fullMethodComment) {

        if (fullMethodComment == null || fullMethodComment.isEmpty()) {
            return Optional.empty();
        }

        int start = fullMethodComment.indexOf(START_TAG_DOCLIB);
        int end = fullMethodComment.indexOf(END_TAG_DOCLIB);

        if (start == -1 || end == -1) {
            return Optional.empty();
        }

        return Optional.of(fullMethodComment.substring(start, end).replace(START_TAG_DOCLIB, ""));

    }

}
