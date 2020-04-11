package com.sesame.worker;

import com.sesame.core.worker.DocumentationWorker;
import org.springframework.stereotype.Component;

@Component
public final class DocumentationFactoryImpl implements DocumentationFactory {

    private DocumentationWorker textDocumentationWorker;

    private DocumentationWorker yamlDocumentationWorker;

    public DocumentationFactoryImpl(DocumentationWorker textDocumentationWorker,
                                    DocumentationWorker yamlDocumentationWorker) {
        this.textDocumentationWorker = textDocumentationWorker;
        this.yamlDocumentationWorker = yamlDocumentationWorker;
    }

    @Override
    public final DocumentationWorker getTextDocumentationWorker() {
        return textDocumentationWorker;
    }

    @Override
    public final DocumentationWorker getYamlDocumentationWorker() {
        return yamlDocumentationWorker;
    }

}
