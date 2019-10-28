package com.sesame.worker;

import com.sun.javadoc.MethodDoc;

public interface DocumentationFactory {

    DocumentationWorker<MethodDoc> createJavaDocsWorker();

    DocumentationWorker<?> createYmlWorker();

}
