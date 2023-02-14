import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        EmpService empService = EmpService.getInstance();

        List<EmpDto> empDtoList = empService.getEmpDtoList();
        empDtoList.forEach((dto) -> System.out.println(dto.getEmail()));

        EmpDto empDto = empService.getEmpDto(100);
        System.out.println(empDto.getSalary());
    }
}
