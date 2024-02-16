package HomeTask_240215_Testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ComplexOperationsServiceTest {
    private final ComplexOperationsService service = new ComplexOperationsService();

    @ParameterizedTest
    @ValueSource(strings = {"ygtrfd", "1234hj", "uyg"})
    void validatePasswordStrengthWithExceptionTest(String password) {
        assertThrows(IllegalArgumentException.class, () -> service.validatePasswordStrength(password));
    }
    @ParameterizedTest
    @ValueSource(strings = {"ygtrfd!1233A", "1TTR5&234hj", "432!23fUuyg"})
    void validatePasswordStrengthWithoutExceptionTest(String password) {
        assertDoesNotThrow(() -> service.validatePasswordStrength(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"This is correct", "This text contains error", "fail is not good", "wrong way"})
    void processTextTest(String text) {
        if (text.contains("error") || text.contains("fail") || text.contains("wrong")) {
            assertThrows(IllegalArgumentException.class, () -> service.processText(text));
        } else {
            assertDoesNotThrow(() -> service.processText(text));
        }
    }

    @ParameterizedTest
    @CsvSource({"'1,2,3,4', true",
            "'This text, 4 contains error', false",
            "'2,4,5,6,7,7,0', true",
            "'3,4,5,6,6,5', true"})
    void sumOfNumbersInStringTest(String text, boolean isCorrect){
        if (!isCorrect) {
            assertThrows(IllegalArgumentException.class, () -> service.sumOfNumbersInString(text));
        } else {
            assertDoesNotThrow(() -> service.sumOfNumbersInString(text));
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"http://treuyt.com", "https://qwsa.ua"})
    void validateUrlFormatWithoutExceptionTest(String text){
        assertDoesNotThrow(() -> service.validateUrlFormat(text));
    }
    @ParameterizedTest
    @ValueSource(strings = {"htt://treuyt.com", "https://qwsa"})
    void validateUrlFormatWithExceptionTest(String text){
        assertThrows(IllegalArgumentException.class, () -> service.validateUrlFormat(text));
    }
    @ParameterizedTest
    @ValueSource(strings = {"http://www.example.com/", "https://subdomain.example.org",
            "http://www.subdomain123.example.net", "https://www.example.co.uk", "https://qwsa"})
    void validateUrlFormatPositiveTest(String url) {
        String str = "^(http|https)://[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}.*$";
        if (url.matches(str)) {
            assertDoesNotThrow(() -> service.validateUrlFormat(url));
        }else {
            assertThrows(IllegalArgumentException.class, () -> service.validateUrlFormat(url));
        }
    }

    static Stream<List<String>> generateAdressList(){
        return Stream.of(
                Arrays.asList("jhj@yt.ru", "kjh@iu.com"),
                Arrays.asList("jhj@yt.bel", "kjh@iu.ua"),
                Arrays.asList("jhjyt.ex", "kjh@iu.de")
        );
    }
    @ParameterizedTest
    @MethodSource("generateAdressList")
    void checkEmailListConsistencyTest(List<String> str) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        boolean result = str.stream()
                .filter(el->el.matches(regex))
                .count() == str.size();
        //if(email.stream().allMatch(s -> s.matches(regex)))

        if (!result) {
            assertThrows(IllegalArgumentException.class, () -> service.checkEmailListConsistency(str));
        } else {
            assertDoesNotThrow(() -> service.checkEmailListConsistency(str));
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"Paap","maa m","alex"})
    void checkStringPalindromeTest(String s) {
        String reversed = new StringBuilder(s.replaceAll("\\s+","").toLowerCase()).reverse().toString();
        if (reversed.equals(s.replaceAll("\\s+","").toLowerCase())){
            assertDoesNotThrow(() -> service.checkStringPalindrome(s));
        }else {
            assertThrows(IllegalArgumentException.class,() -> service.checkStringPalindrome(s));
        }
    }


    @ParameterizedTest
    @ValueSource(strings = {"DF765432", "UY987600", "BN765456", "JHG76"})
    void validateIdentificationNumberTest(String str) {
        if (str.matches("^[A-Za-z]{2}\\d{6}$")) {
            assertDoesNotThrow(() -> service.validateIdentificationNumber(str));
        } else {
            assertThrows(IllegalArgumentException.class, () -> service.validateIdentificationNumber(str));
        }
    }
    static Stream<Arguments> generateData(){
        return Stream.of(
        Arguments.of(List.of(1, 2, 3), 4),
        Arguments.of(List.of(1, 2, 10), 12),
        Arguments.of(List.of(1, 2, 10), 19),
        Arguments.of(List.of(10, 27, 37), 40));
    }
    @ParameterizedTest
    @MethodSource("generateData")
    void checkSumOfListAgainstThresholdTest(List<Integer> list, int sum) {
        if (list.stream().mapToInt(Integer::intValue).sum() <= sum){
            assertThrows(IllegalArgumentException.class, () -> service.checkSumOfListAgainstThreshold(list, sum));
        } else {
            assertDoesNotThrow(() -> service.checkSumOfListAgainstThreshold(list, sum));
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"1.123.255.23", "567.7659.6.0", "0.0.0.0", "1.1.1.1"})
    void validateIPAddressTest(String ip) {
        if (!ip.matches("^([0-9]{1,3}\\.){3}[0-9]{1,3}$")) {
            assertThrows(IllegalArgumentException.class, () -> service.validateIPAddress(ip));
        }
    }
    static Stream<List<String>> generateStrings(){
        return Stream.of(
                List.of("aa", "ss", "aa"),
                List.of("aa", "ss", "cc"),
                List.of("bb", "ss", "aa")
        );
    }
    @ParameterizedTest
    @MethodSource("generateStrings")
    void ensureNoDuplicateEntriesTest(List<String> stringList) {
        if (stringList.stream().distinct().count() < stringList.size()) {
            assertThrows(IllegalArgumentException.class, () -> service.ensureNoDuplicateEntries(stringList));
        }
    }
}