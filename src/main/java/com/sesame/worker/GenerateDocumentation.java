package com.sesame.worker;

import com.sesame.core.worker.DocumentationWorker;
import com.sesame.domain.internal.DocMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Optional;

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

        DocumentationWorker worker;

        switch (configType) {
            case "TEXT":
                worker = documentationFactory.getTextDocumentationWorker();
                break;

            case "JSON":
                worker = documentationFactory.getYamlDocumentationWorker();
                break;

            default:
                LOGGER.info("msg='Can not support this type of configuration', configType='{}'", configType);
                return false;
        }

        Optional<DocMethod> docMethod = worker.processInterfaceMethod(new File(configPath));

        if (docMethod.isPresent()) {
            LOGGER.info("{}", docMethod);
            this.docMethod = docMethod.get();
            return true;
        }


        return false;
    }

    public DocMethod getDocMethod() {
        return docMethod;
    }


}
