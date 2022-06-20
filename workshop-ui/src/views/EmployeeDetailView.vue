<script setup lang="ts">

import type { EmployeeDetailRouteParams } from '@/router/routes/employee-detail';
import { useRoute } from 'vue-router';

const route = useRoute();
const employeeId: string = getRouteParams().id;

function getRouteParams(): EmployeeDetailRouteParams {
  return route.params as EmployeeDetailRouteParams;
}

</script>
<template>
  <main>
    <h1>
      Employee Detail View:  {{ employeeId }}
    </h1>
  </main>
</template>
