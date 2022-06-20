import RouteDefinition from '@/router/domain/RouteDefinition';
import EmployeeDetailView from '@/views/EmployeeDetailView.vue';
import type { RouteParams } from 'vue-router';

export const employeeDetailRoute = new RouteDefinition(
    '/employee/:id',
    'EmployeeDetail',
    EmployeeDetailView,
);

export interface EmployeeDetailRouteParams extends RouteParams {
    id: string;
}

export function buildEmployeeDetailRouteParams(id: string): EmployeeDetailRouteParams {
    return {
        id: id,
    };
}
