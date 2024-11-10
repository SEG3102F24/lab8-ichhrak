package seg3102.lab8_employee_api_graphql.resolvers.types

class CreateEmployeeInput(
    val employeeId: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val birthDate: String,
    val title: String,
    val dept: String
)