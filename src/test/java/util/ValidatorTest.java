package util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorTest {


    @ParameterizedTest
    @CsvSource({
            "artem, danil",
            "eblan, sosal"
    })
    void listOfErrorsShouldBeEmptyWithCorrectNames(String name1, String name2){
        List<String> errors = Validator.isCorrectName(name1, name2);

        assertThat(errors).isEmpty();
    }

    @ParameterizedTest
    @CsvSource({
            "artem, artem",
            "a, soassal",
            "verylongnamethatincorrect, name",
            "123, name"
    })
    void listOfErrorsShouldBeNotEmptyWithIncorrectNames(String name1, String name2){
        List<String> errors = Validator.isCorrectName(name1, name2);

        assertThat(errors).isNotEmpty();
    }
}
