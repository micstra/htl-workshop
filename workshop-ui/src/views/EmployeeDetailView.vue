<script setup lang="ts">

import type {EmployeeDetailRouteParams} from '@/router/routes/employee-detail';
import {useRoute} from 'vue-router';
import {onMounted, ref} from "vue";
import {EmployeeDetails, EmployeeDetailsService} from "@/service/EmployeeDetailsService";
import TaskTable from "@/components/tasks/TaskTable.vue";
import UpdateEmployee from "@/components/employee/UpdateEmployee.vue";

const route = useRoute();
const employeeId: string = getRouteParams().id;
const employeeDetailsService = EmployeeDetailsService.singleton();

const employee = ref<EmployeeDetails>(EmployeeDetails.mockDetailsWhileFetching())

function getRouteParams(): EmployeeDetailRouteParams {
  return route.params as EmployeeDetailRouteParams;
}

onMounted(async () => {
  employee.value = await employeeDetailsService.findEmployeeById(employeeId)
});

</script>
<template>
  <main class="detail-container">
    <h2>Employee-Details</h2>
    <h2>Employee:</h2>

    <update-employee :id="employee.employeeId"
                     :first-name="employee.firstName"
                     :last-name="employee.lastName"/>

    <h2>Tasks:</h2>
    <task-table :tasks="employee.tasks"></task-table>
  </main>
</template>

<style scoped>
 .detail-container {
   display: flex;
   flex-direction: column;
 }
</style>