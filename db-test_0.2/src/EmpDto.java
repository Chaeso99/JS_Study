public class EmpDto {
    private Integer idx;
    private String name;
    private String email;
    private Integer salary;
    private Integer depIdx;

    public EmpDto(Integer idx, String name, String email, Integer salary, Integer depIdx) {
        this.idx = idx;
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.depIdx = depIdx;
    }

    public Integer getIdx() {
        return idx;
    }

    public String getName() {
        return name;
    }

    public String getEmail() { //매개변수에 값 넣으면 그 값이 맞아야 이메일을 출력해주는 등으로 활용가능
        return email + "@test.com";
    }

    public Integer getSalary() {
        return salary;
    }

    public Integer getDepIdx() {
        return depIdx;
    }

}
