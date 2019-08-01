package com.docletinterface.doclet;

import com.docletinterface.domain.DocMethod;
import com.sun.javadoc.Doclet;
import com.sun.javadoc.RootDoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *
 * @doclib
 *  methodName: start
 *  methodDescription: Description
 *  return: String - Description
 *  param-1: Int - Description
 *  ...
 *
 * @enddoclib
 *
 * </pre>
 */
public class GenDocletInterface extends Doclet {

    private final static Logger LOGGER = LoggerFactory.getLogger(GenDocletInterface.class);

    private static DocMethod docMethod;

    public static boolean start(RootDoc root) {

        DocletWorker worker = new DocletWorkerImpl();
        //FIXME: Change for custome exception
        docMethod = worker.processInterfaceMethod(root.specifiedClasses()[0].methods()[0]).orElseThrow(() -> new RuntimeException(""));

        LOGGER.info("{}", docMethod);

        return true;
    }

    public static DocMethod getDocMethod() {
        return docMethod;
    }

}
