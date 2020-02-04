package com.sesame.worker;

import org.yaml.snakeyaml.Yaml;

public interface DocumentationFactory {

    DocumentationWorker<String> getJavaDocsDocumentationWorker();

    DocumentationWorker<Yaml> getYamlDocumentationWorker();

}
