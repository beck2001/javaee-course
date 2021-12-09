package com.sharibekoff.aspect;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@Interceptor
@ServiceLogger
public class ServiceLoggerInterceptor implements Serializable {
    @AroundInvoke
    public Object computeEfficiency(InvocationContext context) throws Exception {
        long start = System.currentTimeMillis();
        Object returnedValue = context.proceed();
        long end = System.currentTimeMillis();
        System.out.println("Latency of " + context.getMethod().getName() + ": " + (end - start) + "ms");
        return returnedValue;
    }
}
