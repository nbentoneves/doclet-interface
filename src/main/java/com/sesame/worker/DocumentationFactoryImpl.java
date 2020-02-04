package com.sesame.worker;

import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

@Component
public final class DocumentationFactoryImpl implements DocumentationFactory {

    private DocumentationWorker<String> javaDocsDocumentationWorker;

    private DocumentationWorker<Yaml> yamlDocumentationWorker;

    public DocumentationFactoryImpl(DocumentationWorker<String> javaDocsDocumentationWorker,
                                    DocumentationWorker<Yaml> yamlDocumentationWorker) {
        this.javaDocsDocumentationWorker = javaDocsDocumentationWorker;
        this.yamlDocumentationWorker = yamlDocumentationWorker;
    }

    @Override
    public final DocumentationWorker<String> getJavaDocsDocumentationWorker() {
        return javaDocsDocumentationWorker;
    }

    @Override
    public final DocumentationWorker<Yaml> getYamlDocumentationWorker() {
        return yamlDocumentationWorker;
    }

}
