import type {EmployeeWithTasksDTO, TaskDTO} from "workshop-client";
import {employeeApi} from "@/api";
import type {EmployeeFormData} from "@/components/employee/domain/EmployeeFormData";

export class EmployeeDetailsService {
    private static instance = new EmployeeDetailsService();

    public static singleton(): EmployeeDetailsService {
        return this.instance;
    }

    private constructor() {
    }

    private api = employeeApi;

    public async findEmployeeById(id: string): Promise<EmployeeDetails> {
        const apiResult = await this.api.findEmployeeDetails({id: id})
        return EmployeeDetails.fromDto(apiResult);
    }

    public async updateEmployee(id: string, data: EmployeeFormData): Promise<void> {
        const updateDto = {
            firstName: data.firstName,
            lastName: data.lastName
        }
        return this.api.saveOrUpdateEmployee({id: id, updateEmployeeDTO: updateDto})
    }

}


export class EmployeeDetails {
    public readonly employeeId: string
    public readonly firstName: string
    public readonly lastName: string
    public readonly tasks: Task[]


    constructor(employeeId: string, firstName: string, lastName: string, tasks: Task[]) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tasks = tasks;
    }

    public static fromDto(employeeWithTasksDto: EmployeeWithTasksDTO): EmployeeDetails {
        return new EmployeeDetails(
            employeeWithTasksDto.id,
            employeeWithTasksDto.firstName,
            employeeWithTasksDto.lastName,
            employeeWithTasksDto.tasks.map((el: TaskDTO) => Task.fromDto(el))
        );
    }

    public static mockDetailsWhileFetching(): EmployeeDetails {
        return new EmployeeDetails("...", "...", "...", []);
    }
}

export class Task {
    public readonly id: number
    public readonly description: string
    public readonly finishedAt: Date
    public readonly hoursWorked: number

    constructor(id: number, description: string, finishedAt: Date, hoursWorked: number) {
        this.id = id;
        this.description = description;
        this.finishedAt = finishedAt;
        this.hoursWorked = hoursWorked;
    }

    public static fromDto(dto: TaskDTO): Task {
        return new Task(dto.id, dto.description, dto.finished, dto.hoursWorked)
    }

    public finishedAtAsString(): String {
        const day = this.finishedAt.getDate().toString().padStart(2, '0');
        const month = (this.finishedAt.getMonth() + 1).toString().padStart(2, '0');
        const year = this.finishedAt.getFullYear();

        return day + "." + month + "." + year;
    }
}