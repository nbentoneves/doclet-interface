package com.sesame.internal;

import com.sesame.domain.internal.ParameterType;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class ParameterTypeTest {

    @Parameters(method = "dataInvalidDocumentation")
    @Test
    public void testParameterTypeEnum(ParameterType parameterType, String displayType, Class classType) {

        assertEquals(displayType, parameterType.getDisplayType());
        assertEquals(classType, parameterType.getInternalType());

    }

    @SuppressWarnings("unused")
    private static Object[][] dataInvalidDocumentation() {
        return new Object[][]{
                {ParameterType.BOOLEAN, "boolean", boolean.class},
                {ParameterType.INT, "int", int.class},
                {ParameterType.OBJECT, "object", Object.class}
        };
    }

}