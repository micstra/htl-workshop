<script setup lang="ts">

import InputText from 'primevue/inputtext';
import Button from 'primevue/button';
import {ref, watchEffect} from "vue";
import {EmployeeFormData} from "@/components/employee/domain/EmployeeFormData";

const firstName = ref("")
const lastName = ref("")

const props = defineProps<{
  firstName: string,
  lastName: string
}>();

watchEffect(() => firstName.value = props.firstName)
watchEffect(() => lastName.value = props.lastName)


const emit = defineEmits(["button-clicked"])

function handleOnActionButtonClicked() {
  emit("button-clicked", new EmployeeFormData(firstName.value, lastName.value));
}

</script>
<template>

  <div class="edit-employee-container">
    <div>
      <div class="field-label">Vorname</div>
      <input-text type="text" v-model="firstName"></input-text>
    </div>
    <div>
      <div class="field-label">Nachname</div>
      <input-text type="text" v-model="lastName"></input-text>
    </div>
    <div class="action-button">
      <Button label="Speichern" @click="handleOnActionButtonClicked()"></Button>
    </div>
  </div>
</template>

<style scoped lang="css">

.edit-employee-container {
  display: flex;
  justify-content: center;
}

.field-label {
  font-weight: bold;
}

.edit-employee-container > div {
  padding-right: 3em;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.edit-employee-container > .action-button {
  justify-content: end;
}

</style>
