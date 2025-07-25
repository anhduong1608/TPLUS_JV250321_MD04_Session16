package entity;

public class EmployeeProject {
    private int employeeId;
    private int projectId;

    public EmployeeProject() {
    }

    public EmployeeProject(int employeeId, int projectId) {
        this.employeeId = employeeId;
        this.projectId = projectId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
