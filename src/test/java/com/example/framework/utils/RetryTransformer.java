package com.example.framework.utils;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RetryTransformer implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation annotation, Class testClass,
                          Constructor testConstructor, Method testMethod) {
        // TestNG 7.x compatible: set directly; no getter call needed.
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}
