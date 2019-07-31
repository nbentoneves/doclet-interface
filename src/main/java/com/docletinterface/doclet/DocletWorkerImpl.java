package com.docletinterface.doclet;

import com.docletinterface.domain.DocMethod;
import com.docletinterface.util.DocletUtils;
import com.sun.javadoc.MethodDoc;

import java.util.Optional;

public class DocletWorkerImpl implements DocletWorker {

    private static final String DELIMITATOR_COLON = ":";
    private static final String DELIMITATOR_DASH = "-";

    private static final String FIELD_METHODNAME = "methodName";
    private static final String FIELD_METHODDESCRIPTION = "methodDescription";
    private static final String FIELD_RETURN = "return";
    private static final String FIELD_PARAM = "param";

    private enum Tag {
        METHOD_NAME, METHOD_DESCRIPTION, RETURN, PARAM, OTHER;
    }

    @Override
    public Optional<DocMethod> processInterfaceMethod(MethodDoc documentation) {

        if (documentation == null) {
            return Optional.empty();
        }

        Optional<String> filterDocumentation = DocletUtils.extractDoclibTags(documentation.getRawCommentText());

        if (!filterDocumentation.isPresent()) {
            return Optional.empty();
        }

        String[] docLines = filterDocumentation.get().split("\n");

        DocMethod.Builder docMethod = new DocMethod.Builder();

        for (String docLine : docLines) {

            String line = docLine.trim();

            switch (getTag(line)) {
                case METHOD_NAME:
                    String methodName = line.split(DELIMITATOR_COLON)[1].trim();
                    docMethod.withMethodName(methodName);
                    break;
                case METHOD_DESCRIPTION:
                    String methodDescrip = line.split(DELIMITATOR_COLON)[1].trim();
                    docMethod.withMethodDescription(methodDescrip);
                    break;
                case RETURN:
                    String returnType = line.split(DELIMITATOR_COLON)[1].split(DELIMITATOR_DASH)[0].trim();
                    docMethod.withReturnObject(returnType);
                    break;
                case PARAM:
                    String paramType = line.split(DELIMITATOR_COLON)[1].split(DELIMITATOR_DASH)[0].trim();
                    int paramIndex = Integer.valueOf(line.split(DELIMITATOR_COLON)[0].split(DELIMITATOR_DASH)[1]);
                    docMethod.addParamObjects(paramIndex, paramType);
                    break;
                default:
                    break;
            }

        }

        return Optional.of(docMethod.build());
    }

    private Tag getTag(String line) {

        if (line.startsWith(FIELD_METHODNAME)) {
            return Tag.METHOD_NAME;
        } else if (line.startsWith(FIELD_METHODDESCRIPTION)) {
            return Tag.METHOD_DESCRIPTION;
        } else if (line.startsWith(FIELD_RETURN)) {
            return Tag.RETURN;
        } else if (line.startsWith(FIELD_PARAM)) {
            return Tag.PARAM;
        }

        return Tag.OTHER;
    }


}
