package gb.spring.homework5.controller;

import gb.spring.homework5.model.dto.CompanyDto;
import gb.spring.homework5.service.CompanyService;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/company")
@RequiredArgsConstructor
@Api(value = "Призводители")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    @ApiOperation("Получение списка компаний производителей")
    public List<CompanyDto> getCompaniesList(){
        return companyService.getCompaniesList();
    }
}
