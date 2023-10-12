import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class VagaTest {

    Vaga vaga1;

    @Before
    public void setUp() throws Exception {
        vaga1 = new Vaga(1, 1);
    }

    @Test
    public void testEstacionar() {
        vaga1.estacionar();
        assertFalse(vaga1.disponivel());
    }

    @Test
    public void testSair() {
        vaga1.sair();
        assertTrue(vaga1.disponivel());
    }

}