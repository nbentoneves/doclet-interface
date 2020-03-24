package com.sesame.worker;

import com.sesame.core.worker.DocumentationWorker;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GenerateDocumentationTest {

    private GenerateDocumentation victim;

    @Mock
    private DocumentationWorker textDocumentationWorker;

    @Mock
    private DocumentationFactory documentationFactory;

    @Before
    public void setupClass() {
        initMocks(this);

        victim = new GenerateDocumentation(documentationFactory);
        when(documentationFactory.getTextDocumentationWorker()).thenReturn(textDocumentationWorker);
    }

    @Test
    public void testWhenConfigPathIsNullOrEmpty() {
        assertFalse(victim.start("TEXT", null));
        assertFalse(victim.start("TEXT", ""));
    }

    @Test
    public void testWhenConfigTypeIsNullOrEmpty() {
        assertFalse(victim.start(null, "PATH"));
        assertFalse(victim.start("", "PATH"));
    }

    @Test
    public void testWhenTextDocumentationWorkerReturnEmpty() {

        when(textDocumentationWorker.processInterfaceMethod(any())).thenReturn(Optional.empty());

        assertFalse(victim.start("TEXT", getClass().getClassLoader().getResource("config/config.txt").getPath()));
        assertNull(victim.getDocMethod());

    }

}
