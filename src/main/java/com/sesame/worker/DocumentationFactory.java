package com.sesame.worker;

import com.sesame.core.worker.DocumentationWorker;

public interface DocumentationFactory {

    DocumentationWorker getTextDocumentationWorker();

    DocumentationWorker getYamlDocumentationWorker();

}
