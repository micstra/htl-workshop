import { employeeApi } from '@/api';
import type { EmployeeListEntryDTO, ListFilteredEmployeesRequest } from 'workshop-client/generated-client';

class EmployeeListService {

    public async getAllEmployees(): Promise<Array<EmployeeListEntryDTO>> {
        return await employeeApi.listEmployees();
    }

    public async getFilteredEmployees(partialName: string): Promise<Array<EmployeeListEntryDTO>> {
        const listFilteredEmployeesRequest: ListFilteredEmployeesRequest = {
            partialName: partialName,
        };

        return await employeeApi.listFilteredEmployees(listFilteredEmployeesRequest);
    }


    constructor() {
    }
}

const employeeListService = new EmployeeListService();
export default employeeListService;
