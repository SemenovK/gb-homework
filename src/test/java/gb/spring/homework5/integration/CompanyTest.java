package gb.spring.homework5.integration;

import gb.spring.homework5.model.dto.CompanyDto;
import gb.spring.homework5.service.CompanyService;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompanyTest {

    @Autowired
    private CompanyService companyService;

    private final String BASE_API_PATH = "/api/v1";

    @LocalServerPort
    String port;

    @Test
    @DisplayName("Check that we return companies list")
    public void getCompaniesList(){
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<CompanyDto[]> actual = testRestTemplate.getForEntity("http://localhost:"+port+BASE_API_PATH+"/company", CompanyDto[].class);
        Assertions.assertThat(actual.getStatusCode().equals(HttpStatus.OK));
        Assertions.assertThat(actual).isNotNull();
    }
}
