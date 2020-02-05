package com.sesame.worker;

import org.yaml.snakeyaml.Yaml;

public interface DocumentationFactory {

    DocumentationWorker<String> getTextDocumentationWorker();

    DocumentationWorker<Yaml> getYamlDocumentationWorker();

}
