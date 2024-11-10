package seg3102.lab8_employee_api_graphql.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import seg3102.lab8_employee_api_graphql.entity.Employee

@Repository
interface EmployeeRepository : MongoRepository<Employee, String>