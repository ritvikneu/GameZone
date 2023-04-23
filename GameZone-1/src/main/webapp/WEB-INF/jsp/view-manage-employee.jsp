<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Employee Management System</title>
        <link href="<c:url value="/css/home.css"/>?version=51" rel="stylesheet" type="text/css">
    </head>
    <body>

        <h1>Manage Employees</h1>
        <div class="logout">
            <a style="padding: 5px;color: black;font-family: cursive;" href="http://localhost:8080/manager/logout">Logout</a>
        </div>
        <div class="portal-layout">

            <div class="dash-heading">
                Current Employees
            </div>
            <div class="portal-leave"> 
                <c:forEach items="${employees}" var="e">
                    <div class = "dash-info-rev">

                        <table>
                            <tr>
                                <td>Employee ID:</td>
                                <td class="pad-td"><c:out value="${e.getEmployeeId()}"/></td>
                            </tr>
                            <tr>
                                <td>First Name:</td>
                                <td class="pad-td"><c:out value="${e.getFirstName()}"/></td>
                            </tr>
                            <tr>
                                <td>Last Name:</td>
                                <td class="pad-td"><c:out value="${e.getLastName()}"/></td>
                            </tr>
                            <tr>
                                <td>Department:</td>
                                <td class="pad-td"><c:out value="${e.getDepartment()}"/></td>
                            </tr>
                            <tr>
                                <td>Branch:</td>
                                <td class="pad-td"><c:out value="${e.getBranch()}"/></td>
                            </tr>
                            <tr>
                                <td>Email ID:</td>
                                <td class="pad-td"><c:out value="${e.getEmailId()}"/></td>
                            </tr>
                            <tr>
                                <td>Salary Per Hour:</td>
                                <td class="pad-td"><c:out value="${e.getSalaryPerHour()}"/>$</td>
                            </tr>
                            <tr>
                                <td>Manager LastName:</td>
                                <td class="pad-td"><c:out value="${e.getManager().getLastName()}"/></td>
                            </tr>
                            <tr>
                                <td>Project Name:</td>
                                <td class="pad-td"><c:out value="${e.getProject().getProjectName()}"/></td>
                            </tr>
                        </table>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="dashboard-box">
            <div class = "dash-one">
                <h3>Add/Update Employee</h3>
                <div class="layout">
                    <form action="http://localhost:8080/admin/add-update-employee" method="POST">
                        <div class="input-layout">
                            <div class="form-group">
                                <label for="employeeId">Employee ID</label>
                                <input type='text' name="employeeId" />
                            </div>
                            <div class="form-group">
                                <label for="firstName">FirstName</label>
                                <input type='text' name="firstName"/>
                            </div>
                            <div class="form-group">
                                <label for="lastName">LastName</label>
                                <input type='text' name="lastName" />
                            </div>
                            <div class="form-group">
                                <label for="branch">Branch</label>
                                <input type='text' name="branch" />
                            </div>
                            <div class="form-group">
                                <label for="department">Department</label>
                                <input type='text'  name="department" />
                            </div>
                            <div class="form-group">
                                <label for="email">Email ID</label>
                                <input type='email'  name="email" />
                            </div>
                            <div class="form-group">
                                <label for="city">City</label>
                                <input type='text'  name="city" />
                            </div>
                            <div class="form-group">
                                <label for="address">Address</label>
                                <input type='text'  name="address" />
                            </div>
                            <div class="form-group">
                                <label for="leaves">Leaves Available</label>
                                <input type='number'  name="leaves" />
                            </div>
                            <div class="form-group">
                                <label for="salary">Salary Per Hour</label>
                                <input type='number'  name="salary" />
                            </div>
                            <div class="form-group mg-top">

                                <input class="dp-in" type="radio"  value="add" name="selection"><span><label for="selection">Add</label></span>

                                <input class="dp-in" type="radio" value="update" name="selection"><span><label for="selection">Update</label></span>
                            </div>
                            <div class="form-group">
                                <label for="setManager">Select Manager</label>
                                <select name="setManager">
                                    <c:forEach var="e" items="${managers}">
                                        <option value="${e.getManagerId()}">${e.getLastName()} - ${e.getDepartment()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="setProject">Select Project</label>
                                <select name="setProject">
                                    <c:forEach var="e" items="${projects}">
                                        <option value="${e.getProjectId()}">${e.getProjectName()} - ${e.getDepartment()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <button class="btn-submit" >Submit</button>
                        </div>
                        <c:if test="${addEmployeeError != null}">
                            <div class="err-msg">
                                <c:out value="${addEmployeeError}"/>
                            </div>
                        </c:if>
                        <c:if test="${addEmployeeError == null}">
                            <div class="suc-msg ">
                                <c:out value="${addEmployeeSuccess}"/>
                            </div>
                        </c:if>
                    </form>
                </div>
            </div>
            <div class = "dash-two rg-0">
                <h3>Delete Employee</h3>
                <div class="layout">
                    <form action="http://localhost:8080/admin/delete-employee" method="POST">
                        <div class="input-layout">
                            <div class="form-group">
                                <label for="deleteid">Employee ID</label>
                                <input type='text' name="deleteid" required/>
                            </div>
                        </div>
                        <div class="form-group l-1">
                            <button class="btn-submit" >Submit</button>
                        </div>
                        <c:if test="${deleteEmployeeError != null}">
                            <div class="err-msg">
                                <c:out value="${deleteEmployeeError}"/>
                            </div>
                        </c:if>
                        <c:if test="${deleteEmployeeError == null}">
                            <div class="suc-msg ">
                                <c:out value="${deleteEmployeeSuccess}"/>
                            </div>
                        </c:if>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>




