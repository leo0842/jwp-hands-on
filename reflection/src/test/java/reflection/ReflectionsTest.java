package reflection;

import annotation.Controller;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ReflectionsTest {

    private static final Logger log = LoggerFactory.getLogger(ReflectionsTest.class);

    @Test
    void showAnnotationClass() throws Exception {
        Reflections reflections = new Reflections("examples");
        Reflections annotationReflections = new Reflections("annotation");
        List<Class<? extends Annotation>> subTypesOfAnnotation = annotationReflections.getSubTypesOf(Annotation.class)
                .stream()
                .filter(c -> !"Inject".equals(c.getSimpleName()))
                .collect(Collectors.toList());

        // TODO 클래스 레벨에 @Controller, @Service, @Repository 애노테이션이 설정되어 모든 클래스 찾아 로그로 출력한다.
        subTypesOfAnnotation.forEach(annotation -> logEachAnnotation(annotation, reflections));
    }

    private void logEachAnnotation(Class<? extends Annotation> annotation, Reflections reflections) {
        reflections.getTypesAnnotatedWith(annotation)
                .forEach(c -> log.info(String.format("@%s annotated to %s", annotation.getSimpleName(), c.getSimpleName())));
    }
}
