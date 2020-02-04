package com.sesame.worker;

import com.sesame.domain.internal.DocMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenDocletInterface {

    private final static Logger LOGGER = LoggerFactory.getLogger(GenDocletInterface.class);

    private DocMethod docMethod;

    private DocumentationFactory documentationFactory;

    public GenDocletInterface(@Autowired DocumentationFactory documentationFactory) {
        this.documentationFactory = documentationFactory;
    }

    public boolean start(String root) {

        //FIXME: How can extract this to set/constructor injection
        DocumentationWorker<String> worker = documentationFactory.getJavaDocsDocumentationWorker();

        if (root == null || root.isEmpty()) {
            LOGGER.error("Can't process any source. Please check the property inserted!");
            return false;
        }

        docMethod = worker.processInterfaceMethod(root).orElseThrow(
                () -> new RuntimeException("Can not find any documentation generated!"));
        LOGGER.info("{}", docMethod);

        return true;
    }

    public DocMethod getDocMethod() {
        return docMethod;
    }

}
