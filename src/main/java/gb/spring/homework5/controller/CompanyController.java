package gb.spring.homework5.controller;

import gb.spring.homework5.model.dto.CompanyDto;
import gb.spring.homework5.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public List<CompanyDto> getCompaniesList(){
        return companyService.getCompaniesList();
    }
}
