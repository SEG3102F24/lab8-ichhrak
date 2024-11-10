package seg3102.lab8_employee_api_graphql.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "employee")
data class Employee(
    var employeeId: String,
    var firstName: String,
    var lastName: String,
    var email: String,
    var phone: String,
    var birthDate: String,
    var title: String,
    var dept: String
) {
    @Id
    var id: String = ""
}