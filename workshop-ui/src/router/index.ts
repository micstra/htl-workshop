import { employeeDetailRoute } from '@/router/routes/employee-detail';
import { employeeList } from '@/router/routes/employee-list';
import { home } from '@/router/routes/home-view';
import { createRouter, createWebHistory } from 'vue-router';

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        home,
        employeeList,
        employeeDetailRoute,
    ],
});

export default router;
