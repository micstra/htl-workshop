<script setup lang="ts">
import { employeeApi } from '@/api';
import router from '@/router';
import { buildEmployeeDetailRouteParams, employeeDetailRoute } from '@/router/routes/employee-detail';
import Column from 'primevue/column';
import type DataTable from 'primevue/datatable';
import type { DataTableRowSelectEvent } from 'primevue/datatable';
import { onMounted, Ref, ref } from 'vue';
import type { EmployeeListEntryDTO } from 'workshop-client/generated-client';

const employeeList: Ref<EmployeeListEntryDTO[]> = ref([]);

onMounted(async () => {
  employeeList.value = (await employeeApi.listEmployees());
  console.log('Function: mounted, Line 12:  ', employeeList.value);
});

function onRowSelected(event: DataTableRowSelectEvent) {
  redirectToDetailPage(event.data);
}

function redirectToDetailPage(data: EmployeeListEntryDTO) {
  router.push(employeeDetailRoute.toRouteWithParams(buildEmployeeDetailRouteParams(data.id)));
}
</script>

<template>
  <main>
    <h1>
      Employee List
    </h1>
    <DataTable :value="employeeList" responsive-layout="scroll"
               @rowSelect="onRowSelected" selectionMode="single" dataKey="id">
      <Column field="firstName" :header="$t('employeeList.headers.firstName')" />
      <Column field="lastName" :header="$t('employeeList.headers.lastName')" />
    </DataTable>
  </main>
</template>
