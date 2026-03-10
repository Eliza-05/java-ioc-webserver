import edu.eci.tdse.controller.GreetingController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreetingControllerTest {

    private GreetingController controller;

    @BeforeEach
    void setUp() { controller = new GreetingController(); }

    @Test
    void greetingWithCustomName() {
        assertEquals("Hola Juan", controller.greeting("Juan"));
    }

    @Test
    void greetingWithDefaultName() {
        assertEquals("Hola World", controller.greeting("World"));
    }

    @Test
    void counterIncrements() {
        String first  = controller.greetingWithCount("Ana");
        String second = controller.greetingWithCount("Ana");
        assertTrue(first.contains("#1"));
        assertTrue(second.contains("#2"));
    }
}
