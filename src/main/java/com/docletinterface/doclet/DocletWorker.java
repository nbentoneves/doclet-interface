package com.docletinterface.doclet;

import com.docletinterface.domain.DocMethod;
import com.sun.javadoc.MethodDoc;

import java.util.Optional;

public interface DocletWorker {

    Optional<DocMethod> processInterfaceMethod(MethodDoc documentation);

}
