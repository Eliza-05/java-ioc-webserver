import edu.eci.tdse.annotation.GetMapping;
import edu.eci.tdse.annotation.RequestParam;
import edu.eci.tdse.annotation.RestController;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import static org.junit.jupiter.api.Assertions.*;


class ComponentScannerTest {

    @RestController
    static class SampleController {
        @GetMapping("/test")
        public String test() { return "ok"; }

        @GetMapping("/echo")
        public String echo(@RequestParam(value = "msg", defaultValue = "hi") String msg) {
            return msg;
        }
    }

    @Test
    void restControllerAnnotationRetainedAtRuntime() {
        assertTrue(SampleController.class.isAnnotationPresent(RestController.class));
    }

    @Test
    void getMappingValueIsCorrect() throws Exception {
        Method m = SampleController.class.getMethod("test");
        assertTrue(m.isAnnotationPresent(GetMapping.class));
        assertEquals("/test", m.getAnnotation(GetMapping.class).value());
    }

    @Test
    void requestParamAnnotationRetainedAtRuntime() throws Exception {
        Method m = SampleController.class.getMethod("echo", String.class);
        Parameter param = m.getParameters()[0];
        assertTrue(param.isAnnotationPresent(RequestParam.class));
        RequestParam rp = param.getAnnotation(RequestParam.class);
        assertEquals("msg", rp.value());
        assertEquals("hi", rp.defaultValue());
    }

    @Test
    void controllerCanBeInstantiatedViaReflection() throws Exception {
        Object instance = SampleController.class.getDeclaredConstructor().newInstance();
        assertNotNull(instance);
    }

    @Test
    void methodCanBeInvokedViaReflection() throws Exception {
        SampleController instance = new SampleController();
        Method m = SampleController.class.getMethod("test");
        m.setAccessible(true);
        assertEquals("ok", m.invoke(instance));
    }

    @Test
    void methodWithParamCanBeInvokedViaReflection() throws Exception {
        SampleController instance = new SampleController();
        Method m = SampleController.class.getMethod("echo", String.class);
        m.setAccessible(true);
        assertEquals("hello", m.invoke(instance, "hello"));
    }
}
