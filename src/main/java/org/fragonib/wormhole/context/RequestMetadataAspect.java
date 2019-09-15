package org.fragonib.wormhole.context;

import java.util.UUID;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Implements an AOP aspect based on <i>wormhole pattern</i></p>
 *
 * <p>Intercepts <i>entry points</i> to generate a <i>request UUID</i> and passthrough that <i>UUID</i> to
 * consuming point where metadata is needed and requested.</p>
 *
 * @author fragonib
 */
// 'Percflow' instantiation model gives a new aspect object any time "entryPointPointcut" is hit
@Aspect("percflow(entryPointPointcut())")
public class RequestMetadataAspect {

    private static final Logger log = LoggerFactory.getLogger(RequestMetadataAspect.class);

    private UUID currentRequestUuid = RequestIdGenerator.VOID_UUID;

    private RequestIdGenerator requestIdGenerator = new RequestIdGenerator();

    @Pointcut("execution(* org.fragonib.wormhole.greeting..*.*(..)) && " +
        "@annotation(org.springframework.web.bind.annotation.GetMapping)")
    private void entryPointPointcut() {}

    @Pointcut("execution(* org.fragonib.wormhole.context.RequestMetadataHolder.retrieveRequestContext(..))")
    private void metadataConsumingPointcut() {}

    @Before("entryPointPointcut()")
    public void beforeEntryPoint() {
        this.currentRequestUuid = requestIdGenerator.generateRequestId();
        log.debug("Hitting entry point, generated request ID: {}", currentRequestUuid);
    }

    @Around("metadataConsumingPointcut()")
    public Object aroundConsumingPoint(ProceedingJoinPoint pjp) throws Throwable {
        log.debug("Consuming request ID through wormhole: {}", currentRequestUuid);
        UUID uuidParam = this.currentRequestUuid;
        Object typeParam = pjp.getArgs()[1];
        return pjp.proceed(new Object[] { uuidParam, typeParam });
    }

}

