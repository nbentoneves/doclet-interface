package com.docletinterface.testing;

public interface TestServiceInterface {

    /**
     * Test javadoc
     *
     * <pre>
     * @doclib
     *
     * className: TestServiceClass
     * packageName: com.docletinterface.testing
     * methodName: calculator
     * methodDescription: It's a service to calculate two numbers
     * return: Int - the result of calculation
     * param-1: Int - The first number - Simple description for the first parameter
     * param-2: Int - The second number - Simple description for the second parameter
     *
     * @enddoclib
     * </pre>
     */
    int calculator(int a, int b);

}
