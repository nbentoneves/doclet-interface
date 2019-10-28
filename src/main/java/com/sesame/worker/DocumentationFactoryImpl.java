package com.sesame.worker;

import com.sun.javadoc.MethodDoc;

public final class DocumentationFactoryImpl implements DocumentationFactory {

    @Override
    public final DocumentationWorker<MethodDoc> createJavaDocsWorker() {
        return new DocumentationWorkerImpl();
    }

    @Override
    public final DocumentationWorker<?> createYmlWorker() {
        throw new UnsupportedOperationException("Need to implement this worker!");
    }

}
