package com.sesame.worker;

import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

@Component
public final class DocumentationFactoryImpl implements DocumentationFactory {

    private DocumentationWorker<String> textDocumentationWorker;

    private DocumentationWorker<Yaml> yamlDocumentationWorker;

    public DocumentationFactoryImpl(DocumentationWorker<String> textDocumentationWorker,
                                    DocumentationWorker<Yaml> yamlDocumentationWorker) {
        this.textDocumentationWorker = textDocumentationWorker;
        this.yamlDocumentationWorker = yamlDocumentationWorker;
    }

    @Override
    public final DocumentationWorker<String> getTextDocumentationWorker() {
        return textDocumentationWorker;
    }

    @Override
    public final DocumentationWorker<Yaml> getYamlDocumentationWorker() {
        return yamlDocumentationWorker;
    }

}
