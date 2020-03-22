package com.sesame.worker;

import com.sesame.core.worker.DocumentationWorker;
import com.sesame.domain.internal.DocMethod;
import com.sesame.domain.internal.ParameterType;
import com.sesame.util.TextUtils;
import com.sesame.worker.exception.DocumentInvalidFormatException;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.regex.PatternSyntaxException;

@Deprecated
public class TextDocumentationWorkerImpl implements DocumentationWorker {

    private final static Logger LOGGER = LoggerFactory.getLogger(TextDocumentationWorkerImpl.class);

    private static final String DELIMITATOR_COLON = ":";
    private static final String DELIMITATOR_DASH = "-";

    private static final String FIELD_BEAN_IDENTIFICATION = "beanIdentification";
    private static final String FIELD_PACKAGENAME = "packageName";
    private static final String FIELD_CLASSNAME = "className";
    private static final String FIELD_METHODNAME = "methodName";
    private static final String FIELD_METHODDESCRIPTION = "methodDescription";
    private static final String FIELD_RETURN = "return";
    private static final String FIELD_PARAM = "param";

    private enum Tag {
        BEAN_IDENTIFICATION_NAME, PACKAGE_NAME, CLASS_NAME, METHOD_NAME, METHOD_DESCRIPTION, RETURN, PARAM, OTHER
    }

    @Override
    public Optional<DocMethod> processInterfaceMethod(File file) {

        if (file == null) {
            return Optional.empty();
        }

        try {
            String configs = IOUtils.toString(Files.newInputStream(Paths.get(file.getAbsolutePath())), Charset.defaultCharset());


            Optional<String> filterDocumentation = TextUtils.extractDoclibTags(configs);

            if (!filterDocumentation.isPresent()) {
                return Optional.empty();
            }

            String[] docLines = filterDocumentation.get().split("\n");

            DocMethod.Builder docMethod = new DocMethod.Builder();

            for (String docLine : docLines) {

                String line = docLine.trim();

                try {
                    switch (getTag(line)) {
                        case BEAN_IDENTIFICATION_NAME:
                            String beanIdentification = line.split(DELIMITATOR_COLON)[1].trim();
                            docMethod.withBeanIdentification(beanIdentification);
                            break;
                        case PACKAGE_NAME:
                            String packageName = line.split(DELIMITATOR_COLON)[1].trim();
                            docMethod.withPackageName(packageName);
                            break;
                        case CLASS_NAME:
                            String className = line.split(DELIMITATOR_COLON)[1].trim();
                            docMethod.withClassName(className);
                            break;
                        case METHOD_NAME:
                            String methodName = line.split(DELIMITATOR_COLON)[1].trim();
                            docMethod.withMethodName(methodName);
                            break;
                        case METHOD_DESCRIPTION:
                            String methodDescrip = line.split(DELIMITATOR_COLON)[1].trim();
                            docMethod.withMethodDescription(methodDescrip);
                            break;
                        case RETURN:
                            String returnDocumentation = line.split(DELIMITATOR_COLON)[1].trim();

                            try {
                                String returnDescription = returnDocumentation.split(DELIMITATOR_DASH)[1].trim();
                                String returnType = returnDocumentation.split(DELIMITATOR_DASH)[0].trim();
                                docMethod.withReturnObject(returnType);
                                docMethod.withReturnObjectDescription(returnDescription);
                            } catch (PatternSyntaxException ex) {
                                LOGGER.warn("msg='Can not extract the value detail, check the format of documentation', return={}", returnDocumentation, ex);
                                docMethod.withReturnObject(returnDocumentation);
                            }

                            break;
                        case PARAM:
                            String paramDocumentation = line.split(DELIMITATOR_COLON)[1].trim();
                            int paramIndex = Integer.parseInt(line.split(DELIMITATOR_COLON)[0].split(DELIMITATOR_DASH)[1]);

                            try {
                                String paramDescription = paramDocumentation.split(DELIMITATOR_DASH)[1].trim();
                                String paramType = paramDocumentation.split(DELIMITATOR_DASH)[0].trim();
                                docMethod.addParamObjects(paramIndex, ParameterType.getInternalType(paramType));
                            } catch (PatternSyntaxException ex) {
                                LOGGER.warn("msg='Can not extract the param detail, check the format of documentation', param={}", paramDocumentation, ex);
                            }

                            break;
                        default:
                            break;
                    }
                } catch (Exception ex) {
                    LOGGER.error("msg='Can not extract the value, check the format of documentation', docLine={}", docLine, ex);
                    throw new DocumentInvalidFormatException("Error when try to extract the values form line: ", ex);
                }

            }

            return Optional.of(docMethod.build());

        } catch (IOException ex) {
            LOGGER.error("msg='Can not extract the value from config variable.'", ex);
            throw new DocumentInvalidFormatException("Error when try to extract the values form line: ", ex);
        }
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
        } else if (line.startsWith(FIELD_CLASSNAME)) {
            return Tag.CLASS_NAME;
        } else if (line.startsWith(FIELD_PACKAGENAME)) {
            return Tag.PACKAGE_NAME;
        } else if (line.startsWith(FIELD_BEAN_IDENTIFICATION)) {
            return Tag.BEAN_IDENTIFICATION_NAME;
        }

        return Tag.OTHER;
    }


}
