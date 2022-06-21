<script setup lang="ts">

import type { Task } from "@/service/EmployeeDetailsService";

const props = defineProps<{
  tasks: Task[]
}>();

</script>
<template>
  <data-table :value="tasks" >
    <Column field="id" header="ID"></Column>
    <Column field="description" :header="$t('employeeDetails.tasks.description')"></Column>
    <Column field="finishedAt" :header="$t('employeeDetails.tasks.finishedAt')">
      <template #body="{ data }">
        {{ data.finishedAtAsString() }}
      </template>
    </Column>
    <Column field="hoursWorked" :header="$t('employeeDetails.tasks.hoursWorked')"></Column>
  </data-table>
</template>

<style scoped>
</style>
