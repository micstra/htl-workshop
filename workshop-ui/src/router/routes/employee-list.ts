import RouteDefinition from '@/router/domain/RouteDefinition';
import EmployeeListView from '@/views/EmployeeListView.vue';

export const employeeList = new RouteDefinition(
    '/employee/list',
    'EmployeeList',
    EmployeeListView,
);
