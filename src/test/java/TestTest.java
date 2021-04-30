import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestTest {
    @Test
    public void HandlesPin1(){
        Set<String> expected = Set.of("1", "2", "4");
        PinGuesser pinGuesser = new PinGuesser();
        Set<String> actual = pinGuesser.getPINs("1");
        Assertions.assertEquals(expected,actual);
    }
}
