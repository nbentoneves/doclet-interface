package com.sesame.worker;

import com.sesame.domain.internal.DocMethod;
import com.sesame.worker.exception.DocumentInvalidFormatException;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static java.nio.file.Paths.get;

@Component
public class GenerateDocumentation {

    private final static Logger LOGGER = LoggerFactory.getLogger(GenerateDocumentation.class);

    private DocMethod docMethod;

    private DocumentationFactory documentationFactory;

    public GenerateDocumentation(@Autowired DocumentationFactory documentationFactory) {
        this.documentationFactory = documentationFactory;
    }

    public boolean start(String configType, String configPath) {

        if (configPath == null || configPath.isEmpty() || configType == null || configType.isEmpty()) {
            LOGGER.error("Can't process any source. Please check the property inserted.");
            return false;
        }

        if (configType.equals("TEXT")) {

            DocumentationWorker<String> worker = documentationFactory.getTextDocumentationWorker();

            try {

                String documentation = IOUtils.toString(Files.newInputStream(Paths.get(configPath)), Charset.defaultCharset());

                Optional<DocMethod> docMethod = worker.processInterfaceMethod(documentation);

                if (docMethod.isPresent()) {
                    LOGGER.info("{}", docMethod);
                    return true;
                }

            } catch (IOException ex) {
                LOGGER.error("msg='Can not extract the value from config variable.'", ex);
                throw new DocumentInvalidFormatException("Error when try to extract the values form line: ", ex);
            }
        }

        return false;
    }

    public DocMethod getDocMethod() {
        return docMethod;
    }


}
