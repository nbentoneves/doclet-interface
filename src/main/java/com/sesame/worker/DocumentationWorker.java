package com.sesame.worker;

import com.sesame.domain.internal.DocMethod;

import java.util.Optional;

public interface DocumentationWorker<T> {

    Optional<DocMethod> processInterfaceMethod(T documentation);

}
