package com.sesame.worker;

import com.sesame.domain.internal.DocMethod;
import org.yaml.snakeyaml.Yaml;

import java.util.Optional;

public class YamlDocumentationWorkerImpl implements DocumentationWorker<Yaml> {

    @Override
    public Optional<DocMethod> processInterfaceMethod(Yaml yaml) {
        return Optional.empty();
    }
}
