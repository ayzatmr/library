<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <form:form method="post" action="saveBook" modelAttribute="book">
        <table>
            <form:hidden path="id"/>
            <tr>
                <td><form:label path="name">
                    Name
                </form:label></td>
                <td><form:input path="name"/></td>
                <td><form:errors cssClass="error" path="name"></form:errors></td>
            </tr>
            <tr>
                <td><form:label path="genre">
                    Genre
                </form:label>
                </td>
                <td><form:input path="genre"/></td>
                <td><form:errors cssClass="error" path="genre"></form:errors></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Save"/></td>
            </tr>
        </table>
    </form:form>
</t:template>