package com.sesame.worker;

import com.sesame.domain.internal.DocMethod;
import com.sun.javadoc.Doclet;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.RootDoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GenDocletInterface extends Doclet {

    private final static Logger LOGGER = LoggerFactory.getLogger(GenDocletInterface.class);

    private static DocMethod docMethod;

    public static boolean start(RootDoc root) {

        //FIXME: How can extract this to set/constructor injection
        DocumentationWorker<MethodDoc> worker = new DocumentationFactoryImpl().createJavaDocsWorker();

        if (root == null || root.specifiedClasses() == null || root.specifiedClasses().length == 0) {
            LOGGER.error("Can't process any source. Please check the property inserted!");
            return false;
        }

        docMethod = worker.processInterfaceMethod(root.specifiedClasses()[0].methods()[0]).orElseThrow(
                () -> new RuntimeException("Can not find any documentation generated!"));
        LOGGER.info("{}", docMethod);

        return true;
    }

    public static DocMethod getDocMethod() {
        return docMethod;
    }

}
