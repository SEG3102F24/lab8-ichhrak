package seg3102.lab8_employee_api_graphql.resolvers

import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import seg3102.lab8_employee_api_graphql.entity.Employee
import seg3102.lab8_employee_api_graphql.repository.EmployeeRepository
import seg3102.lab8_employee_api_graphql.resolvers.types.CreateEmployeeInput
import java.util.*

@Controller
class EmployeesController(
    private val employeeRepository: EmployeeRepository,
    private val mongoOperations: MongoOperations
) {
    @QueryMapping
    fun employees(): List<Employee> {
        return employeeRepository.findAll()
    }

    @QueryMapping
    fun employeeById(@Argument id: String): Employee? {
        return employeeRepository.findById(id).orElse(null)
    }

    @QueryMapping
    fun employeeByEmployeeId(@Argument employeeId: String): Employee? {
        val query = Query()
        query.addCriteria(Criteria.where("employeeId").`is`(employeeId))
        return mongoOperations.findOne(query, Employee::class.java)
    }

    @MutationMapping
    fun newEmployee(@Argument createEmployeeInput: CreateEmployeeInput): Employee {
        val employee = Employee(
            createEmployeeInput.employeeId,
            createEmployeeInput.firstName,
            createEmployeeInput.lastName,
            createEmployeeInput.email,
            createEmployeeInput.phone,
            createEmployeeInput.birthDate,
            createEmployeeInput.title,
            createEmployeeInput.dept
        )
        employee.id = UUID.randomUUID().toString()
        return employeeRepository.save(employee)
    }

    @MutationMapping
    fun updateEmployee(
        @Argument id: String,
        @Argument createEmployeeInput: CreateEmployeeInput
    ): Employee {
        val existingEmployee = employeeRepository.findById(id)
            .orElseThrow { Exception("Employee not found") }

        existingEmployee.apply {
            employeeId = createEmployeeInput.employeeId
            firstName = createEmployeeInput.firstName
            lastName = createEmployeeInput.lastName
            email = createEmployeeInput.email
            phone = createEmployeeInput.phone
            birthDate = createEmployeeInput.birthDate
            title = createEmployeeInput.title
            dept = createEmployeeInput.dept
        }

        return employeeRepository.save(existingEmployee)
    }

    @MutationMapping
    fun deleteEmployee(@Argument id: String): Boolean {
        return try {
            employeeRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }
}