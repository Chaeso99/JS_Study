import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpService {
    //싱글톤(EmpService가 단 하나만 있어야됨)
    private EmpService() {
    }

    private static EmpService empService = null;

    public static EmpService getInstance() {
        if (empService == null) {
            empService = new EmpService(); //클래스 내이므로 new 가능
        }
        return empService;
    }

    public EmpDto getEmpDto(Integer idx) {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT EMPLOYEE_ID,");
        sb.append("        FIRST_NAME,");
        sb.append("        LAST_NAME,");
        sb.append("        EMAIL,");
        sb.append("        SALARY,");
        sb.append("        DEPARTMENT_ID");
        sb.append(" FROM HR.EMPLOYEES");
        sb.append(" WHERE EMPLOYEE_ID = ?");

        Connection connect = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        EmpDto empDto = null;

        try {
            connect = DB.open();
            pstmt = connect.prepareStatement(sb.toString());
            pstmt.setInt(1, idx);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                empDto = new EmpDto(
                        rs.getInt("EMPLOYEE_ID"),
                        rs.getString("FIRST_NAME") + " " + rs.getString("LAST_NAME"),
                        rs.getString("EMAIL"),
                        rs.getInt("SALARY"),
                        rs.getInt("DEPARTMENT_ID"));
            }

        } catch (Exception e) {
            System.out.println("쿼리 중 에러가 발생하였습니다.");
            e.printStackTrace();
        } finally {
            DB.close(connect, pstmt, rs);
        }

        return empDto;
    }

    public List<EmpDto> getEmpDtoList() {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT EMPLOYEE_ID,");
        sb.append("        FIRST_NAME,");
        sb.append("        LAST_NAME,");
        sb.append("        EMAIL,");
        sb.append("        SALARY,");
        sb.append("        DEPARTMENT_ID ");
        sb.append(" FROM HR.EMPLOYEES");

        Connection connect = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<EmpDto> empDtoList = new ArrayList<EmpDto>();

        try {
            connect = DB.open();
            pstmt = connect.prepareStatement(sb.toString());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                EmpDto tempEmpDto = new EmpDto(
                        rs.getInt("EMPLOYEE_ID"),
                        rs.getString("FIRST_NAME") + " " + rs.getString("LAST_NAME"),
                        rs.getString("EMAIL"),
                        rs.getInt("SALARY"),
                        rs.getInt("DEPARTMENT_ID"));

                empDtoList.add(tempEmpDto);
            }

        } catch (Exception e) {
            System.out.println("쿼리 중 에러가 발생하였습니다.");
            e.printStackTrace();
        } finally {
            DB.close(connect, pstmt, rs);
        }

        return empDtoList;

    }

}
