<script setup lang="ts">
import DebouncedInputText from '@/components/DebouncedInputText.vue';
import router from '@/router';
import { buildEmployeeDetailRouteParams, employeeDetailRoute } from '@/router/routes/employee-detail';
import employeeListService from '@/service/EmployeeListService';
import Column from 'primevue/column';
import type DataTable from 'primevue/datatable';
import type { DataTableRowSelectEvent } from 'primevue/datatable';
import { onMounted, Ref, ref } from 'vue';
import type { EmployeeListEntryDTO } from 'workshop-client';

const employeeList: Ref<EmployeeListEntryDTO[]> = ref([]);

onMounted(async () => {
  employeeList.value = await employeeListService.getAllEmployees();
});

function onRowSelected(event: DataTableRowSelectEvent) {
  redirectToDetailPage(event.data);
}

function redirectToDetailPage(data: EmployeeListEntryDTO) {
  router.push(employeeDetailRoute.toRouteWithParams(buildEmployeeDetailRouteParams(data.id)));
}

async function filterEmployees(partialName: string) {
  employeeList.value = await employeeListService.getFilteredEmployees(partialName);
}
</script>

<template>
  <main>
    <DataTable :value="employeeList" responsive-layout="scroll" class="employee-table"
               @rowSelect="onRowSelected" selectionMode="single" dataKey="id">
      <template v-slot:header>
        <div class="employee-table-header grid">
          <div class="col-8 flex justify-content-center">
            <h1>{{ $t('employeeList.headers.table') }}</h1>

          </div>
          <DebouncedInputText class="col-4 debounced-input-text" :placeholder="$t('employeeList.filterPlaceholder')"
                              @change="filterEmployees" />
        </div>
      </template>
      <Column field="firstName" :header="$t('employeeList.headers.firstName')" />
      <Column field="lastName" :header="$t('employeeList.headers.lastName')" />
    </DataTable>
  </main>
</template>

<style>

.employee-table{
  padding-top: 2rem;
}

.debounced-input-text {
  display: flex;
  justify-content: end;
  align-items: center;
}

</style>
