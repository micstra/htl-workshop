<script setup lang="ts">

import EmployeeEdit from "@/components/employee/EmployeeEdit.vue";
import {EmployeeDetailsService} from "@/service/EmployeeDetailsService";
import type {EmployeeFormData} from "@/components/employee/domain/EmployeeFormData";

const employeeDetailsService = EmployeeDetailsService.singleton();

const props = defineProps<{
  id: string,
  firstName: string,
  lastName: string
}>();

function handleButtonClicked(data: EmployeeFormData) {
  employeeDetailsService.updateEmployee(props.id, data);
}

</script>
<template>
  <div class="update-employee">
    <div class="id-column">
      <div class="id-label">ID</div>
      <div class="id-field">{{ id }}</div>
    </div>
    <employee-edit :first-name="firstName"
                   :last-name="lastName"
                   @button-clicked="handleButtonClicked($event)"/>
  </div>

</template>

<style scoped lang="css">

.update-employee {
  display: flex;
  justify-content: center;
  align-items: center;
}

.id-column {
  padding-right: 3em;
  display: flex;
  flex-direction: column;
  height: 100%;
}

.id-label {
  font-weight: bolder;
}

.id-field {
  height: 100%;
  align-items: center;
  display: flex;
}

</style>
